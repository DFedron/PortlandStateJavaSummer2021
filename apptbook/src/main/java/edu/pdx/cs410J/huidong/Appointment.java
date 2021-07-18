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
  private Date BeginDate;
  private Date EndDate;
  private String Description;
  private String BeginTimeToString;
  private String EndTimeToString;
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
  public Appointment(String description, String beginTimeToString, String endTimeToString){
    Description = description;
    BeginTimeToString = beginTimeToString;
    EndTimeToString = endTimeToString;
  }

  /**
   * This constructor pass five parameters, description, beginDate, beginTime, endDate, and endTime
   * @param description
   * @param beginDate
   * @param beginTime
   * @param endDate
   * @param endTime
   */
  public Appointment(String description, Date beginDate, Date beginTime, Date endDate, Date endTime){
    Description = description;
    BeginDate =  beginDate;
    BeginTime = beginTime;
    EndDate = endDate;
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
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("HH:mm");
    String s = String.valueOf(BeginDate) +" " + String.valueOf(BeginTime);

    return simpleD1.format(BeginDate) + " " + simpleD2.format(BeginTime);
  //  throw new UnsupportedOperationException("This method is not implemented yet");
  }


  /**
   * Get the string which is the string form of the end time.
   * @return the string form of the end time.
   */
  @Override
  public String getEndTimeString() {
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("HH:mm");
    return simpleD1.format(EndDate) +" " + simpleD2.format(EndTime);
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
