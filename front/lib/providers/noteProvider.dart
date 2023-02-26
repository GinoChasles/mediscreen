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
    _noteList.addAll(noteList);
    update();
  }

  void deleteNote(int id) {
    _noteList.removeWhere((element) => element.id == id);
    update();
  }

  void updateNote(int id, Note note) {
    List<Note> updatedList = List.empty(growable: true);
    _noteList.forEach((element) {
      if (element.id == id) {
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
