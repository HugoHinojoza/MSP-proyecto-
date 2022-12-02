package msp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

public class UserManager {

    public static final String CLASS_NAME = UserManager.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    private HashMap<String, Socket> connections;
    private HashMap<Socket, String> connections_inv;


    public UserManager() {
        super();
        connections = new  HashMap<String, Socket>();
        connections_inv = new  HashMap<Socket, String>();

    }

    public boolean connect(String user, Socket socket) {
        boolean result = true;

       Socket s = connections.put(user,socket);
       connections_inv.put(socket,user);
       if( s != null) {
           result = false;
       }
       return result;
    }

    public void send(String message, String target) {




        Socket s = connections.get(target);
        try {
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            output.println(message);
        } catch(IOException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }


    }

    public boolean disconnect(String userName) {
        boolean result = false;
        connections_inv.remove(connections.get(userName));
        Socket s = connections.remove(userName);
        if( s == null) {
            result = true;
        }
        return result;
    }

    public Socket get(String user) {

        Socket s = connections.get(user);
        return s;
    }

    public String getUser(Socket socket) {
        return connections_inv.get(socket);
    }

    public Set<String> getUserList() {
        return connections.keySet();
    }


}
