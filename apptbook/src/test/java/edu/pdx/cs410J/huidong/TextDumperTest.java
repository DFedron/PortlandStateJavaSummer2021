package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TextDumperTest extends InvokeMainTestCase {
    @Test
    void dumperDumpsAppointmentBookOwner() throws IOException {
        String owner = "Owner";
        AppointmentBook book = new AppointmentBook(owner);
        DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat simpleD2 = new SimpleDateFormat("HH:mm");
       try{
           Appointment appointment = new Appointment("test for dump", simpleD1.parse("06/04/2021"),simpleD2.parse("12:00"),simpleD1.parse("6/4/2021"),simpleD2.parse("13:00"));
           book.addAppointment(appointment);
       } catch (ParseException e) {
           System.err.println("text for dump: parse failed!");
           System.exit(1);
       }
        File file = new File("testForDump.txt");
       if(file.createNewFile()){
           TextDumper textDumper = new TextDumper("testForDump.txt");
           textDumper.dump(book);
           BufferedReader br = new BufferedReader(new FileReader("testForDump.txt"));
           String s;
           s = br.readLine();
           br.close();
           assertThat(s,containsString("Owner"));
       }else{
           BufferedReader br = new BufferedReader(new FileReader("testForDump.txt"));
           String s;
           s = br.readLine();
           br.close();
           assertThat(s,containsString("Owner"));
       }


    }
}
