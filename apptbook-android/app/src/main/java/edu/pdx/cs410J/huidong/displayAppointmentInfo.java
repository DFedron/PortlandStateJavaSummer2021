package edu.pdx.cs410J.huidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class displayAppointmentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_appointment_info);

        Button search = findViewById(R.id.ownerSearch);
        search.setOnClickListener(view -> OwnerSearch());
    }

    private void OwnerSearch() {
        EditText owner = findViewById(R.id.ownername);
        String ownerToString = owner.getText().toString();
        ArrayAdapter<String> appts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ListView listOfAppts = findViewById(R.id.Appointments);
        listOfAppts.setAdapter(appts);

        if(ownerToString.isEmpty()){
            displayErrorMessage("Owner Name is Missing");
            return;
        }
        File apptsFile = getFile(ownerToString);


        if (!apptsFile.exists()) {
            displayErrorMessage("There is no such appointment book");
            return;
        }

        try (
                BufferedReader br = new BufferedReader(new FileReader(apptsFile))
        ) {
            String line = br.readLine();
            line = br.readLine();
            while(line != null) {
                appts.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            displayErrorMessage("Read failed\n" + e.getMessage());
        }


        //ListView listOfAppts = findViewById(R.id.Appointments);
        listOfAppts.setAdapter(appts);
    }

    private File getFile(String owner) {
        File contextDirectory = getApplicationContext().getDataDir();
        File book = new File(contextDirectory, owner + ".txt");
        return book;
    }

    private void displayErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}