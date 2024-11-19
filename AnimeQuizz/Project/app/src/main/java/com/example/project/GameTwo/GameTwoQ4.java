package com.example.project.GameTwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

public class GameTwoQ4 extends AppCompatActivity {

    private TextView timer;
    private Button button;
    private CountDownTimer cdt;
    private GametwoData gametwoData;
    private ProgressBar pgbar;
    private int selectedAnswer;
    private RadioGroup rg1;
    private int rightAnwser = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gametwo_q4);
        timer = findViewById(R.id.textView7);
        button = findViewById(R.id.button6);
        pgbar = findViewById(R.id.progressBar2);
        rg1 = findViewById(R.id.radioGroup4);
        gametwoData = getIntent().getParcelableExtra("gametwoData");

        selectedAnswer = gametwoData.getAnswer(3);
        pgbar.setMax(10);
        startCDT(10000);
        if(selectedAnswer!=-1)
        {
            rg1.check(selectedAnswer);
        }
        button.setOnClickListener(v->{
            Log.d("GameTwoQ3","boutonnext clicked");
            int tmpSelected = rg1.getCheckedRadioButtonId();
            if(tmpSelected==-1)
            {
                Toast.makeText(this, "Veuillez selectionner une réponse", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedAnswer = tmpSelected;
            int id = rg1.indexOfChild(findViewById(tmpSelected));
            if(id!=rightAnwser)
            {
                gametwoData.setScore_result(3,0);
            }
            else
            {
                gametwoData.setScore_result(3,1);
            }
            gametwoData.setAnswer(3,selectedAnswer);
            Log.d("GameQ3","Score actuelle : " + gametwoData.getScore_result());
            Intent intent = new Intent(this, GametwoPage.class);
            intent.putExtra("gametwoData",gametwoData);
            startActivity(intent);
            finish();
        });

    }

    private void startCDT(long durationMillis){
        cdt = new CountDownTimer(durationMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int remaainingTime = (int) (millisUntilFinished/1000);
                timer.setText(""+millisUntilFinished/1000);
                pgbar.setProgress(10-remaainingTime);
            }

            @Override
            public void onFinish() {
                //pgbar.setProgress(10);
                int tmpSelected = rg1.getCheckedRadioButtonId();
                if(tmpSelected==-1)
                {
                    selectedAnswer=999;
                    gametwoData.setScore_result(3,0);
                    gametwoData.setAnswer(3,selectedAnswer);
                }
                else
                {
                    int id = rg1.indexOfChild(findViewById(tmpSelected));
                    selectedAnswer = rg1.getId();
                    if (id == rightAnwser) {
                        gametwoData.setScore_result(3, 1);
                    } else {
                        gametwoData.setScore_result(3, 0);
                    }
                    gametwoData.setAnswer(3, selectedAnswer);
                }
                Log.d("GameQ1","Score actuelle : " + gametwoData.getScore_result());
                Intent intent = new Intent(GameTwoQ4.this, GametwoPage.class);
                intent.putExtra("gametwoData",gametwoData);
                startActivity(intent);
                finish();
            }
        };
        cdt.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(cdt!=null)
        {
            cdt.cancel();
        }
    }

    @Override
    public void onBackPressed(){
        Log.d("GameTwoQ1","onBackPressed");
        super.onBackPressed();
        //fais rien
    }
}