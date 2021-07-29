package edu.pdx.cs410J.huidong;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>AppointmentBook</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class AppointmentBookServlet extends HttpServlet {
    static final String OWNER_PARAMETER = "owner";
    static final String DESCRIPTION_PARAMETER = "description";
    static final String BEGINTIME_PARAMETER = "beginTime";
    static final String ENDTIME_PARAMETER = "endTime";
    static final String START_PARAMETER = "start";
    static final String END_PARAMETER = "end";
    private final Map<String, AppointmentBook> books = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String owner = getParameter(OWNER_PARAMETER, request );
        String start = getParameter(START_PARAMETER, request);
        String end = getParameter(END_PARAMETER, request);
        if (owner == null) {
            missingRequiredParameter(response, OWNER_PARAMETER);
        } else if (start != null ){
            if(end != null){
                ListAllAppointmentBetweenStartAndEnd(owner,start,end,response);
            }else {
                missingRequiredParameter(response, END_PARAMETER);
            }
        }else if (end != null){
            if(start != null){
                ListAllAppointmentBetweenStartAndEnd(owner,start,end,response);
            }else {
                missingRequiredParameter(response,START_PARAMETER);
            }
        }
        else {
            listAppointmentInBook(owner, response);
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String owner = getParameter(OWNER_PARAMETER, request );
        if (owner == null) {
            missingRequiredParameter(response, OWNER_PARAMETER);
            return;
        }

        String description = getParameter(DESCRIPTION_PARAMETER, request );
        if ( description == null) {
            missingRequiredParameter( response, DESCRIPTION_PARAMETER);
            return;
        }

        String beginTime = getParameter(BEGINTIME_PARAMETER, request );
        if ( beginTime == null) {
            missingRequiredParameter( response, BEGINTIME_PARAMETER);
            return;
        }

        String endTime = getParameter(ENDTIME_PARAMETER, request );
        if ( endTime == null) {
            missingRequiredParameter( response, ENDTIME_PARAMETER);
            return;
        }

        AppointmentBook book = this.books.get(owner);
        if (book == null){
            book = new AppointmentBook(owner);
            this.books.put(owner,book);
        }

        Appointment appointment = new Appointment(description, beginTime, endTime);
        book.addAppointment(appointment);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.addNewAppointment(appointment));
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        this.books.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with
     * {@link Messages#formatDictionaryEntry(String, String)}
     */
    private void listAppointmentInBook(String owner, HttpServletResponse response) throws IOException {
        AppointmentBook book = this.books.get(owner);

        if (book == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            PrintWriter pw = response.getWriter();
            TextDumper textDumper = new TextDumper(pw);
            textDumper.dumpUsingPrintWriter(book);

            pw.flush();

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with
     * {@link Messages#formatDictionaryEntry(String, String)}
     */
    private void ListAllAppointmentBetweenStartAndEnd(String owner, String start, String end, HttpServletResponse response ) throws IOException
    {
        AppointmentBook book = this.books.get(owner);
        PrintWriter pw = response.getWriter();
        boolean flag = true;
        if (book == null) {
            pw.println("No such appointment book!");

        } else {

            ArrayList<Appointment> arrayList = new ArrayList<>();
            arrayList.addAll(book.getAppointments());
            for(Appointment appointment : arrayList){
                if(checkForTime(start, end, appointment)){
                    pw.println(appointment.toString());
                    flag = false;
                }

            }
            if(flag){
                pw.println("There is no match appointment!");
            }

        }
        pw.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private boolean checkForTime(String start, String end, Appointment appointment) {
        DateFormat simpleD = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);
        Date START = null;
        Date END = null;
        try {
            START = simpleD.parse(start);
            END = simpleD.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(appointment.getBeginTime().getTime() >= START.getTime()){
            if(appointment.getEndTime().getTime() <= END.getTime()){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

    @VisibleForTesting
    AppointmentBook getDefinition(String word) {
        return this.books.get(word);
    }
}
