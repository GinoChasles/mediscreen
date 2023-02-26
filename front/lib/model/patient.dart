import 'package:intl/intl.dart';

class Patient {
  int? id;
  String? firstName;
  String? lastName;
  DateTime? birthDate;
  String? gender;
  String? address;
  String? phone;

  Patient.empty();
  
  Patient(this.id, this.firstName, this.lastName, this.birthDate,
      this.gender, this.address, this.phone) ;

  Patient.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        firstName = json['firstname'],
        lastName = json['lastname'],
        birthDate = DateTime.fromMillisecondsSinceEpoch(json['birthdate']),
        gender = json['gender'] ?? "",
        address = json['address'] ?? "",
        phone = json['phone'] ?? "";

  Map<String, dynamic> toJson() => {
        'id': id,
        'firstname': firstName,
        'lastname': lastName,
        'birthdate': birthDate?.millisecondsSinceEpoch,
        'gender': gender,
        'address': address,
        'phone': phone,
      };

  @override
  String toString() {
    return 'Patient{id: $id, firstName: $firstName, lastName: $lastName,birthDate: $birthDate, gender: $gender, address: $address, phone: $phone}';
  }

  static String convertDateTimeDisplay(String date) {
    final DateTime displayDate = DateTime.parse(date);
    String formatedDate = DateFormat('yyyy-MM-dd').format(displayDate);
    // "${displayDate.year}-${displayDate.month}-${displayDate.day}";
    return formatedDate;
  }
  
}
