package main.java.com.mlaszyn.callsimulator;

import java.util.Calendar;

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

    public boolean call(String number) {
        return (callSystem.initiateCall(this, number));
    }

}
