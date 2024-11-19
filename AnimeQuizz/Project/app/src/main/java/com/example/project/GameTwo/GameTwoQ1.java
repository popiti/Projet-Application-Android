package com.example.project.GameTwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

public class GameTwoQ1 extends AppCompatActivity {

    private Button button;
    private RadioGroup rg1;
    private CountDownTimer cdt;
    private ProgressBar pgbar;
    private TextView timer;
    private GametwoData gametwoData;
    private int selectedAnswer;
    private int rightAnwser = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("GameTwoQ1", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gametwo_q1);
        button = findViewById(R.id.button3);
        rg1 = findViewById(R.id.radioGroup);
        pgbar = findViewById(R.id.progressBar4);
        timer = findViewById(R.id.textView8);
        gametwoData = getIntent().getParcelableExtra("gametwoData");
        pgbar.setMax(10);
        startCDT(10000);

        selectedAnswer = gametwoData.getAnswer(0);
        if(selectedAnswer!=-1)
        {
            rg1.check(selectedAnswer);
        }
        button.setOnClickListener(v->{
            int tmpSelected = rg1.getCheckedRadioButtonId();
            if(tmpSelected==-1)
            {
                Toast.makeText(this, "Veuillez selectionner une réponse", Toast.LENGTH_SHORT).show();
                return;
            }
                int id = rg1.indexOfChild(findViewById(tmpSelected));
                selectedAnswer = rg1.getId();
                if (id != rightAnwser) {
                    gametwoData.setScore_result(0, 0);
                } else {
                    gametwoData.setScore_result(0, 1);
                }
                gametwoData.setAnswer(0, selectedAnswer);
                Log.d("GameQ1","Score actuelle : " + gametwoData.getScore_result());
                Intent intent = new Intent(this, GameTwoQ2.class);
                intent.putExtra("gametwoData", gametwoData);
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
                    gametwoData.setScore_result(0,0);
                    gametwoData.setAnswer(0,selectedAnswer);
                }
                else
                {
                    int id = rg1.indexOfChild(findViewById(tmpSelected));
                    selectedAnswer = rg1.getId();
                    if (id == rightAnwser) {
                        gametwoData.setScore_result(0, 1);
                    } else {
                        gametwoData.setScore_result(0, 0);
                    }
                    gametwoData.setAnswer(0, selectedAnswer);
                }
                Log.d("GameQ1","Score actuelle : " + gametwoData.getScore_result());
                Intent intent = new Intent(GameTwoQ1.this, GameTwoQ2.class);
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