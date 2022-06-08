import 'dart:core';
import 'dart:convert';
import 'dart:ffi';

RouteResponseModel routeResponseModel(String str) =>
    RouteResponseModel.fromJson(json.decode(str));

class RouteResponseModel {
  RouteResponseModel({
    required this.id,
    required this.appUserId,
    required this.startingPoint,
    required this.endingPoint,
    required this.tripDistance,
    required this.tripCost,
  });

  late final int? id;
  late final int? appUserId;
  late final String? startingPoint;
  late final String? endingPoint;
  late final String? tripDistance;
  late final String? tripCost;

  RouteResponseModel.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    appUserId = json['appUserId'];
    startingPoint = json['startingPoint'];
    endingPoint = json['endingPoint'];
    tripDistance = json['tripDistance'];
    tripCost = json['tripCost'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['appUserId'] = appUserId;
    _data['startingPoint'] = startingPoint;
    _data['endingPoint'] = endingPoint;
    _data['tripDistance'] = tripDistance;
    _data['tripCost'] = tripCost;
    return _data;
  }
}
