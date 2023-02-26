import 'package:flutter/material.dart';
import 'package:front/datasources/note_repository.dart';
import 'package:front/model/note.dart';
import 'package:front/model/patient.dart';
import 'package:front/providers/noteProvider.dart';
import 'package:front/ui/noteForm.dart';
import 'package:front/ui/noteListTile.dart';
import 'package:provider/provider.dart';

class NoteList extends StatefulWidget {
  final Patient patient;
  const NoteList({Key? key, required this.patient}) : super(key: key);

  @override
  State<NoteList> createState() => _NoteListState();
}

class _NoteListState extends State<NoteList> {
  late Future<List<Note>> noteList;
  NoteRepository noteRepository = new NoteRepository();

  @override
  void initState() {
    noteList = noteRepository.getNote(widget.patient.id!);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Consumer<NoteProvider>(
      builder: (context, item, child) {
        return Container(
          height: MediaQuery.of(context).size.height,
          width: MediaQuery.of(context).size.width * 0.8,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Center(
                child: Text("Patient's Notes List"),
              ),
              ElevatedButton.icon(
                  onPressed: () async {
                    await showDialog(
                        context: context,
                        builder: (_) {
                          return AlertDialog(
                              insetPadding: EdgeInsets.all(100),
                              content: NoteForm());
                        });
                  },
                  icon: Icon(Icons.add),
                  label: Text("Add")),
              Expanded(
                child: ListView.builder(
                    itemBuilder: (context, index) =>
                        new NoteListTile(note: item.noteList[index]),
                    itemCount: item.noteList.length),
              ),
            ],
          ),
        );
      },
    );
  }
}