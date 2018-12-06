
package katapokerhands;

public class PokerHandComparer {
    //Receives two hands, reports which one wins or if they tie
    
    public enum Result{
        HAND_1, HAND_2, EQUAL, UNKNOWN
    }
    
    public Result compare(PokerHand hand1, PokerHand hand2){
        return Result.UNKNOWN;
    }
}
