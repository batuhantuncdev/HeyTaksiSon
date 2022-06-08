import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:heytaksimobil/constants/ColorConstants.dart';
import 'package:heytaksimobil/models/User.dart';
import 'package:heytaksimobil/pages/Map/CustomerMapPage.dart';
import 'package:heytaksimobil/pages/Profile/ProfilePage.dart';
import 'package:heytaksimobil/pages/Favorites/FavoritesPage.dart';
import 'Map/DriverMapPage.dart';
import 'NavigationDrawer.dart';

class HomePage extends StatefulWidget {
  final User? user;
  const HomePage({Key? key, required this.user}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  PageController pageController = PageController();
  String? role;
  String? appBarTitle;
  User? user;
  bool isDriver = false;

  void onTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
    pageController.jumpToPage(index);
  }

  @override
  Widget build(BuildContext context) {
    role = widget.user?.role;
    appBarTitle = "Hey Taksi " + role!;
    user = widget.user;
    if (role == "DRIVER") {
      setState(() {
        isDriver = true;
      });
    } else {
      setState(() {
        isDriver = false;
      });
    }
    return Scaffold(
      appBar: AppBar(
        title: Text(appBarTitle!),
        backgroundColor: ColorConstants.heyTaxiDarkYellow,
      ),
      drawer: NavigationDrawer(user: user),
      body: PageView(
        controller: pageController,
        children: [
          isDriver
              ? DriverMapPage(
                  user: widget.user,
                )
              : CustomerMapPage(
                  user: widget.user,
                ),
          FavoritesPage(
            user: widget.user,
          ),
          ProfilePage(
            user: widget.user!,
          )
        ],
        physics: const NeverScrollableScrollPhysics(),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(icon: Icon(Icons.map), label: 'Harita'),
          BottomNavigationBarItem(icon: Icon(Icons.star), label: 'Favoriler'),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: 'Profil')
        ],
        currentIndex: _selectedIndex,
        onTap: onTapped,
        type: BottomNavigationBarType.fixed,
        backgroundColor: ColorConstants.heyTaxiDarkYellow,
        selectedItemColor: Colors.black,
        unselectedItemColor: Colors.white,
      ),
    );
  }
}
