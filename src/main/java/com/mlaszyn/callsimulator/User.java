package main.java.com.mlaszyn.callsimulator;

import java.util.Calendar;

//Representation of user,
//each user has it's own
//file containing history
//of calls as well as messages.
public class User {

    CallSystem callSystem;
    private final String number;
    private boolean available;

    public User(String number, CallSystem callSystem) {
        this.callSystem = callSystem;
        this.number = number;
        available = true;
    }
    //Setter + Getters
    public void setAvailable(boolean available) { this.available = available; }
    public boolean getAvailable() { return this.available; }
    public String getNumber() { return this.number; }

    //method that represents dialing the number
    //TODO - same as initiateCall
    public boolean call(String number) {
        return (callSystem.initiateCall(this, number));
    }

    //method that represents disconnecting from call
    //TODO - same as endCall
    public void hangup() {
        callSystem.endCall(this);
    }

    //sending a message
    public void sendMessage(String number, String msg) {
        callSystem.sendMessage(number, msg, this);
    }

}
