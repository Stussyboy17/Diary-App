package com.example.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteNote extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    private String header, text;
    private EditText edit_header, edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        Button save_button = findViewById(R.id.SaveButton);
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
                    bw.write(header );
                    bw.write("\n");
                    bw.write(text);
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
