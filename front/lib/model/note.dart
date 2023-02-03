class Note {
  int? id;
  String? notes;

  Note(int id, String notes);

  Note.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        notes = json['notes'];

  Map<String, dynamic> toJson() => {
        'id': id,
        'notes': notes,
      };
}
