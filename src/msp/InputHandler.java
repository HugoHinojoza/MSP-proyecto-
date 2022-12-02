package msp;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler implements Runnable {

    private BufferedReader input;

    public InputHandler(BufferedReader br) {
        input = br;
    }
    @Override
    public void run() {
        String message = null;
        while (true) {
            try {
                if ((message = input.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if( message.trim().equals(".") ) {
                System.out.println(message);
                break;
            }   else {
                System.out.println(message);
            }
        }
    }
}
