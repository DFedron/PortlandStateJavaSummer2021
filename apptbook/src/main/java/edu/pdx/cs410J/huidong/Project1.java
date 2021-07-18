package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

  public static boolean flag = false;
  public static String DateFormatMach = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d{4})";
  public static void main(String[] args) throws IOException, ParserException {

      String ownerName = null;
      String Description = null;
      Date BeginDate = null;
      Date EndDate = null;
      Date BeginTime = null;
      Date EndTime = null;

      /**
       * Check all of the arguments.
       */
      if (args.length == 0) {
          System.err.println("Missing command line arguments");
          printErrorMessageAndExit();
      }

      for (String arg : args) {
          boolean flagForExtraCommandLine = true;
          if(arg.contains("-")){
              checkOption(arg);
              flagForExtraCommandLine = false;
          } else if (ownerName == null) {

              ownerName = arg;
              flagForExtraCommandLine = false;
          } else if (Description == null){
              Description = arg;
              if(!Description.contains(" ")){
                  Description = null;
              }
              flagForExtraCommandLine = false;
          } else if (BeginDate == null) {
              if (arg.matches(DateFormatMach)) {
                  DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                  try {

                      BeginDate = simpleD.parse(arg);

                  } catch (ParseException e) {
                      System.err.println("BeginTime is malformatted!");
                      printErrorMessageAndExit();
                  }

              }else {
                  System.err.println("BeginTime is malformatted!");
                  printErrorMessageAndExit();
              }
              flagForExtraCommandLine = false;
          } else if (BeginTime == null){
              DateFormat simpleD = new SimpleDateFormat("HH:mm");
              simpleD.setLenient(false);
              try {
                  BeginTime = simpleD.parse(arg);
              } catch (ParseException e) {
                  System.err.println("BeginTime is malformatted!");
                  printErrorMessageAndExit();
              }
              flagForExtraCommandLine = false;
          } else if (EndDate == null){
              if (arg.matches(DateFormatMach)) {
                  DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                  try {
                      EndDate = simpleD.parse(arg);
                  } catch (ParseException e) {
                      System.err.println("EndTime is malformatted!");
                      printErrorMessageAndExit();
                  }
              }else{
                  System.err.println("EndTime is malformatted!");
                  printErrorMessageAndExit();
              }
              flagForExtraCommandLine = false;
          } else if (EndTime == null){
              DateFormat simpleD = new SimpleDateFormat("HH:mm");
              simpleD.setLenient(false);
              try{
                  EndTime = simpleD.parse(arg);
              } catch (ParseException e) {
                  System.err.println("EndTime is malformatted!");
                  printErrorMessageAndExit();
                  //e.printStackTrace();
              }
              flagForExtraCommandLine = false;
          }
          if(flagForExtraCommandLine){
              System.err.println("There are extraneous command line arguments ");
              printErrorMessageAndExit();
          }
      }
      if (ownerName == null){
          System.err.println("Name is missing!");
          printErrorMessageAndExit();
      } else if (Description == null){
          System.err.println("Something wrong with description!\n" +
                  " Please check your description. The description should be a complete sentence");
          printErrorMessageAndExit();
      } else if (BeginDate == null || BeginTime == null){
          System.err.println("BeginTime is malformatted!");
          printErrorMessageAndExit();
      } else if (EndDate == null || EndTime == null){
          System.err.println("EndTime is malformatted!");
          printErrorMessageAndExit();
      }

      Appointment appointment = new Appointment(Description,BeginDate,BeginTime,EndDate,EndTime);
      AppointmentBook appointmentBook = new AppointmentBook(ownerName);
      appointmentBook.addAppointment(appointment);
      System.out.println("Add appointment successfully!");
      if(flag){
          System.out.println("Appointment: ");
          System.out.println(appointmentBook.getAppointments());
      }
//      String s;
//      s = appointmentBook.getOwnerName() + ": " + appointment.toString();
//      System.out.println(s);
//      System.out.println(appointmentBook.getAppointments());


      System.exit(0);
  }

    /**
     * print the error message and exit;
     */
    private static void printErrorMessageAndExit(){
        System.err.println("Please enter owner, description, begin time, and end time in order. \n" +
                "There is only three options, -README, -print, and -textFile file\n" +
                "More information please enter java edu.pdx.cs410J.<login-id>.Project1 -README");
        System.exit(1);
    }

    /**
     * Check the option given if it's correct.
     * @param option
     * @throws ParserException
     */
    private static void checkOption(String option) throws ParserException {
        if (option.equals("-README")) {
            try {
                Class c = Project1.class;
                InputStream inputStream = c.getResourceAsStream("README.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while (br.ready()) {
                    System.out.println(br.readLine());
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
            System.exit(0);
        } else if (option.equals("-print")) {
            flag = true;
            return;
        } else{
            System.err.println("Wrong option appeared");
            printErrorMessageAndExit();
        }
    }
}