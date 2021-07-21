package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project3IT extends InvokeMainTestCase {
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

    @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    void testMissingDescription(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong.txt","huidong", "03/03/2021", "12:00", "am","03/03/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Something wrong with description!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testBeginDateIsMalformatted(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt","huidong", "Test for begin time","03/03/2//1", "12:00", "am","03/03/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testBeginTimeIsMalformatted(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt","huidong", "Test for begin time","03/03/2021", "12:SS", "am","03/03/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testBeginTimePMIsMalformatted(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt","huidong", "Test for begin time","03/03/2021", "12:00", "am", "03/03/2021", "1:00");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }
    @Test
    void testEndDateIsMalformatted(){
        MainMethodResult result = invokeMain(Project3.class,"-textFile","huidong/huidong-x.txt","-pretty","huidong/huidong-x.txt", "huidong", "Test for end time","03/03/2021", "12:00", "am","03/03/2//1", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testEndTimeIsMalformatted(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt", "huidong", "Test for end time","03/03/2021", "12:00", "am","03/03/2021", "1:ss", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testMissingEndTime(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt", "huidong", "Test for missing end time","03/03/2021", "12:00", "am","03/03/2//1", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testUnknownCommandLineOption(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt", "-unknown","huidong", "Test for unknown command line argument","03/03/2021", "12:00", "am","03/03/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Wrong option appeared"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testExtraCommandLineOption(){
        MainMethodResult result = invokeMain(Project3.class,"-pretty","huidong/huidong-x.txt", "-print","huidong", "Test for extra command line argument","03/03/2021", "12:00", "am","03/03/2021", "1:00", "pm", "extra");
        assertThat(result.getTextWrittenToStandardError(), containsString("There are extraneous command line arguments "));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void testPrettyPrint(){
        MainMethodResult result = invokeMain(Project3.class,"-textFile","huidong/huidong.txt","-pretty", "-","huidong", "Test for pretty print option","03/03/2021", "12:00", "am","03/03/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString("Pretty print out the appointment info"));
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    void testPrettyFile() throws IOException {
        MainMethodResult result = invokeMain(Project3.class,"-pretty", "huidong/huidong.txt","huidong", "Test for pretty print file","03/03/2021", "12:00", "am","03/03/2021", "1:00", "pm");
        BufferedReader br = new BufferedReader(new FileReader("huidong/huidong.txt"));
        String s;
        s = br.readLine();
        br.close();
        assertThat(s,containsString("huidong"));
        assertThat(result.getExitCode(), equalTo(0));
    }
}
