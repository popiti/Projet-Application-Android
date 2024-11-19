package com.example.project.GameOne;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {

    private static final String PREFS_NAME = "QuizResponses";

    public static void saveResponse(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getResponse(Context context, String key, String defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, defaultValue);
    }

    // Méthode pour sauvegarder une réponse sous forme de int
    public static void saveResponseInt(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    // Méthode pour récupérer une réponse sous forme de int
    public static int getResponseInt(Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, defaultValue);
    }

    public static void clearResponse(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }
    public static void clearMultipleResponses(Context context, String[] keys) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply(); // Applique toutes les suppressions en une fois
    }

    public static void clearGameoneResponses(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String[] keysToClear = {
                "reponse_question_1",
                "response_question_1_id",
                "reponse_question_2",
                "reponse_question_3_AventurePreference",
                "reponse_question_3_AventurePreferenceINT",
                "reponse_question_3_ActionPreference",
                "reponse_question_3_ActionPreferenceINT",
                "reponse_question_3_ComediePreference",
                "reponse_question_3_ComediePreferenceINT",
                "reponse_question_4_genre",
                "reponse_question_5_traits",
                "reponse_question_5_courage",
                "reponse_question_5_intelligence",
                "reponse_question_5_comique",
                "reponse_question_6",
                "reponse_question_7"
        };

        for (String key : keysToClear) {
            editor.remove(key);
        }

        editor.apply();
    }

    public static void clearAllResponses(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Efface toutes les données sauvegardées
        editor.apply();
    }


}