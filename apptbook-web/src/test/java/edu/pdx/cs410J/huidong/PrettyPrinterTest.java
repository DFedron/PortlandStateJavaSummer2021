package edu.pdx.cs410J.huidong;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrettyPrinterTest {



    @Test
    void TestPrettyPrintUsingWriterForNullBook() throws ParseException {
        DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date beginTime = simpleD3.parse("06/03/2020 12:59 am");
        Date endTime = simpleD3.parse("06/03/2020 02:59 am");
        AppointmentBook  book = new AppointmentBook();
        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.printUsingWriter(null, beginTime,endTime );
        sw.flush();
        String Text = sw.toString();
        assertThat(Text, containsString("No such appointment book!"));
    }

    @Test
    void TestPrettyPrintUsingWriterForNotMatchBook() throws ParseException {
        DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date beginTime = simpleD3.parse("06/03/2020 12:59 am");
        Date endTime = simpleD3.parse("06/03/2020 02:59 am");
        AppointmentBook  book = new AppointmentBook();
        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.printUsingWriter(book, beginTime,endTime );
        sw.flush();
        String Text = sw.toString();
        assertThat(Text, containsString("There is no match appointment!"));
    }

    @Test
    void TestPrettyPrintUsingWriter() throws ParseException {
        String owner = "huidong";
        String description = "TEST for description";
        DateFormat simpleD3 = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date beginTime = simpleD3.parse("06/03/2020 12:59 am");
        Date endTime = simpleD3.parse("06/03/2020 02:59 am");
        Appointment appointment = new Appointment(description, "06/03/2020 12:59 am","06/03/2020 02:59 am");
        AppointmentBook  book = new AppointmentBook(owner);
        book.addAppointment(appointment);
        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.printUsingWriter(book, beginTime,endTime );
        sw.flush();
        String Text = sw.toString();
        assertThat(Text, containsString(appointment.toString()));


    }


    @Test
    void TestDumpUsingWriter(){
        String owner = "huidong";
        String description = "TEST for description";
        String beginTime = "06/03/2020 12:59 am";
        String endTime = "06/03/2020 02:59 pm";
        Appointment appointment = new Appointment(description,beginTime,endTime);
        AppointmentBook appointmentBook = new AppointmentBook(owner);
        appointmentBook.addAppointment(appointment);

        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.dumpUsingWriter(appointmentBook);
        sw.flush();
        String dumpedText = sw.toString();
        assertThat(dumpedText, containsString(owner));
    }
    @Test
    void PrettyPrintDumps() throws IOException {
        AppointmentBook appointmentBook = new AppointmentBook();

        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.dump(appointmentBook);
        sw.flush();
        String dumpedText = sw.toString();
        assertThat(dumpedText, containsString("Doesn't match this project"));

    }

    @Test
    void PrettyPrintPrint() throws IOException {
        AppointmentBook appointmentBook = new AppointmentBook();

        StringWriter sw = new StringWriter();
        PrettyPrinter prettyPrinter = new PrettyPrinter(sw);
        prettyPrinter.print(appointmentBook);
        sw.flush();
        String dumpedText = sw.toString();
        assertThat(dumpedText, containsString("Doesn't match this project"));

    }
}
