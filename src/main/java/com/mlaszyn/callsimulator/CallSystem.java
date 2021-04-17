package main.java.com.mlaszyn.callsimulator;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CallSystem {
    //List of all available users
    List<User> userList;
    //List of all calls in progress
    List<Call> callList;
    //Empty Constructor
    public CallSystem(){
        callList = new LinkedList<Call>();
    }
    //Find user on user list
    public User findUser(String number){
        User user = null;
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getNumber().equals(number)) {
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
                writeLog(find.getReceiver().getNumber(), receiverLog);
                writeLog(find.getCaller().getNumber(), callerLog);

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

    public String readLog(String number, int type, int mode) {
        try {
            FileReader file = new FileReader("./users/" + number + ".txt");
            BufferedReader reader = new BufferedReader(file);
            String msgType;
            boolean ifAll;
            switch (mode) {
                case 0:
                    ifAll = false;
                    break;
                case 1:
                    ifAll = true;
                    break;
                default:
                    ifAll = false;

            }
            switch (type) {
                case 0:
                    msgType = "Call";
                    break;
                case 1:
                    msgType = "Message";
                    break;
                case 2:
                    msgType = "";
                    break;
                default:
                    msgType = "";
            }
            String finalMsg = "";
            String line = reader.readLine();
            while(line != null) {
                if (ifAll == true) {
                    if (line.substring(0, line.indexOf(" ")).contains(msgType)) {
                        finalMsg = finalMsg + line;
                    }
                }else {
                    if(line.substring(0, line.indexOf(" ")).contains(msgType)) {
                        finalMsg = line;
                    }
                }
                line = reader.readLine();
            }
            return finalMsg;
        } catch (IOException exc) {
            return "Error! Can't read file";
        }
    }

    public boolean sendMessage(String number, String msg, User sender) {
        User receiver = findUser(number);
        if(receiver != null) {
            String senderMsg = "Message to:" + number + " content:" +msg;
            String receiverMsg = "Message from:" + number + " content:" +msg;
            writeLog(sender.getNumber(), senderMsg);
            writeLog(number, receiverMsg);
            return true;
        }
        return false;
    }
}
