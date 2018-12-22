package com.example.lauribohm.viikko7javaaa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    EditText text;
    EditText tiedosto;
    Button tallenna;
    Button lataa;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        tiedosto = (EditText) findViewById(R.id.Tiedosto);
        tallenna = (Button) findViewById(R.id.Tallenna);
        lataa =  (Button) findViewById(R.id.Lataa);

        context = MainActivity.this;

        tallenna.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                try {

                    OutputStreamWriter kirjoittaja = new OutputStreamWriter(context.openFileOutput(tiedosto.getText().toString().trim(),Context.MODE_PRIVATE));

                    kirjoittaja.write(text.getText().toString().trim());
                    kirjoittaja.close();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        lataa.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                String tulos;

                try {

                    InputStream inputStream = context.openFileInput(tiedosto.getText().toString().trim());

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    while ((tulos = bufferedReader.readLine()) != null ) {
                        text.setText(tulos);
                    }

                    inputStream.close();
                }

                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });



    }
}

