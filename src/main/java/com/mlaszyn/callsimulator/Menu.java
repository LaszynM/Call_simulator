package main.java.com.mlaszyn.callsimulator;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

//Main class that initiates all required objects
//and is rensponsible for communication with application user
public class Menu {
    List<User> userList;
    //Start the system:
    //Initiate list of users from their files
    public Menu() {
        //Create CallSystem instance
        CallSystem callSystem = new CallSystem();

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
       /* while (true) {
            //System.out.print("\033[H\033[2J");
            //System.out.flush();
            System.out.println("Main menu");
            System.out.println("-------------");
            System.out.println("1. Start call");
            System.out.println("2. End call");
            System.out.println("3. Check messages");
            System.out.println("4. Check history");
            break;
        }
        System.out.print("\f");
        System.out.flush();
        */

    }
}
