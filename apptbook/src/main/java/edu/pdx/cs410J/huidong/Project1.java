package edu.pdx.cs410J.huidong;

import java.io.*;
import java.net.URL;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

  public static void main(String[] args) throws IOException {
      /**
       * Create two object, appointment and appointmentBook
       */
    Appointment appointment = new Appointment();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    AppointmentBook appointmentBook = new AppointmentBook();

      /**
       * Check all of the arguments.
       */
    if(args.length == 0){
      System.err.println("Missing command line arguments");
      System.err.println("Please enter owner, description, begin time, and end time in order. " +
              "More information please enter java edu.pdx.cs410J.<login-id>.Project1 -README");
    }
    else if (args[0].equals("-README")){
        String path = "src/main/java/edu/pdx/cs410J/huidong/README.txt";
        Class c = Project1.class;
        URL url = c.getResource(path);
        System.out.println(url);

        try{
            InputStream is = c.getResourceAsStream("README.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //String s;
            while(br.ready()){
                System.out.println(br.readLine());
            }
         //   br.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }




/*        String path = "edu/pdx/cs410J/huidong/README.txt";
        Class c = Project1.class;
        URL url = c.getResource(path);
        System.out.println(url);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("apptbook\\src\\main\\java\\edu\\pdx\\cs410J\\huidong\\README.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s;
        while((s = br.readLine()) != null){
            System.out.println(s);
        }
        br.close();

 */


 /*       System.out.println("This is a project that can create an appointment and add to appointment book" +
              "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n" +
              "args are (in this order):\n" +
              "owner            The person who owns the appt book\n" +
              "description      A description of the appointment\n" +
              "beginTime        When the appt begins (24-hour time)\n" +
              "endTime          When the appt ends (24-hour time)\n" +
              "options are (options may appear in any order):\n" +
              "-print           Prints a description of the new appointment\n" +
              "-README          Prints a README for this project and exits\n" +
              "Date and time should be in the format: mm/dd/yyyy hh:mm");

  */


    }
    else if (args.length == 6) {
        appointmentBook.setOwner(args[0]);
        appointment.setDescription(args[1]);
        appointment.setBeginTime(args[2]+" "+args[3]);
        appointment.setEndTime(args[4]+" "+args[5]);
        appointmentBook.addAppointment(appointment);
        System.out.println(appointmentBook.toString());
        System.out.println("Owner is " + appointmentBook.getOwnerName());
        System.out.println(appointment.toString());

    }
    else if (args.length == 7){
      appointmentBook.setOwner(args[1]);
      appointment.setDescription(args[2]);
      appointment.setBeginTime(args[3]+" "+args[4]);
      appointment.setEndTime(args[5]+" "+args[6]);
      if(args[0].equals("-print"))
        System.out.println(appointment.toString());
    }
    else{
      System.err.println("The input format is incorrect. " +
              "Please enter owner, description, begin time, and end time in order. " +
              "More information please enter java edu.pdx.cs410J.<login-id>.Project1 -README");
    }
 /*  for (String arg : args) {
      System.out.println(arg);
    }

  */
    System.exit(1);
  }

}