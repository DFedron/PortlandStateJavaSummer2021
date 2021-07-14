package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.net.URL;

public class TextDumper implements AppointmentBookDumper {
    /**
     * This Dumper() function will store the appointment to appointment book;
     * @param book
     * @throws IOException
     */

    @Override
    public void dump(AbstractAppointmentBook book) throws IOException {
        String path = "appointmentBook.txt";
        Class c = TextDumper.class;
        URL url = c.getResource(path);
//        System.out.println("TextDumper: " + url);

        String Realpath = String.valueOf(url);
        Realpath = Realpath.replaceAll("file:", "");

        if(Realpath == null){
            Realpath = "src/main/java/edu/pdx/cs410J/huidong/appointmentBook.txt";
        }
//        System.out.println(Realpath);
        String s = null;

        BufferedWriter bw = new BufferedWriter(new FileWriter(Realpath, true));
        bw.write(book.getOwnerName() +": "+ book.getAppointments() + "\n");
        bw.close();
        return;
    }
}
