/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assserver;

/**
 *
 * @author Shinobi
 */
public class hand{
    int maxCardsInHand;
    boolean twist, stick, Pontoon, fCT;
    cards card1, card2, card3, card4, card5;
    int twistCount, totalVal;
    deck newDeck = new deck();
    hand()
    {
        twist=Pontoon=stick=false;
        card1=card2=card3=card4=card5=null;
        twistCount=totalVal=0;
        maxCardsInHand=5;
        newDeck.createDeck();
    }
    
     public void drawCards()
    {
        card1 = newDeck.getCard(0);
        newDeck.removeCard(0);
        totalVal += card1.cardValue;
        maxCardsInHand+=1;
        card2 = newDeck.getCard(0);
        newDeck.removeCard(0);
        totalVal+=card2.cardValue;
        maxCardsInHand+=1;
        if("Ace".equals(card1.cardFace) && 
                ("King".equals(card2.cardFace) ||
                "Queen".equals(card2.cardFace)|| 
                "Jack".equals(card2.cardFace)))
        {
            Pontoon=true;
        }
        else if("Ace".equals(card2.cardFace) && 
                ("King".equals(card1.cardFace) 
                ||"Queen".equals(card1.cardFace)
                || "Jack".equals(card1.cardFace)))
        {
            Pontoon=true;
        }
        else {Pontoon=false;}
    }
     public void twist()
     {
      if(twistCount == 0)
        {
            card3 = newDeck.getCard(0);
            newDeck.removeCard(0);
            if("Ace".equals(card3.cardFace)
                    &&totalVal>=11){card3.cardValue=1;}
            totalVal+=card3.cardValue;
            twistCount++;
            maxCardsInHand+=1;
        }
        else if(twistCount ==1)
        {
            card4 = newDeck.getCard(0);
            newDeck.removeCard(0);
            if("Ace".equals(card4.cardFace)
                    &&totalVal>=11){card4.cardValue=1;}
            totalVal+=card4.cardValue;
            twistCount++;
            maxCardsInHand+=1;
        }
        else if(twistCount ==2)
        {
            card5 = newDeck.getCard(0);
            newDeck.removeCard(0);
            if("Ace".equals(card5.cardFace)
                    &&totalVal>=11){card5.cardValue=1;}
            totalVal+=card5.cardValue;
            twistCount++;
            maxCardsInHand+=1;
            fCT = true; //5 card trick
        }
     }
}
