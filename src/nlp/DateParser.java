package nlp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {
    public static Date parseDate(String dateString) {
        final int MS_IN_SINGLE_DAY = 24 * 3600000;
        Date date = new Date();
        SimpleDateFormat sd;
        String dateFormats[] = {"dd/MM/yyyy", "yyyy/MM/dd", "dd/MM", "dd-MM-yyyy", "yyyy-MM-dd","dd MMMMMMMMM, yyyy", "dd, MMMMMMMMM yyyy", "dd , MMMMMMMMM yyyy", "dd MMMMMMMMM , yyyy", "dd ,MMMMMMMMM yyyy","dd MMMMMMMMM ,yyyy","dd,MMMMMMMMM yyyy", "dd-MM","dd-MMMMMMMMM","dd -MMMMMMMMM", "dd- MMMMMMMMM","dd - MMMMMMMMM" ,"dd,MMMMMMMMM","dd ,MMMMMMMMM","dd MMMMMMMMM"};
        int hit = 0;
        while (hit < dateFormats.length) {
            if (isValid(dateFormats[hit], dateString))
                break;
            hit++;
        }
        if (hit != dateFormats.length) {
            sd = new SimpleDateFormat(dateFormats[hit]);
            try {
                date = sd.parse(dateString);
                if(!dateFormats[hit].contains("y"))
                    date.setYear(new Date().getYear());
            } catch (ParseException e) {//we will never encounter this.
            }
        } else if (dateString.contains("tomorrow")) {
            date = new Date(date.getTime() + MS_IN_SINGLE_DAY - (date.getTime() % MS_IN_SINGLE_DAY));
        } else if (dateString.contains("next to next")) {

        } else if (dateString.contains("first")) {
            //may be first month , first week, first day of month
            //same for second and third and so on...
        }
        return date;
    }

    public static int getNextDay(Calendar cal) {
        return cal.get(Calendar.WEEK_OF_MONTH) + 1;
    }

    public static boolean isValid(String pattern, String dateString) {
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        try {
            Date date = sd.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
