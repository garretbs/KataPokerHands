
package katapokerhands;

public class PokerHand {
    public PokerCard[] cards; //The cards within the hand. 5 count
    
    public PokerHand(){
        cards = new PokerCard[5];
        for(int i=0;i<5;i++){
            cards[i] = new PokerCard(5, PokerCard.Suit.SPADE);
        }
    }
}
