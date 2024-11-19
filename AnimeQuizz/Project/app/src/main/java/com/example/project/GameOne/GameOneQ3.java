package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameOneQ3 extends AppCompatActivity {

    private SeekBar sbAventure, sbAction, sbComedie;
    private Button btSuivant_Q3,btPreced_Q3;
    private TextView tvValue1Q3,tvValue2Q3,tvValue3Q3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page3);

        sbAventure = findViewById(R.id.skb_Q3_Aventure);
        sbAction = findViewById(R.id.skb_Q3_Action);
        sbComedie = findViewById(R.id.skb_Q3_Comedie);
        btSuivant_Q3 = findViewById(R.id.btSuivant_Q3);
        btPreced_Q3 = findViewById(R.id.btPreced_Q3);

        tvValue1Q3 = findViewById(R.id.tvValue1Q3);
        tvValue2Q3 = findViewById(R.id.tvValue2Q3);
        tvValue3Q3 = findViewById(R.id.tvValue3Q3);

        sbAventure.setMax(10);
        sbAction.setMax(10);
        sbComedie.setMax(10);

        setupSeekBar(sbAventure,tvValue1Q3);
        setupSeekBar(sbAction,tvValue2Q3);
        setupSeekBar(sbComedie,tvValue3Q3);

        int aventurePref = PrefUtil.getResponseInt(this, "reponse_question_3_AventurePreferenceINT", 0);
        int actionPref = PrefUtil.getResponseInt(this, "reponse_question_3_ActionPreferenceINT", 0);
        int comediePref = PrefUtil.getResponseInt(this, "reponse_question_3_ComediePreferenceINT", 0);

        Log.d("GameOneQ3", "Restoring AventurePref: " + aventurePref + ", ActionPref: " + actionPref + ", ComediePref: " + comediePref);


        sbAventure.setProgress(Math.min(aventurePref, sbAventure.getMax()));
        sbAction.setProgress(Math.min(actionPref, sbAction.getMax()));
        sbComedie.setProgress(Math.min(comediePref, sbComedie.getMax()));


        btSuivant_Q3.setOnClickListener(v->{
            int AventurePreference = sbAventure.getProgress();
            int ActionPreference  = sbAction.getProgress();
            int ComediePreference = sbComedie.getProgress();

            if(AventurePreference >= 0 || ActionPreference >= 0 || ComediePreference >= 0 ){
            PrefUtil.saveResponse(this,"reponse_question_3_AventurePreference",String.valueOf(AventurePreference));
            PrefUtil.saveResponse(this,"reponse_question_3_ActionPreference",String.valueOf(ActionPreference));
            PrefUtil.saveResponse(this,"reponse_question_3_ComediePreference",String.valueOf(ComediePreference));

            PrefUtil.saveResponseInt(this, "reponse_question_3_AventurePreferenceINT", AventurePreference);
            PrefUtil.saveResponseInt(this, "reponse_question_3_ActionPreferenceINT", ActionPreference);
            PrefUtil.saveResponseInt(this, "reponse_question_3_ComediePreferenceINT", ComediePreference);



            Intent intent = new Intent(GameOneQ3.this, GameOneQ4.class);

            startActivity(intent);

            }else{
                Toast.makeText(this, "Veuillez évaluer au moins un genre", Toast.LENGTH_SHORT).show();
            }
        });

        btPreced_Q3.setOnClickListener(v->{

            int AventurePreference = sbAventure.getProgress();
            int ActionPreference  = sbAction.getProgress();
            int ComediePreference = sbComedie.getProgress();

            if(AventurePreference > 0 || ActionPreference > 0 || ComediePreference > 0 ) {
                PrefUtil.saveResponse(this, "reponse_question_3_AventurePreference", String.valueOf(AventurePreference));
                PrefUtil.saveResponse(this, "reponse_question_3_ActionPreference", String.valueOf(ActionPreference));
                PrefUtil.saveResponse(this, "reponse_question_3_ComediePreference", String.valueOf(ComediePreference));

                PrefUtil.saveResponseInt(this, "reponse_question_3_AventurePreferenceINT", AventurePreference);
                PrefUtil.saveResponseInt(this, "reponse_question_3_ActionPreferenceINT", ActionPreference);
                PrefUtil.saveResponseInt(this, "reponse_question_3_ComediePreferenceINT", ComediePreference);
            }
            finish();
        });

    }

    private void setupSeekBar(SeekBar seekBar, TextView textView) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Méthode de callback appelée lorsque l'utilisateur commence à toucher la SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Méthode de callback appelée lorsque l'utilisateur arrête de toucher la SeekBar
            }
        });
    }
}