import 'package:http/http.dart' show Client;
import 'dart:convert';
import 'package:front/model/patient.dart';

const URL = "http://localhost:8081";

class PatientRepository {
  final Client client = Client();
  PatientRepository();

  Patient? getPatient(int id) {
    dynamic res = client.get(Uri.parse("$URL/patient/$id"));
    Patient? result;
    if (res.statusCode == 200) {
      result = Patient.fromJson(json.decode(res));
    }
    return result;
  }

  Future<List<Patient>> patientList() async {
    final headers = {'Content-Type': 'application/json'};

    List<Patient> patientList = [];
    try {
      dynamic res = await client.get(
        Uri.parse("$URL/patient/"),
        headers: headers,
      );
      dynamic result = json.decode(res.body);
      // print(result);
      if (res.statusCode == 200) {
        patientList =
            List<Patient>.from(result.map((i) => Patient.fromJson(i)));
      }
    } catch (e) {
      print(e);
    }
    return patientList;
  }

  Future<Patient> postPatient(Patient patient) async {
    final headers = {'Content-Type': 'application/json'};
    final result = await client.post(Uri.parse("$URL/patient/add"),
        headers: headers, body: json.encode(patient.toJson()));
    return Patient.fromJson(json.decode(result.body));
  }

  Future<Patient> updatePatient(int id, Patient patient) async {
    final headers = {'Content-Type': 'application/json'};
    try {
      final result = await client.put(Uri.parse("$URL/patient/$id"),
          headers: headers, body: json.encode(patient.toJson()));
      return Patient.fromJson(json.decode(result.body));
    } catch (e) {
      print(e);
      return patient;
    }
  }

  void deletePatient(int id) async {
    final headers = {'Content-Type': 'application/json'};
    await client.delete(Uri.parse("$URL/patient/$id"), headers: headers);
  }
}
