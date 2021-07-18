package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * the TextDumper class for write appointment to a file
 */
public class TextDumper implements AppointmentBookDumper {

    private String fileName = null;
    private String RealFilePath = null;
    /**
     * This constructor for null param.
     */
    TextDumper(){

    }


    /**
     * This constructor can store the file name given.
     * @param path  The file path
     */
    TextDumper(String path){
        RealFilePath = path;
    }
    /**
     * This dump() function will store the appointment to appointment book;
     * @param book  the appointment that need to be dump
     */

    @Override
    public void dump(AbstractAppointmentBook book){


        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(RealFilePath));
            bw.write(book.getOwnerName() +"\n");

            ArrayList<Appointment> arrayList = new ArrayList<>();
            arrayList.addAll(book.getAppointments());
            for (int i = 0; i < book.getAppointments().size(); ++i) {
                bw.write("[" +arrayList.get(i) + "]"+ "\n");
            }

            bw.close();
        }catch (IOException e){
            System.err.println("Read failed!");
            System.exit(1);
        }


    }
}
