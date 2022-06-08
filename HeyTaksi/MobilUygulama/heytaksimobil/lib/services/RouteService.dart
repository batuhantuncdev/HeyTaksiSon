import 'package:heytaksimobil/dto/RouteRequestModel.dart';
import 'package:heytaksimobil/dto/RouteResponseModel.dart';

import '../config.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:io';

class RouteService {
  static var client = http.Client();
  static var url;

  static Future<RouteResponseModel> saveFavouriteRoute(
      String jwtToken, RouteRequestModel routeRequestModel) async {
    Map<String, String> requestHeaders = {
      'Content-Type': 'application/json',
      'Authorization': "Bearer $jwtToken",
    };

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, Config.saveFavouriteRoute);
    } else {
      url = Uri.http(Config.iOSApiURL, Config.saveFavouriteRoute);
    }

    var response = await client.post(url,
        headers: requestHeaders, body: jsonEncode(routeRequestModel.toJson()));

    if (response.statusCode == 200) {
      return routeResponseModel(response.body);
    } else {
      return Future.error("Hata Olustu");
    }
  }

  static Future<List<RouteResponseModel>> getFavouriteRouteList(
      int appUserId) async {
    Map<String, String> requestHeaders = {
      'Content-Type': 'application/json',
    };

    if (Platform.isAndroid) {
      url =
          Uri.http(Config.androidApiURL, "/app/route/getRouteList/$appUserId");
    } else {
      url = Uri.http(Config.iOSApiURL, "/app/route/getRouteList/$appUserId");
    }

    var response = await client.get(url, headers: requestHeaders);
    if (response.statusCode == 200) {
      final List parsedList = json.decode(response.body);

      List<RouteResponseModel> list =
          parsedList.map((val) => RouteResponseModel.fromJson(val)).toList();
      return list;
    } else {
      return Future.error("Hata Olustu");
    }
  }
}
