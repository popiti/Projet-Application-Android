package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import java.util.ArrayList;

public class GameOneQ5 extends AppCompatActivity {

    private CheckBox cbCourage, cbIntelligence, cbComique;
    private Button btSuivantQ5, btPreced_Q5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page5);

        cbCourage = findViewById(R.id.cbCourage_Q5);
        cbIntelligence = findViewById(R.id.cbIntel_Q5);
        cbComique = findViewById(R.id.cbComique_Q5);
        btSuivantQ5 = findViewById(R.id.btSuivant_Q5);
        btPreced_Q5 = findViewById(R.id.btPreced_Q5);

        restoreCheckBoxStates();

        btSuivantQ5.setOnClickListener(v->{
            ArrayList<String> selectedTraits = new ArrayList<>();
            if (cbCourage.isChecked()) selectedTraits.add("Courageux");
            if (cbIntelligence.isChecked()) selectedTraits.add("Intelligent");
            if (cbComique.isChecked()) selectedTraits.add("Comique");

            if (selectedTraits.isEmpty()) {
                Toast.makeText(GameOneQ5.this, "Veuillez sélectionner au moins un trait", Toast.LENGTH_SHORT).show();
            } else {

                String selectedTraitsText = TextUtils.join(", ", selectedTraits);

                PrefUtil.saveResponse(this, "reponse_question_5_traits", selectedTraitsText);


                Intent intent = new Intent(GameOneQ5.this, GameOneQ6.class);

                startActivity(intent);
            }
        });

        btPreced_Q5.setOnClickListener(v->{
            saveCheckBoxStates();
            finish();
        });
    }

    private void saveCheckBoxStates() {
        PrefUtil.saveResponse(this, "reponse_question_5_courage", String.valueOf(cbCourage.isChecked()));
        PrefUtil.saveResponse(this, "reponse_question_5_intelligence", String.valueOf(cbIntelligence.isChecked()));
        PrefUtil.saveResponse(this, "reponse_question_5_comique", String.valueOf(cbComique.isChecked()));
    }

    private void restoreCheckBoxStates() {
        cbCourage.setChecked(PrefUtil.getResponse(this, "reponse_question_5_courage", "false").equals("true"));
        cbIntelligence.setChecked(PrefUtil.getResponse(this, "reponse_question_5_intelligence", "false").equals("true"));
        cbComique.setChecked(PrefUtil.getResponse(this, "reponse_question_5_comique", "false").equals("true"));
    }
}