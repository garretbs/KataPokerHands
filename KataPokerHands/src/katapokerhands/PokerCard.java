
package katapokerhands;

public class PokerCard {
    
    public final static int TWO = 2;
    public final static int THREE = 3;
    public final static int FOUR = 4;
    public final static int FIVE = 5;
    public final static int SIX = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE = 9;
    public final static int TEN = 10;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;
    public final static int ACE = 14;
    
    
    public static enum Suit{
        CLUB, SPADE, DIAMOND, HEART
    }
    //For scoring purposes, the suits are unordered while the values are ordered
    //as given above, with 2 being the lowest and ace the highest value.
    
    public int value; //the number/face value (e.g. 4, 9, ace, queen, etc.)
    public Suit suit; //club, space, diamond, heart
    
    public PokerCard(int value_, Suit suit_){
        value = value_;
        suit = suit_;
    }
}
