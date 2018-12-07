
package katapokerhands;

public class PokerHandComparer {
    //Receives two hands, reports which one wins or if they tie
    
    public enum Result{
        HAND_1, HAND_2, EQUAL, UNKNOWN
    }
    
    private void compareEqualHandTypes(){
        //If hand types are both equal, compare their rank
    }
    
    private void compareTwoPairs(){
        //Special comparison
    }
    
    private void comparePairs(){
        //Special comparison
    }
    
    public Result compare(PokerHand hand1, PokerHand hand2){
        hand1.determineHandType();
        hand2.determineHandType();
        int hand1Rank = hand1.getHandRank();
        int hand2Rank = hand2.getHandRank();
        int hand1Value;
        int hand2Value;
        
        System.out.println(hand1.getHandType());
        //Hand1 is the better hand
        if(hand1Rank > hand2Rank){
            return Result.HAND_1;
        }
        
        //Hand2 is the better hand
        if(hand2Rank > hand1Rank){
            return Result.HAND_2;
        }
        
        //Both hands are the same type, so now we must do more special comparisons.
        
        switch(hand1.getHandType()){
            case TWO_PAIRS:
                //hands with the same highest pair are ranked by the value of their
                //other pair. If these values are the same the hands are ranked by the
                //value of the remaining card.
                break;
            case PAIR:
                //If these values are the same, the hands are ranked by the values
                //of the cards not forming the pair, in decreasing order.
                break;
            
            //Values predetermined from hand type calculations
            default:
                hand1Value = hand1.getHandValue();
                hand2Value = hand2.getHandValue();
                if(hand1Value > hand2Value){
                    return Result.HAND_1;
                }

                if(hand2Value > hand1Value){
                    return Result.HAND_2;
                }

                return Result.EQUAL;
        }
        return Result.UNKNOWN;
    }
}
