import 'package:flutter/material.dart';
import 'package:front/datasources/note_repository.dart';
import 'package:front/model/note.dart';
import 'package:front/providers/noteProvider.dart';
import 'package:provider/provider.dart';

class NoteFormUpdate extends StatefulWidget {
  final Note note;
  final int patId;
  final String keyNote;
  const NoteFormUpdate(
      {Key? key,
      required this.note,
      required this.patId,
      required this.keyNote})
      : super(key: key);

  @override
  State<NoteFormUpdate> createState() => _NoteFormUpdateState();
}

class _NoteFormUpdateState extends State<NoteFormUpdate> {
  final NoteRepository noteRepository = new NoteRepository();
  final _formKey = GlobalKey<FormState>();
  late final TextEditingController _noteController;

  @override
  void initState() {
    _noteController = TextEditingController();
    _noteController.text = widget.note.note ?? '';
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
                  maxLines: null,
                  keyboardType: TextInputType.multiline,
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
                        note.note = _noteController.text;
                        note.patId = widget.patId;
                        note.key = widget.note.key;

                        noteRepository
                            .updateNote(note.key, note)
                            .then((value) => note = value)
                            .whenComplete(() => Provider.of<NoteProvider>(
                                    context,
                                    listen: false)
                                .updateNote(note.key, note));

                        Navigator.pop(context);
                      }
                    },
                    child: Text('Update'),
                  ),
                ),
              ],
            )),
      ),
    );
  }
}
