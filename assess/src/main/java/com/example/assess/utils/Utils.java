package com.example.assess.utils;

import com.example.assess.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static int ageLimitor = 30;

    public static int getAssesCompt(List<Note> notes) {
        int compt = 0;

        List<String> notesList = new ArrayList<String>();

        for(int j=0; j < notes.size(); j++) {
            notesList.add(notes.get(j).getNote().toLowerCase());
        }
        String str = String.join("", notesList);

        List<String> allAssess = Arrays.asList(
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Anormal",
                "Cholestérol",
                "Vertige",
                "Rechute",
                "Réaction",
                "Anticorps",
                "Hemoglobin A1C",
                "Size",
                "Weight",
                "Smoker",
                "Abnormal",
                "Cholesterol",
                "Vertigo",
                "Relapse",
                "Reaction",
                "Antibodies"
        );
        for (int i = 0; i < allAssess.size(); i++) {
            if (str.contains(allAssess.get(i).toLowerCase())) {
                compt++;
            }
        }
        return compt;
    }
}
