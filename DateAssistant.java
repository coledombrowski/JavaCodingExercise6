/* JCE on Creation and Use of JARs
   This is part of the JAR to be created
   No student coding is needed
*/

package dombrowski.cole.dateapp;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateAssistant
{
    public static GregorianCalendar convertDateString(String dateString)
    {
        /* This method converts an String argument that is in the "mm/dd/yyyy"
        format into a GregorianCalendar object. */

        // Declare the GregorianCalendar object to be returned
        GregorianCalendar gc;

        // To call the constructor of GregorianCalendar with month, day, and
        // year arguments, three int variables are needed.
        String monthString, dayString, yearString;
        int month, day, year;

        /* The substring method of the String class returns part of a String as
        a "substring". The charaters included in the substring are determined
        by their indexes in the original string, an index being the position of
        the character.
        For example: in the date string
        character:   m | m | / | d | d | / | y | y | y | y
        position:    0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

        The substring() method is overloaded. One version takes two position
        indexes as arguments: the first one marks the start position of the
        substring and the scond, ending. The tricky part is that the first
        index is inclusive whereas the second is exclusive. Another version
        takes only one position index as argument. In that case, this index
        marks the start position of the substring, and it will run all the way
        to tne end of the original string.
         */

        // Call the substring() method with two arguments. Since the month part
        // is the first two characters of the date string, the start and ending
        // indexes are 0 and 2.
        monthString = dateString.substring(0, 2);
        month = Integer.parseInt(monthString);
        //month = Integer.parseInt(dateString.substring(0,2)); // better

        // Get the day part.
        dayString = dateString.substring(3, 5);
        day = Integer.parseInt(dayString);

        // Get the year part. We may use the other version with one argument.
        yearString = dateString.substring(6);
        year = Integer.parseInt(yearString);

        // Now we have all three arguments for calling one of the constructors
        // of the GregorianCalendar class. Note that the GregorianCalendar class
        // counts months from 0 (i.e., January = Month 0; December = Month 11).
        gc = new GregorianCalendar(year, month - 1, day);

        return gc;
    }

    public static String formatDate(GregorianCalendar gc, int loc)
    {
        /* This method takes a GregorianCalendar object that represents a given
           point in time and displays it in a format that is determined by one
           of three locales: the United States, the United Kingdom, and Japan.
           To specify which local to use, pass an integer as the second
           argument:
               1 - US, 2 - UK, 3 - Japan. */

        // Variables
        String dateDisplay;

        // Convert the GregorianCalendar object to Date object. This is
        // necessary because the format() method of the DateFormat class takes
        // a Date object as the argument.
        Date d = gc.getTime();

        /* The DateFormat class formats dates and times in a locale-specific way.
           It is an abstract class and thus cannot be instantiated directly. So,
           Unlike the DecimalFormat class, you don't create a "DateFormat"
           object by calling its constructor - DateFormat df = new DateFormat();
           Instead, you call one of a number of static methods to create a
           particular instance.

           ---------------------------------------------------------------------
               if you want to format...              then call this method...
           ---------------------------------------------------------------------
                   date only                       getDateInstance()
                   time only                       getTimeInstance()
              date and time (long format)          getDateTimeInstance()
              date and time (short format)         getInstance()
           ---------------------------------------------------------------------

            However, at this point, the local for date display is not set yet.
            The display style of the DateFormat instances can be controlled by
            the arguments you pass to the method.

            For the first argument, you pass one of the few constants that are
            defined in the DateFormat class; it controls the format used on the
            date portion:
                * DateFormat.FULL ( = 0 ):      Tuesday, January 1, 2011
                * DateFormat.LONG ( = 1 ):      January 1, 2011
                * DateFormat.MEDIUM ( = 2 ):    January 1, 2011
                * DateFormat.SHORT ( = 3 ):     1/1/11
                * DateFormat.DEFAULT (default is MEDIUM)
            The second argument is again one of those constants, such as
            DateFormat.FULL). However, when passed as the second argument, it
            controls the format used on the time portion instead.
            The last argument controls the locale, which is a java.util.Locale
            object.
         */

        // Control how the date will be formatted based on locale speified by
        // the user.
        DateFormat df = null;

        // loc is the int the user enters: 1 - US, 2 - UK, 3 - Japan
        switch (loc)
        {
            case 1:
                df = DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM, DateFormat.SHORT, Locale.US);
                break;
            case 2:
                df = DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM, DateFormat.SHORT, Locale.UK);
                break;
            case 3:
                df = DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM, DateFormat.SHORT, Locale.JAPAN);
                break;
        }

        // Create the display string
        dateDisplay = df.format(d);

        // Return date display string
        return dateDisplay;
    }

    public static String formatDate(GregorianCalendar gc, String zone)
    {
        /* This method takes a GregorianCalendar object that represents a given
           point in time and displays it in a format that is determined by time
           zones:
                  Hawaii, Alaska, Pacific, Mountain, Central, Eastern
           To specify which time zone to use, pass one of the above six strings
           as the second argument when calling this method.
        */
        // Variables
        String dateDisplay;
        TimeZone tz;

        // Convert the GregorianCalendar object to Date object.
        Date d = gc.getTime();

        // Create the DateFormat object and set date display in U.S. style
        DateFormat df = null;
        df = DateFormat.getDateTimeInstance(
                        DateFormat.MEDIUM, DateFormat.SHORT, Locale.US);

        // Set default time zone as Central
        // This is a catch-all option so that if the user doesn't enter a string
        // that matches any of the six below, the display is set as Central
        tz = TimeZone.getTimeZone("America/Chicago");

        // Adjust the time zone for date display based on the zone names
        // passed into this method
	if ( zone.equalsIgnoreCase("Hawaii") )
        {
            tz = TimeZone.getTimeZone("Pacific/Honolulu");
        }
	else if ( zone.equalsIgnoreCase("Alaska") )
        {
            tz = TimeZone.getTimeZone("America/Anchorage");
        }
        else if ( zone.equalsIgnoreCase("Pacific") )
        {
            tz = TimeZone.getTimeZone("America/Los_Angeles");
        }
        else if ( zone.equalsIgnoreCase("Mountain") )
        {
            tz = TimeZone.getTimeZone("America/Denver");
        }
        else if ( zone.equalsIgnoreCase("Central") )
        {
            tz = TimeZone.getTimeZone("America/Chicago");
        }
        else if ( zone.equalsIgnoreCase("Eastern") )
        {
            tz = TimeZone.getTimeZone("America/New_York");
        }

        // Actaully set time zone for the DateFormat object
        df.setTimeZone(tz);

        // Create the display string
        dateDisplay = df.format(d);

        // Return date display string
        return dateDisplay;
    }

    public static int calcInterval(String date1, String date2)
    {
        /* This method takes two dates in the "mm/dd/yyyy" format and
        calculates the number of days between them. */

        // Variables
        int interval;
        GregorianCalendar gc1, gc2;
        long time1, time2;

        // Convert date strings to GregorianCalendar objects
        gc1 = convertDateString(date1);
        gc2 = convertDateString(date2);

        /* The getTimeInMillis() is an example of the methods the
        GregorianCalendar class inherits from the Calendar class. Similar
        to the Date class, it represents the number of milliseconds from the
        "epoch" (midnight, GMT, Jan. 1, 1970) as a long value. This is handy
        for:
        1. Comparing two dates, or
        2. Calculating the time interval between two dates.
        We use it for both in this method.
         */
        // Convert GregorianCalendar objects to longs
        time1 = gc1.getTimeInMillis();
        time2 = gc2.getTimeInMillis();

        // Calculate time interval in days
        // 1 day = 24 hours * 60 mins * 60 seconds * 1000 millisecs
        interval = (int) ((Math.max(time1, time2) - Math.min(time1, time2))
                    / (24 * 60 * 60 * 1000));
        return interval;
    }

    public static String calcNewDate(String startDate, long interval,
            boolean forward)
    {
        // String variable to hold calculation result
        String targetDateString = "";

        // Other variables needed in the calculation
        GregorianCalendar gcStart;
        long startTime, targetTime;
        Date targetDate;
        
        // SimpleDateFormat object to format the new date; the "MM/dd/yyyy"
        // string defines the formatting style. Note that the month part is "MM"
        // rather than "mm" because the latter, if included in the style, will
        // display the minutes field in the Date object
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        // Convert start date into GC object
        gcStart = convertDateString(startDate);

        // ***** Date Arithmetics *****
        // Convert start date into milliseconds since the "epoch"
        startTime = gcStart.getTimeInMillis();
        // Convert the desired interval into milliseconds
        long intervalInMillis = interval * 24 * 60 * 60 * 1000;
        // Add or substract the desired interval
        // Get future date if it is forward calculation
        if ( forward )
        {
            targetTime = startTime + intervalInMillis;
        }
        // Get past date if it is not forward calculation
        else
        {
            targetTime = startTime - intervalInMillis;
        }

        // The result of calculation still is the number of milliseconds between
        // the "epoch" and the new date; So convert it into a Date object
        targetDate = new Date(targetTime);

        // Format the Date object
        targetDateString = sdf.format(targetDate);

        // Return the resultant date in "mm/dd/yyyy" format
        return targetDateString;
    }
}