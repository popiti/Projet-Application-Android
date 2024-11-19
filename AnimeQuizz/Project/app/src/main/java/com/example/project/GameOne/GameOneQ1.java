package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.GameChoice;
import com.example.project.R;

public class GameOneQ1 extends AppCompatActivity {


    public RadioGroup RG_Q1;
    public Button btSuivant_Q1;
    public Button btPrec_Q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page1);

        btSuivant_Q1 = findViewById(R.id.btSuivant_Q1);
        btPrec_Q1 = findViewById(R.id.btPreced_Q1);
        RG_Q1 = findViewById(R.id.RG_Q1);


        btSuivant_Q1.setOnClickListener(v -> {
            int selectedId = RG_Q1.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);

            if (selectedRadioButton != null){
                String selectedResponse = selectedRadioButton.getText().toString();

                PrefUtil.saveResponse(this, "reponse_question_1", selectedResponse);

                Intent intent = new Intent(GameOneQ1.this, GameOneQ2.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
            }
        });

        btPrec_Q1.setOnClickListener(v->{
            int selectedId = RG_Q1.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);


                String selectedResponse = selectedRadioButton.getText().toString();

                PrefUtil.saveResponse(this, "reponse_question_1", selectedResponse);

                Intent intent = new Intent(GameOneQ1.this, GameChoice.class);
                startActivity(intent);
                finish();


        });

    }
}