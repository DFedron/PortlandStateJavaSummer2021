package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentBook extends AbstractAppointmentBook {

    private String owner;
    private Appointment appointment;
    ArrayList<AbstractAppointment> appLists = new ArrayList<>();

    public AppointmentBook() {
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
