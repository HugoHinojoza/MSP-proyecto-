package msp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class MessageServer {
    public static final String CLASS_NAME = MessageServer.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    public static final int PORT = 1818;

    public static void main(String args[]) {

        int portNumber;
        if (args.length != 1) {
            System.err.println("Usage: java MessageServer <port number>");
            portNumber = PORT;

        } else {
            portNumber = Integer.parseInt(args[0]);
        }

        UserManager users = new UserManager();


        try {
            ServerSocket serverSocket
                    = new ServerSocket( portNumber );

            while( true ) {

                Socket clientSocket = serverSocket.accept();

                LOGGER.info("CONNECTION: " +
                        clientSocket.getRemoteSocketAddress().toString());

                Thread serviceProcess = new Thread( new ConnectionHandler(users, clientSocket)) ;
                serviceProcess.start();
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
