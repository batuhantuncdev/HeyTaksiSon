import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:heytaksimobil/pages/Authentication/LoginPage.dart';
import 'package:heytaksimobil/pages/CalculatePaymentPage.dart';

import '../models/User.dart';
import '../services/AuthenticationService.dart';

class NavigationDrawer extends StatelessWidget {
  final User? user;
  const NavigationDrawer({Key? key, this.user}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: SingleChildScrollView(
        child: Column(
          children: <Widget>[buildHeader(context), buildMenuItems(context)],
        ),
      ),
    );
  }

  Widget buildHeader(BuildContext context) => Container(
        padding: EdgeInsets.only(top: MediaQuery.of(context).padding.top),
      );

  Widget buildMenuItems(BuildContext context) => Container(
        padding: const EdgeInsets.all(24),
        child: Wrap(
          runSpacing: 16,
          children: [
            ListTile(
              leading: const Icon(Icons.calculate),
              title: const Text('Ucret Hesapla'),
              onTap: () => Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => CalculatePaymentPage(user: user))),
            ),
            ListTile(
              leading: const Icon(Icons.remove_rounded),
              title: const Text('Hesabi Sil'),
              onTap: () => AuthenticationService.removeAccount(
                      user?.jwtToken, user?.userId)
                  .then((response) {
                print("HOLA");
                print(user?.userId);
                print(response.result);
                if (response.result == true) {
                  Fluttertoast.showToast(
                      msg: "Hesabiniz basariyla silindi",
                      toastLength: Toast.LENGTH_SHORT,
                      gravity: ToastGravity.CENTER,
                      timeInSecForIosWeb: 1,
                      backgroundColor: Colors.red,
                      textColor: Colors.white,
                      fontSize: 16.0);
                  Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => LoginPage()));
                }
              }),
            )
          ],
        ),
      );
}
