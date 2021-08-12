package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class PrettyPrinter implements AppointmentBookDumper {
    private String RealFilePath;
    private PrintWriter pw;
    private Writer writer;

    PrettyPrinter(PrintWriter writer) {
        this.pw = writer;
    }
    PrettyPrinter(Writer writer) {
        this.writer = writer;
    }

    PrettyPrinter(){

    }
    PrettyPrinter(String path){
        RealFilePath = path;
    }

    public void dumpUsingWriter(AppointmentBook book){
        Collection<Object> strings = Collections.singleton(new ArrayList<String>());

        //PrintWriter pw = new PrintWriter(this.writer);
        pw.println(book.getOwnerName());
        ArrayList<Appointment> arrayList = new ArrayList<>();
        arrayList.addAll(book.getAppointments());
        for (Appointment appointment : arrayList) {
            pw.println(appointment.toString() + ".");
        }

        pw.flush();
    }

    public boolean printUsingWriter(AppointmentBook book, Date start, Date end){
        //PrintWriter pw = new PrintWriter(this.writer);
        boolean flag = true;
        if (book == null) {
           return false;
        } else {
            ArrayList<Appointment> arrayList = new ArrayList<>();
            arrayList.addAll(book.getAppointments());
            for(Appointment appointment : arrayList){
                if(checkForTime(start, end, appointment)){
                    pw.println(appointment.toString());
                    flag = false;
                }
            }
            if(flag){
                return false;
            }
        }
        pw.flush();
        return true;
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
    @Override
    public void dump(AbstractAppointmentBook book){
        PrintWriter pw = new PrintWriter(this.writer);
        pw.println("Doesn't match this project");
        pw.close();
    }

    public void print(AbstractAppointmentBook book) throws IOException {
        PrintWriter pw = new PrintWriter(this.writer);
        pw.println("Doesn't match this project");
        pw.close();
    }

}
