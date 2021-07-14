package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextParser implements AppointmentBookParser{
    /**
     * This Parser() function can print out the newest content of appointment
     */
    @Override
    public AbstractAppointmentBook parse() throws ParserException{
        AppointmentBook appointmentBook = new AppointmentBook();
        Class c = TextParser.class;
        InputStream is = c.getResourceAsStream("appointmentBook.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = null;
        while(true){
            try {
                if (!br.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(s == null){
            System.out.println("There is no appointment in appointment book");
        }
        System.out.println(s);

        return appointmentBook;
    }
}
