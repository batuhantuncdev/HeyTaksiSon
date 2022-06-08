import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:heytaksimobil/services/GeolocatorService.dart';
import 'package:heytaksimobil/services/PlacesService.dart';

import '../../dto/PlaceSearch.dart';
import '../../models/Place.dart';

class ApplicationBloc with ChangeNotifier {
  final geoLocatorService = GeolocatorService();
  final placesService = PlacesService();

  Position currentLocation = Position(
      longitude: 34.0,
      latitude: 35.0,
      timestamp: DateTime(1, 1, 1),
      accuracy: 12,
      altitude: 1212,
      heading: 12,
      speed: 12,
      speedAccuracy: 12);
  List<PlaceSearch> searchResults = <PlaceSearch>[];
  StreamController<Place> selectedLocation = StreamController<Place>();

  ApplicationBloc() {
    setCurrentLocation();
  }

  setCurrentLocation() async {
    currentLocation = await geoLocatorService.getCurrentLocation();
    notifyListeners();
  }

  searchPlaces(String searchTerm) async {
    searchResults = await placesService.getAutocomplete(searchTerm);
    notifyListeners();
  }

  setSelectedLocation(String placeId) async {
    selectedLocation.add(await placesService.getPlace(placeId));
    notifyListeners();
  }

  @override
  void dispose() {
    selectedLocation.close();
    super.dispose();
  }
}
