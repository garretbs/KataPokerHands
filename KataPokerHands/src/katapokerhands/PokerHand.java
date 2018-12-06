
package katapokerhands;

import java.util.ArrayList;

public class PokerHand {
    
    public ArrayList<PokerCard> cards; //The cards within the hand. 5 count
    
    public PokerHand(String[] handInfo) throws Exception{
        if(handInfo.length != 6){ //5 cards plus label
            throw new Exception("Hand does not contain valid number of cards");
        }
        for(String cardInfo : handInfo){
            if(cardInfo.length() == 2){ //Ignore black/white label
                cards.add(new PokerCard(cardInfo));
            }
        }
        //iterate through and separate into number-letter pairs
        //create new pokercard based on those
        //error if unknown symbol
    }
}
