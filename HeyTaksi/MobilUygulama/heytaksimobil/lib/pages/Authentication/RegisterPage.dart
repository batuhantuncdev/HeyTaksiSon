import 'package:flutter/material.dart';
import 'package:heytaksimobil/constants/ColorConstants.dart';
import 'package:heytaksimobil/dto/RegisterRequestModel.dart';
import 'package:heytaksimobil/dto/RegisterRequestModel.dart';
import 'package:heytaksimobil/pages/Authentication/LoginPage.dart';
import 'package:heytaksimobil/services/AuthenticationService.dart';
import 'package:snippet_coder_utils/FormHelper.dart';
import 'package:snippet_coder_utils/ProgressHUD.dart';

import '../../config.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({Key? key}) : super(key: key);

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  bool isAPICallProcess = false;
  bool hidePassword = true;
  GlobalKey<FormState> globalFormKey = GlobalKey<FormState>();
  String? fullname;
  String? email;
  String? password;
  String? retypedPassword;
  String? role;
  String dropdownValue = 'CUSTOMER';

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: ColorConstants.heyTaxiLightYellow,
        body: ProgressHUD(
          child: Form(
            key: globalFormKey,
            child: _registerUI(context),
          ),
          inAsyncCall: isAPICallProcess,
          opacity: 0.3,
          key: UniqueKey(),
        ),
      ),
    );
  }

  Widget _registerUI(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            width: MediaQuery.of(context).size.width,
            height: MediaQuery.of(context).size.height / 3,
            decoration: const BoxDecoration(
              gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    ColorConstants.heyTaxiLightYellow,
                    ColorConstants.heyTaxiDarkYellow
                  ]),
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(100),
                bottomRight: Radius.circular(100),
              ),
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Align(
                  alignment: Alignment.center,
                  child: Image.asset(
                    "assets/heytaksilogo.png",
                    width: 250,
                    fit: BoxFit.contain,
                  ),
                )
              ],
            ),
          ),
          const Padding(
            padding: EdgeInsets.only(
              left: 20,
              bottom: 10,
              top: 10,
            ),
            child: Text(
              "Register",
              style: TextStyle(
                fontSize: 25,
                color: Colors.black,
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.only(top: 8.0),
            child: FormHelper.inputFieldWidget(
              context,
              "fullname",
              "Full Name",
              (onValidateVal) {
                if (onValidateVal.isEmpty) {
                  return "Full Name must be entered";
                }
                return null;
              },
              (onSavedVal) {
                fullname = onSavedVal;
              },
              borderFocusColor: Colors.white,
              prefixIconColor: Colors.white,
              borderColor: Colors.white,
              textColor: Colors.black,
              backgroundColor: Colors.white,
              hintColor: Colors.black.withOpacity(0.7),
              borderRadius: 20,
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: FormHelper.inputFieldWidget(context, "email", "Email",
                (onValidateVal) {
              if (onValidateVal.isEmpty) {
                return "Email can\'t be empty.";
              }
              return null;
            }, (onSavedVal) {
              email = onSavedVal;
            },
                borderFocusColor: Colors.white,
                prefixIconColor: Colors.white,
                borderColor: Colors.white,
                textColor: Colors.black,
                hintColor: Colors.black.withOpacity(0.7),
                borderRadius: 20,
                backgroundColor: Colors.white),
          ),
          Padding(
            padding: EdgeInsets.only(top: 8.0),
            child: FormHelper.inputFieldWidget(context, "role", "User Role",
                (onValidateVal) {
              if (onValidateVal != "DRIVER" && onValidateVal != "CUSTOMER") {
                return "User role should be DRIVER OR CUSTOMER";
              }
              return null;
            }, (onSavedVal) {
              role = onSavedVal;
            },
                borderFocusColor: Colors.white,
                prefixIconColor: Colors.white,
                borderColor: Colors.white,
                textColor: Colors.black,
                hintColor: Colors.black.withOpacity(0.7),
                borderRadius: 20,
                backgroundColor: Colors.white),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 10),
            child: FormHelper.inputFieldWidget(
              context,
              "password",
              "Password",
              (onValidateVal) {
                if (onValidateVal.isEmpty) {
                  return "Password can\'t be empty.";
                }
                return null;
              },
              (onSavedVal) {
                password = onSavedVal;
              },
              borderFocusColor: Colors.white,
              prefixIconColor: Colors.white,
              borderColor: Colors.white,
              textColor: Colors.black,
              backgroundColor: Colors.white,
              hintColor: Colors.black.withOpacity(0.7),
              borderRadius: 20,
              obscureText: hidePassword,
              suffixIcon: IconButton(
                onPressed: () {
                  setState(() {
                    hidePassword = !hidePassword;
                  });
                },
                color: Colors.black.withOpacity(0.7),
                icon: Icon(
                  hidePassword ? Icons.visibility_off : Icons.visibility,
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 10),
            child: FormHelper.inputFieldWidget(
              context,
              "retypePassword",
              "Retype Password",
              (onValidateVal) {
                if (onValidateVal.isEmpty) {
                  return "Retype Password can\'t be empty.";
                }
                return null;
              },
              (onSavedVal) {
                retypedPassword = onSavedVal;
              },
              borderFocusColor: Colors.white,
              prefixIconColor: Colors.white,
              borderColor: Colors.white,
              textColor: Colors.black,
              backgroundColor: Colors.white,
              hintColor: Colors.black.withOpacity(0.7),
              borderRadius: 20,
              obscureText: hidePassword,
              suffixIcon: IconButton(
                onPressed: () {
                  setState(() {
                    hidePassword = !hidePassword;
                  });
                },
                color: Colors.black.withOpacity(0.7),
                icon: Icon(
                  hidePassword ? Icons.visibility_off : Icons.visibility,
                ),
              ),
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          Center(
            child: FormHelper.submitButton(
              "Sign Up",
              () {
                if (validateAndSave()) {
                  setState(() {
                    isAPICallProcess = true;
                  });
                  RegisterRequestModel model = RegisterRequestModel(
                      userEmail: email!,
                      userPassword: password!,
                      username: email!,
                      role: role,
                      userPhoneNumber: "123",
                      fullname: fullname!);

                  AuthenticationService.signUp(model).then((response) {
                    if (response.userEmail != null) {
                      FormHelper.showSimpleAlertDialog(
                        context,
                        Config.appName,
                        "Registration successfull. Please Login with Your Account.",
                        "OK",
                        () {
                          Navigator.pushNamedAndRemoveUntil(
                              context, "/login", (route) => false);
                        },
                      );
                    } else {
                      FormHelper.showSimpleAlertDialog(
                        context,
                        Config.appName,
                        "Hata Olustu",
                        "OK",
                        () {
                          Navigator.pushNamedAndRemoveUntil(
                              context, "/register", (route) => false);
                        },
                      );
                    }
                  });
                }
              },
              btnColor: ColorConstants.heyTaxiDarkYellow,
              borderColor: ColorConstants.heyTaxiBlack,
              txtColor: ColorConstants.heyTaxiBlack,
              borderRadius: 10,
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          Center(
            child: FormHelper.submitButton(
              "Back To Login",
              () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => LoginPage()));
              },
              btnColor: ColorConstants.heyTaxiDarkYellow,
              borderColor: ColorConstants.heyTaxiBlack,
              txtColor: ColorConstants.heyTaxiBlack,
              borderRadius: 10,
            ),
          ),
        ],
      ),
    );
  }

  bool validateAndSave() {
    final form = globalFormKey.currentState;

    if (form!.validate()) {
      form.save();
      return true;
    } else {
      return false;
    }
  }
}
