package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * An integration test for {@link Project4} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");


    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line"));
    }

    @Test
    void test4WrongPort() {
        MainMethodResult result = invokeMain( Project4.class, "-host","localhost", "-port","123", "huidong");

        assertThat(result.getTextWrittenToStandardError(), containsString(""));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port",PORT );
        assertThat(result.getTextWrittenToStandardError(), containsString("Name is Missing!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test3EmptyBook() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port",PORT , "weee");
        assertThat(result.getTextWrittenToStandardError(), containsString("Cannot find the book page!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test4MisformatBeginTime() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port",PORT , "we" , "test for misfarmatBeginTime", "06/06/202/", "12:00", "am","06/06/2020", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test4MisformatEndTime() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port",PORT , "we" , "test for misfarmatEndTime", "06/06/2020", "12:00", "am","06/06/202/", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test4CorrectTimeOrder() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port",PORT , "we" , "test for misfarmatTime", "06/06/2020", "12:00", "am","06/05/2020", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("The appointment’s end time is before its starts time"));
        assertThat(result.getExitCode(), equalTo(1));
    }



    @Test
    void test4WrongPortForSearch() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","123" , "we");
        assertThat(result.getTextWrittenToStandardError(), containsString(""));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test4Nobook() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","8080" ,"-search", "huidong" , "06/06/2021", "12:00", "am","06/06/2021", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString(""));
       // assertThat(result.getExitCode(), equalTo(0));
    }
    @Test
    void test4SearchMissingTimeBegin() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","8080" ,"-search", "huidong");
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing Begin Time"));
         assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    void test4SearchMissingTimeEnd() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","8080" ,"-search", "huidong","06/06/2021", "12:00", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString("Time parse failed!"));
        assertThat(result.getExitCode(), equalTo(1));
    }
    @Test
    void test4addANewAppointment() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","8080" , "we" , "test for misfarmatTime", "06/06/2020", "12:00", "am","06/06/2020", "1:00", "pm");
        assertThat(result.getTextWrittenToStandardOut(), containsString(""));
       // assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    void test4WrongPortNotNumer() {
        MainMethodResult result = invokeMain( Project4.class, "-host",HOSTNAME, "-port","asd" , "we" );
        assertThat(result.getTextWrittenToStandardError(), containsString("port must be an integer!"));
         assertThat(result.getExitCode(), equalTo(1));
    }
//    @Test
//    void test3NoDefinitionsThrowsAppointmentBookRestException() {
//        String word = "WORD";
//        try {
//            invokeMain(Project4.class, HOSTNAME, PORT, word);
//            fail("Expected a RestException to be thrown");
//
//        } catch (UncaughtExceptionInMain ex) {
//            RestException cause = (RestException) ex.getCause();
//            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
//        }
//    }


}