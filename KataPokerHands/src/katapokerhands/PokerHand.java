
package katapokerhands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class PokerHand {
    
    public ArrayList<PokerCard> cards = new ArrayList<>(); //The cards within the hand. 5 count
    
    public static enum HandType{
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
    
    private static final TreeMap<HandType, Integer> HAND_RANKS = new TreeMap<HandType, Integer>(){{
        put(HandType.STRAIGHT_FLUSH, 9);
        put(HandType.FOUR_OF_A_KIND, 8);
        put(HandType.FULL_HOUSE, 7);
        put(HandType.FLUSH, 6);
        put(HandType.STRAIGHT, 5);
        put(HandType.THREE_OF_A_KIND, 4);
        put(HandType.TWO_PAIRS, 3);
        put(HandType.PAIR, 2);
        put(HandType.HIGH_CARD, 1);
    }};
    
    private HandType handType;
    private int handValue;
    
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
        
        //Sort the cards based on value. Necessary for determining certain hand
        //types.
        cards.sort(Comparator.comparingInt(PokerCard::getValue));
    }
    
    public int getHandRank(){
        return HAND_RANKS.get(handType);
    }
    
    public int getHandValue(){
        return handValue;
    }
    
    public HandType getHandType(){
        return handType;
    }
    
    public void determineHandType(){
        
        //The cards have been sorted by value in ascending order.
        
        if(isStraightFlush()){
            handType = HandType.STRAIGHT_FLUSH;
        }
        else if(isFourOfAKind()){
            handType = HandType.FOUR_OF_A_KIND;
        }
        else if(isFullHouse()){
            handType = HandType.FULL_HOUSE;
        }
        else if(isFlush()){
            handType = HandType.FLUSH;
        }
        else if(isStraight()){
            handType = HandType.STRAIGHT;
        }
        else if(isThreeOfAKind()){
            handType = HandType.THREE_OF_A_KIND;
        }
        else if(isTwoPairs()){
            handType = HandType.TWO_PAIRS;
        }
        else if(isPair()){
            handType = HandType.PAIR;
        }
        else{
            //High Card: Hands which do not fit any higher category are ranked by
            //the value of their highest card. If the highest cards have the same
            //value, the hands are ranked by the next highest, and so on.
            handType = HandType.HIGH_CARD;
            handValue = cards.get(cards.size()-1).getValue();
        }
    }
    
    public boolean isStraightFlush(){
        //Straight flush: 5 cards of the same suit with consecutive values.
        //Ranked by the highest card in the hand.
        
        if(isStraight() && isFlush()){
            handValue = cards.get(cards.size()-1).getValue();
            return true;
        }
        
        return false;
    }
    
    public boolean isFourOfAKind(){
        //Four of a kind: 4 cards with the same value. Ranked by the value of
        //the 4 cards.
        List<PokerCard> subList;
        
        //First possibility: first four cards are the same value
        subList = cards.subList(0, cards.size()-1);
        if(haveSameValue(subList)){
            handValue = subList.get(0).getValue();
            return true;
        }
        
        //Second possibility: last four cards are the same value
        subList = cards.subList(1, cards.size());
        if(haveSameValue(subList)){
            handValue = subList.get(0).getValue();
            return true;
        }
        
        return false;
    }
    
    public boolean isFullHouse(){
        //Full House: 3 cards of the same value, with the remaining 2 cards
        //forming a pair. Ranked by the value of the 3 cards.
        List<PokerCard> firstList;
        List<PokerCard> secondList;
        
        //First possibility: first two cards are a pair
        firstList = cards.subList(0, 2);
        secondList = cards.subList(2, cards.size());
        if(haveSameValue(firstList) && haveSameValue(secondList)){
            handValue = secondList.get(0).getValue();
            return true;
        }
        
        //Second possibility: last two cards are a pair
        firstList = cards.subList(0, 3);
        secondList = cards.subList(3, cards.size());
        if(haveSameValue(firstList) && haveSameValue(secondList)){
            handValue = firstList.get(0).getValue();
            return true;
        }
        
        return false;
    }
    
    public boolean isFlush(){
        //Flush: Hand contains 5 cards of the same suit. Hands which are both
        //flushes are ranked using the rules for High Card.
        
        if(haveSameSuit(cards)){
            handValue = cards.get(cards.size()-1).getValue();
            return true;
        }
        
        return false;
    }
    
    public boolean isStraight(){
        //Straight: Hand contains 5 cards with consecutive values. Hands which
        //both contain a straight are ranked by their highest card.
        
        PokerCard previousCard = cards.get(0);
        PokerCard currentCard;
        for(int i=1;i<cards.size();i++){
            currentCard = cards.get(i);
            
            //Not consecutive value.
            if(currentCard.getValue() != (previousCard.getValue()+1)){
                return false;
            }
            previousCard = currentCard;
        }
        
        //All cards are the same suit and are consecutive in value
        handValue = cards.get(cards.size()-1).getValue();
        return true;
    }
    
    public boolean isThreeOfAKind(){
        //Three of a Kind: Three of the cards in the hand have the same value.
        //Hands which both contain three of a kind are ranked by the value of
        //the 3 cards.
        
        List<PokerCard> threeCards;
        
        //First possibility: first three cards match
        threeCards = cards.subList(0, 3);
        if(haveSameValue(threeCards)){
            handValue = threeCards.get(0).getValue();
            return true;
        }
        
        //Second possibility: middle three cards match
        threeCards = cards.subList(1, 4);
        if(haveSameValue(threeCards)){
            handValue = threeCards.get(0).getValue();
            return true;
        }
        
        //Third possibility: last three cards match
        threeCards = cards.subList(2, 5);
        if(haveSameValue(threeCards)){
            handValue = threeCards.get(0).getValue();
            return true;
        }
        return false;
    }
    
    public boolean isTwoPairs(){
        //Two Pairs: The hand contains 2 different pairs. Hands which both
        //contain 2 pairs are ranked by the value of their highest pair. Hands
        //with the same highest pair are ranked by the value of their other pair.
        //If these values are the same the hands are ranked by the value of the
        //remaining card.
        
        List<PokerCard> pair1;
        List<PokerCard> pair2;
        
        //First possibility: first four cards are two pairs
        pair1 = cards.subList(0, 2);
        pair2 = cards.subList(2, 4);
        if(haveSameValue(pair1) && haveSameValue(pair2)){
            handValue = pair2.get(0).getValue();
            return true;
        }
        
        //Second possibility: First two and last two cards are pairs
        pair1 = cards.subList(0, 2);
        pair2 = cards.subList(3, 4);
        if(haveSameValue(pair1) && haveSameValue(pair2)){
            handValue = pair2.get(0).getValue();
            return true;
        }
        
        //Third possibility: Last four cards are two pairs
        pair1 = cards.subList(1, 3);
        pair2 = cards.subList(3, 4);
        if(haveSameValue(pair1) && haveSameValue(pair2)){
            handValue = pair2.get(0).getValue();
            return true;
        }
        return false;
    }
    
    public boolean isPair(){
        //Pair: 2 of the 5 cards in the hand have the same value. Hands which
        //both contain a pair are ranked by the value of the cards forming the
        //pair. If these values are the same, the hands are ranked by the values
        //of the cards not forming the pair, in decreasing order.
        
        List<PokerCard> pair;
        
        //First possibility: first two cards
        pair = cards.subList(0, 2);
        if(haveSameValue(pair)){
            handValue = pair.get(0).getValue();
            return true;
        }
        
        //Second possibility: cards 2 and 3
        pair = cards.subList(1, 3);
        if(haveSameValue(pair)){
            handValue = pair.get(0).getValue();
            return true;
        }
        
        //Third possibility: cards 3 and 4
        pair = cards.subList(2, 4);
        if(haveSameValue(pair)){
            handValue = pair.get(0).getValue();
            return true;
        }
        
        //Fourth possibility: last two cards
        pair = cards.subList(3, 5);
        if(haveSameValue(pair)){
            handValue = pair.get(0).getValue();
            return true;
        }
        return false;
    }
    
    private boolean haveSameValue(List<PokerCard> subList){
        int value = subList.get(0).getValue();
        for(int i=1;i<subList.size();i++){
            if(subList.get(i).getValue() != value){
                return false;
            }
        }
        return true;
    }
    
    private boolean haveSameSuit(List<PokerCard> subList){
        PokerCard firstCard = subList.get(0);
        for(int i=1;i<subList.size();i++){
            if(subList.get(i).getSuit() != firstCard.getSuit()){
                return false;
            }
        }
        return true;
    }
}