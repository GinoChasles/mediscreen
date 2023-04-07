class Note {
  dynamic key;
  int? patId;
  String? note;

  Note(String note);

  Note.empty();

  Note.fromJson(Map<String, dynamic> json)
      : key = json['key'],
        patId = json["patId"],
        note = json['note'];

  Map<String, dynamic> toJson() => {
        'key': key,
        'patId' : patId,
        'note': note,
      };
}
