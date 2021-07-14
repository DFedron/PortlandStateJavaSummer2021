package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project2 {
    public static void main(String[] args) throws IOException, ParserException {
        /**
         * Create two object, appointment and appointmentBook
         */
        String ownerName = null;
        String Description = null;
        String BeginDate = null;
        String EndDate = null;
        Date BeginTime = null;
        Date EndTime = null;

        /**
         * Check all of the arguments.
         */
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.err.println("Please enter owner, description, begin time, and end time in order. " +
                    "More information please enter java edu.pdx.cs410J.<login-id>.Project1 -README");
        }

        for (String arg : args) {
            boolean flag = true;
            if(arg.contains("-")){
                checkOption(arg);
            } else if (ownerName == null) {
                ownerName = arg;
                TextParser textParser = new TextParser();
                if(!textParser.compare(ownerName)){
                    ownerName = null;
                }
                flag = false;
            } else if (Description == null){
                Description = arg;
                if(!Description.contains(" ")){
                    Description = null;
                }
                flag = false;
            } else if (BeginDate == null) {
                BeginDate = arg;
                flag = false;
            } else if (BeginTime == null){
                String s;
                s = BeginDate + " " + arg;
                DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                try {
                    BeginTime = simpleD.parse(s);
                } catch (ParseException e) {
                    BeginTime = null;
                    //System.out.println("Something is wrong");
                    //e.printStackTrace();
                }
                flag = false;
            } else if (EndDate == null){
                EndDate = arg;
                flag = false;
            } else if (EndTime == null){
                String s;
                s = EndDate + " " + arg;
                DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                try{
                    EndTime = simpleD.parse(s);
                } catch (Exception e) {
                    EndTime = null;
                    //e.printStackTrace();
                }
                flag = false;
            }
            if(flag){
                System.err.println("There are extraneous command line arguments ");
                printErrorMessageAndExit();
            }
        }
        if (ownerName == null){
            System.err.println("Name is incorrect!");
            printErrorMessageAndExit();
        } else if (Description == null){
            System.err.println("Something wrong with description!");
            printErrorMessageAndExit();
        } else if (BeginDate == null || BeginTime == null){
            System.err.println("BeginTime is malformatted!");
            printErrorMessageAndExit();
        } else if (EndDate == null || EndTime == null){
            System.err.println("EndTime is malformatted!");
            printErrorMessageAndExit();
        }


        Appointment appointment = new Appointment(Description,BeginTime,EndTime);
        AppointmentBook appointmentBook = new AppointmentBook(ownerName);
        TextParser textParser = new TextParser();
        TextDumper textDumper = new TextDumper();
//        String s;
//        s = appointmentBook.getOwnerName() + ": " + appointment.toString();
        appointmentBook.addAppointment(appointment);
//        System.out.println(appointmentBook.getAppointments());
        textDumper.dump(appointmentBook);
        textParser.parse();

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
                Class c = Project2.class;
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
            TextParser textParser = new TextParser();
            textParser.parse();
            System.exit(0);
        } else if (option.equals("-textFile")) {
            String textFile = "appointmentbook.txt";
            Class c = Project2.class;
            URL url = c.getResource(textFile);
            System.out.println("Here is the address where to read/write the appointment book");
            System.out.println(url);
            System.exit(0);
        }else{
            System.err.println("Wrong option appeared");
            printErrorMessageAndExit();
        }
    }

}

