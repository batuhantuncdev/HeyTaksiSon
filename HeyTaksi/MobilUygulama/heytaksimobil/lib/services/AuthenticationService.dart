import 'dart:convert';
import 'dart:io';
import 'package:heytaksimobil/dto/DeleteResponseModel.dart';
import 'package:heytaksimobil/dto/LoginRequestModel.dart';
import 'package:heytaksimobil/dto/LoginResponseModel.dart';
import 'package:heytaksimobil/dto/RegisterRequestModel.dart';
import 'package:heytaksimobil/dto/RegisterResponseModel.dart';

import '../config.dart';
import 'package:http/http.dart' as http;

import 'SharedService.dart';

class AuthenticationService {
  static var client = http.Client();
  static var url;

  static Future<LoginResponseModel> login(
      LoginRequestModel loginRequestModel) async {
    Map<String, String> requestHeaders = {'Content-Type': 'application/json'};

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, Config.logInURLPath);
    } else {
      url = Uri.http(Config.iOSApiURL, Config.logInURLPath);
    }

    var response = await client.post(url,
        headers: requestHeaders, body: jsonEncode(loginRequestModel.toJson()));

    if (response.statusCode == 200) {
      await SharedService.setLoginDetails(loginResponseModel(response.body));
      return loginResponseModel(response.body);
    } else {
      return LoginResponseModel(
          token: null, error: "Hata Olustu", registerResponseModel: null);
    }
  }

  static Future<RegisterResponseModel> signUp(
      RegisterRequestModel registerRequestModel) async {
    Map<String, String> requestHeaders = {
      'Content-Type': 'application/json',
    };

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, Config.signUpURLPath);
    } else {
      url = Uri.http(Config.iOSApiURL, Config.signUpURLPath);
    }

    var response = await client.post(
      url,
      headers: requestHeaders,
      body: jsonEncode(registerRequestModel.toJson()),
    );

    return registerResponseModel(response.body);
  }

  static Future<DeleteResponseModel> removeAccount(jwtToken, userId) async {
    Map<String, String> requestHeaders = {
      'Authorization': 'Bearer $jwtToken',
    };

    if (Platform.isAndroid) {
      url = Uri.http(Config.androidApiURL, "/app/user/delete/$userId");
    } else {
      url = Uri.http(Config.iOSApiURL, "/app/user/delete/$userId");
    }

    var response = await client.delete(
      url,
      headers: requestHeaders,
    );
    print(response.body);
    return deleteResponseModel(response.body);
  }
}
