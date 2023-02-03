import 'package:flutter/material.dart';
import 'package:front/datasources/patient_repository.dart';
import 'package:front/model/patient.dart';
import 'package:front/ui/patientForm.dart';

class PatientListTile extends StatefulWidget {
  final Patient patient;
  const PatientListTile({Key? key, required this.patient}) : super(key: key);

  @override
  State<PatientListTile> createState() => _PatientListTileState();
}

class _PatientListTileState extends State<PatientListTile> {
  PatientRepository patientRepository = new PatientRepository();

  String convertDateTimeDisplay(String date) {
    final DateTime displayDate = DateTime.parse(date);
    String formatedDate =
        "${displayDate.year}-${displayDate.month}-${displayDate.day}";
    return formatedDate;
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(widget.patient.firstName.toString()),
        Text(widget.patient.lastName.toString()),
        Text(convertDateTimeDisplay(widget.patient.birthDate.toString())),
        Text(widget.patient.gender.toString()),
        Text(widget.patient.address.toString()),
        Text(widget.patient.phone.toString()),
        IconButton(
            icon: Icon(
              Icons.edit,
            ),
            onPressed: () async {
              await showDialog(
                  context: context,
                  builder: (_) {
                    return AlertDialog(
                        insetPadding: EdgeInsets.all(100),
                        content: PatientForm(
                          patient: widget.patient,
                          update: true,
                        ));
                  });
            }),
        IconButton(
            onPressed: () {
              patientRepository.deletePatient(widget.patient.id!);
            },
            icon: Icon(Icons.delete))
      ],
    );
  }
}
