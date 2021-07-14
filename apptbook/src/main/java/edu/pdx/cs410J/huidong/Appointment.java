package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;
import jdk.jfr.Description;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Appointment extends AbstractAppointment {

  private Date BeginTime;
  private Date EndTime;
  private String Description;
  public Appointment(){

  }

  /**
   * This constructor pass three parameters, description, beginTime, and endTime
   * @param description
   * @param beginTime
   * @param endTime
   */
  public Appointment(String description, Date beginTime, Date endTime){
    Description = description;
    BeginTime = beginTime;
    EndTime = endTime;
  }

  /**
   * Set the begin time of appointment
   *
   * @param beginTime the begin time of appointment
   */

  public void setBeginTime(String beginTime) {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      BeginTime = simpleD.parse(beginTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Set the description of the appointment
   *
   * @param description the description of the Appointment
   */
  public void setDescription(String description) {
    Description = description;
  }

  /**
   * Set the end time of appointment
   *
   * @param endTime the end of appointment
   */
  public void setEndTime(String endTime) {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      EndTime = simpleD.parse(endTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the string which is the string form of the begin time.
   * @return the string form of the begin time.
   */
  @Override
  public String getBeginTimeString() {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    return simpleD.format(BeginTime);
  //  throw new UnsupportedOperationException("This method is not implemented yet");
  }
  /**
   * Get the string which is the string form of the end time.
   * @return the string form of the end time.
   */
  @Override
  public String getEndTimeString() {
    DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    return simpleD.format(EndTime);
    // throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * Get the description
   * @return the description
   */
  @Override
  public String getDescription() {
    return Description;
    //"This method is not implemented yet";
  }
}
