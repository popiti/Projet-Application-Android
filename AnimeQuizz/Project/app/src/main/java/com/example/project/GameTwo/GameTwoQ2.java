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

public class GameTwoQ2 extends AppCompatActivity {
    private Button button3;
    private RadioGroup rg1;
    private GametwoData gametwoData;
    private CountDownTimer cdt;
    private ProgressBar pgbar;
    private TextView timer;
    private int selectedAnswer;
    private int rightAnwser = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gametwo_q2);

        button3 = findViewById(R.id.button4);
        rg1 = findViewById(R.id.radioGroup2);
        gametwoData = getIntent().getParcelableExtra("gametwoData");
        pgbar = findViewById(R.id.progressBar3);
        timer = findViewById(R.id.textView4);
        selectedAnswer = gametwoData.getAnswer(1);
        pgbar.setMax(10);
        startCDT(10000);
        if(selectedAnswer!=-1)
        {
            rg1.check(selectedAnswer);
        }
        button3.setOnClickListener(v->{
            Log.d("GameTwoQ2","boutonclicked");
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
                gametwoData.setScore_result(1,0);
            }
            else
            {
                gametwoData.setScore_result(1, 1);
            }
            gametwoData.setAnswer(1,selectedAnswer);
            Log.d("GameQ2","Score actuelle : " + gametwoData.getScore_result());
            Intent intent = new Intent(GameTwoQ2.this, GameTwoQ3.class);
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
                int tmpSelected = rg1.getCheckedRadioButtonId();
                if(tmpSelected==-1)
                {
                    selectedAnswer=999;
                    gametwoData.setScore_result(1,0);
                    gametwoData.setAnswer(1,selectedAnswer);
                }
                else
                {
                    int id = rg1.indexOfChild(findViewById(tmpSelected));
                    selectedAnswer = rg1.getId();
                    if (id == rightAnwser) {
                        gametwoData.setScore_result(1, 1);
                    } else {
                        gametwoData.setScore_result(1, 0);
                    }
                    gametwoData.setAnswer(1, selectedAnswer);
                }
                Intent intent = new Intent(GameTwoQ2.this, GameTwoQ3.class);
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