package edu.pdx.cs410J.huidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateAppointmentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment_page);

        Button create = findViewById(R.id.CreateAppointmentPageButton);
        create.setOnClickListener(view -> createAppointment());
    }

    private void createAppointment() {
        EditText owner = findViewById(R.id.ownername);
        EditText description = findViewById(R.id.description);
        EditText beginTime = findViewById(R.id.beginTime);
        EditText endTime = findViewById(R.id.endTime);

        String ownerToString = owner.getText().toString();
        String DescriptionToString = description.getText().toString();
        String BeginTimeToString = beginTime.getText().toString();
        String EndTimeToString = endTime.getText().toString();

        if(ownerToString.isEmpty()){
            displayErrorMessage("Owner Name is Missing");
            return;
        }else if (DescriptionToString.isEmpty()){
            displayErrorMessage("Description is Missing");
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
        File apptsFile = getFile(ownerToString);
        Appointment appointment = new Appointment(DescriptionToString, BeginTimeToString, EndTimeToString);

        if(!apptsFile.exists()){
            AppointmentBook book = new AppointmentBook(ownerToString);
            book.addAppointment(appointment);
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(apptsFile));
                PrettyPrinter prettyPrinter = new PrettyPrinter(pw);
                prettyPrinter.dumpUsingWriter(book);
            } catch (IOException e) {
                displayErrorMessage("Write failed\n" + e.getMessage());
                return;
            }

        }else {

            try {
                TextParser textParser = new TextParser(new FileReader(apptsFile));
                AppointmentBook book = textParser.parseUsingReader();
                if(book == null){
                    displayErrorMessage("Cannot parse file correctly, file may misformatted");
                    return;
                }
                book.addAppointment(appointment);
                PrintWriter pw = new PrintWriter(new FileWriter(apptsFile));
                PrettyPrinter prettyPrinter = new PrettyPrinter(pw);
                prettyPrinter.dumpUsingWriter(book);

            } catch (IOException e) {
                displayErrorMessage("Write failed!!\n" + e.getMessage());
                return;
            }

        }



        Toast.makeText(this, "Add Appointment Successfully!\n" + appointment.toString(), Toast.LENGTH_LONG).show();
    }


    private File getFile(String owner) {
        File contextDirectory = getApplicationContext().getDataDir();
        File book = new File(contextDirectory, owner + ".txt");
        return book;
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

    private void displayErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}