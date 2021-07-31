package edu.pdx.cs410J.huidong;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test that tests the REST calls made by {@link AppointmentBookRestClient}
 */
@TestMethodOrder(MethodName.class)
class AppointmentBookRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private AppointmentBookRestClient newAppointmentBookRestClient() {
    int port = Integer.parseInt(PORT);
    return new AppointmentBookRestClient(HOSTNAME, port);
  }

  @Test
  void testGetAppointment() throws IOException, ParseException {
    AppointmentBookRestClient client = newAppointmentBookRestClient();
    String owner = "huidong";
    String description = "TEST for description";
    String begingDate = "06/03/2020";
    String beginTime = "12:59 pm";
    String endDate = "06/03/2020";
    String endTime = "02:59 pm";
    DateFormat simpleD1 = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat simpleD2 = new SimpleDateFormat("hh:mm a", Locale.US);
    AppointmentBook book1 = new AppointmentBook(owner);
    Appointment appointment = new Appointment(description,simpleD1.parse(begingDate), simpleD2.parse(beginTime), simpleD1.parse(endDate), simpleD2.parse(endTime));
    book1.addAppointment(appointment);
    client.createAppointment(owner,appointment);
    AppointmentBook book2 = client.getAppointments(owner);
    assertEquals(book2.getOwnerName(),owner);
  }

//  @Test
//  void test1EmptyServerContainsNoDictionaryEntries() throws IOException {
//    AppointmentBookRestClient client = newAppointmentBookRestClient();
//    Map<String, String> dictionary = client.getAllDictionaryEntries();
//    assertThat(dictionary.size(), equalTo(0));
//  }

//  @Test
//  void test2DefineOneWord() throws IOException {
//    AppointmentBookRestClient client = newAppointmentBookRestClient();
//    String testWord = "TEST WORD";
//    String testDefinition = "TEST DEFINITION";
//    client.addDictionaryEntry(testWord, testDefinition);
//
//    String definition = client.getDefinition(testWord);
//    assertThat(definition, equalTo(testDefinition));
//  }

  @Test
  void test4MissingRequiredParameterReturnsPreconditionFailed() throws IOException {
    AppointmentBookRestClient client = newAppointmentBookRestClient();
    HttpRequestHelper.Response response = client.postToMyURL(Map.of());
    assertThat(response.getContent(), containsString("The required parameter \"owner\" is missing"));
    assertThat(response.getCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
  }

}
