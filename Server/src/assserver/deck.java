package assserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 *
 * @author Shinobi
 */
public class deck
{
    int maxCards;
    String[] suit = {"Clubs","Diamonds", "Hearts", "Spades"};
    String[] card = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
                        "Jack", "Queen", "King"};
    String addedElement;
    int value, totalVal;
    ArrayList<cards> cards = new ArrayList();
  deck()
   {
       maxCards=52;
       addedElement = null;
       value = totalVal = 0;      
   }
  public void createDeck()
  {
    for (int i = 0; i <4;i++)
    {
           for (int j = 0; j < 13; j++)
          {
              cards tempCard = new cards();
              tempCard.cardFace = card[j];
              tempCard.cardSuit = suit[i];
              String tempStr = tempCard.cardFace;
              switch (tempStr)
              {
                  case "Ace":value=11; break;
                  case "1": value=1;break;
                  case "2": value=2;break;
                  case "3": value=3;break;
                  case "4": value=4;break;
                  case "5": value=5;break;
                  case "6": value=6;break;
                  case "7": value=7;break;
                  case "8": value=8;break;
                  case "9": value=9;break;
                  case "10": value=10; break;
                  case "Jack": value=10; break;
                  case "Queen": value=10; break;
                  case "King": value=10; break;
              }
              tempCard.cardValue=value;
              cards.add(tempCard);
          }
    }
    long seed = System.nanoTime();
    Collections.shuffle(cards, new Random (seed) );
  }
  public cards getCard(int n)
  {
      return cards.get(n);
  }
  public void removeCard(int n)
  {
      cards.remove(n);
  }
}

