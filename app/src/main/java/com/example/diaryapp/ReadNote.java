package com.example.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadNote extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        text = findViewById(R.id.EditText2);
        final Intent intent = getIntent();
        final String filename = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d(LOG_TAG, filename);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(filename)));
            String str = "";
            String text_from_file = "";
            while ((str = br.readLine()) != null) {
                text_from_file += str;
                text.setText(text_from_file.toString());
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
