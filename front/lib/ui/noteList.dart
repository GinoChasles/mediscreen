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

  void getValues() async {
    List<Note> notes = await noteList;
    Provider.of<NoteProvider>(context, listen: false).addNotes(notes);
  }

  @override
  Widget build(BuildContext context) {
    getValues();

    return Scaffold(
      appBar: AppBar(
        title: const Text("Patient's Notes List"),
      ),
      body: Center(
        child: Consumer<NoteProvider>(
          builder: (context, item, child) {
            return Container(
              height: MediaQuery.of(context).size.height,
              width: MediaQuery.of(context).size.width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Center(
                    child: ElevatedButton.icon(
                        onPressed: () async {
                          await showDialog(
                              context: context,
                              builder: (_) {
                                return AlertDialog(
                                    scrollable: true,
                                    insetPadding: EdgeInsets.all(100),
                                    content:
                                        NoteForm(patId: widget.patient.id));
                              });
                        },
                        icon: Icon(Icons.add),
                        label: Text("Add")),
                  ),
                  Expanded(
                    child: ListView.builder(
                        itemBuilder: (context, index) => new NoteListTile(
                            patId: widget.patient.id,
                            note: item.noteList[index]),
                        itemCount: item.noteList.length),
                  ),
                ],
              ),
            );
          },
        ),
      ),
    );
  }
}
