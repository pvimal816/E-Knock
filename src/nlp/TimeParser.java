package nlp;

import java.sql.Time;
import java.util.ArrayList;

public class TimeParser {
    public static Time parseTime(String timeString) {
        String time[] = timeString.split("[^0123456789]");
        int count = 0;
        for(String t : time){
            if(!t.equals("")){
                time[count++] = t;
            }
        }

        int hour=00, minute=00,second=00;
        if (timeString.contains("am")) {
            hour = Integer.parseInt(time[0]);
            if(hour == 12)
                hour = 00;
        }else if(timeString.contains("pm")){
            hour = Integer.parseInt(time[0]);
            if(hour != 12)
                hour += 12;

        }else if(timeString.contains("early morning")){
            hour = 6;
        }else if(timeString.contains("morning")){
            hour = 9;
        }else if(timeString.contains("noon")){
            hour = 12;
        }else if(timeString.contains("after noon") || timeString.contains("afternoon")){
            hour = 14;
        }else if(timeString.contains("evening")){
            hour = 18;
        }else if(timeString.contains("late night")){
            hour = 23;
        }else if(timeString.contains("mid night")){
            hour = 0;
        }else if(timeString.contains("tea time")){
            hour = 16;
        }

        if(time.length>1)
            minute = Integer.parseInt(time[1]);

        if(time.length>2)
            if(!time[2].equals(""))
            second = Integer.parseInt(time[2]);

        return new Time(hour,minute,second);
    }
}
