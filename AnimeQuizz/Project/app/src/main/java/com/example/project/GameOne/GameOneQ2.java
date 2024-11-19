package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameOneQ2 extends AppCompatActivity {

    public Spinner SP_Q2;
    public Button btSuivant_Q2, btPreced_Q2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page2);

        SP_Q2 = findViewById(R.id.SP_Q2);
        btSuivant_Q2 = findViewById(R.id.btSuivant_Q2);
        btPreced_Q2 = findViewById(R.id.btPreced_Q2);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.studios_anime, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SP_Q2.setAdapter(adapter);

        String savedStudio = PrefUtil.getResponse(this, "reponse_question_2", "");
        if (!savedStudio.isEmpty()) {
            int position = adapter.getPosition(savedStudio);
            SP_Q2.setSelection(position, true);
        }


        btSuivant_Q2.setOnClickListener(v -> {
            if (SP_Q2.getSelectedItemPosition() > 0) {

                String selectedStudio = SP_Q2.getSelectedItem().toString();
                PrefUtil.saveResponse(this, "reponse_question_2", selectedStudio);


                Intent intent = new Intent(GameOneQ2.this, GameOneQ3.class);
                startActivity(intent);
            } else {

                Toast.makeText(this, "Veuillez sélectionner un studio", Toast.LENGTH_SHORT).show();
            }
        });

        btPreced_Q2.setOnClickListener(v-> {
            int selectedItemPosition = SP_Q2.getSelectedItemPosition();
            if (selectedItemPosition > 0) {
                String selectedStudio = (String) SP_Q2.getSelectedItem();
                PrefUtil.saveResponse(this, "reponse_question_2", selectedStudio);
            }

            finish();

        });

    }



}