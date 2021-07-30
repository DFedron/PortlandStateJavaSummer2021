package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TextParserTest {

    @Test
    void emptyFileCannotBeParsed() {
        InputStream resource = getClass().getResourceAsStream("empty.txt");
        assertNotNull(resource);

        TextParser parser = new TextParser(new InputStreamReader(resource));
     //   System.out.println(resource.toString());
       // assertThat(ParserException.class, parser::parse);
    }

    @Test
    void appointmentBookOwnerCanBeDumpedAndParsedUsingReader() throws IOException, ParserException {
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
        TextParser parser = new TextParser(new StringReader(sw.toString()));
        book = parser.parseUsingReader();

        assertThat(book.getOwnerName(), equalTo(owner));
    }

    @Test
    void appointmentBookOwnerCanBeDumpedAndParsed() throws IOException, ParserException {
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

        TextParser parser = new TextParser(new StringReader(sw.toString()));
        book = (AppointmentBook) parser.parse();

        assertThat(book.getOwnerName(), equalTo(owner));
    }

}
