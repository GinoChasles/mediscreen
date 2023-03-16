import 'package:front/model/assess.dart';
import 'package:http/http.dart' show Client;
import 'dart:convert';

class AssessRepository {
  final Client client = Client();
  AssessRepository();

  Future<Assess> getAssess(dynamic id) async {
    final headers = {'Content-Type': 'application/json'};

    dynamic res = await client.get(Uri.parse("http://localhost:8080/assess/$id"),headers: headers);
    Assess assess = new Assess.empty();
    dynamic result = json.decode(res.body);
    if (res.statusCode == 200) {
      assess = Assess.fromJson(result);
    }
    return assess;
  }
}
