import 'package:flutter/material.dart';
import 'package:front/datasources/patient_repository.dart';
import 'package:front/model/patient.dart';
import 'package:front/ui/patientForm.dart';
import 'package:front/ui/patientListTile.dart';

class PatientList extends StatefulWidget {
  const PatientList({Key? key}) : super(key: key);

  @override
  State<PatientList> createState() => _PatientListState();
}

class _PatientListState extends State<PatientList> {
  late Future<List<Patient>> list;
  PatientRepository patientRepository = new PatientRepository();
  @override
  void initState() {
    list = patientRepository.patientList();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return new FutureBuilder<List>(
      future: list,
      builder: (BuildContext context, AsyncSnapshot<List> snapshot) {
        switch (snapshot.connectionState) {
          case ConnectionState.none:
            return new Text('Waiting to start');
          case ConnectionState.waiting:
            return new Text('Loading...');
          default:
            if (snapshot.hasError) {
              return new Text('Error: ${snapshot.error}');
            } else {
              return Container(
                height: MediaQuery.of(context).size.height*0.8,
                width: MediaQuery.of(context).size.width*0.8,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Center(
                      child: Text("Patient's List"),
                    ),
                    IconButton(
                        onPressed: () async {
                          await showDialog(
                              context: context,
                              builder: (_) {
                                return AlertDialog(
                                    insetPadding: EdgeInsets.all(100),
                                    content: PatientForm());
                              });
                        },
                        icon: Icon(Icons.add)),
                     Expanded(
                       child: ListView.builder(
                          itemBuilder: (context, index) =>
                              new PatientListTile(patient: snapshot.data?[index]),
                          itemCount: snapshot.data?.length),
                     ),
                  ],
                ),
              );
            }
        }
      },
    );
  }
}
