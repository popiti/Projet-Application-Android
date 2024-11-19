package com.example.project;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.project.GameOne.GameOneQ1;
import com.example.project.GameTwo.GametwoData;
import com.example.project.GameTwo.GametwoPage;
import com.example.project.GameOne.PrefUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class GameChoice extends AppCompatActivity {

    public ImageButton buttonQuizzOne;
    public ImageButton buttonQuizzTwo;
    public SharedPreferences sharedPref;
    public Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choice);
        PrefUtil.clearGameoneResponses(this);
        Log.d("GameChoice","onCreate : ");
        buttonQuizzOne = findViewById(R.id.imageButtonQuizzOne);
        buttonQuizzTwo = findViewById(R.id.imageButtonQuizzTwo);
        exit = findViewById(R.id.button8);

        buttonQuizzOne.setOnClickListener(v -> {
            Log.d("GameChoice","buttonQuizzOne is clicked ");
            Intent intent = new Intent(this, GameOneQ1.class);
            startActivity(intent);
            finish();
        });

        buttonQuizzTwo.setOnClickListener(v -> {
            Log.d("GameChoice","buttonQuizzTwo is clicked ");
            GametwoData gametwoData = new GametwoData();
            Intent intent = new Intent(this, GametwoPage.class);
            intent.putExtra("gametwoData", gametwoData);
            startActivity(intent);
            finish();
        });

        exit.setOnClickListener(v -> {
            saveFile_result();
            //Tentative d'envoyer par mail
                /*Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abcd@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "je suis la");
                intent.putExtra(Intent.EXTRA_TEXT, "hello from walid");
                Intent.putExtra(Intent.EXTRA_STREAM,intent.getData());
                intent.setData(Uri.parse("mailto:"));
                startActivity(intent);*/
            super.onBackPressed();
            finishAffinity();
        });
        sharedPref = getSharedPreferences("scores",MODE_PRIVATE);
        getName();
        getPersonnage();
        getScoreResults();
    }

    public void saveFile_result()
    {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(folder,"bilan_Quizz.txt");
        try(FileOutputStream fs = new FileOutputStream(file,true)){
            PrintStream out = new PrintStream(fs);
            String resultTxt = "Anime Quizz Results de : " + getName() + " fait le "+ java.time.LocalDateTime.now()+"\n"+ "Score in second quizz : " + getScoreResults() + "\n" + "Résultat du test de personnalité : " + getPersonnage()+"\n"+"****************************************************************** \n";
            out.print(resultTxt);
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getScoreResults()
    {
        int score = sharedPref.getInt("score_quizcult",0);
        Log.d("GameChoice","Score : " + score);
        return String.valueOf(score);
    }
    public String getPersonnage(){
        String personnage = PrefUtil.getResponse(this,"matchingCharacter","Pas de personnage");
        Log.d("GameChoice","personnage : " + personnage);
        return personnage;
    }
    public String getName(){
        SharedPreferences sharedPreferences = getSharedPreferences("game",MODE_PRIVATE);
        String name = sharedPreferences.getString("name","Inconnu");
        Log.d("GameChoice","Name : " + name);
        return name;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(GameChoice.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}