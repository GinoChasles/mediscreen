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
    _patientList.clear();
    _patientList.addAll(patientsList);
    update();
  }

  void deletePatient(int id) {
    _patientList.removeWhere((element) => element.id == id);
    update();
  }

  void updatePatient(int id, Patient patient) {
    List<Patient> updatedList = List.empty(growable: true);
    _patientList.forEach((element) {
      if (element.id == id) {
        element = patient;
        updatedList.add(patient);
      } else {
        updatedList.add(element);
      }
    });
    _patientList = updatedList;
    update();

  }
}
