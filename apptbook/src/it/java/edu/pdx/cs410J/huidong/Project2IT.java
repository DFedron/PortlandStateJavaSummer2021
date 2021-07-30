package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project2IT extends InvokeMainTestCase {
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    void testREADME(){
        MainMethodResult result = invokeMain(Project2.class,"-README");
        assertThat(result.getTextWrittenToStandardOut(), containsString("This is a README file!"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    void testMissingDescription(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt","huidong", "03/03/2021", "12:00", "03/03/2021", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Something wrong with description!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testBeginDateIsMalformatted(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt","huidong", "Test for begin time","03/03/2//1", "12:00", "03/03/2021", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testBeginTimeIsMalformatted(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt","huidong", "Test for begin time","03/03/2021", "12:SS", "03/03/2021", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }
    @Test
    void testEndDateIsMalformatted(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt", "huidong", "Test for end time","03/03/2021", "12:00", "03/03/2//1", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testEndTimeIsMalformatted(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt", "huidong", "Test for end time","03/03/2021", "12:00", "03/03/2021", "13:ss");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testMissingEndTime(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt", "huidong", "Test for missing end time","03/03/2021", "12:00", "03/03/2//1", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testUnknownCommandLineOption(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt", "-unknown","huidong", "Test for unknown command line argument","03/03/2021", "12:00", "03/03/2//1", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("Wrong option appeared"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testExtraCommandLineOption(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong-x.txt", "-print","huidong", "Test for extra command line argument","03/03/2021", "12:00", "03/03/2021", "13:00", "extra");
        assertThat(result.getTextWrittenToStandardError(), containsString("There are extraneous command line arguments "));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testPrintingOutAnAppointment(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong.txt", "-print","huidong", "Test for print option","03/03/2021", "12:00", "03/03/2021", "13:00");
        assertThat(result.getTextWrittenToStandardOut(), containsString("The appointment info print out"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    void testStartingNewAppointmentBookFile(){
        File file = new File("huidong/huidong1.txt");
        file.delete();
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong1.txt", "-print","huidong", "Test for print option","03/03/2021", "12:00", "03/03/2021", "13:00");


        assertThat(result.getTextWrittenToStandardOut(), containsString("Create a new appointment book file, add appointment successfully"));
        assertThat(result.getExitCode(), equalTo(0));
        file.delete();
    }


    @Test
    void testDifferentOwnerName(){
        MainMethodResult result = invokeMain(Project2.class,"-textFile","huidong/huidong.txt", "-print","huidong", "Test for print option","03/03/2021", "12:00", "03/03/2021", "13:00");
        result = invokeMain(Project2.class,"-textFile","huidong/huidong.txt", "-print","Different", "Test for print option","03/03/2021", "12:00", "03/03/2021", "13:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("The given owner name does not match the owner name of the appointment book"));
        assertThat(result.getExitCode(), equalTo(1));

    }
}
