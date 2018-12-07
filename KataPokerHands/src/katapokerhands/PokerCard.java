
package katapokerhands;

import java.util.TreeMap;

public class PokerCard {
    
    public static enum Suit{
        CLUB, SPADE, DIAMOND, HEART
    }
    
    private final static TreeMap<Character, Integer> CARD_VALUES = new TreeMap<Character, Integer>(){{
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('T', 10);
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};
    
    private final static TreeMap<Character, Suit> SUIT_VALUES = new TreeMap<Character, Suit>(){{
        put('C', Suit.CLUB);
        put('S', Suit.SPADE);
        put('D', Suit.DIAMOND);
        put('H', Suit.HEART);
    }};
    
    //For scoring purposes, the suits are unordered while the values are ordered
    //as given above, with 2 being the lowest and ace the highest value.
    
    private final char value; //the number/face value (e.g. 4, 9, ace, queen, etc.)
    private final char suit; //club, space, diamond, heart
    
    public PokerCard(String cardInfo){
        value = cardInfo.charAt(0);
        suit = cardInfo.charAt(1);
    }
    
    public int getValue(){
        //error if unknown symbol
        return CARD_VALUES.get(value);
    }
    
    public Suit getSuit(){
        return SUIT_VALUES.get(suit);
    }
}
