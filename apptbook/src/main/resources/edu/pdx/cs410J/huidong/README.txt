This is a README file!
This Java program is about making an appointment. It can receive an appointment,
and add it to appointment book. The only thing you need to do is to enter four elements. First is owner
who owns the appointment book. Second is a description of the appointment. Third is the begin time of the
appointment. The final thing is the end time of the appointment. It also provides Three options, textFile file,
print, and README.The textFile file can show the path Where to read/write the appointment book. The print option
can prints a description of the new appointment. The README option can display the usage.

usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>
args are (in this order):
owner               The person who owns the appt book
description         A description of the appointment
beginTime           When the appt begins (24-hour time)
endTime             When the appt ends (24-hour time)

options are (options may appear in any order):
-textFile file      Where to read/write the appointment book
-print              Prints a description of the new appointment
-README             Prints a README for this project and exits
Date and time should be in the format: mm/dd/yyyy hh:mm
