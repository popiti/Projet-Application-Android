package com.example.project.GameTwo;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.GameChoice;
import com.example.project.GameOne.PrefUtil;
import com.example.project.R;


public class GametwoPage extends AppCompatActivity {
    private Button button;
    private TextView text;
    private TextView bilan;
    private GametwoData gametwoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fonctionne
     /*   OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(GametwoPage.this,GameChoice.class);
                startActivity(intent);
                // Back is pressed... Finishing the activity
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);*/
        setContentView(R.layout.activity_gametwo_page);
        button = findViewById(R.id.button);
        text = findViewById(R.id.textView);
        bilan = findViewById(R.id.textView10);
        gametwoData = getIntent().getParcelableExtra("gametwoData");
//        Log.d("GametwoPage", "gameTerminated : " + gametwoData.gameTerminated());
        if (gametwoData.gameTerminated())
        {
            int result = gametwoData.getScore_result();
            PrefUtil.saveResponse(this,"score_quizcult",String.valueOf(result));
            text.setText("Score finale : " + result + "/5");
            button.setText(R.string.home);
            if(result <= 2)
                bilan.setText(R.string.text_score_inf2);
            if(result==3)
                bilan.setText(R.string.text_score_3);
            if(result>3)
                bilan.setText(R.string.text_score_45);
        }
        button.setOnClickListener(v->{
            if(gametwoData.gameTerminated())
            {
                Intent intent = new Intent(this, GameChoice.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, GameTwoQ1.class);
                intent.putExtra("gametwoData", gametwoData);
                startActivity(intent);
            }
            finish();
        });
    }

    //fonctionne aussi
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("GametwoPage", "onBackPressed: returning to GameChoice");
    }
}