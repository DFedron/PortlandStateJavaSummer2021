package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class TextDumper implements AppointmentBookDumper {

    private String fileName = null;
    private String RealFilePath = null;
    TextDumper(){

    }
    TextDumper(StringWriter sw){

    }

    /**
     * This constructor can store the file name given.
     * @param path
     */
    TextDumper(String path){
        RealFilePath = path;
    }
    /**
     * This dump() function will store the appointment to appointment book;
     * @param book
     * @throws IOException
     */

    @Override
    public void dump(AbstractAppointmentBook book){
//        String dir = System.getProperty("user.dir");
//        String dir = null;
//        File f1 = new File(this.getClass().getResource("").getPath());
//        dir = String.valueOf(f1);
//        String path = dir + File.separator + fileName;
//        System.out.println("Path is: " + path);
//
//        File file = new File(path);
//        File fileParent = file.getParentFile();
//        if (!fileParent.exists()){
//            fileParent.mkdir();
//        }
//        if(file.createNewFile()){
//            System.out.println("Create a new file!");
//        if (RealFilePath == null){
//            System.err.println("Given path wrong!");
//            System.exit(1);
//        }

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

//        }else {
//            System.out.println("Write to the exited file");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
//            bw.write(book.getOwnerName() +"\n");
//
//            ArrayList<Appointment> arrayList = new ArrayList<>();
//            arrayList.addAll(book.getAppointments());
//            for (int i = 0; i < book.getAppointments().size(); ++i) {
//                bw.write("[" + arrayList.get(i) + "]" +"\n");
//            }
//
//            bw.close();
//        }
//
//        RealFilePath = path;
        return;
    }
}
