package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameoneInfos extends AppCompatActivity {

    private TextView tvRep1_info,tvRep2_info,tvRep3_info,tvRep4_info,tvRep5_info,tvRep6_info,tvRep7_info;
    private Button btCommInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_infos);

        btCommInfo = findViewById(R.id.btComInfo);

        tvRep1_info = findViewById(R.id.tvRep1_info);
        tvRep2_info = findViewById(R.id.tvRep2_info);
        tvRep3_info = findViewById(R.id.tvRep3_info);
        tvRep4_info = findViewById(R.id.tvRep4_info);
        tvRep5_info = findViewById(R.id.tvRep5_info);
        tvRep6_info = findViewById(R.id.tvRep6_info);
        tvRep7_info = findViewById(R.id.tvRep7_info);

        // Afficher les réponses sauvegardées dans chaque TextView
        tvRep1_info.setText(PrefUtil.getResponse(this, "reponse_question_1", "Non spécifié"));
        tvRep2_info.setText(PrefUtil.getResponse(this, "reponse_question_2", "Non spécifié"));

        String question3Results = "Aventure: " + PrefUtil.getResponse(this, "reponse_question_3_AventurePreference", "0") +
                ", Action: " + PrefUtil.getResponse(this, "reponse_question_3_ActionPreference", "0") +
                ", Comédie: " + PrefUtil.getResponse(this, "reponse_question_3_ComediePreference", "0");
        tvRep3_info.setText(question3Results);

        tvRep4_info.setText(PrefUtil.getResponse(this, "reponse_question_4_genre", "Non spécifié"));
        tvRep5_info.setText(PrefUtil.getResponse(this, "reponse_question_5_traits", "Non spécifié"));
        tvRep6_info.setText(PrefUtil.getResponse(this, "reponse_question_6", "Non spécifié"));

        int savedValue = PrefUtil.getResponseInt(this, "reponse_question_7", 0);  // Default to 0 if nothing found

        tvRep7_info.setText(String.valueOf(savedValue));


        btCommInfo.setOnClickListener(v->{

            Intent intent = new Intent(GameoneInfos.this,GameoneCommentaires.class);
            startActivity(intent);
        });

    }
}