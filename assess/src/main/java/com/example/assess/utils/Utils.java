package com.example.assess.utils;

import com.example.assess.model.Note;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static int ageLimitor = 30;

    public static int getAssesCompt(List<Note> notes) {
        int compt = 0;
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
                "Anticorps"
        );
        for (int i = 0; i < allAssess.size(); i++) {
            if (notes.contains(allAssess.get(i))) {
                compt++;
            }
        }

        return compt;
    }
}
