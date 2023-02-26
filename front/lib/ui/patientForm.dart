import 'package:flutter/material.dart';
import 'package:front/datasources/patient_repository.dart';
import 'package:front/model/patient.dart';
import 'package:front/providers/patientProvider.dart';
import 'package:intl/intl.dart';
import 'package:provider/provider.dart';

class PatientForm extends StatefulWidget {
  final Patient? patient;
  final bool update;
  const PatientForm({Key? key, this.patient, this.update = false})
      : super(key: key);

  @override
  State<PatientForm> createState() => _PatientFormState();
}

class _PatientFormState extends State<PatientForm> {
  PatientRepository patientRepository = new PatientRepository();
  final _formKey = GlobalKey<FormState>();
  late final TextEditingController _firstNameController;
  late final TextEditingController _lastNameController;
  late final TextEditingController _birthDateController;
  // late final TextEditingController _genderController;
  late final TextEditingController _addressController;
  late final TextEditingController _phoneController;
  String? genderValue = "M";

  @override
  void initState() {
    super.initState();
    _firstNameController = TextEditingController();
    _lastNameController = TextEditingController();
    _birthDateController = TextEditingController();
    _addressController = TextEditingController();
    _phoneController = TextEditingController();

    _firstNameController.text = widget.patient?.firstName ?? "";
    _lastNameController.text = widget.patient?.lastName ?? "";
    _addressController.text = widget.patient?.address ?? "";
    _phoneController.text = widget.patient?.phone ?? "";
    if (widget.update) {
      String birthDate = widget.patient?.birthDate.toString() ?? "";
      _birthDateController.text = Patient.convertDateTimeDisplay(birthDate);
    } else {
      _birthDateController.text = "";
    }
  }

  @override
  void dispose() {
    _firstNameController.dispose();
    _lastNameController.dispose();
    _birthDateController.dispose();
    _addressController.dispose();
    _phoneController.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    List<DropdownMenuItem<String>> menuItems = [
      DropdownMenuItem(child: Text("Mâle"), value: "M"),
      DropdownMenuItem(child: Text("Female"), value: "F"),
    ];

    return Container(
      child: Center(
        child: Form(
            key: _formKey,
            child: Column(
              children: [
                TextFormField(
                  decoration: InputDecoration(labelText: "FirstName"),
                  controller: _firstNameController,
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a firstName';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  decoration: InputDecoration(labelText: "LastName"),
                  controller: _lastNameController,
                  // The validator receives the text that the user has entered.
                  validator: (value) {
                    if (value == null || value.isEmpty) {
                      return 'Please enter a lastName';
                    }
                    return null;
                  },
                ),
                TextFormField(
                    decoration: InputDecoration(labelText: "BirthDate"),
                    controller: _birthDateController,
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter a birthdate';
                      }
                      return null;
                    },
                    onTap: () async {
                      FocusScope.of(context).requestFocus(new FocusNode());
                      DateTime? date = DateTime(1900);

                      date = await showDatePicker(
                          context: context,
                          initialDate: DateTime.now(),
                          firstDate: DateTime(1900),
                          lastDate: DateTime(2100));
                      if (date != null) {
                        _birthDateController.text =
                            Patient.convertDateTimeDisplay(date.toString());
                      } else {
                        String birthdate =
                            widget.patient?.birthDate.toString() ?? "";
                        _birthDateController.text =
                            Patient.convertDateTimeDisplay(birthdate);
                      }
                    }),
                DropdownButton(
                  isExpanded: true,
                  value: genderValue,
                  items: menuItems,
                  onChanged: (String? value) {
                    setState(() {
                      genderValue = value;
                    });
                  },
                ),
                TextFormField(
                  decoration: InputDecoration(labelText: "Address"),
                  controller: _addressController,
                ),
                TextFormField(
                  decoration: InputDecoration(labelText: "Phone"),
                  controller: _phoneController,
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(vertical: 16.0),
                  child: ElevatedButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        _formKey.currentState!.save();
                        late int id;
                        if (widget.patient != null) {
                          id = widget.patient!.id!;
                        }

                        Patient patient = Patient(
                          1,
                            _firstNameController.text,
                            _lastNameController.text,
                            DateFormat('yyyy-MM-dd')
                                .parse(_birthDateController.text),
                            genderValue,
                            _addressController.text,
                            _phoneController.text);

                        if (widget.update) {
                          patientRepository
                              .updatePatient(id, patient)
                              .then((value) => patient = value)
                              .whenComplete(() => Provider.of<PatientProvider>(
                                      context,
                                      listen: false)
                                  .updatePatient(patient.id!, patient));
                          ;
                        } else {
                          patientRepository
                              .postPatient(patient)
                              .then((value) => patient = value);
                          Provider.of<PatientProvider>(context, listen: false)
                              .addPatient(patient);
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
