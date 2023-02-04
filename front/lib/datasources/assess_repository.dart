import 'package:front/model/assess.dart';
import 'package:http/http.dart' show Client;
import 'dart:convert';

class AssessRepository {
  final Client client = Client();
  AssessRepository();

  Future<Assess?> getAssess(int id) async {
    dynamic res = await client.get(Uri.parse("http://localhost:8080/assess/$id"));
    Assess? assess;
    if (res.statusCode == 200) {
      assess = Assess.fromJson(json.decode(res));
    }
    return assess;
  }
}
