import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:heytaksimobil/constants/ColorConstants.dart';
import 'package:heytaksimobil/dto/RouteResponseModel.dart';

import '../../models/User.dart';
import '../../services/RouteService.dart';

class FavoritesPage extends StatefulWidget {
  final User? user;
  const FavoritesPage({Key? key, this.user}) : super(key: key);

  @override
  State<FavoritesPage> createState() => _FavoritesPageState();
}

class _FavoritesPageState extends State<FavoritesPage> {
  List<RouteResponseModel>? routeList;
  User? user;
  int? userId;

  @override
  void initState() {
    super.initState();
    user = widget.user;
    if (user?.userId != null) {
      userId = user?.userId;
      print("userId");
      print(userId);
      RouteService.getFavouriteRouteList(userId!).then((response) => {
            setState(() => {routeList = response})
          });
    } else {
      print("User is null");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: ColorConstants.heyTaxiLightYellow,
      body: ListView.separated(
        padding: const EdgeInsets.all(8),
        itemCount: routeList?.length ?? 0,
        itemBuilder: (BuildContext context, int index) {
          return Container(
            color: ColorConstants.heyTaxiDarkYellow,
            height: 80,
            child: Center(
              child: Column(
                children: [
                  Text('Baslangic Noktasi: ${routeList![index].startingPoint}'),
                  Text('Bitis Noktasi: ${routeList![index].endingPoint}'),
                  Text('Uzaklik: ${routeList![index].tripDistance} km'),
                  Text('Ucret: ${routeList![index].tripCost} TL')
                ],
              ),
            ),
          );
        },
        separatorBuilder: (BuildContext context, int index) => const Divider(),
      ),
    );
  }
}
