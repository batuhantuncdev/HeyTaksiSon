import 'package:heytaksimobil/dto/AppUserRequestModel.dart';
import 'package:heytaksimobil/dto/AppUserResponseModel.dart';
import '../config.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:io';

class AppUserService {
  static var client = http.Client();
  static var url;

  static Future<AppUserResponseModel> updateAppUser(
      appUserId, AppUserRequestModel appUserRequestModel) async {
    Map<String, String> requestHeaders = {'Content-Type': 'application/json'};

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, "/app/user/update/$appUserId");
    } else {
      url = Uri.http(Config.iOSApiURL, "/app/user/update/$appUserId");
    }

    var response = await client.put(url,
        headers: requestHeaders,
        body: jsonEncode(appUserRequestModel.toJson()));

    if (response.statusCode == 200) {
      return appUserResponseModel(response.body);
    } else {
      return Future.error("Hata Olustu");
    }
  }

  static Future<List<AppUserResponseModel>> getDriverList() async {
    Map<String, String> requestHeaders = {
      'Content-Type': 'application/json',
    };

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, "/app/user/driver/getDriverList");
    } else {
      url = Uri.http(Config.iOSApiURL, "/app/user/driver/getDriverList");
    }

    var response = await client.get(url, headers: requestHeaders);
    if (response.statusCode == 200) {
      final List parsedList = json.decode(response.body);

      List<AppUserResponseModel> list =
          parsedList.map((val) => AppUserResponseModel.fromJson(val)).toList();
      return list;
    } else {
      return Future.error("Hata Olustu");
    }
  }
}
