package com.example.project.GameOne;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.GameChoice;
import com.example.project.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameoneCommentaires extends AppCompatActivity {

    private EditText etCommentaire;
    private RatingBar ratingBar;
    private Button btSauvegarder,btQuit,btHome;
    private RecyclerView rvCommentaires;

    private CommentaireAdapter commentaireAdapter;
    private ArrayList<CommentaireEtNote> listeCommentaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone_commentaires);

        etCommentaire = findViewById(R.id.etCommentaire);
        ratingBar = findViewById(R.id.ratingBarComm);
        btSauvegarder = findViewById(R.id.btSaveComm);
        btHome = findViewById(R.id.button5);
        btQuit = findViewById(R.id.button);
        rvCommentaires = findViewById(R.id.rvCommentaires);




        rvCommentaires.setLayoutManager(new LinearLayoutManager(this));

        listeCommentaires = chargerCommentaires();
        commentaireAdapter = new CommentaireAdapter(this,listeCommentaires);
        rvCommentaires.setAdapter(commentaireAdapter);

        btSauvegarder.setOnClickListener(v->{
            String commentaireTexte = etCommentaire.getText().toString().trim();
            float note = ratingBar.getRating();

            if (!commentaireTexte.isEmpty() && note != 0) {
                CommentaireEtNote nouveauCommentaire = new CommentaireEtNote(commentaireTexte, note);
                listeCommentaires.add(nouveauCommentaire); // Ajoute le nouveau commentaire à la liste

                sauvegarderCommentaireEtNote(commentaireTexte, note); // Sauvegarde le commentaire et la note

                commentaireAdapter.notifyDataSetChanged(); // Notifie l'adaptateur que les données ont changé

                etCommentaire.setText(""); // Réinitialise le champ de commentaire
                ratingBar.setRating(0); // Réinitialise la RatingBar
            } else {
                Toast.makeText(GameoneCommentaires.this, "Veuillez entrer un commentaire et une note", Toast.LENGTH_SHORT).show();
            }
        });

        btQuit.setOnClickListener(v ->
        {
            super.onBackPressed();
            finishAffinity();
        });

        btHome.setOnClickListener(v -> {
            Intent intent = new Intent(GameoneCommentaires.this, GameChoice.class);
            startActivity(intent);
            finish();
        });
    }

    private void sauvegarderCommentaireEtNote(String commentaire, float note) {
        ArrayList<CommentaireEtNote> commentaires = chargerCommentaires();
        CommentaireEtNote nouveauCommentaire = new CommentaireEtNote(commentaire, note);
        commentaires.add(nouveauCommentaire);
        try {
            FileOutputStream fos = openFileOutput("commentaires", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(commentaires);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<CommentaireEtNote> chargerCommentaires() {
        ArrayList<CommentaireEtNote> commentaires;
        try {
            FileInputStream fis = openFileInput("commentaires");
            ObjectInputStream ois = new ObjectInputStream(fis);
            commentaires = (ArrayList<CommentaireEtNote>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            commentaires = new ArrayList<>();
        }
        return commentaires;
    }
}