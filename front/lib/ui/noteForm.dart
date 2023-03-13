import 'package:flutter/material.dart';
import 'package:front/datasources/note_repository.dart';
import 'package:front/model/note.dart';
import 'package:front/providers/noteProvider.dart';
import 'package:provider/provider.dart';

class NoteForm extends StatefulWidget {
  final Note? note;
  final int? patId;
  final bool update;
  const NoteForm(
      {Key? key, this.note, this.update = false, required this.patId})
      : super(key: key);

  @override
  State<NoteForm> createState() => _NoteFormState();
}

class _NoteFormState extends State<NoteForm> {
  final NoteRepository noteRepository = new NoteRepository();
  final _formKey = GlobalKey<FormState>();
  late final TextEditingController _noteController;

  @override
  void initState() {
    _noteController = TextEditingController();
    super.initState();
  }

  @override
  void dispose() {
    _noteController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Center(
        child: Form(
            key: _formKey,
            child: Column(
              children: [
                TextFormField(
                  decoration: InputDecoration(labelText: "Note"),
                  controller: _noteController,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a note';
                    }
                    return null;
                  },
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(vertical: 16.0),
                  child: ElevatedButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        _formKey.currentState!.save();
                        Note note = Note.empty();
                        note.notes = _noteController.text;
                        note.patId = widget.patId;
                        note.key = widget.note?.key!;

                        if (widget.note?.key != null) {
                          note.key = widget.note!.key!;
                        }

                        if (widget.update) {
                          noteRepository
                              .updateNote(note.key, note)
                              .then((value) => note = value)
                              .whenComplete(() => Provider.of<NoteProvider>(
                                      context,
                                      listen: false)
                                  .updateNote(note.key, note));
                        } else {
                          noteRepository.postNote(note).then((value) =>
                              {note = value, widget.note?.key = value.key});
                          Provider.of<NoteProvider>(context, listen: false)
                              .addNote(note);
                        }
                        Navigator.pop(context);
                      }
                    },
                    child: Text(widget.update ? 'Update' : "Submit"),
                  ),
                ),
              ],
            )),
      ),
    );
  }
}
