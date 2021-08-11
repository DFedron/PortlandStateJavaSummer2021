package edu.pdx.cs410J.huidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class READMEview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmeview);

        try {
            String readme = "README Info";
            Class c = READMEview.class;
            InputStream inputStream = c.getResourceAsStream("README.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while (br.ready()) {
                readme = readme + "\n" + br.readLine();
            }
            br.close();
            TextView textView = findViewById(R.id.readme);
            textView.setText(readme);
        } catch (IOException e) {
            Toast.makeText(this, "README cannot read", Toast.LENGTH_LONG).show();
        }

    }
}