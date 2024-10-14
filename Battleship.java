/**
 * Class SimpleDotCom
 *
 * Created by: Farid Gaydarov
 * Creation date: October 14, 2024
 *
 *This class represents a dot com (ship) in the game.
 * It manages the ship's location, checks if it has been hit, and determines if
 */
import java.util.Random;

public class Battleship {


    int[] locationCells;
    int ShipsCount = 0;
    int ShipsSizes = 0;
    int destroyedShipsCount = 0;

    public Battleship(int fieldSize){
        locationCells = new int[fieldSize];

    }

    public void addShip(int shipSize){
        int maxAttempts = 30;
        int tryingAttempts = 0;
        boolean shipPlased = false;
        Random random = new Random();

        while (!shipPlased){

            if (tryingAttempts>=maxAttempts){
                System.out.println("There is not enough space to accommodate a ship of this size " + shipSize );
                break;
            }
            int startPosition = random.nextInt( locationCells.length - shipSize + 1);

            //System.out.println("startPosition="+startPosition);

            if (canPlaceShip(startPosition,shipSize)){
                for (int i = 0; i < shipSize; i++) {
                    locationCells[startPosition + i] = shipSize;
                }
                shipPlased = true;
                ShipsCount++;
            }
            tryingAttempts++;

        }

    }

    public boolean canPlaceShip(int startPosition,int shipSize) {

        for (int i=startPosition-1; i< startPosition+shipSize+1;i++){
            //System.out.println(i);

            if (i>=0 && i<locationCells.length &&  locationCells[i]!=0){
                //System.out.println("No possible=" +i);
                return false;
            }
        }
        return true;
    }

    public String checkYOurself(String userGuess){
        String result = "Past";
        int guess = 0;

        try{
            guess = Integer.parseInt(userGuess);
            if (guess<0 || guess >= locationCells.length  ){
                result = "Error! The shot must not go beyond the dimensions of the playing field! 0 and " + locationCells.length + ". Try again";
                return result;
            }

        }catch (NumberFormatException e){
            result = "Please enter a valid number";
            return result;
        }


        for (int i=0;i<locationCells.length;i++){

            if ( (guess == i ) && locationCells[i]!=0 ){
                if (locationCells[i] < 0 ) {
                    result = "You already shot at this target";
                    break;
                }else{

                    result = "You hit the target";
                    locationCells[guess] = -locationCells[guess];
                    if (checkShipIsAlive(i)){
                        result= result +"\nShip is wound";
                    }else{
                        result= result +"\nShip was destroyed";
                        destroyedShipsCount++;
                    }
                    break;
                }
            }
        }


        if (destroyedShipsCount==ShipsCount) result = result +"\nAll ships are destroyed!";
        return result;
    }

    public boolean checkShipIsAlive(int position){

        int shipSize = Math.abs( locationCells[position] );
        for (int i=position;i<locationCells.length;i++){
            System.out.println(i +" " + locationCells[i]);
            if (locationCells[i]==0){
                break;
            }

            if (locationCells[i] == shipSize) {
                return true;  // ship is alive

            }
        }

        for (int i=position; i>=0; i--){
            if (locationCells[i]==0){
                break;
            }

            if (locationCells[i] == shipSize) {
                return true;  // ship is alive
            }
        }

        return false;
    }
}
