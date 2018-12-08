
package katapokerhands;

import java.util.Scanner;

/**
    Your job is to compare several pairs of poker hands and to indicate which,
    if either, has a higher rank.
 */
public class KataPokerHands {
    

    public static void main(String[] args) throws Exception {
        /*
        Sample input:

        Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH
        Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S
        Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH
        Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH
        */
        Scanner inputReader = new Scanner(System.in);
        PokerHand black;
        PokerHand white;
        PokerHandComparer comparer = new PokerHandComparer();
        
        System.out.println("Please input the game info: ");
        String game = inputReader.nextLine();
        inputReader.close();
        String[] handsInfo = game.split("  "); //Two spaces separate the hands in the sample input
        
        if(handsInfo.length != 2){//Input does not have right number of arguments
            throw new Exception("Input format does not have two hands");
        }
        
        black = new PokerHand(handsInfo[0].split("\\s"));
        white = new PokerHand(handsInfo[1].split("\\s"));
        
        switch(comparer.compare(black, white)){
            case HAND_1:
                System.out.print("Black wins. ");
                System.out.println(black.getHandType());
                break;
            case HAND_2:
                System.out.print("White wins. ");
                System.out.println(white.getHandType());
                break;
            case EQUAL:
                System.out.print("Both hands are equal. ");
                System.out.println(white.getHandType());
                break;
            default:
                System.out.println("Unknown hand comparison");
                break;
        }
        
        //Each row of input is a game with two players. The first five cards
        //belong to the player named "Black" and the second five cards belong to
        //the player named "White".
        
    }
    
}
