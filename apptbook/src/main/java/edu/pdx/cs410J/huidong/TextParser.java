package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextParser implements AppointmentBookParser{

    private String RealFilePath;

    TextParser(){

    }
    TextParser(String path){
        RealFilePath = path;
    }
    /**
     * This Parser() function can print out the newest content of appointment
     */
    @Override
    public AbstractAppointmentBook parse(){
        String ownerName = null;
        String Description = null;
        String BeginTimeToString = null;
        String EndTimeToString = null;
        Date BeginDate = null;
        Date EndDate = null;
        Date BeginTime = null;
        Date EndTime = null;
        AppointmentBook appointmentBook = null;

        if(RealFilePath == null){
            return null;
        }
        File file = new File(RealFilePath);
        if (!file.exists()){
            System.err.println("Given path wrong!");
            System.exit(1);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(RealFilePath));
            String s = null;
            if ((s = br.readLine()) != null){
                ownerName = s;
                appointmentBook = new AppointmentBook(ownerName);
            }
            while ((s = br.readLine()) != null){

                Description = s.substring(s.indexOf("[")+1, s.indexOf("from")-1);

                DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
                DateFormat simpleD2 = new SimpleDateFormat("HH:mm");
                String s1;
                BeginTimeToString = s.substring(s.indexOf("from")+5, s.indexOf("until")-1);

                s1 = BeginTimeToString.substring(0,10);
                BeginDate = simpleD1.parse(s1);

                s1 = BeginTimeToString.substring(11);
                BeginTime = simpleD2.parse(s1);

                EndTimeToString = s.substring(s.indexOf("until")+6, s.indexOf("]"));
                s1 = EndTimeToString.substring(0,10);
                EndDate = simpleD1.parse(s1);

                s1 = EndTimeToString.substring(11);
                EndTime = simpleD2.parse(s1);

                Appointment appointment = new Appointment(Description,BeginDate,BeginTime,EndDate,EndTime);
                appointmentBook.addAppointment(appointment);
            }
        } catch (IOException | ParseException  | StringIndexOutOfBoundsException e) {
            System.err.println("Malformatted text file!");
            System.exit(1);
        }
        return appointmentBook;
    }



}
