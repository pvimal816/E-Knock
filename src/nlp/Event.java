package nlp;

import java.sql.Time;
import java.util.Date;

public class Event {
    String subject;
    Time time;
    String place;
    Date date;

    public Event(String subject, Time time, Date date, String place) {
        this.subject = subject;
        this.time = time;
        this.date = date;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Event{" +
                "subject='" + subject + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
