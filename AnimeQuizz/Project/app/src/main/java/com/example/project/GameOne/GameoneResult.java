package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameoneResult extends AppCompatActivity {

    private TextView  tvResult;
    ImageView characterImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_result);


        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("SON GOKU", 0);
        scores.put("NARUTO", 0);
        scores.put("MONKEY.D.LUFFY", 0);

        Button btPartager = findViewById(R.id.btPartager);
        Button btReponses = findViewById(R.id.btReponses);

        String matchingCharacter = determineMatchingCharacter();


        characterImage = findViewById(R.id.characterImage);
        tvResult = findViewById(R.id.tvResult);

        String bonus;
        int reponseQ7 = PrefUtil.getResponseInt(this, "reponse_question_7", 0);
        if(reponseQ7 < 10){
            bonus = "Vous regarder pas mal de anime ";
        } else if (reponseQ7 < 20) {
            bonus = "WoW on a trouvé un passioné d'anime ici";
        } else if (reponseQ7 < 20000 ) {
            bonus = "C'est possible de regarder " + reponseQ7 + " heures d'anime par semaine ?????";
        } else {
            bonus = "Par contre , Vous regarder que des animes dans votre vie ou comment ? ";
        }

        updateUIWithCharacter(matchingCharacter,bonus);
        PrefUtil.saveResponse(this,"matchingCharacter",matchingCharacter);
        btPartager.setOnClickListener(v -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Mon Résultat du Questionnaire Anime");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Je ressemble le plus à " + matchingCharacter + " dans les animes!");
            startActivity(Intent.createChooser(shareIntent, "Partager via"));
        });

        btReponses.setOnClickListener(v ->{
            Intent intent = new Intent(GameoneResult.this,GameoneInfos.class);
            startActivity(intent);
        });
    }

    private String determineMatchingCharacter() {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("SON GOKU", 0);
        scores.put("NARUTO", 0);
        scores.put("ITACHI", 0);
        scores.put("MONKEY.D.LUFFY", 0);

        // Question 1:
        String reponseQ1 = PrefUtil.getResponse(this, "reponse_question_1", "");
        if ("Action".equals(reponseQ1)) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 3);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 2);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
        }
        if ("Aventure".equals(reponseQ1)) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 1);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 3);
            scores.put("NARUTO", scores.get("NARUTO") + 2);
        }
        if ("Romance".equals(reponseQ1)) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 1);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
        }
        if ("Horreur".equals(reponseQ1)) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 2);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
        }
        if ("Comédie".equals(reponseQ1)) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 3);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 3);
            scores.put("NARUTO", scores.get("NARUTO") + 3);
        }

        // Question 2:
        String reponseQ2 = PrefUtil.getResponse(this, "reponse_question_2", "");
        if ("Toei Animation".equals(reponseQ2)) {
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
            scores.put("SON GOKU", scores.get("SON GOKU") + 1);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
            scores.put("ITACHI", scores.get("ITACHI") + 1);
        }

        // Question 3:
        int aventurePreference = Integer.parseInt(PrefUtil.getResponse(this, "reponse_question_3_AventurePreference", "0"));
        int actionPreference = Integer.parseInt(PrefUtil.getResponse(this, "reponse_question_3_ActionPreference", "0"));
        int comediePreference = Integer.parseInt(PrefUtil.getResponse(this, "reponse_question_3_ComediePreference", "0"));
        scores.put("SON GOKU", scores.get("SON GOKU") + actionPreference);
        scores.put("NARUTO", scores.get("NARUTO") + comediePreference);
        scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + aventurePreference);

        // Question 4:
        String genreSelectionne = PrefUtil.getResponse(this, "reponse_question_4_genre", "");
        if ("Combat".equals(genreSelectionne)) {
            scores.put("NARUTO", scores.get("NARUTO") + 1);
        }

        // Question 5:
        String selectedTraits = PrefUtil.getResponse(this, "reponse_question_5_traits", "");
        if (selectedTraits.contains("Courageux")) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 3);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 3);
            scores.put("NARUTO", scores.get("NARUTO") + 3);
        }
        if (selectedTraits.contains("Intelligent")) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 1);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
            scores.put("NARUTO", scores.get("NARUTO") + 3);
        }
        if (selectedTraits.contains("Comique")) {
            scores.put("SON GOKU", scores.get("SON GOKU") + 1);
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 3);
            scores.put("NARUTO", scores.get("NARUTO") + 1);
        }

        String reponseQ6 = PrefUtil.getResponse(this, "reponse_question_6", "");
        if ("Suiveur".equals(reponseQ6)) {
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
        }
        if ("Leader".equals(reponseQ6)) {
            scores.put("MONKEY.D.LUFFY", scores.get("MONKEY.D.LUFFY") + 1);
        }


        return Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private void updateUIWithCharacter(String character,String bonus) {
        int characterImageResId;
        String details;

        switch (character) {
            case "SON GOKU":
                characterImageResId = R.drawable.son_goku;
                details = "Vous ressemblez le plus à Son Goku - courageux, fort et toujours prêt à aider les autres et à vous améliorer.";
                break;
            case "NARUTO":
                characterImageResId = R.drawable.naruto;
                details = "Vous ressemblez le plus à Naruto - déterminé, loyal et un grand ami qui ne renonce jamais.";
                break;
            case "MONKEY.D.LUFFY":
                characterImageResId = R.drawable.monkey_d_luffy;
                details = "Vous ressemblez le plus à Monkey D. Luffy - aventurier, insouciant et doté d'un grand cœur.";
                break;
            case "ITACHI":
                characterImageResId = R.drawable.itachi;
                details = "Vous ressemblez le plus à Itachi Uchiha - mystérieux, profondément intelligent et complexe.";
                break;
            case "ICHIGO":
                characterImageResId = R.drawable.ichigo;
                details = "Vous ressemblez le plus à Ichigo Kurosaki - courageux, un peu rebelle et toujours prêt à se battre.";
                break;
            case "YAGAMI":
                characterImageResId = R.drawable.yagami;
                details = "";
                break;
            case "GOJO":
                characterImageResId = R.drawable.gojo;
                details = "";
                break;
            case "EREN":
                characterImageResId = R.drawable.eren;
                details = "";
                break;

            default:
                characterImageResId = R.drawable.placeholder;
                details = "Votre personnage n'a pas été clairement identifié.";
                break;
        }

        characterImage.setImageResource(characterImageResId);
        String VotreChoix = details + "\n" + bonus;
        tvResult.setText(VotreChoix);
    }
}