
package katapokerhands;

public class PokerHandComparer {
    //Receives two hands, reports which one wins or if they tie
    
    public enum Result{
        HAND_1, HAND_2, EQUAL, UNKNOWN
    }
    
    public Result compare(PokerHand hand1, PokerHand hand2){
        hand1.determineHandType();
        hand2.determineHandType();
        int hand1Rank = hand1.getHandRank();
        int hand2Rank = hand2.getHandRank();
        
        //Hand1 is the better hand
        if(hand1Rank > hand2Rank){
            return Result.HAND_1;
        }
        
        //Hand2 is the better hand
        if(hand2Rank > hand1Rank){
            return Result.HAND_2;
        }
        
        //Both hands are the same type, so now we must do more special comparisons.
        int hand1Value;
        int hand2Value;        
        
        hand1Value = hand1.getHandValue();
        hand2Value = hand2.getHandValue();
        if(hand1Value > hand2Value){
            return Result.HAND_1;
        }

        if(hand2Value > hand1Value){
            return Result.HAND_2;
        }
        
        //Still possible that the pair type hands rank differently
        switch(hand1.getHandType()){
            
            case TWO_PAIRS:
                //hands with the same highest pair are ranked by the value of their
                //other pair.
                hand1Value = hand1.getLowerPairValue();
                hand2Value = hand2.getLowerPairValue();
                if(hand1Value > hand2Value){
                    return Result.HAND_1;
                }

                if(hand2Value > hand1Value){
                    return Result.HAND_2;
                }
                
                //If these values are the same the hands are ranked by the value
                //of the remaining card.
                hand1Value = hand1.getRemainingCardValue();
                hand2Value = hand2.getRemainingCardValue();
                if(hand1Value > hand2Value){
                    return Result.HAND_1;
                }

                if(hand2Value > hand1Value){
                    return Result.HAND_2;
                }
                
                //Must be equal at this point
                return Result.EQUAL;
                
            case PAIR:
                //If these values are the same, the hands are ranked by the values
                //of the cards not forming the pair, in decreasing order.
                for(int i=0;i<3;i++){
                    
                    hand1Value = hand1.getRemainingCardValue(i);
                    hand2Value = hand2.getRemainingCardValue(i);
                    if(hand1Value > hand2Value){
                        return Result.HAND_1;
                    }

                    if(hand2Value > hand1Value){
                        return Result.HAND_2;
                    }
                    
                }
                //Must be equal at this point
                return Result.EQUAL;
            
            //No further comparisons to make, so they are equal
            default:
                return Result.EQUAL;
                
        }
    }
}
