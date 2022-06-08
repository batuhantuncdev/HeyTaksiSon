import 'dart:core';
import 'dart:convert';

DeleteResponseModel deleteResponseModel(String str) =>
    DeleteResponseModel.fromJson(json.decode(str));

class DeleteResponseModel {
  DeleteResponseModel({
    required this.result,
    required this.description,
  });

  late final bool? result;
  late final String? description;

  DeleteResponseModel.fromJson(Map<String, dynamic> json) {
    result = json['result'];
    description = json['description'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['result'] = result;
    _data['description'] = description;
    return _data;
  }
}
