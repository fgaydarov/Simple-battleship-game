/**
 * Class SimpleDotComGame
 *
 * Created by: Farid Gaydarov
 * Creation date: October 14, 2024
 *
 * This class provides utility functions to help with user input.
 * It reads input from the console and handles exceptions.
 *
 */

import java.io.*;

public class GameHelper {

    public String getUserInput(String Message){
        String inputLine = null;
        System.out.print(Message +": ");
        try {
            BufferedReader is = new BufferedReader( new InputStreamReader( System.in) );
            inputLine = is.readLine();
            if (inputLine.length() == 0) return  null;
        }catch (IOException e){
            System.out.println("IOException: " + e);
        }

        return inputLine;
    }
}
