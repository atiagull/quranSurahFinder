package com.example.quran_surahversefindermobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnVerseFinder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText surahNumber = findViewById(R.id.textSurahNumber);
                EditText verseNumber = findViewById(R.id.textVerseNumber);
                if(!surahNumber.getText().toString().equals("") && !verseNumber.getText().toString().equals(""))
                {
                    String surahNoStirng = surahNumber.getText().toString();
                    int surahNo = Integer.parseInt(surahNoStirng);
                    if(surahNo > 0 && surahNo <= 114)
                    {
                        String verseNoStirng = verseNumber.getText().toString();
                        int verseNo = Integer.parseInt(verseNoStirng);

                        QuranArabicText quranText  = new QuranArabicText();
                        QDH quranIndexing = new QDH();

                        int surahAyatCount = quranIndexing.surahAyatCount[surahNo-1];
                        if(verseNo > surahAyatCount || verseNo <= 0)
                        {
                            Toast.makeText(MainActivity.this, "Enter Verse from 1 to "+ surahAyatCount, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            int indexOfSSPInQuranArabicText = quranIndexing.SSP[surahNo-1];
                            String surahVerseInQAT = quranText.QuranArabicText[indexOfSSPInQuranArabicText+ (verseNo-1)];
                            TextView txtVerseFinder = findViewById(R.id.textViewVerseArabic);
                            txtVerseFinder.setText(surahVerseInQAT);

                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Enter Surah No from 1 to 114", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {

                    Toast.makeText(MainActivity.this, "Fill all of the Fields", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}