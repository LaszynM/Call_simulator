package main.java.com.mlaszyn.callsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

//Main class that initiates all required objects
//and is rensponsible for communication with application user
public class Menu {
    List<User> userList;
    //Start the system:
    //Initiate list of users from their files
    CallSystem callSystem;
    public Menu() {
        //Create CallSystem instance
        callSystem = new CallSystem();

        //Create list of users
        userList = new LinkedList<User>();
        File folder = new File("./users");
        File[] list = folder.listFiles();

        //Load users into list,
        //phone numbers are taken from file name
        //Users require CallSystem instance
        for(int i = 0; i < list.length; i++) {
            userList.add(new User(list[i].getName().substring(0, list[i].getName().indexOf(".")), callSystem));
        }
        //Pass user list to system
        callSystem.setUserList(userList);
    }

    public void startProgram() {
        //TODO
        //User tescik = this.callSystem.findUser("555222333");
        //User tescik2 = this.callSystem.findUser("606606606");
        //tescik.sendMessage("606606606", "test");
        //tescik.call("888222999");
        //tescik2.call("888222999");
        //tescik.hangup();
        //tescik.readLog(2, 1);
       while(true) {
            System.out.println("Menu");
            System.out.println("Choose option:");
            System.out.println("1: Initiate call");
            System.out.println("2: End call");
            System.out.println("3: Send message");
            System.out.println("4: Check logs");
            System.out.println("5: Exit program");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                if (name.equals("1")) {
                    initCall();
                } else if (name.equals("2")) {
                    endCall();
                }
                else if (name.equals("3")) {
                    sendMessage();
                }
                else if (name.equals("4")) {
                    readLogs();
                }
                else if (name.equals("5"))
                    return;
                else
                    System.out.println("Wrong command, please try again");
            } catch (IOException io) {
                System.out.println("Input error");
            }
        }
    }

    public void initCall() {
        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please enter caller:");
                String call = reader.readLine();
                System.out.println("Please enter receiver:");
                String val = reader.readLine();
                User caller = this.callSystem.findUser(call);
                if(caller != null) {
                    caller.call(val);
                    return;
                } else
                    System.out.println("User not found");
                return;
            } catch (IOException io) {
                System.out.println("Input error");
            }
        }

    }
    public void endCall() {
        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please enter number:");
                String call = reader.readLine();
                User caller = this.callSystem.findUser(call);
                if(caller != null) {
                    caller.hangup();
                    return;
                } else
                    System.out.println("User not found");
                return;
            } catch (IOException io) {
                System.out.println("Input error");
            }
        }
    }

    public void sendMessage() {
        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please enter sender:");
                String call = reader.readLine();
                System.out.println("Please enter receiver:");
                String val = reader.readLine();
                System.out.println("Please enter message:");
                String msg = reader.readLine();
                User caller = this.callSystem.findUser(call);
                if(caller != null) {
                    caller.sendMessage(val, msg);
                    return;
                } else
                    System.out.println("User not found");
                return;
            } catch (IOException io) {
                System.out.println("Input error");
            }
        }

    }
    public void readLogs() {
        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Please enter user number:");
                String call = reader.readLine();
                System.out.println("Please enter log type \n" +
                        "(type 0 - calls, type 1 - messages, type 2 - all\n" +
                        "type 3 - messages from, type 4 - messages to\n" +
                        "type 5 - calls from, type 6 - calls to):");
                String type = reader.readLine();
                System.out.println("Please enter mode (0 - last, 1 - all):");
                String mode = reader.readLine();
                User caller = this.callSystem.findUser(call);
                if(caller != null) {
                    caller.readLog(Integer.parseInt(type), Integer.parseInt(mode));
                    return;
                } else
                    System.out.println("User not found");
                return;
            } catch (IOException io) {
                System.out.println("Input error");
            }
        }

    }

}
