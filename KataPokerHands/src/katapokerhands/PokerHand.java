
package katapokerhands;

import java.util.ArrayList;

public class PokerHand {
    
    public ArrayList<PokerCard> cards = new ArrayList<>(); //The cards within the hand. 5 count
    
    public enum HandType{
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_A_KIND,
        TWO_PAIRS,
        PAIR,
        HIGH_CARD,
    }
    
    private HandType handType;
    private PokerCard handValue;
    
    public PokerHand(String[] handInfo) throws Exception{
        if(handInfo.length != 6){ //5 cards plus label
            throw new Exception("Hand does not contain valid number of cards");
        }
        
        //Build each of hand's cards from input string
        for(String cardInfo : handInfo){
            if(cardInfo.length() == 2){ //Ignore black/white player label
                cards.add(new PokerCard(cardInfo));
            }
        }
        
    }
    
    public int getTotalValue(){
        
        if(isStraightFlush()){
            handType = HandType.STRAIGHT_FLUSH;
            return highestCard().getValue();
        }
        
        if(isFourOfAKind()){
            handType = HandType.FOUR_OF_A_KIND;
            return highestCard().getValue();
        }
        
        if(isFullHouse()){
            handType = HandType.FULL_HOUSE;
            return highestCard().getValue();
        }
        
        if(isFlush()){
            handType = HandType.FLUSH;
            return highestCard().getValue();
        }
        
        if(isStraight()){
            handType = HandType.STRAIGHT;
            return highestCard().getValue();
        }
        
        if(isThreeOfAKind()){
            handType = HandType.THREE_OF_A_KIND;
            return highestCard().getValue();
        }
       
        if(isTwoPairs()){
            handType = HandType.TWO_PAIRS;
            return highestCard().getValue();
        }
        
        if(isPair()){
            handType = HandType.PAIR;
            return highestCard().getValue();
        }
        
        //High Card: Hands which do not fit any higher category are ranked by
        //the value of their highest card. If the highest cards have the same
        //value, the hands are ranked by the next highest, and so on.
        handType = HandType.HIGH_CARD;
        return highestCard().getValue();
    }
    
    private boolean isStraightFlush(){
        //Straight flush: 5 cards of the same suit with consecutive values.
        //Ranked by the highest card in the hand.
        //should just use boolean here. if true, calculate value and return
        for(PokerCard card : cards){
            //If any cards do not match the first card's suit, it cannot be a
            //straight flush. Move down the rank and continue.
            if(card.getSuit() != cards.get(0).getSuit()){
                return false;
            }
        }
        return false;
    }
    
    private boolean isFourOfAKind(){
        //Four of a kind: 4 cards with the same value. Ranked by the value of
        //the 4 cards.
        return false;
    }
    
    private boolean isFullHouse(){
        //Full House: 3 cards of the same value, with the remaining 2 cards
        //forming a pair. Ranked by the value of the 3 cards.
        return false;
    }
    
    private boolean isFlush(){
        //Flush: Hand contains 5 cards of the same suit. Hands which are both
        //flushes are ranked using the rules for High Card.
        return false;
    }
    
    private boolean isStraight(){
        //Straight: Hand contains 5 cards with consecutive values. Hands which
        //both contain a straight are ranked by their highest card.
        return false;
    }
    
    private boolean isThreeOfAKind(){
        //Three of a Kind: Three of the cards in the hand have the same value.
        //Hands which both contain three of a kind are ranked by the value of
        //the 3 cards.
        return false;
    }
    
    private boolean isTwoPairs(){
        //Two Pairs: The hand contains 2 different pairs. Hands which both
        //contain 2 pairs are ranked by the value of their highest pair. Hands
        //with the same highest pair are ranked by the value of their other pair.
        //If these values are the same the hands are ranked by the value of the
        //remaining card.
        return false;
    }
    
    private boolean isPair(){
        //Pair: 2 of the 5 cards in the hand have the same value. Hands which
        //both contain a pair are ranked by the value of the cards forming the
        //pair. If these values are the same, the hands are ranked by the values
        //of the cards not forming the pair, in decreasing order.
        return false;
    }
    
    public PokerCard highestCard(){
        return cards.get(0);
    }
}