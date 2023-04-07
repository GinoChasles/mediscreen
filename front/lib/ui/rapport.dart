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
          print(snapshot.data!.assessement);
          
          return Scaffold(
            appBar: AppBar(
              title: const Text("Rapport"),
            ),
            body: Center(
              child: Container(
                width: MediaQuery.of(context).size.width * 0.9,
                height: 500,
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text('${snapshot.data!.lastName}  ${snapshot.data!.firstName}  ${snapshot.data!.age.toString()} ans'),
                        // Text(snapshot.data!.firstName ?? 'no data '),
                        // Text(snapshot.data!.lastName ?? 'no data'),
                        // Text(snapshot.data!.age.toString() ?? 'no data'),
                      ],
                    ),
                    Divider(thickness: 2.0,),
                    Text(
                      snapshot.data!.assessement ?? 'no data',
                      style: TextStyle(
                          color: snapshot.data!.assessement == "EARLYONSET" ||
                                  snapshot.data!.assessement == "INDANGER"
                              ? Colors.red
                              : snapshot.data!.assessement == "BORDERLINE"
                                  ? Colors.orange
                                  : Colors.black),
                    ),
                  ],
                ),
              ),
            ),
          );
        } else {
          return const CircularProgressIndicator();
        }
      },
    );
  }
}
