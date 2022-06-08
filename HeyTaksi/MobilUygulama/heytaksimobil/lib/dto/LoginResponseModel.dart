import 'dart:core';

import 'dart:convert';

import 'package:heytaksimobil/dto/RegisterResponseModel.dart';

LoginResponseModel loginResponseModel(String str) =>
    LoginResponseModel.fromJson(json.decode(str));

class LoginResponseModel {
  LoginResponseModel({
    required this.token,
    required this.error,
    required this.registerResponseModel,
  });

  late final String? error;
  late final String? token;
  late final RegisterResponseModel? registerResponseModel;

  LoginResponseModel.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    error = json['error'];
    registerResponseModel = json['registerResponseDTO'] != null
        ? RegisterResponseModel.fromJson(json['registerResponseDTO'])
        : null;
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['token'] = token;
    _data['error'] = error;
    _data['registerResponseDTO'] = registerResponseModel!.toJson();
    return _data;
  }
}
