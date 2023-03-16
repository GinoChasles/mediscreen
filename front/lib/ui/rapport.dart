import 'package:flutter/material.dart';
import 'package:front/datasources/assess_repository.dart';
import 'package:front/model/assess.dart';

class Rapport extends StatefulWidget {
  final int patId;
  const Rapport({Key? key, required this.patId}) : super(key: key);

  @override
  State<Rapport> createState() => _RapportState();
}

class _RapportState extends State<Rapport> {
  final AssessRepository assessRepository = new AssessRepository();
  late Future<Assess> assess;

  @override
  void initState() {
    super.initState();
    assess = assessRepository.getAssess(widget.patId);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Assess>(
      future: assess,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              title: const Text("Rapport"),
            ),
            body: Container(
              color: Colors.red,
              height: 500,
              child: Column(
                children: [
                  Text( snapshot.data!.firstName ?? 'no data'),
                  Text( snapshot.data!.lastName ?? 'no data'),
                  Text( snapshot.data!.age.toString() ?? 'no data'),
                  Text( snapshot.data!.assessement ?? 'no data'),
                ],
              ),
            ),
          );
        } else  {
          return const CircularProgressIndicator();
        }
      },
    );
  }
}
