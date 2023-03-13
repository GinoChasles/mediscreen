import 'package:flutter/material.dart';
import 'package:front/model/note.dart';

class NoteProvider extends ChangeNotifier {
  List<Note> _noteList = List.empty(growable: true);

  void update() {
    notifyListeners();
  }

  void addNote(Note note) {
    _noteList.add(note);
    update();
  }

  List<Note> get noteList {
    return _noteList;
  }

  void addNotes(List<Note> noteList) {
    _noteList.clear();
    _noteList.addAll(noteList);
    update();
  }

  void deleteNote(String id) {
    _noteList.removeWhere((element) => element.key == id);
    update();
  }

  void updateNote(dynamic id, Note note) {
    List<Note> updatedList = List.empty(growable: true);
    _noteList.forEach((element) {
      if (element.key == id) {
        element = note;
        updatedList.add(note);
      } else {
        updatedList.add(element);
      }
    });
    _noteList = updatedList;
    update();
  }
}
