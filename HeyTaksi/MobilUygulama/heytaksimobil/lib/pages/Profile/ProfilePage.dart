import 'dart:io';

import 'package:firebase_storage/firebase_storage.dart' as firebase_storage;
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:heytaksimobil/constants/ColorConstants.dart';
import 'package:heytaksimobil/models/User.dart';
import 'package:heytaksimobil/pages/Authentication/LoginPage.dart';
import 'package:image_picker/image_picker.dart';
import 'package:dio/dio.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

class ProfilePage extends StatefulWidget {
  final User? user;
  const ProfilePage({Key? key, required this.user}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  firebase_storage.FirebaseStorage storage =
      firebase_storage.FirebaseStorage.instance;
  File? _photo;
  bool isDownloaded = false;
  User? user;
  final ImagePicker _picker = ImagePicker();

  @override
  void initState() {
    super.initState();
    userEmail = widget.user?.userEmail;
    downloadImageFromFirebase();
  }

  Future downloadImageFromFirebase() async {
    try {
      final ref = firebase_storage.FirebaseStorage.instance
          .ref()
          .child('images/$userEmail');
      var url = await ref.getDownloadURL();
      var filePath = await getImage(url);
      setState(() {
        isDownloaded = true;
        _photo = filePath;
      });
    } catch (e) {
      print("ref is null");
    }
  }

  Future<File> getImage(url) async {
    final Response res = await Dio().get<List<int>>(
      url,
      options: Options(
        responseType: ResponseType.bytes,
      ),
    );
    final Directory appDir = await getApplicationDocumentsDirectory();
    final String imageName = url.split('/').last;
    final File file = File(join(appDir.path, imageName));
    file.writeAsBytesSync(res.data as List<int>);
    return file;
  }

  Future imgFromGallery() async {
    final pickedFile = await _picker.pickImage(source: ImageSource.gallery);

    setState(() {
      if (pickedFile != null) {
        _photo = File(pickedFile.path);
        isDownloaded = true;
        uploadFile();
      } else {
        print('No image selected.');
      }
    });
  }

  Future uploadFile() async {
    if (_photo == null) return;
    try {
      final ref = firebase_storage.FirebaseStorage.instance
          .ref()
          .child('images/$userEmail');
      await ref.putFile(_photo!);
    } catch (e) {
      print('error occured');
    }
  }

  signOut(context) async {
    // await auth.signOut();
    Navigator.pushReplacement(
        context, MaterialPageRoute(builder: (context) => LoginPage()));
  }

  String? userEmail;
  String? fullname;
  String? role;
  bool? isOnline;
  @override
  Widget build(BuildContext context) {
    userEmail = widget.user?.userEmail;
    fullname = widget.user?.fullname;
    role = widget.user?.role;
    return Scaffold(
      backgroundColor: Colors.white70,
      body: Center(
        child: Column(
          children: [
            const SizedBox(height: 30),
            SizedBox(
              height: 120,
              width: 120,
              child: Stack(
                fit: StackFit.expand,
                children: [
                  _photo != null
                      ? isDownloaded
                          ? CircleAvatar(
                              backgroundImage: FileImage(File(_photo!.path)),
                            )
                          : const Center(
                              child: CircularProgressIndicator(),
                            )
                      : CircleAvatar(
                          backgroundImage:
                              AssetImage("assets/heytaksilogo.png"),
                        ),
                  Positioned(
                    right: 0,
                    bottom: 0,
                    child: SizedBox(
                      height: 35,
                      width: 35,
                      child: TextButton(
                        style: TextButton.styleFrom(
                          padding: EdgeInsets.zero,
                          primary: Color(0xFFF5F6F9),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(50),
                            side: BorderSide(
                                color: ColorConstants.heyTaxiDarkYellow),
                          ),
                        ),
                        onPressed: () {
                          imgFromGallery();
                        },
                        child: Icon(
                          Icons.add_a_photo,
                          color: ColorConstants.heyTaxiDarkYellow,
                        ),
                      ),
                    ),
                  )
                ],
              ),
            ),
            const SizedBox(height: 30),
            Padding(
              padding: EdgeInsets.only(bottom: 10),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    margin: const EdgeInsets.all(15.0),
                    padding: const EdgeInsets.all(14),
                    alignment: Alignment.centerLeft,
                    decoration: BoxDecoration(
                        border: Border.all(color: Colors.black54),
                        borderRadius: BorderRadius.all(Radius.circular(8.0))),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'İsim',
                          style: TextStyle(color: Colors.black54),
                        ),
                        Text(
                          '$userEmail',
                          style: TextStyle(color: Colors.black54, fontSize: 18),
                        )
                      ],
                    ),
                  ),
                  Container(
                    margin: const EdgeInsets.all(15.0),
                    padding: const EdgeInsets.all(14),
                    alignment: Alignment.centerLeft,
                    decoration: BoxDecoration(
                        border: Border.all(color: Colors.black54),
                        borderRadius: BorderRadius.all(Radius.circular(8.0))),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Kullanıcı Adı',
                          style: TextStyle(color: Colors.black54),
                        ),
                        Text(
                          '$fullname',
                          style: TextStyle(color: Colors.black54, fontSize: 18),
                        )
                      ],
                    ),
                  ),
                  Container(
                    margin: const EdgeInsets.all(15.0),
                    padding: const EdgeInsets.all(14),
                    alignment: Alignment.centerLeft,
                    decoration: BoxDecoration(
                        border: Border.all(color: Colors.black54),
                        borderRadius: BorderRadius.all(Radius.circular(8.0))),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Rol',
                          style: TextStyle(color: Colors.black54),
                        ),
                        Text(
                          '$role',
                          style: TextStyle(color: Colors.black54, fontSize: 18),
                        )
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          signOut(context);
        },
        child: Icon(Icons.logout_rounded),
        backgroundColor: ColorConstants.heyTaxiDarkYellow,
      ),
    );
  }
}
