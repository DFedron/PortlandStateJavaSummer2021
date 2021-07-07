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

    @Override
    public String getOwnerName() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public Collection getAppointments() {
        return appLists;
    }

    @Override
    public void addAppointment(AbstractAppointment abstractAppointment) {
        appLists.add((Appointment) abstractAppointment);
    }
}
