class Note {
  dynamic key;
  int? patId;
  String? notes;

  Note(String notes);

  Note.empty();

  Note.fromJson(Map<String, dynamic> json)
      : key = json['key'],
        patId = json["patId"],
        notes = json['notes'];

  Map<String, dynamic> toJson() => {
        'key': key,
        'patId' : patId,
        'notes': notes,
      };
}
