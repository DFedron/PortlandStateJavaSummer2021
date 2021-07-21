package edu.pdx.cs410J.huidong;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrettyPrinterTest {
    @Test
    void PrettyPrintDumps() throws IOException {
        String owner = "Owner";
        AppointmentBook book = new AppointmentBook(owner);
        DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);
        try{
            Appointment appointment = new Appointment("test for Pretty dump", simpleD1.parse("06/04/2021"),simpleD2.parse("12:00 am"),simpleD1.parse("6/4/2021"),simpleD2.parse("1:00 pm"));
            book.addAppointment(appointment);
        } catch (ParseException e) {
            System.err.println("text for Pretty dump: parse failed!");
            System.exit(1);
        }
        File file = new File("testForPrettyDump.txt");
        if(file.createNewFile()){
            PrettyPrinter prettyPrinter = new PrettyPrinter("testForPrettyDump.txt");
            prettyPrinter.dump(book);
            BufferedReader br = new BufferedReader(new FileReader("testForPrettyDump.txt"));
            String s;
            s = br.readLine();
            br.close();
            assertThat(s,containsString("Owner"));
        }else{
            BufferedReader br = new BufferedReader(new FileReader("testForPrettyDump.txt"));
            String s;
            s = br.readLine();
            br.close();
            assertThat(s,containsString("Owner"));
        }


    }
}
