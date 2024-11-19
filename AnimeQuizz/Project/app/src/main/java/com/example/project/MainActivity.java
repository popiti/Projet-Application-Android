package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.os.LocaleListCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public Button buttonHome;
    public Switch switchLang;
    public SharedPreferences sharedPreferenceslang;
    public SharedPreferences sharedPreferences;
    public CheckBox chkboc;
    public EditText pseudo;
    private boolean isSwitched = false;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate :");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHome = findViewById(R.id.buttonHome);
        switchLang = findViewById(R.id.switchLangage);
        pseudo = findViewById(R.id.editTextText);
        chkboc = findViewById(R.id.checkBoxHome);
        switchLang.setChecked(isSwitched);
        sharedPreferences = getSharedPreferences("game", MODE_PRIVATE);
        sharedPreferenceslang = getSharedPreferences("languages", MODE_PRIVATE);


       // Locale.setDefault(Locale.getDefault());
        Log.d("MainActivity","onCreate : locale.setdefault : " + Locale.getDefault());
        String savedLocale = sharedPreferenceslang.getString("locale", "fr");
        Log.d("MainActivity","onCreate : savedLocale : " + savedLocale);
        if (!savedLocale.isEmpty()) {
            switchLang.setChecked(!Locale.getDefault().getLanguage().equals("fr"));

            defineLanguage(savedLocale);
        }

        switchLang.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d("MainActivity", "onCreate : Switch language clicked : "+isChecked);
                if (!isChecked)
                {
                    Log.d("MainActivity", "onCreate: switch checked");
                    if (!Locale.getDefault().getLanguage().equals("en-us"))
                    {
                        Log.d("MainActivity", "onCreate: en-us");
                        defineLanguage("en-us");
                        SharedPreferences.Editor editor = sharedPreferenceslang.edit();
                        editor.putString("locale", "en-us");
                        editor.apply();
                    }
                }
                else
                {
                    Log.d("MainActivity", "onCreate: switch unchecked");
                    if (!Locale.getDefault().getLanguage().equals("fr"))
                    {
                        Log.d("MainActivity", "onCreate: fr");
                        defineLanguage("fr");
                        SharedPreferences.Editor editor = sharedPreferenceslang.edit();
                        editor.putString("locale", "fr");
                        editor.apply();
                    }
                }
        });

        buttonHome.setOnClickListener(v->{
            Log.d("MainActivity","buttonHome clicked");
            String name = pseudo.getText().toString();
            if(name.isEmpty())
            {
                Log.d("MainActivity", "name is empty");
                Toast.makeText(this, R.string.toastPseudo, Toast.LENGTH_SHORT).show();
                return;
            }
            if(chkboc.isChecked())
            {
                Log.d("MainActivity", "checkBoxSave checked");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",name);
                editor.apply();
            }
            SharedPreferences preferences = getSharedPreferences("scores",AppCompatActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(this, GameChoice.class);
            startActivity(intent);
        });
        verifyStoragePermissions(this);
    }

    private void defineLanguage(String languageCode) {
        if (!languageCode.equals(Locale.getDefault().getLanguage())) {
            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(languageCode);
// Call this on the main thread as it may require Activity.restart()
            AppCompatDelegate.setApplicationLocales(appLocale);
            Log.d("MainActivity", "onCreate: applocale : " + appLocale);
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
// Vérifie si nous avons les droits d’écriture
        int permission = ActivityCompat.checkSelfPermission(activity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
// Aie, il faut les demander àl’utilisateur
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }


    @Override
    public void onResume() {
        Log.d("MainActivity", "onResume ");
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("game", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
       // SharedPreferences sharedPrefLang = getSharedPreferences("languages", AppCompatActivity.MODE_PRIVATE);
       // SharedPreferences.Editor editorLang = sharedPrefLang.edit();
        editor.clear();
        editor.apply();
     //   editorLang.clear();
     //   editorLang.apply();
    }
}