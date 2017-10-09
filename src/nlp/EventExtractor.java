package nlp;

import java.util.Date;
import java.sql.Time;

public class EventExtractor {

    public static Event exractEvent(String[] tokens, String[] tags){
        StringBuffer subject, timeString, place, dateString;
        subject = new StringBuffer("");
        timeString = new StringBuffer("");
        place = new StringBuffer("");
        dateString = new StringBuffer("");

        if(tokens.length != tags.length)
            return null;
        for(int i=0; i<tokens.length; i++){
            switch (tags[i]){
                case "SUBJECT":
                    subject.append(tokens[i]+" ");
                    break;
                case "TIME":
                    timeString.append(tokens[i]+" ");
                    break;
                case "PLACE":
                    place.append(tokens[i]+" ");
                    break;
                case "DATE":
                    dateString.append(tokens[i]+" ");
                    break;
            }
        }

        Time time = TimeParser.parseTime(timeString.toString());
        Date date = DateParser.parseDate(dateString.toString());

        return new Event(subject.toString(), time, date, place.toString());
    }
}
