/* JCE on Creation and Use of JARs
   This is part of the JAR to be created
   No student coding is needed
*/

package dombrowski.cole.dateapp;

import javax.swing.JOptionPane;

public class FrontUI
{
    public static String[] promptDateCalc()
    {
        // ***** Get User Input to call calcNewDate() *****
        String dateCalcArgs[] = new String[3];
        String forwardStr = "";

        // Ask user to enter a start date
        dateCalcArgs[0] = JOptionPane.showInputDialog(null,
                "Start Date is (in mm/dd/yyyy format):",
                "calcNewDate() Test",
                JOptionPane.QUESTION_MESSAGE);

        // Ask whether it is for calculating a future or a past date
        // The default is set as Yes (hence the [Y] part in the prompt)
        dateCalcArgs[2] = JOptionPane.showInputDialog(null,
                "Looking for a future date? (Y/N) [Y]",
                "calcNewDate() Test",
                JOptionPane.QUESTION_MESSAGE);
        // Based on user's answer, adjust the prompt for the next dialog box,
        // and if not for forward calculation (default), change that.
        // The user will be searching for a past date only when he/she
        // specifically type in "N" or "No" (case-insensitive). If the user
        // enters anything else, or simply presses Enter without entering
        // anyting at all, the search is always forward. This behavior is in
        // agreement with Yes being the default.
        if (dateCalcArgs[2].equalsIgnoreCase("N")
                || dateCalcArgs[2].equalsIgnoreCase("No"))
        {
            forwardStr = "How many days ago in the past?";
        }
        else
        {
            forwardStr = "How many days ahead in the future?";
        }

        // Ask user for the interval between the start date and the target date
        dateCalcArgs[1] = JOptionPane.showInputDialog(null,
                forwardStr,
                "calcNewDate() Test",
                JOptionPane.QUESTION_MESSAGE);

        return dateCalcArgs;
    }

    public static String promptTimeZone()
    {

        // Ask user for the time zone
        // The default is Central, as indicated by the [Central] part at the
        // end of the prompt. If the user doesn't enter any of the other five
        // zone names in correct spelling (but case doesn't matter), or enters
        // anything else, the time zone remains Central (also see source code
        // in DateAssistant.class).
        String timeZone = JOptionPane.showInputDialog(null,
                "Please specify a time zone (Hawaii, Alaska, Pacific,\n"
                + "Mountain, Central, Eastern) [Central]",
                "formatDate() Test",
                JOptionPane.QUESTION_MESSAGE);

        return timeZone;
    }
}
