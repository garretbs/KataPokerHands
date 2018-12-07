
package katapokerhands;

public class PokerHandComparer {
    //Receives two hands, reports which one wins or if they tie
    
    public enum Result{
        HAND_1, HAND_2, EQUAL, UNKNOWN
    }
    
    private void compareEqualHandTypes(){
        //If hand types are both equal, compare their rank
    }
    
    public Result compare(PokerHand hand1, PokerHand hand2){
        if(hand1.getTotalValue() > hand2.getTotalValue()){
            return Result.HAND_1;
        }
        if(hand2.getTotalValue() > hand1.getTotalValue()){
            return Result.HAND_2;
        }
        if(hand1.getTotalValue() == hand2.getTotalValue()){
            return Result.EQUAL;
        }
        return Result.UNKNOWN;
    }
}
