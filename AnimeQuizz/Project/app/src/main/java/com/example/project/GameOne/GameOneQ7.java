package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameOneQ7 extends AppCompatActivity {

    private Button btSuivant_Q7, btPreced_Q7;
    private EditText etdn_Q7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page7);

        btSuivant_Q7 = findViewById(R.id.btSuivant_Q7);
        btPreced_Q7 = findViewById(R.id.btPreced_Q7);
        etdn_Q7 = findViewById(R.id.etdn_Q7);

        restorePreferences();

        btSuivant_Q7.setOnClickListener(v->{
            savePreferences();
            Intent nextIntent = new Intent(GameOneQ7.this, GameoneResult.class);
            startActivity(nextIntent);
        });

        btPreced_Q7.setOnClickListener(v->{
            savePreferences();
            finish();
        });
    }

    private void savePreferences() {
        int enteredValue = 0;
        try {
            enteredValue = Integer.parseInt(etdn_Q7.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        PrefUtil.saveResponseInt(this, "reponse_question_7", enteredValue);
    }

    private void restorePreferences() {
        int savedValue = PrefUtil.getResponseInt(this, "reponse_question_7", 0);  // Default to 0 if nothing found
        etdn_Q7.setText(String.valueOf(savedValue));
    }
}