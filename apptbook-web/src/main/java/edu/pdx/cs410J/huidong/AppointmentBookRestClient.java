package edu.pdx.cs410J.huidong;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AppointmentBookRestClient extends HttpRequestHelper {
  private static final String WEB_APP = "apptbook";
  private static final String SERVLET = "appointments";

  private final String url;


  /**
   * Creates a client to the appointment book REST service running on the given host and port
   *
   * @param hostName The name of the host
   * @param port     The port
   */
  public AppointmentBookRestClient(String hostName, int port) {
    this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
  }


  /**
   * Returns the definition for the given word
   */
  public AppointmentBook getAppointments(String owner) throws IOException {
    Response response = null;
    try {
      response = get(this.url, Map.of("owner", owner));
    }catch (ConnectException | UnknownHostException e){
      System.err.println("Cannot connect! Please check your host and port!");
      System.exit(1);
    }

    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();
//    System.out.println(content);
    TextParser parser = new TextParser(new StringReader(content));
    return parser.parseUsingReader();
  }

  public void createAppointment(String owner, Appointment appointment) throws IOException {
    Response response = postToMyURL(Map.of("owner", owner, "description", appointment.getDescription(), "beginTime", appointment.getBeginTimeString(), "endTime", appointment.getEndTimeString()));
    throwExceptionIfNotOkayHttpStatus(response);
  }


  public void addDictionaryEntry(String word, String definition) throws IOException {
    Response response = postToMyURL(Map.of("word", word, "definition", definition));
    throwExceptionIfNotOkayHttpStatus(response);
  }

  @VisibleForTesting
  Response postToMyURL(Map<String, String> appointmentinfo) throws IOException {
    return post(this.url, appointmentinfo);
  }

  public void removeAllDictionaryEntries() throws IOException {
    Response response = delete(this.url, Map.of());
    throwExceptionIfNotOkayHttpStatus(response);
  }

  private Response throwExceptionIfNotOkayHttpStatus(Response response) {
    int code = response.getCode();
    if (code != HTTP_OK) {
      String message = response.getContent();
      System.err.println("Cannot find the book page!");
      System.err.println("Got an HTTP Status Code of " + code + ": " + message);
      System.exit(1);
      //throw new RestException(code, message);
    }
    return response;
  }

}
