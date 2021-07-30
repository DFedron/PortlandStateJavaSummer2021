package edu.pdx.cs410J.huidong;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Project4 {
    public static boolean flagForCheckName = false;     //flag for check name
    public static boolean flagForTextFile = false;      //flag for textFile
    public static boolean flagForFileExist = false;     //flag for file exist
    public static boolean flagForPrint = false;         //flag for -print
    public static boolean flagForPort = false;   //flag for -pretty
    public static boolean flagForPrettyPrint = false;         //flag for -pretty print
    public static boolean flagForPrettyFile = false;         //flag for -pretty file
    public static String RealPath = null;               //flag for file path
    public static String DateFormatMach = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d{4})";    //String for compare the give Date.
    public static String DateFormatMach2 = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(\\d{4}) (0?[0-9]|1[012]):(0?[0-9]|[12345][0-9]) (am|AM|pm|PM)";
    public static boolean flagForSearch = false;

    public static void main(String[] args) throws IOException {

        String ownerName = null;
        String fileName = null;
        String host = null;
        String portString = null;
        int port;
        String Description = null;
        Date BeginDate = null;
        Date EndDate = null;
        Date BeginTime = null;
        Date EndTime = null;
        String AM = null;
        String PM = null;
        String EndTimeString = null;
        String BeginTimeString = null;
        String BeginDateString = null;
        String EndDateString = null;

        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            printErrorMessageAndExit();
        }

        for (String arg : args) {
            boolean flag = true;
            if (arg.substring(0, 1).equals("-")) {
                checkOption(arg);
                flag = false;
            } else if (flagForCheckName) {
                if (host == null){
                    host = arg;
                }
                flagForCheckName = false;
                flag = false;
            }else if (flagForPort){
                if(portString == null){
                    portString = arg;
                }
                flagForPort = false;
                flag = false;
            } else if(flagForSearch){


                if (ownerName == null) {
                    ownerName = arg;

                } else if (BeginDate == null) {
                    if (arg.matches(DateFormatMach)) {
                        DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                        try {
                            BeginDate = simpleD.parse(arg);
                            BeginDateString = arg;
                        } catch (ParseException e) {
                            System.err.println("BeginTime is malformatted!");
                            printErrorMessageAndExit();
                        }
                    } else {
                        System.err.println("BeginTime is malformatted!");
                        printErrorMessageAndExit();
                    }
                } else if (BeginTime == null) {
                    DateFormat simpleD = new SimpleDateFormat("HH:mm");
                    try {
                        BeginTimeString = arg;
                        BeginTime = simpleD.parse(arg);

                    } catch (ParseException e) {
                        System.err.println("BeginTime is malformatted!");
                        printErrorMessageAndExit();
                    }

                } else if (AM == null) {
                    AM = arg;

                } else if (EndDate == null) {
                    if (arg.matches(DateFormatMach)) {
                        DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                        try {
                            EndDate = simpleD.parse(arg);
                            EndDateString = arg;
                        } catch (ParseException e) {
                            System.err.println("EndTime is malformatted!");
                            printErrorMessageAndExit();
                        }
                    } else {
                        System.err.println("EndTime is malformatted!");
                        printErrorMessageAndExit();
                    }

                } else if (EndTime == null) {
                    DateFormat simpleD = new SimpleDateFormat("HH:mm");
                    try {
                        EndTime = simpleD.parse(arg);
                        EndTimeString = arg;
                    } catch (Exception e) {
                        System.err.println("EndTime is malformatted!");
                        printErrorMessageAndExit();
                    }

                } else if (PM == null) {
                    PM = arg;

                }
                flag = false;
            } else if (ownerName == null) {
                ownerName = arg;
                flag = false;
            } else if (Description == null) {

                Description = arg;
                if (!Description.contains(" ")) {
                    Description = null;
                }
                flag = false;
            } else if (BeginDate == null) {
                if (arg.matches(DateFormatMach)) {
                    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        BeginDate = simpleD.parse(arg);
                        BeginDateString = arg;
                    } catch (ParseException e) {
                        System.err.println("BeginTime is malformatted!");
                        printErrorMessageAndExit();
                    }
                } else {
                    System.err.println("BeginTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            } else if (BeginTime == null) {
                DateFormat simpleD = new SimpleDateFormat("HH:mm");
                try {
                    BeginTimeString = arg;
                    BeginTime = simpleD.parse(arg);

                } catch (ParseException e) {
                    System.err.println("BeginTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            } else if (AM == null) {
                AM = arg;
                flag = false;
            } else if (EndDate == null) {
                if (arg.matches(DateFormatMach)) {
                    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        EndDate = simpleD.parse(arg);
                        EndDateString = arg;
                    } catch (ParseException e) {
                        System.err.println("EndTime is malformatted!");
                        printErrorMessageAndExit();
                    }
                } else {
                    System.err.println("EndTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            } else if (EndTime == null) {
                DateFormat simpleD = new SimpleDateFormat("HH:mm");
                try {
                    EndTime = simpleD.parse(arg);
                    EndTimeString = arg;
                } catch (Exception e) {
                    System.err.println("EndTime is malformatted!");
                    printErrorMessageAndExit();
                }
                flag = false;
            } else if (PM == null) {
                PM = arg;
                flag = false;
            }
            if (flag) {
                System.err.println("There are extraneous command line arguments ");
                printErrorMessageAndExit();
            }
        }

        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            System.err.println("port must be an integer!");
            printErrorMessageAndExit();
            return;
        }
        if (ownerName == null) {
            System.err.println("Name is Missing!");
            printErrorMessageAndExit();
        }else if (host == null){
            System.err.println("Host name is Missing!");
            printErrorMessageAndExit();
        }else if(portString == null){
            System.err.println("Port is Missing!");
            printErrorMessageAndExit();
        }
        Date start = null;
        Date end = null;
        if(BeginDateString != null){
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
            try {
                start = dateFormat.parse(BeginDateString + " " + BeginTimeString + " " + AM);
                end = dateFormat.parse(EndDateString + " " + EndTimeString + " " + PM);
                if (end.getTime() - start.getTime() < 0) {
                    System.err.println("The appointment’s end time is before its starts time");
                    System.exit(1);
                }
            } catch (ParseException e) {
                System.err.println("Time parse failed!");
                printErrorMessageAndExit();
            }


            DateFormat df = new SimpleDateFormat("hh:mm a", Locale.US);
            try {
                BeginTime = df.parse(BeginTimeString + " " + AM);
                EndTime = df.parse(EndTimeString + " " + PM);
            } catch (ParseException e) {
                System.err.println("Time parse failed!");
                printErrorMessageAndExit();
            }
        }


        AppointmentBookRestClient client = new AppointmentBookRestClient(host, port);


        if(flagForSearch){
            //System.out.println("search flag is on!");
            if (start != null){
                if(end != null){
                    AppointmentBook book = client.getAppointments(ownerName);
                    PrettyPrinter pretty = new PrettyPrinter(new OutputStreamWriter(System.out));
                    pretty.printUsingWriter(book, start, end);
                }else {
                    System.err.println("Missing End Time");
                    printErrorMessageAndExit();
                }

            }else {
                System.err.println("Missing Begin Time");
                printErrorMessageAndExit();
            }
            //flagForSearch = false;
        }
        else if(Description == null){
            AppointmentBook book = client.getAppointments(ownerName);
            if(book == null){
                System.err.println("There is no such appointment book, please create a new one!");
                System.exit(1);
            }
            PrettyPrinter pretty = new PrettyPrinter(new OutputStreamWriter(System.out));
            pretty.dumpUsingWriter(book);
        }else {
            Appointment appointment = new Appointment(Description, BeginDate, BeginTime, EndDate, EndTime);
            client.createAppointment(ownerName,appointment);
            System.out.println("Successfully, Create a new appointment!");

            if (flagForPrint) {
                System.out.println("The appointment info print out");
                System.out.println(appointment);
                flagForPrint = false;
            }
        }

        if (flagForPrint){
            System.out.println("There is no new appointment added!");
        }

        System.exit(0);


    }


    /**
     * print error message and exit
     */
    private static void printErrorMessageAndExit() {
        System.err.println("Please enter -host hostname -port port owner, description, begin time, and end time in order. \n" +
                "There is some options, -README, -print, and -search\n" +
                "More information please enter java edu.pdx.cs410J.<login-id>.Project1 -README");
        System.exit(1);
    }

    /**
     * Check the option given if it's correct.
     *
     * @param option the option the input
     */
    private static void checkOption(String option) {
        if (option.equals("-README")) {
            try {
                Class c = Project4.class;
                InputStream inputStream = c.getResourceAsStream("README.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while (br.ready()) {
                    System.out.println(br.readLine());
                }
                br.close();
            } catch (IOException e) {
                System.err.println("Cannot fine README file!");
                System.exit(1);
            }
            System.exit(0);
        } else if (option.equals("-print")) {
            flagForPrint = true;
        } else if (option.equals("-host")) {
            flagForCheckName = true;

        } else if (option.equals("-port")) {
            flagForPort = true;
        } else if (option.equals("-search")){
            flagForSearch = true;
        } else {
            System.err.println("Wrong option appeared");
            printErrorMessageAndExit();
        }
    }
}



