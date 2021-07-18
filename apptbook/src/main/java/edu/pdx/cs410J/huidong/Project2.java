package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J appointment book Project2
 */

public class Project2 {
    public static boolean flagForCheckName = false;     //flag for check name
    public static boolean flagForTextFile = false;      //flag for textFile
    public static boolean flagForFileExist = false;     //flag for file exist
    public static boolean flagForPrint = false;         //flag for -print
    public static String RealPath = null;               //flag for file path
    public static String DateFormatMach = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d{4})";    //String for compare the give Date.

    public static void main(String[] args) throws IOException, ParserException {

//        Project2 project2 = new Project2();
//        project2.getCurrentFilePath();

        String ownerName = null;
        String fileName = null;
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
            boolean flag = true;

            if(arg.substring(0,1).equals("-")){

                checkOption(arg);
                flag = false;
            }else if (flagForCheckName){
                if(fileName == null){
                    fileName = arg;
                    flagForCheckName = false;
                    flagForTextFile = true;
                }
                flag = false;
            } else if (ownerName == null) {

                ownerName = arg;
//                TextParser textParser = new TextParser();
//                if(!textParser.compare(ownerName)){
//                    ownerName = null;
//                }
                flag = false;
            } else if (Description == null){

                Description = arg;
                if(!Description.contains(" ")){
                    Description = null;
                }
                flag = false;
            } else if (BeginDate == null) {
                if(arg.matches(DateFormatMach)){
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
                flag = false;
            } else if (BeginTime == null){
                DateFormat simpleD = new SimpleDateFormat("HH:mm");
                try {
                    BeginTime = simpleD.parse(arg);
                } catch (ParseException e) {
                    System.err.println("BeginTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            } else if (EndDate == null){
                if(arg.matches(DateFormatMach)){
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
                flag = false;
            } else if (EndTime == null){
                DateFormat simpleD = new SimpleDateFormat("HH:mm");
                try{
                    EndTime = simpleD.parse(arg);
                } catch (Exception e) {
                    System.err.println("EndTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            }
            if(flag){
                System.err.println("There are extraneous command line arguments ");
                printErrorMessageAndExit();
            }
        }
        if (ownerName == null){
            System.err.println("Name is Missing!");
            printErrorMessageAndExit();
        } else if (Description == null){
            System.err.println("Something wrong with description! Please check your description. The description should be a complete sentence");
            printErrorMessageAndExit();
        } else if (BeginDate == null || BeginTime == null){
            System.err.println("BeginTime is malformatted!");
            printErrorMessageAndExit();
        } else if (EndDate == null || EndTime == null){
            System.err.println("EndTime is malformatted!");
            printErrorMessageAndExit();
        }

        Appointment appointment = new Appointment(Description,BeginDate,BeginTime,EndDate,EndTime);
        if(flagForTextFile){
            Project2 project2 = new Project2();
            project2.constructPath(fileName,ownerName);
            TextDumper textDumper = new TextDumper(RealPath);
            TextParser textParser = new TextParser(RealPath);
            if (flagForFileExist){
                AppointmentBook appointmentBook = (AppointmentBook) textParser.parse();
                appointmentBook.addAppointment(appointment);
                textDumper.dump(appointmentBook);
            }else {
                AppointmentBook appointmentBook = new AppointmentBook(ownerName);
                appointmentBook.addAppointment(appointment);
                textDumper.dump(appointmentBook);
            }
        }
        if(flagForPrint){
            System.out.println(appointment.toString());
        }
//        AppointmentBook appointmentBook = new AppointmentBook(ownerName);
//        appointmentBook.addAppointment(appointment);

//        System.out.println("Path for test: " + RealPath);
//        System.out.println("Name: " + ownerName);
//        System.out.println("Appointment for test: " + appointment);
        System.exit(0);
    }

    /**
     *
     * @param fileName The file name
     * @param name      the owner name
     * @throws IOException  throw IO exception
     * @throws ParserException  throw parse exception
     */
    public void constructPath(String fileName, String name) throws IOException, ParserException {
        String dir = null;
        File f1 = new File(this.getClass().getResource("").getPath());
        dir = String.valueOf(f1);
        RealPath = dir + File.separator + fileName;

//        System.out.println("Path is: " + RealPath);

        File file = new File(RealPath);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()){
            fileParent.mkdir();
        }
        if(file.createNewFile())  {
            System.out.println("Create a new appointment book file, add appointment successfully");
        }else {
            TextParser textParser = new TextParser(RealPath);
            String ownerName = textParser.parse().getOwnerName();
            if(!name.equals(ownerName)){
                System.err.println("The given owner name does not match the owner name of the appointment book");
                System.err.println("Given name: " + name);
                System.err.println("The owner name of the appointment book: " + ownerName);
//                System.out.println("The given name is: " + name);
//                System.out.println("The appointment book name is: " + ownerName);
                printErrorMessageAndExit();
            }
            System.out.println("The given appointment book file exists");
            flagForFileExist = true;
        }

    }

    /**
     * print error message and exit
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
            flagForPrint = true;
        } else if (option.equals("-textFile")) {
            flagForCheckName = true;

        }else{
            System.err.println("Wrong option appeared");
            printErrorMessageAndExit();
        }
    }



}

