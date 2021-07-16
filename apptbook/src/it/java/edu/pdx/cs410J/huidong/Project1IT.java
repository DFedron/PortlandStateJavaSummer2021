package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project1} main class.
 */
class Project1IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project1.class, args );
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }
  @Test
  void testMissingDescription(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "03/03/2021", "12:00", "03/03/2021", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("Something wrong with description!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testBeginDateIsMalformatted(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "Test for begin time","03/03/2//1", "12:00", "03/03/2021", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testBeginTimeIsMalformatted(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "Test for begin time","03/03/2021", "12:SS", "03/03/2021", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("BeginTime is malformatted!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testEndDateIsMalformatted(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "Test for end time","03/03/2021", "12:00", "03/03/2//1", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testEndTimeIsMalformatted(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "Test for end time","03/03/2021", "12:00", "03/03/2021", "13:ss");
    assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testMissingEndTime(){
    MainMethodResult result = invokeMain(Project1.class,"huidong", "Test for missing end time","03/03/2021", "12:00", "03/03/2//1", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("EndTime is malformatted!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testUnknownCommandLineOption(){
    MainMethodResult result = invokeMain(Project1.class,"-unknown","huidong", "Test for unknown command line argument","03/03/2021", "12:00", "03/03/2//1", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString("Wrong option appeared"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testExtraCommandLineOption(){
    MainMethodResult result = invokeMain(Project1.class,"-print","huidong", "Test for extra command line argument","03/03/2021", "12:00", "03/03/2021", "13:00", "extra");
    assertThat(result.getTextWrittenToStandardError(), containsString("There are extraneous command line arguments "));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void testPrintingOutAnAppointment(){
    MainMethodResult result = invokeMain(Project1.class,"-print","huidong", "Test for print option","03/03/2021", "12:00", "03/03/2021", "13:00");
    assertThat(result.getTextWrittenToStandardError(), containsString(""));
    assertThat(result.getExitCode(), equalTo(0));
  }


}