import 'dart:core';
import 'dart:convert';
import 'dart:ffi';

AppUserResponseModel appUserResponseModel(String str) =>
    AppUserResponseModel.fromJson(json.decode(str));

class AppUserResponseModel {
  AppUserResponseModel({
    required this.id,
    required this.role,
    required this.userEmail,
    required this.currentLocationX,
    required this.currentLocationY,
  });

  late final int? id;
  late final String? userEmail;
  late final String? role;
  late final double? currentLocationX;
  late final double? currentLocationY;

  AppUserResponseModel.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    userEmail = json['userEmail'];
    role = json['role'];
    currentLocationX = json['currentLocationX'];
    currentLocationY = json['currentLocationY'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['userEmail'] = userEmail;
    _data['role'] = role;
    _data['currentLocationX'] = currentLocationX;
    _data['currentLocationY'] = currentLocationY;
    return _data;
  }
}
