class Assess {
  
  String? firstName;
  String? lastName;
  int? age;
  String? assessement;
  Assess.empty();

  Assess(String firstName, String lastName, int age, String assessement);
  
  
  Assess.fromJson(Map<String, dynamic> json)
      : 
        firstName = json['firstName'] ?? "",
        lastName = json['lastName'] ?? "",
        age = json['age'] ?? "",
        assessement = json['assessement'];

  Map<String, dynamic> toJson() => {
        
        'firstName': firstName,
        'lastName': lastName,
        'age': age,
        'assessement': assessement,
      };
}
