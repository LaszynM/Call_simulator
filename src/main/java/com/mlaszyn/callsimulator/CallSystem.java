package main.java.com.mlaszyn.callsimulator;

import java.util.List;

public class CallSystem {
    //List of all available users
    List<User> userList;
    //List of all calls in progress
    List<Call> callList;
    //Empty Constructor
    public CallSystem(){}
    public void setUserList(List<User> userList) { this.userList = userList; }
    public boolean initiateCall(User from, String to) {
        User caller = from;
        User receiver = null;
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getNumber() == to) {
                receiver = userList.get(i);
                break;
            }
        }
        //TODO differentiate "number not found" and "number is busy"
        if(receiver == null)
            return false;
        if(receiver.getAvailable() == true) {
            caller.setAvailable(false);
            receiver.setAvailable(false);
            callList.add(new Call(caller, receiver));
            return true;
        }
        else
            return false;
    }
    //Ending call
    //TODO differentiate "disconnect" and "no active call"
    public boolean endCall(User user) {

        for(int i = 0; i < callList.size(); i++) {

        }

    }
}
