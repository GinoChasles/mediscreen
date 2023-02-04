import 'package:flutter/material.dart';
import 'package:front/datasources/patient_repository.dart';
import 'package:front/model/patient.dart';
import 'package:front/providers/patientProvider.dart';
import 'package:front/ui/patientForm.dart';
import 'package:provider/provider.dart';

class PatientListTile extends StatefulWidget {
  final Patient patient;
  const PatientListTile({Key? key, required this.patient}) : super(key: key);

  @override
  State<PatientListTile> createState() => _PatientListTileState();
}

class _PatientListTileState extends State<PatientListTile> {
  PatientRepository patientRepository = new PatientRepository();
  int selectedMenu = 1;

  String convertDateTimeDisplay(String date) {
    final DateTime displayDate = DateTime.parse(date);
    String formatedDate =
        "${displayDate.year}-${displayDate.month}-${displayDate.day}";
    return formatedDate;
  }

  @override
  Widget build(BuildContext context) {
    List<PopupMenuEntry<int>> popupItems = [
      PopupMenuItem(
        value: 3,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Icon(
              Icons.notes,
            ),
            Text("Notes")
          ],
        ),),
      PopupMenuItem(
        value: 1,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Icon(
              Icons.edit,
            ),
            Text("Modify")
          ],
        ),
        onTap: () async {
          await showDialog(
              context: context,
              builder: (_) {
                return AlertDialog(
                    scrollable: true,
                    content: PatientForm(
                      patient: widget.patient,
                      update: true,
                    ));
              }).then((value) => Navigator.of(context).pop());
        },
      ),
      PopupMenuItem(
        value: 2,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,

          children: [Icon(Icons.delete), Text("Delete")],
        ),
        onTap: () {
          patientRepository.deletePatient(widget.patient.id!);
          Provider.of<PatientProvider>(context, listen: false).deletePatient(widget.patient.id!);
        },
      )
    ];

    return Container(
        margin: EdgeInsets.only(bottom: 10),
        child: Table(
            columnWidths: {
              0: FixedColumnWidth(50),
              1: FixedColumnWidth(50),
              2: FixedColumnWidth(50),
              3: FixedColumnWidth(10),
              4: FixedColumnWidth(80),
              5: FixedColumnWidth(50),
              6: FixedColumnWidth(10),
              7: FixedColumnWidth(10),
            },
            defaultVerticalAlignment: TableCellVerticalAlignment.middle,
            border: TableBorder(
              bottom: BorderSide(),
            ),
            children: [
              TableRow(
                children: [
                  Center(
                    child: Text(widget.patient.firstName.toString(),
                        overflow: TextOverflow.ellipsis),
                  ),
                  Center(
                    child: Text(widget.patient.lastName.toString(),
                        overflow: TextOverflow.ellipsis),
                  ),
                  Center(
                    child: Text(
                        convertDateTimeDisplay(
                            widget.patient.birthDate.toString()),
                        overflow: TextOverflow.ellipsis),
                  ),
                  Center(
                    child: Text(widget.patient.gender.toString(),
                        overflow: TextOverflow.ellipsis),
                  ),
                  Center(
                    child: Text(
                      widget.patient.address.toString(),
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Center(child: Text(widget.patient.phone.toString())),
                  PopupMenuButton<int>(
                    splashRadius: 16,
                      initialValue: selectedMenu,
                      // Callback that sets the selected popup menu item.
                      onSelected: (value) {
                        setState(() {
                          selectedMenu = value;
                        });
                        if(selectedMenu == 3) {print("oh yeah mother fuckeeeeer");}
                      },
                      itemBuilder: (BuildContext context) => popupItems),
                ],
              ),
            ]));
  }
}
