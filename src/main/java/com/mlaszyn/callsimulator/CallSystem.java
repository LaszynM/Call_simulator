package main.java.com.mlaszyn.callsimulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CallSystem {
    //List of all available users
    List<User> userList;
    //List of all calls in progress
    List<Call> callList;
    //Empty Constructor
    public CallSystem(){}
    //Find user on user list
    public User findUser(String number){
        User user = null;
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getNumber() == number) {
                user = userList.get(i);
                break;
            }
        }
        return user;
    }
    public void setUserList(List<User> userList) { this.userList = userList; }
    public boolean initiateCall(User from, String to) {
        User caller = from;
        User receiver = findUser(to);
        //for(int i = 0; i < userList.size(); i++) {
        //    if(userList.get(i).getNumber() == to) {
        //        receiver = userList.get(i);
        //        break;
        //    }
        //}
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
        if(user.getAvailable() == true)
            return false;
        Call find;
        for(int i = 0; i < callList.size(); i++) {
            find = callList.get(i);
            if(find.getCaller() == user || find.getReceiver() == user) {
                find.getReceiver().setAvailable(true);
                find.getCaller().setAvailable(true);
                String callerLog = "Call to:" + find.getReceiver().getNumber() +
                        " finished, duration from:" + find.getStartDate() + " to:" + find.getHangupDate();
                String receiverLog= "Call from:" + find.getCaller().getNumber() +
                        " finished, duration from:" + find.getStartDate() + " to:" + find.getHangupDate();

                //TODO
                writeLog(find.getReceiver().getNumber(), callerLog);
                writeLog(find.getCaller().getNumber(), receiverLog);

                //remove object from list
                //IMPORTANT NOTE
                //in c/c++ there should be delete called to destroy object
                //in Java garbage collector should remove object here
                callList.remove(i);
                find = null;
                break;
            }
        }
        return true;
    }

    public boolean writeLog(String number, String log) {
        try {
            FileWriter file = new FileWriter("./users/"+number+".txt", true);
            BufferedWriter writer = new BufferedWriter(file);
            writer.write(log + "\n");
            writer.close();
            return true;

        } catch(IOException exc) {
            System.out.println("Can't open file!");
            return false;
        }
    }
    public boolean sendMessage(String number, String msg, User sender) {
        User receiver = findUser(number);
        if(receiver != null) {
            String senderMsg = "To:" + number + " message:" +msg;
            String receiverMsg = "From:" + number + " message:" +msg;
            writeLog(sender.getNumber(), senderMsg);
            writeLog(number, receiverMsg);
            return true;
        }
        return false;
    }
}
