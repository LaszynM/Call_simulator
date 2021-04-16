package main.java.com.mlaszyn.callsimulator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Class representing the call
//It only serves as a representation
//of a connection between 2 Users

public class Call {

    String startDate;
    User caller;
    User receiver;
    //Getters, used when ending call
    public String getStartDate() { return startDate; }
    public User getCaller() { return caller; }
    public User getReceiver() { return receiver; }

    //get date+time of ending call
    public String getHangupDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return (dateFormat.format(date));
    }

    //Constructor, sets users in connection
    //and start of the conversation
    public Call(User from, User to) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        startDate = dateFormat.format(date);
        caller = from;
        receiver = to;
    }
}
