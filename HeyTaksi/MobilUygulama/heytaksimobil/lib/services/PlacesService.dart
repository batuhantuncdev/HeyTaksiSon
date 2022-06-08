import 'package:heytaksimobil/dto/PlaceSearch.dart';
import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

import '../models/Place.dart';

class PlacesService {
  Future<List<PlaceSearch>> getAutocomplete(String search) async {
    var url = Uri.parse(
        "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=$search&types=establishment&key=AIzaSyA9Kc6GlT_vjyzIMoyZnLZ1Om4MCBTawVA");
    var response = await http.get(url);
    var json = convert.jsonDecode(response.body);
    var jsonResults = json['predictions'] as List;
    return jsonResults.map((place) => PlaceSearch.fromJson(place)).toList();
  }

  Future<Place> getPlace(String placeId) async {
    var url = Uri.parse(
        "https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyA9Kc6GlT_vjyzIMoyZnLZ1Om4MCBTawVA&place_id=$placeId");
    var response = await http.get(url);
    var json = convert.jsonDecode(response.body);
    var jsonResults = json['result'] as Map<String, dynamic>;
    return Place.fromJson(jsonResults);
  }
}
