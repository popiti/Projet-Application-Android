package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameOneQ6 extends AppCompatActivity {

    private Switch switchQ6;
    private Button btSuivant_Q6,btPreced_Q6;
    private TextView tvOff_Q6,tvOn_Q6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page6);

        switchQ6 = findViewById(R.id.switchQ6);
        btSuivant_Q6 = findViewById(R.id.btSuivant_Q6);
        btPreced_Q6 = findViewById(R.id.btPreced_Q6);
        tvOff_Q6 = findViewById(R.id.tvOff_Q6);
        tvOn_Q6 = findViewById(R.id.tvOn_Q6);

        restorePreferences();

        btSuivant_Q6.setOnClickListener(v -> {
            savePreferences();
            Intent nextIntent = new Intent(GameOneQ6.this, GameOneQ7.class);
            startActivity(nextIntent);
        });

        btPreced_Q6.setOnClickListener(v -> {
            savePreferences();
            finish();
        });
    }

    private void savePreferences() {
        String response = switchQ6.isChecked() ? tvOn_Q6.getText().toString() : tvOff_Q6.getText().toString();
        PrefUtil.saveResponse(this, "reponse_question_6", response);
    }

    private void restorePreferences() {
        String savedResponse = PrefUtil.getResponse(this, "reponse_question_6", "");
        boolean isOn = savedResponse.equals(tvOn_Q6.getText().toString());
        switchQ6.setChecked(isOn);
    }
}