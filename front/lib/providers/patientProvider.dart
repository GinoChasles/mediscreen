import 'package:flutter/material.dart';
import 'package:front/model/patient.dart';

class PatientProvider with ChangeNotifier {
  List<Patient> _patientList = List.empty(growable: true);

  void update() {
    notifyListeners();
  }

  void addPatient(Patient patient) {
    _patientList.add(patient);
    update();
  }
  List<Patient> get patientList {
    return _patientList;
  }
  void addPatients(List<Patient> patientsList) {
    _patientList.addAll(patientsList);
    update();
  }

  void deletePatient(int id) {
    _patientList.removeWhere((element) => element.id == id);
    update();
  }
}