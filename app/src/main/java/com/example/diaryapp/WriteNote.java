package com.example.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class WriteNote extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    private String header, text;
    private EditText edit_header, edit_text;
    private Button save_button;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        final Intent intent = getIntent();
        final String filename = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        save_button = findViewById(R.id.SaveButton);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_header = findViewById(R.id.NoteHeader);
                edit_text = findViewById(R.id.EditText);
                header = edit_header.getText().toString();
                text = edit_text.getText().toString();
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(header, MODE_PRIVATE)));
                    bw.write(header + "\n" + text);
                    bw.close();
                    Log.d(LOG_TAG, "Файл записан");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                closeActivity();
            }
        });
    }

    private void closeActivity() {
        this.finish();
    }
}
