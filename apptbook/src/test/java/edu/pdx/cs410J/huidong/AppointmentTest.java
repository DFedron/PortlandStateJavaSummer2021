package edu.pdx.cs410J.huidong;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Appointment} class.
 *
 * You'll need to update these unit tests as you build out your program.
 */
public class AppointmentTest {

  @Test
  void getBeginTimeStringNeedsToBeImplemented() throws ParseException {
//    String s = "03/03/2021 12:00";
//    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//    Date BeginTime = simpleD.parse(s);
//    System.out.println("BeginTime: " + BeginTime);
//    s = "04/04/2021 16:00";
//    Date EndTime = simpleD.parse(s);
//    Appointment appointment = new Appointment("Date with Lisa", BeginTime,EndTime);
    Appointment appointment = new Appointment();
    assertThrows(NullPointerException.class, appointment::getBeginTimeString);
  }
@Test
void getEndTimeStringNeedsToBeImplemented() throws ParseException {
    Appointment appointment = new Appointment();
    assertThrows(NullPointerException.class, appointment::getEndTimeString);
}
  @Test
  void initiallyAllAppointmentsHaveTheSameDescription() throws ParseException {
    String s = "03/03/2021 12:00";
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    Date BeginTime = simpleD.parse(s);
    System.out.println("BeginTime: " + BeginTime);
    s = "04/04/2021 16:00";
    Date EndTime = simpleD.parse(s);
    Appointment appointment = new Appointment("Date with Lisa", BeginTime,EndTime);
    assertThat(appointment.getDescription(), containsString("Date with Lisa"));
  }

  @Test
  void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

}
