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

    /**
     * reture the owner name of the appointment book;
     * @return
     * @throws IOException
     */
    public String owner() throws IOException {
        AppointmentBook appointmentBook = new AppointmentBook();
        Class c = TextParser.class;
        InputStream is = c.getResourceAsStream("appointmentBook.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s = null;

        if (!br.ready()) {
            s = br.readLine();
        }
        if(s == null){
            return null;
        }
        return s;
    }

    /**
     * Check the owner name given if it matches the owner of appointment book name;
     * @param name
     * @return
     * @throws IOException
     */
    public boolean compare(String name) throws IOException {
        if(name != owner()){
            return true;
        }else {
            return false;
        }
    }

}
