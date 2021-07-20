package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PrettyPrinter implements AppointmentBookDumper {
    private String RealFilePath;

    PrettyPrinter(){

    }
    PrettyPrinter(String path){
        RealFilePath = path;
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
