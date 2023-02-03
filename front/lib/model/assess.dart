class Assess {
  int? id;
  String? firstName;
  String? lastName;
  int? age;
  String? assessement;

  Assess(
      int id, String firstName, String lastName, int age, String assessement);

  Assess.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        firstName = json['firstName'],
        lastName = json['lastName'],
        age = json['age'],
        assessement = json['assessement'];

  Map<String, dynamic> toJson() => {
        'id': id,
        'firstName': firstName,
        'lastName': lastName,
        'age': age,
        'assessement': assessement,
      };
}
