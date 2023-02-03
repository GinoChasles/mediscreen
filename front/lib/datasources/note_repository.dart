import 'package:front/model/note.dart';
import 'package:http/http.dart' show Client;
import 'dart:convert';

class NoteRepository {
  final Client client = Client();
  NoteRepository();

  Future<List<Note>> getNote(int id) async {
    dynamic res =
        await client.get(Uri.parse("http://localhost:8082/patHistory/$id/all"));
    dynamic result = json.decode(res.body) as List;
    List<Note> noteList = [];
    if (res.statusCode == 200) {
      noteList = result.map((i) => Note.fromJson(i)).toList();
    }
    return noteList;
  }

  void postNote(Note note) async {
    final headers = {'Content-Type': 'application/json'};
    await client.post(Uri.parse("http://localhost:8082/patHistory/add"),
        headers: headers, body: json.encode(note.toJson()));
  }

  void updateNote(int id, Note note) async {
    final headers = {'Content-Type': 'application/json'};
    await client.put(Uri.parse("http://localhost:8082/patHistory/$id"),
        headers: headers, body: json.encode(note.toJson()));
  }

  void deleteNote(int id) async {
    final headers = {'Content-Type': 'application/json'};
    await client.delete(Uri.parse("http://localhost:8082/patHistory/$id"),
        headers: headers);
  }
}
