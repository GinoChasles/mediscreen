import 'package:front/model/note.dart';
import 'package:http/http.dart' show Client;
import 'dart:convert';

const URL = "http://localhost:8082";

class NoteRepository {
  final Client client = Client();
  NoteRepository();

  Future<List<Note>> getNote(int id) async {
    final headers = {'Content-Type': 'application/json'};
    List<Note> noteList = [];
    try {
      dynamic res = await client.get(Uri.parse("$URL/patHistory/$id/all"),
          headers: headers);
      // print("res.body " + res.body);
      dynamic result = json.decode(res.body);
      if (res.statusCode == 200) {
        noteList = List<Note>.from(result.map((i) => Note.fromJson(i)));
      }
    } catch (e) {
      print(e);
    }
    return noteList;
  }

  Future<Note> postNote(Note note) async {
    final headers = {'Content-Type': 'application/json'};
    final result = await client.post(Uri.parse("$URL/patHistory/add"),
        headers: headers, body: json.encode(note.toJson()));
    return Note.fromJson(json.decode(result.body));
  }

  Future<Note> updateNote(dynamic id, Note note) async {
    final headers = {'Content-Type': 'application/json'};
    final result = await client.put(Uri.parse("$URL/patHistory/$id"),
        headers: headers, body: json.encode(note.toJson()));

    return Note.fromJson(json.decode(result.body));
  }

  void deleteNote(dynamic id) async {
    final headers = {'Content-Type': 'application/json'};
    await client.delete(Uri.parse("$URL/patHistory/$id"), headers: headers);
  }
}
