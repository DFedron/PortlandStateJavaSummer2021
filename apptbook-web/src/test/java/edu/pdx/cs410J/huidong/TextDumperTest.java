package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest extends InvokeMainTestCase {
    @Test
    void dumperDumpsAppointmentBookOwner() throws IOException {
        String owner = "huidong";
        String description = "TEST for description";
        String beginTime = "06/03/2020 12:59 am";
        String endTime = "06/03/2020 02:59 pm";
        AppointmentBook book = new AppointmentBook(owner);
        Appointment appointment = new Appointment(description,beginTime,endTime);
        book.addAppointment(appointment);
        StringWriter sw = new StringWriter();
        TextDumper dumper = new TextDumper(sw);
        dumper.dump(book);
        //String s = owner + "\n"  +appointment.toString();
        assertThat(sw.toString(), containsString(appointment.toString()));

    }

    @Test
    void dumperUsingWriterDumpsAppointmentBookOwner() throws IOException {
        String owner = "huidong";
        String description = "TEST for description";
        String beginTime = "06/03/2020 12:59 am";
        String endTime = "06/03/2020 02:59 pm";
        AppointmentBook book = new AppointmentBook(owner);
        Appointment appointment = new Appointment(description,beginTime,endTime);
        book.addAppointment(appointment);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        TextDumper dumper = new TextDumper(printWriter);
        dumper.dumpUsingPrintWriter(book);
        //String s = owner + "\n"  +appointment.toString();
        assertThat(stringWriter.toString(), containsString(appointment.toString()));

    }
}
