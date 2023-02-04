import 'package:flutter/material.dart';
import 'package:front/datasources/patient_repository.dart';
import 'package:front/model/patient.dart';
import 'package:front/providers/patientProvider.dart';
import 'package:front/ui/patientForm.dart';
import 'package:front/ui/patientListTile.dart';
import 'package:provider/provider.dart';

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

  void getValues() async {
    
    List<Patient> pList = await list;
    Provider.of<PatientProvider>(context, listen: false).addPatients(pList);
  }
  @override
  Widget build(BuildContext context) {
    getValues();

    return Consumer<PatientProvider>(
      builder: (context, item, child) {
        return Container(
          height: MediaQuery.of(context).size.height,
          width: MediaQuery.of(context).size.width * 0.8,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Center(
                child: Text("Patient's List"),
              ),
              ElevatedButton.icon(
                  onPressed: () async {
                    await showDialog(
                        context: context,
                        builder: (_) {
                          return AlertDialog(
                              insetPadding: EdgeInsets.all(100),
                              content: PatientForm());
                        });
                  },
                  icon: Icon(Icons.add),
                  label: Text("Add")),
              Expanded(
                child: ListView.builder(
                    itemBuilder: (context, index) =>
                        new PatientListTile(patient: item.patientList[index]),
                    itemCount: item.patientList.length),
              ),
            ],
          ),
        );
      },
    );
  }
}
