package assserver;

/**
 *
 * @author Shinobi
 */
public class robut extends player{
    int rTotal, rCount, rnCards;
    String rName;
    hand rHand;
    robut(String n)
    {
        //System.out.println("New Robut");
        rHand = new hand();
        rHand.drawCards();
        rTotal = rHand.totalVal;
        rName = n;
        nCards = 2;
    }
}
