package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment {
  private Date BeginTime;
  private Date EndTime;
  private String Description;

  public void setBeginTime(String beginTime) {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      BeginTime = simpleD.parse(beginTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void setDescription(String description) {
    Description = description;
  }

  public void setEndTime(String endTime) {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      EndTime = simpleD.parse(endTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getBeginTimeString() {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    return simpleD.format(BeginTime);
  //  throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    return simpleD.format(EndTime);
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDescription() {
    return Description;
    //"This method is not implemented yet";
  }
}
