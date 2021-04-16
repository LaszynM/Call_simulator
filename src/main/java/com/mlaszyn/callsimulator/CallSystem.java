package main.java.com.mlaszyn.callsimulator;

import java.util.List;

public class CallSystem {
    List<User> userList;
    List<Call> callList;
    //Constructor
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
        if(receiver == null)
            return false;
        if(receiver.getAvailable() == true) {
            caller.setAvailable(false);
            receiver.setAvailable(false);
            callList.add(new Call(caller, receiver));
        }
    }
}
