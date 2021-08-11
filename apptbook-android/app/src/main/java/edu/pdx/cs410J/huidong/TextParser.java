package edu.pdx.cs410J.huidong;


import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * TextParser class for read file
 */
public class TextParser implements AppointmentBookParser{

    private String RealFilePath;
    private Reader reader;

    public TextParser(Reader reader) {
        this.reader = reader;
    }
    /**
     * This constructor for null param.
     */
    TextParser(){

    }

    /**
     * This constructor can store path.
     * @param path the file path
     */
    TextParser(String path){
        RealFilePath = path;
    }
    /**
     * This Parser() function can print out the newest content of appointment
     */
    @Override
    public AbstractAppointmentBook parse(){
        AppointmentBook appointmentBook = null;
        BufferedReader br = new BufferedReader(this.reader);

        return appointmentBook;

    }

    public AppointmentBook parseUsingReader(){
        String Description;
        String BeginTimeToString;
        String EndTimeToString;
        Date BeginDate;
        Date EndDate;
        Date BeginTime;
        Date EndTime;
        AppointmentBook book = null;
        BufferedReader br = new BufferedReader(this.reader);
        try {
            String owner = br.readLine();
            book = new AppointmentBook(owner);
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                Description = line.substring(0, line.indexOf("from")-1);

                DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
                DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);
                String s1;
                BeginTimeToString = line.substring(line.indexOf("from")+5, line.indexOf("until")-1);

                s1 = BeginTimeToString.substring(0,10);
                BeginDate = simpleD1.parse(s1);

                s1 = BeginTimeToString.substring(11);
                BeginTime = simpleD2.parse(s1);

                EndTimeToString = line.substring(line.indexOf("until")+6, line.indexOf("."));
                s1 = EndTimeToString.substring(0,10);
                EndDate = simpleD1.parse(s1);

                s1 = EndTimeToString.substring(11);
                EndTime = simpleD2.parse(s1);

                Appointment appointment = new Appointment(Description,BeginDate,BeginTime,EndDate,EndTime);
                book.addAppointment(appointment);
            }



        } catch (IOException  | ParseException e) {
            return null;
        }
        return book;
    }


}
