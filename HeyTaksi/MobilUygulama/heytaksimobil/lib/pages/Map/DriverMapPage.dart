import 'dart:ffi';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:heytaksimobil/constants/ColorConstants.dart';
import 'package:heytaksimobil/dto/AppUserRequestModel.dart';
import 'package:rflutter_alert/rflutter_alert.dart';

import '../../models/User.dart';
import '../../services/AppUserService.dart';

class DriverMapPage extends StatefulWidget {
  final User? user;
  const DriverMapPage({Key? key, this.user}) : super(key: key);

  @override
  State<DriverMapPage> createState() => _DriverMapPageState();
}

class _DriverMapPageState extends State<DriverMapPage> {
  late GoogleMapController googleMapController;

  static const CameraPosition initialCameraPosition =
      CameraPosition(target: LatLng(41.28667, 36.33), zoom: 14);

  Set<Marker> markers = {};

  var alertStyle = AlertStyle(
    backgroundColor: ColorConstants.heyTaxiDarkYellow,
    animationType: AnimationType.fromLeft,
    descStyle: TextStyle(color: Colors.black, fontWeight: FontWeight.bold),
    descTextAlign: TextAlign.start,
    animationDuration: Duration(milliseconds: 400),
    alertBorder: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(0.0),
      side: BorderSide(
        color: ColorConstants.heyTaxiDarkYellow,
      ),
    ),
    titleStyle: TextStyle(
      color: Colors.black,
    ),
    alertAlignment: Alignment.center,
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          GoogleMap(
            initialCameraPosition: initialCameraPosition,
            markers: markers,
            zoomControlsEnabled: false,
            mapType: MapType.normal,
            onMapCreated: (GoogleMapController controller) {
              googleMapController = controller;
            },
          ),
          SafeArea(
            child: Align(
              alignment: Alignment.bottomLeft,
              child: Padding(
                padding: const EdgeInsets.only(right: 10.0, bottom: 10.0),
                child: FloatingActionButton.extended(
                  heroTag: "btn3",
                  onPressed: () async {
                    Position position = await _determinePosition();
                    AppUserService.updateAppUser(
                        widget.user?.userId,
                        AppUserRequestModel(
                            role: widget.user!.role!,
                            currentLocationX: position.latitude,
                            currentLocationY: position.longitude,
                            isAvailable: true));

                    Alert(
                      context: context,
                      type: AlertType.success,
                      title: "Müşteri Bildirimi",
                      desc: "ADRES: Ulus, Ankara",
                      style: alertStyle,
                      buttons: [
                        DialogButton(
                          child: Text(
                            "Reddet",
                            style: TextStyle(color: Colors.black, fontSize: 20),
                          ),
                          onPressed: () => Navigator.pop(context),
                          color: Colors.white,
                        ),
                        DialogButton(
                          child: Text(
                            "Kabul Et",
                            style: TextStyle(color: Colors.black, fontSize: 20),
                          ),
                          onPressed: () => Navigator.pop(context),
                          color: Colors.white,
                        )
                      ],
                    ).show();
                  },
                  label: const Text("Musaitim"),
                  icon: const Icon(Icons.location_history),
                  backgroundColor: ColorConstants.heyTaxiDarkYellow,
                ),
              ),
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        heroTag: "btn4",
        onPressed: () async {
          Position position = await _determinePosition();

          AppUserService.updateAppUser(
              widget.user?.userId,
              AppUserRequestModel(
                  role: widget.user!.role!,
                  currentLocationX: position.latitude,
                  currentLocationY: position.longitude,
                  isAvailable: false));

          googleMapController.animateCamera(CameraUpdate.newCameraPosition(
              CameraPosition(
                  target: LatLng(position.latitude, position.longitude),
                  zoom: 14)));

          markers.clear();

          markers.add(Marker(
              markerId: const MarkerId("currentLocation"),
              position: LatLng(position.latitude, position.longitude)));

          setState(() => {});
        },
        label: const Text("Konumum"),
        icon: const Icon(Icons.my_location),
      ),
    );
  }

  Future<Position> _determinePosition() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();

    if (!serviceEnabled) {
      return Future.error("Location services are disabled");
    }

    permission = await Geolocator.checkPermission();

    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();

      if (permission == LocationPermission.denied) {
        return Future.error("Location permision denied");
      }
    }

    if (permission == LocationPermission.deniedForever) {
      return Future.error("Location permisions are permanently denied.");
    }

    Position position = await Geolocator.getCurrentPosition();

    return position;
  }
}
