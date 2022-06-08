import 'dart:ffi';

class RouteRequestModel {
  RouteRequestModel({
    required this.appUserId,
    required this.startingPoint,
    required this.endingPoint,
    required this.tripDistance,
    required this.tripCost,
  });
  late final int appUserId;
  late final String startingPoint;
  late final String endingPoint;
  late final String tripDistance;
  late final String tripCost;

  RouteRequestModel.fromJson(Map<String, dynamic> json) {
    appUserId = json['appUserId'];
    startingPoint = json['startingPoint'];
    endingPoint = json['endingPoint'];
    tripDistance = json['tripDistance'];
    tripCost = json['tripCost'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['appUserId'] = appUserId;
    _data['startingPoint'] = startingPoint;
    _data['endingPoint'] = endingPoint;
    _data['tripDistance'] = tripDistance;
    _data['tripCost'] = tripCost;
    return _data;
  }
}
