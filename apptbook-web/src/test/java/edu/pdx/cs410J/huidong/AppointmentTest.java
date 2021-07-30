package edu.pdx.cs410J.huidong;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    Appointment appointment = new Appointment("not implemented", BeginTime,EndTime);
    assertThat(appointment.getDescription(), containsString("not implemented"));
  }

  @Test
  void forProject4ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }
  @Test
  void TestForGetBeginTimeToString() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "02:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,simpleD1.parse(begingDate), simpleD2.parse(beginTime), simpleD1.parse(endDate), simpleD2.parse(endTime));
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.US);
    Date Adate = simpleD3.parse(begingDate + " " + beginTime);
    assertThat(appointment.getBeginTimeString(), containsString(simpleD3.format(Adate)));
  }
  @Test
  void TestForGetBeginTimeToStringForExist() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "02:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,begingDate + " " + beginTime, endDate + " " + endTime);
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
    Date Adate = simpleD3.parse(begingDate + " " + beginTime);
    assertThat(appointment.getBeginTimeString(), containsString(simpleD3.format(Adate)));
  }

  @Test
  void TestForGetEndTimeToString() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "2:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,simpleD1.parse(begingDate), simpleD2.parse(beginTime), simpleD1.parse(endDate), simpleD2.parse(endTime));
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
    Date Adate = simpleD3.parse(endDate + " " + endTime);
    assertThat(appointment.getEndTimeString(), containsString(simpleD3.format(Adate)));
  }
  @Test
  void TestForGetEndTimeToStringForExist() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "02:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,begingDate + " " + beginTime, endDate + " " + endTime);
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
    Date Adate = simpleD3.parse(endDate + " " + endTime);
    assertThat(appointment.getEndTimeString(), containsString(simpleD3.format(Adate)));
  }


  @Test
  void TestForGetEndTimeToDate() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "2:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,simpleD1.parse(begingDate), simpleD2.parse(beginTime), simpleD1.parse(endDate), simpleD2.parse(endTime));
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
    Date Adate = simpleD3.parse(endDate + " " + endTime);
    assertEquals(appointment.getEndTime(), Adate);
  }

  @Test
  void TestForGetBeginTimeToDate() throws ParseException {
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "2:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);

    Appointment appointment = new Appointment(description,simpleD1.parse(begingDate), simpleD2.parse(beginTime), simpleD1.parse(endDate), simpleD2.parse(endTime));
    DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
    Date Adate = simpleD3.parse(begingDate + " " + beginTime);
    assertEquals(appointment.getBeginTime(), Adate);
  }
}
