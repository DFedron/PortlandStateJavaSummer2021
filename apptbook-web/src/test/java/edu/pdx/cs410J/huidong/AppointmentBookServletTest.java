package edu.pdx.cs410J.huidong;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AppointmentBookServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AppointmentBookServletTest {
  @Test
  void SearchFakeAppointmentBook() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(request.getParameter("owner")).thenReturn("we");
    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_NOT_FOUND, "There is no such book");
  }

  @Test
  void SearchAppointmentBook() throws IOException, ServletException {
    String owner = "huidong";
    String description = "TEST for description";
    String beginTime = "06/03/2020 12:59 am";
    String endTime = "06/03/2020 02:59 pm";
    AppointmentBookServlet servlet = new AppointmentBookServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("description")).thenReturn(description);
    when(request.getParameter("beginTime")).thenReturn(beginTime);
    when(request.getParameter("endTime")).thenReturn(endTime);
    servlet.doPost(request, response);

    Appointment appointment = new Appointment(description,beginTime,endTime);
    verify(pw).println(Messages.addNewAppointment(appointment));
    StringWriter sw = invokeServletMethod(Map.of("owner", owner), servlet::doGet);

    String text = sw.toString();
    assertThat(text, containsString(owner));
    assertThat(text, containsString(appointment.toString()));


    verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void SearchRealAppointmentBetweenStartAndEnd() throws ServletException, IOException {
    String owner = "huidong";
    String description = "TEST for description";
    String beginTime = "06/03/2020 12:59 am";
    String endTime = "06/03/2020 02:59 pm";
    AppointmentBookServlet servlet = new AppointmentBookServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("description")).thenReturn(description);
    when(request.getParameter("beginTime")).thenReturn(beginTime);
    when(request.getParameter("endTime")).thenReturn(endTime);
    servlet.doPost(request, response);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("start")).thenReturn(beginTime);
    when(request.getParameter("end")).thenReturn(endTime);
    servlet.doGet(request, response);

    Appointment appointment = new Appointment(description,beginTime,endTime);
    verify(pw).println(appointment.toString());


    verify(response, times(2)).setStatus(HttpServletResponse.SC_OK);

  }


  @Test
  void addOneAppointment() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "huidong";
    String description = "TEST for description";
    String beginTime = "06/03/2020 12:59 am";
    String endTime = "06/03/2020 02:59 pm";
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("description")).thenReturn(description);
    when(request.getParameter("beginTime")).thenReturn(beginTime);
    when(request.getParameter("endTime")).thenReturn(endTime);
    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    Appointment appointment = new Appointment(description,beginTime,endTime);
    assertThat(stringWriter.toString(), containsString(Messages.addNewAppointment(appointment)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());
//
    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
//
//    assertThat(servlet.getDefinition(word), equalTo(definition));
  }
  private StringWriter invokeServletMethod(Map<String, String> params, ServletMethodInvoker invoker) throws IOException, ServletException {
    HttpServletRequest request = mock(HttpServletRequest.class);
    params.forEach((key, value) -> when(request.getParameter(key)).thenReturn(value));

    HttpServletResponse response = mock(HttpServletResponse.class);

    StringWriter sw = new StringWriter();
    when(response.getWriter()).thenReturn(new PrintWriter(sw));

    invoker.invoke(request, response);

    verify(response).setStatus(HttpServletResponse.SC_OK);
    return sw;
  }

  private interface ServletMethodInvoker {
    void invoke(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  }
}
