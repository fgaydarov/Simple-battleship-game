/**
 * Class SimpleDotComGame
 *
 * Created by: Farid Gaydarov
 * Creation date: October 14, 2024
 *
 * Description: A simple single-line "Battleship" game, using only simple Arrays .
 * This class serves as the main entry point for the Simple Dot Com game.
 * It handles user input and game logic to manage the game flow.
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GameManager {


    public String getUserInput(String Message, GameHelper helperGame){
        String inputLine = null;
        inputLine = helperGame.getUserInput(Message);
        return inputLine;
    }

    public static void main(String[] args) {
        int numOfGuesses = 0;

        GameHelper helperGame = new GameHelper();

        GameManager game = new GameManager();

        Battleship battleship = new Battleship(20);


        battleship.addShip(2);
        battleship.addShip(1);
        battleship.addShip(3);
        battleship.addShip(2);
        battleship.addShip(2);

        battleship.addShip(1);
        battleship.addShip(1);

        System.out.println("--------------------");
        for (int  cell:battleship.locationCells ){
            System.out.print (cell);
        }


        boolean isAlive = true;
        int numOfHits = 0;
        while (isAlive){
            boolean checkAnyShipAlive = true;
            String guess = game.getUserInput("\nEnter shot", helperGame);
            String result = battleship.checkYOurself(guess);
            System.out.println(result );
            for (int  cell:battleship.locationCells ){
                System.out.print (cell);
            }
            Pattern pattern = Pattern.compile("All ships are destroyed");
            Matcher matcher = pattern.matcher(result);

            if (matcher.find()) {
                System.out.println("\nGame Over!");
                isAlive = false;
            }

        }

    }
}
