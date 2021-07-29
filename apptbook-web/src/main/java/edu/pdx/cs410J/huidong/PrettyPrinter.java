package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PrettyPrinter implements AppointmentBookDumper {
    private String RealFilePath;

    private Writer writer;

    PrettyPrinter(Writer writer) {
        this.writer = writer;
    }

    PrettyPrinter(){

    }
    PrettyPrinter(String path){
        RealFilePath = path;
    }

    public void dumpUsingWriter(AppointmentBook book){
        PrintWriter pw = new PrintWriter(this.writer);
        pw.println(book.getOwnerName() + "'s Appointment Book" );
        ArrayList<Appointment> arrayList = new ArrayList<>();
        arrayList.addAll(book.getAppointments());
        for (Appointment appointment : arrayList) {
            pw.println(appointment.toString() + ".");
        }

        pw.flush();
    }

    public void printUsingWriter(AppointmentBook book, Date start, Date end){
        PrintWriter pw = new PrintWriter(this.writer);
        boolean flag = true;
        if (book == null) {
            pw.println("No such appointment book!");

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
                pw.println("There is no match appointment!");
            }
        }
        pw.flush();
    }

    public boolean checkForTime(Date start, Date end, Appointment appointment) {
        if(appointment.getBeginTime().getTime() >= start.getTime()){
            if(appointment.getEndTime().getTime() <= end.getTime()){
                return true;
            }
        }
        return false;
    }
    @Override
    public void dump(AbstractAppointmentBook book){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(RealFilePath));
            bw.write(book.getOwnerName() +"\n");

            ArrayList<Appointment> arrayList = new ArrayList<>();
            arrayList.addAll(book.getAppointments());
            DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
            for (int i = 0; i < book.getAppointments().size(); ++i) {
                bw.write("[" + arrayList.get(i).getDescription() + " from "
                        + simpleD.format(arrayList.get(i).getBeginTime()) + " until " +
                        simpleD.format(arrayList.get(i).getEndTime()) + "]");
                long Duration = arrayList.get(i).getEndTime().getTime() - arrayList.get(i).getBeginTime().getTime();
                Duration = Duration / (1000 * 60);
                bw.write(" Duration: " + Duration + " min" + "\n");
            }

            bw.close();
        }catch (IOException e){
            System.err.println("Read failed!");
            System.exit(1);
        }
    }

    public void print(AbstractAppointmentBook book) throws IOException {
        ArrayList<Appointment> arrayList = new ArrayList<>();
        arrayList.addAll(book.getAppointments());
        DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        System.out.println("Owner Name: " + book.getOwnerName());
        for (int i = 0; i < book.getAppointments().size(); ++i) {
            long Duration = arrayList.get(i).getEndTime().getTime() - arrayList.get(i).getBeginTime().getTime();
            Duration = Duration / (1000 * 60);
            System.out.println(arrayList.get(i).getDescription() + " from "
                    + simpleD.format(arrayList.get(i).getBeginTime()) + " until " +
                    simpleD.format(arrayList.get(i).getEndTime()) +
                    " Duration: " + Duration + " min");
        }
    }


}
