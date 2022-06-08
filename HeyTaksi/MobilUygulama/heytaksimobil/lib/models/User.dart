import 'dart:ffi';

class User {
  int? userId;
  String? jwtToken;
  String? userEmail;
  String? fullname;
  String? userPassword;
  String? registeredDate;
  String? role;
  bool? isOnline;

  User(this.userId, this.jwtToken, this.userEmail, this.fullname, this.role);
}
