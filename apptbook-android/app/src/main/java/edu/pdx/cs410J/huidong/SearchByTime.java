package edu.pdx.cs410J.huidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchByTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_time);

        Button search = findViewById(R.id.SearchTime);
        search.setOnClickListener(view -> OwnerSearch());
    }

    private void OwnerSearch() {
        EditText owner = findViewById(R.id.ownername);
        EditText beginTime = findViewById(R.id.beginTime);
        EditText endTime = findViewById(R.id.endTime);

        String ownerToString = owner.getText().toString();
        String BeginTimeToString = beginTime.getText().toString();
        String EndTimeToString = endTime.getText().toString();
        ArrayAdapter<String> appts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        ListView listOfAppts = findViewById(R.id.Appointments);
        listOfAppts.setAdapter(appts);

        if(ownerToString.isEmpty()){
            displayErrorMessage("Owner Name is Missing");
            return;
        }else if (BeginTimeToString.isEmpty()){
            displayErrorMessage("BeginTime is Missing");
            return;
        }else if (EndTimeToString.isEmpty()){
            displayErrorMessage("EndTime is Missing");
            return;
        }

        if(!parseDate(BeginTimeToString, EndTimeToString)){
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date start ;
        Date end;
        try {
            start = dateFormat.parse(BeginTimeToString);
            end = dateFormat.parse(EndTimeToString);
        }catch (ParseException e){
            displayErrorMessage("Time parse failed!");
            return;
        }


        File apptsFile = getFile(ownerToString);


        if (!apptsFile.exists()) {
            displayErrorMessage("There is no match appointment book!");
            //ListView listOfAppts = findViewById(R.id.Appointments);
            listOfAppts.setAdapter(appts);
            return;
        }


        try (
                BufferedReader br = new BufferedReader(new FileReader(apptsFile))
        ) {
            TextParser textParser = new TextParser(new FileReader(apptsFile));
            AppointmentBook book = textParser.parseUsingReader();

            if(book == null){
                displayErrorMessage("Cannot parse file correctly, file may misformatted");
                return;
            }
            ArrayList<Appointment> arrayList = new ArrayList<>();
            arrayList.addAll(book.getAppointments());

            boolean flag = true;
            for(Appointment appointment : arrayList){
                if(checkForTime(start, end, appointment)){
                   appts.add(appointment.toString());
                   flag = false;
                }
            }
            if(flag){
                displayErrorMessage("There is no match appointments");
               // ListView listOfAppts = findViewById(R.id.Appointments);
                listOfAppts.setAdapter(appts);
                return;
            }
        } catch (IOException e) {
            displayErrorMessage("Read failed\n" + e.getMessage());
            return;
        }


        //ListView listOfAppts = findViewById(R.id.Appointments);
        listOfAppts.setAdapter(appts);
    }

    public boolean checkForTime(Date start, Date end, Appointment appointment) {
        appointment.getBeginTimeString();
        appointment.getEndTimeString();
        if(appointment.getBeginTime().getTime() >= start.getTime()){
            if(appointment.getEndTime().getTime() <= end.getTime()){
                return true;
            }
        }
        return false;
    }

    private boolean parseDate(String beginTimeToString, String endTimeToString) {

        Date start ;
        Date end;
        String DateFormatMach = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d{4}) (0?[0-9]|1[012]):(0?[0-9]|[12345][0-9]) (am|AM|pm|PM)";
        if (!beginTimeToString.matches(DateFormatMach)){
            displayErrorMessage("BeginTime is Misformatted");
            return false;
        }else if(!endTimeToString.matches(DateFormatMach)){
            displayErrorMessage("EndTime is Misformatted");
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        try {
            start = dateFormat.parse(beginTimeToString);
            end = dateFormat.parse(endTimeToString);
            if (end.getTime() - start.getTime() < 0) {
                displayErrorMessage("The appointment’s end time is before its starts time");
                return false;
            }
        } catch (ParseException e) {
            displayErrorMessage("Time parse failed!");
            return false;
        }
        return true;
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