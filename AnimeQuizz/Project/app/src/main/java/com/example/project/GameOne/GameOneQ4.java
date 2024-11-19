package com.example.project.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class GameOneQ4 extends AppCompatActivity {

    private ImageView imgV1_Q4, imgV2_Q4, imgV3_Q4, imgV4_Q4;
    private Button btSuivant_Q4, btPreced_Q4;
    private ImageView[] imageViews;
    private int positionSelectionnee = -1;

    private String[] genresAnime = {"Action","Aventure","Science-Fiction","Combat"};
    private int[] imagesGenres = {
            R.drawable.action,
            R.drawable.aventure,
            R.drawable.fiction,
            R.drawable.combat
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_page4);

        imgV1_Q4 = findViewById(R.id.ImgV1_Q4);
        imgV2_Q4 = findViewById(R.id.ImgV2_Q4);
        imgV3_Q4 = findViewById(R.id.ImgV3_Q4);
        imgV4_Q4 = findViewById(R.id.ImgV4_Q4);
        btSuivant_Q4 = findViewById(R.id.btSuivant_Q4);
        btPreced_Q4 = findViewById(R.id.btPreced_Q4);

        imageViews = new ImageView[]{imgV1_Q4, imgV2_Q4, imgV3_Q4, imgV4_Q4};

        setupImages();
        restoreSelectionAndUpdate();

        btSuivant_Q4.setOnClickListener(vue -> {
            if (positionSelectionnee != -1) {
                String genreSelectionne = genresAnime[positionSelectionnee];
                PrefUtil.saveResponse(this, "reponse_question_4_genre", genreSelectionne);

                Intent intent = new Intent(GameOneQ4.this, GameOneQ5.class);
                startActivity(intent);
            } else {
                Toast.makeText(GameOneQ4.this, "Veuillez sélectionner un genre", Toast.LENGTH_SHORT).show();
            }
        });

        btPreced_Q4.setOnClickListener(vue -> {
            if (positionSelectionnee != -1) {
                String genreSelectionne = genresAnime[positionSelectionnee];
                PrefUtil.saveResponse(this, "reponse_question_4_genre", genreSelectionne);
            }
            finish();
        });
    }

    private void setupImages() {
        for (int i = 0; i < imageViews.length; i++) {
            final int index = i;
            imageViews[i].setImageResource(imagesGenres[i]);
            imageViews[i].setOnClickListener(v -> {
                positionSelectionnee = index;
                updateSelection();
            });
        }
    }

    private void updateSelection() {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setSelected(i == positionSelectionnee); // Mettre à jour l'état sélectionné
            imageViews[i].setBackgroundResource(i == positionSelectionnee ? R.drawable.item_selector : 0); // Appliquer l'anneau pour l'élément sélectionné
        }
    }
    private void restoreSelectionAndUpdate() {
        String savedSelection = PrefUtil.getResponse(this, "reponse_question_4_genre", null);
        if (savedSelection != null) {
            for (int i = 0; i < genresAnime.length; i++) {
                if (genresAnime[i].equals(savedSelection)) {
                    positionSelectionnee = i;
                    break;
                }
            }
        }
        updateSelection();
    }

}
