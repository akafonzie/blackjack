package assserver;
/**
 * @author Shinobi
 */
public class player {
    int pTotal, tCount, nCards;
    String pName;
    hand pHand;
    boolean isRobut=false, isPlayer=false, turnOver = false, bust = false;
    boolean winrar=false;
    player()
    {
        pHand = new hand();
        pHand.drawCards();
        pTotal = pHand.totalVal;
        pName = "";
        nCards = 2;
    }
    
    public void isTwisting()
    {
        if(tCount <=5)
        {
            pHand.twist();
            pTotal=pHand.totalVal;
            tCount++;
            nCards++;
        }
    }
    
    public void isSticking()
    {
        pHand.stick = true;
        pHand.twist = false;
        pTotal = pHand.totalVal;
    }
}
