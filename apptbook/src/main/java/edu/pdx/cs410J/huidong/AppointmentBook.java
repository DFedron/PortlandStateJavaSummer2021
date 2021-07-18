package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is appointmentBook class for store appointment and the owner name
 */

public class AppointmentBook extends AbstractAppointmentBook {

    private String owner;   //owner name
    private Appointment appointment;    //appointment
    /**
     * A arrayList for store appointment
     */
    ArrayList<Appointment> appLists = new ArrayList<>();

    /**
     * A constructor of appointmentBook for null param
     */
    public AppointmentBook() {
    }

    /**
     * A constructor of appointmentBook for ownerName
     * @param ownerName     The ownerName
     */
    public AppointmentBook(String ownerName){
        owner = ownerName;
    }

    /**
     * Get the Owner name
     * @return The owner name
     */
    @Override
    public String getOwnerName() {
        return owner;
    }

    /**
     * Set the name of the owner of appointment book
     *
     * @param owner The owner of appointment book
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Get current appointment
     *
     * @return appLists(appointment list)
     */
    @Override
    public Collection getAppointments() {
        return appLists;
    }

    /**
     * Add an appointment to appList(appointment List)
     *
     * @param abstractAppointment an appointment class
     */
    @Override
    public void addAppointment(AbstractAppointment abstractAppointment) {
        appLists.add((Appointment) abstractAppointment);
    }
}
