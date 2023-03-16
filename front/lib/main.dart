import 'package:flutter/material.dart';
import 'package:front/model/patient.dart';
import 'package:front/providers/noteProvider.dart';
import 'package:front/providers/patientProvider.dart';
import 'package:front/ui/noteList.dart';
import 'package:front/ui/patientList.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider(create: (_) => PatientProvider()),
      ChangeNotifierProvider(create: (_) => NoteProvider()),
    ],
    child: MyApp()));
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mediscreen',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Mediscreen Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
    // Patient patient = new Patient(1, "test", "test", new DateTime.now(), "M", "test", "test");
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            SizedBox(
                height: MediaQuery.of(context).size.height * 0.9,
                width: MediaQuery.of(context).size.width ,
                child: PatientList()),
                // child: NoteList(patient: patient,)),
          ],
        ),
      ),
    );
  }
}
