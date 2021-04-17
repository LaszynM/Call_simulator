package main.java.com.mlaszyn.callsimulator;

//Representation of user,
//each user has it's own
//file containing history
//of calls as well as messages.
public class User {

    CallSystem callSystem;
    private final String number;
    private boolean available;

    //Reading logs
    //type 0 - calls, type 1 - messages, type 2 - all
    //type 3 - messages from, type 4 - messages to
    //type 5 - calls from, type 6 - calls to
    //mode 0 - last, mode 1 - all
    public void readLog(int type, int mode){
        System.out.println(callSystem.readLog(this.getNumber(), type, mode));
    }

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
    public void call(String number) {
        int nr = callSystem.initiateCall(this, number);
        switch (nr){
            case 1:
                System.out.println("Number not found");
                break;
            case 2:
                System.out.println("The number is busy");
                break;
            case 0:
                System.out.println("Initiating call");
                break;
        }
    }

    //method that represents disconnecting from call
    //TODO - same as endCall
    public void hangup() {
        callSystem.endCall(this);
    }

    //sending a message
    public void sendMessage(String number, String msg) {
        if (callSystem.sendMessage(number, msg, this) == false)
            System.out.println("Message undelivered");
    }

}
