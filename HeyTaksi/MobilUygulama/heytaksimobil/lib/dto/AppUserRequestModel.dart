import 'dart:ffi';

class AppUserRequestModel {
  AppUserRequestModel({
    required this.role,
    required this.currentLocationX,
    required this.currentLocationY,
    required this.isAvailable,
  });
  late final String role;
  late final double currentLocationX;
  late final double currentLocationY;
  late final bool isAvailable;

  AppUserRequestModel.fromJson(Map<String, dynamic> json) {
    role = json['role'];
    currentLocationX = json['currentLocationX'];
    currentLocationY = json['currentLocationY'];
    isAvailable = json['isAvailable'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['role'] = role;
    _data['currentLocationX'] = currentLocationX;
    _data['currentLocationY'] = currentLocationY;
    _data['isAvailable'] = isAvailable;
    return _data;
  }
}
