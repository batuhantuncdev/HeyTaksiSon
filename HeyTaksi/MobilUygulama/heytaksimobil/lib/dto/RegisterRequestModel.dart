import 'dart:convert';

RegisterRequestModel signUpResponseModel(String str) =>
    RegisterRequestModel.fromJson(json.decode(str));

class RegisterRequestModel {
  RegisterRequestModel({
    required this.userEmail,
    required this.userPassword,
    required this.username,
    required this.role,
    required this.userPhoneNumber,
    required this.fullname,
  });

  late final String? userEmail;
  late final String? userPassword;
  late final String? username;
  late final String? role;
  late final String? userPhoneNumber;
  late final String? firstName;
  late final String? fullname;

  RegisterRequestModel.fromJson(Map<String, dynamic> json) {
    userEmail:
    json["userEmail"];
    userPassword:
    json["userPassword"];
    username:
    json["username"];
    role:
    json["role"];
    phoneNumber:
    json["userPhoneNumber"];
    firstName:
    json["fullname"];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['userEmail'] = userEmail;
    _data['userPassword'] = userPassword;
    _data['username'] = username;
    _data['role'] = role;
    _data['userPhoneNumber'] = userPhoneNumber;
    _data['fullname'] = fullname;
    return _data;
  }
}
