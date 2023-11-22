/* JCE on Creation and Use of JARs
   Fall 2023
   This is the class to use the JAR
   Name: Cole Dombrowski
   Date: 11/14/2023
*/

package dombrowski.cole.jce6;

import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import dombrowski.cole.dateapp.*;

public class Driver
{
    public static void main(String[] args)
    {
       boolean forward = true;
        String dateArgs[] = FrontUI.promptDateCalc();
        int interval = Integer.parseInt(dateArgs[1]);

        if (dateArgs[2].equalsIgnoreCase("N")
                || dateArgs[1].equalsIgnoreCase("No"))
        {
            forward = false;
        }

        // Call calcNewDate() in DateAssistant class
        String target = DateAssistant.calcNewDate(dateArgs[0], interval, forward);

        // Display the result
        JOptionPane.showMessageDialog(null,
                "The date you're looking for is : " + target + ".",
                "calcNewDate() test",
                JOptionPane.INFORMATION_MESSAGE);

        // **** Test for formatDate() - TimeZone Version *****
        // Create a GregorianCalendar object for the current time
        GregorianCalendar gc = new GregorianCalendar();

        // Get time zone
        String zone = FrontUI.promptTimeZone();

        // Call formatDate() in DateAssisant, passing time zoen string as the
        // second argument
        String nowString = DateAssistant.formatDate(gc, zone);

        // Display the result
        JOptionPane.showMessageDialog(null,
                "The current time is " + nowString + ".",
                "formatDate() Test",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
