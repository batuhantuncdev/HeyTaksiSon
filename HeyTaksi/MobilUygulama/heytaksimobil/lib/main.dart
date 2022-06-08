import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:heytaksimobil/pages/Blocs/ApplicationBloc.dart';
import 'package:heytaksimobil/pages/HomePage.dart';
import 'package:heytaksimobil/pages/Authentication/LoginPage.dart';
import 'package:heytaksimobil/pages/Authentication/RegisterPage.dart';
import 'package:provider/provider.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => ApplicationBloc(),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Hey Taksi App',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        routes: {
          '/': (context) => const LoginPage(),
          '/register': (context) => const RegisterPage(),
          '/login': (context) => const LoginPage(),
          '/home': (context) => const HomePage(
                user: null,
              ),
        },
      ),
    );
  }
}
