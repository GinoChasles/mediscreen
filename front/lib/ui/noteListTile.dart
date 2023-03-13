import 'package:flutter/material.dart';
import 'package:front/datasources/note_repository.dart';
import 'package:front/model/note.dart';
import 'package:front/providers/noteProvider.dart';
import 'package:front/ui/noteForm.dart';
import 'package:provider/provider.dart';

class NoteListTile extends StatefulWidget {
  final Note note;
  final int? patId;
  const NoteListTile({Key? key, required this.note, required this.patId}) : super(key: key);

  @override
  State<NoteListTile> createState() => _NoteListTileState();
}

class _NoteListTileState extends State<NoteListTile> {
  final NoteRepository noteRepository = new NoteRepository();

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.only(bottom: 10),

      child: Table(
        columnWidths: {
          0: FixedColumnWidth(50),
          1: FixedColumnWidth(10),
          2: FixedColumnWidth(10),
        },
        defaultVerticalAlignment: TableCellVerticalAlignment.middle,
        border: TableBorder(
          bottom: BorderSide(),
        ),
        children: [
          TableRow(children: [
            Center(
              child: Text(widget.note.notes.toString(),
                  overflow: TextOverflow.ellipsis),
            ),
            IconButton(
              onPressed: () async {
                await showDialog(
                    context: context,
                    builder: (_) {
                      return AlertDialog(
                          scrollable: true,
                          content: NoteForm(
                            patId: widget.patId,
                            note: widget.note,
                            update: true,
                          ));
                    });
              },
              icon: Icon(
                Icons.edit,
              ),
            ),
            IconButton(
              onPressed: () {
                noteRepository.deleteNote(widget.note.key!);
                Provider.of<NoteProvider>(context, listen: false)
                    .deleteNote(widget.note.key!);
              },
              icon: Icon(Icons.delete),
            ),
          ])
        ],
      ),
    );
  }
}
