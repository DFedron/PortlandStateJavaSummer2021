package edu.pdx.cs410J.huidong;


import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * the TextDumper class for write appointment to a file
 */
public class TextDumper implements AppointmentBookDumper {

    private String fileName;
    private String RealFilePath;
    private PrintWriter pw;
    private Writer writer;

    public TextDumper(Writer writer) {
        this.writer = writer;
    }
    /**
     * This constructor for null param.
     */
    TextDumper(){

    }

    TextDumper(PrintWriter pw){
        this.pw = pw;
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
    public void dump(AbstractAppointmentBook book) throws IOException {
        BufferedWriter bw = new BufferedWriter(this.writer);
        bw.write(book.getOwnerName() + "\n");
        ArrayList<Appointment> arrayList = new ArrayList<>();
        arrayList.addAll(book.getAppointments());
        for(Appointment appointment : arrayList){
            writer.write(appointment.toString() + ".\n");
        }
        writer.flush();
    }

    public void dumpUsingPrintWriter(AppointmentBook book){
        pw.println(book.getOwnerName());
        ArrayList<Appointment> arrayList = new ArrayList<>();
        arrayList.addAll(book.getAppointments());
        for(Appointment appointment : arrayList){
            pw.println(appointment.toString() + ".");
        }
        pw.flush();
    }
}
