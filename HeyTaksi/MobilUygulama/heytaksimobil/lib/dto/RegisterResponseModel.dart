import 'dart:core';
import 'dart:convert';

RegisterResponseModel registerResponseModel(String str) =>
    RegisterResponseModel.fromJson(json.decode(str));

class RegisterResponseModel {
  RegisterResponseModel({
    required this.id,
    required this.userEmail,
    required this.userPassword,
    required this.username,
    required this.role,
    required this.userPhoneNumber,
    required this.fullname,
    required this.isOnline,
  });

  late final int? id;
  late final String? userEmail;
  late final String? userPassword;
  late final String? username;
  late final String? role;
  late final String? userPhoneNumber;
  late final String? fullname;
  late final bool? isOnline;

  RegisterResponseModel.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    userEmail = json['userEmail'];
    userPassword = json['userPassword'];
    username = json['username'];
    role = json['role'];
    userPhoneNumber = json['userPhoneNumber'];
    fullname = json['fullname'];
    isOnline = json['isOnline'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['userEmail'] = userEmail;
    _data['userPassword'] = userPassword;
    _data['username'] = username;
    _data['role'] = role;
    _data['userPhoneNumber'] = userPhoneNumber;
    _data['fullname'] = fullname;
    _data['isOnline'] = isOnline;
    return _data;
  }
}
