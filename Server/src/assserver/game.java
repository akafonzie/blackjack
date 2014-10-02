package assserver;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author Shinobi
 */
public class game implements Runnable{
    player p1, p2, p3, p4;
    int p1t, p2t, p3t, p4t, rc=0, pc=0;
    hand p1h, p2h, p3h, p4h;
    boolean hasRobuts=false,winrar=false,nwinrar=false;
    player[] tp = new player[4];
    private makeGUI mGUI;
    private pdt pp = new pdt();

    game(player pp1, player pp2, player pp3, player pp4, makeGUI m)
    {
        mGUI = m;
        System.out.println("Starting game");
        pp.pal("Starting game");
        //set the class variables as passed variables
        p1=pp1;p2=pp2;p3=pp3;p4=pp4;
        //if null is passed create a new Robut player, set tings
        if(p1==null)
        {p1 = new robut("Robut 2");hasRobuts=true;rc++;p1.isRobut=true;}
        else{p1.isPlayer=true;pc++;}
        if(p2==null)
        {p2 = new robut("Robut 2");hasRobuts=true;rc++;p2.isRobut=true;}
        else{p2.isPlayer=true;pc++;}
        if(p3==null)
        {p3 = new robut("Robut 3");hasRobuts=true;rc++;p3.isRobut=true;}
         else{p3.isPlayer=true;pc++;}
        if(p4==null)
        {p4 = new robut("Robut 4");hasRobuts=true;rc++;p4.isRobut=true;}
         else{p4.isPlayer=true;pc++;}
        //set the totals of each player
        p1t = p1.pTotal;p2t = p2.pTotal;p3t = p3.pTotal;p4t = p4.pTotal;
        //set the hands of each player
        p1h = p1.pHand;p2h = p2.pHand;p3h = p3.pHand;p4h = p4.pHand; 
        //add each player to an array
        tp[0]=p1;tp[1]=p2;tp[2]=p3;tp[3]=p4;
        //set each player to 
    }
    public void run()
    {
        String ph1, ph2, ph3;
        int tt;
        //if the number of robot players is < 4 (0 -> 3 )
        if(hasRobuts)
        {
            String rh1, rh2, rt;
            for(int i = pc; i < 4; i++)
            {
                mGUI.chat.append("Player "+(i+1)+" is a Robut\n");
                pp.pal("Player "+(i+1)+" is a Robut\n");
                rh1 = "Card 1: "+tp[i].pHand.card1.cardFace+" of "
                        +tp[i].pHand.card1.cardSuit+"\n";
                rh2 = "Card 2: "+tp[i].pHand.card2.cardFace+" of "
                        +tp[i].pHand.card2.cardSuit+"\n";
                rt = "Total: "+tp[i].pTotal+"\n";
                if(i==1)
                {mGUI.p2h.append(rh1+rh2);pp.pal(rh1);pp.pal(rh2);}
                else if(i==2)
                {mGUI.p3h.append(rh1+rh2);pp.pal(rh1);pp.pal(rh2);}
                else if(i==3)
                {mGUI.p4h.append(rh1+rh2);pp.pal(rh1);pp.pal(rh2);}
                pause(500);
            }
        }
        //control the bot behavior
        if(rc<4)
        {
            //has player one's turn finished?
            ///////////////////////////////////////player 2 aggressive 
            if(p2.pTotal < 20 && !p2.bust) 
            {
                ph3 = "Player 2 twists\n";
                pp.pal(ph3);
                mGUI.p2h.append(ph3);
                p2.isTwisting();
                p2.pTotal = p2.pHand.totalVal;
                ph1 = "Card 3: "+p2h.card3.cardFace+" of "
                                +p2h.card3.cardSuit+"\n";
                ph2 = "Player 2 Total: "+p2.pTotal+"\n";
                mGUI.p2h.append(ph1);
                pp.pal(ph1);
                //if the total is still less than 20
                pause(2000);
                if(p2.pTotal<20 && !p2.bust) 
                {
                    pp.pal(ph3);
                    mGUI.p2h.append(ph3);
                    p2.isTwisting();
                    p2.pTotal = p2.pHand.totalVal;
                    ph1 = "Card 4: "+p2h.card4.cardFace+" of "
                                    +p2h.card4.cardSuit+"\n";
                    ph2 = "Player 2 Total: "+p2.pTotal+"\n";
                    mGUI.p2h.append(ph1);
                    pp.pal(ph1);
                    //if the total is still less than 20
                    pause(2000);
                    if(p2.pTotal < 20 && !p2.bust) 
                    {
                      pp.pal(ph3);
                      mGUI.p2h.append(ph3);
                      p2.isTwisting();
                      p2.pTotal = p2.pHand.totalVal;
                      ph1 = "Card 5: "+p2h.card5.cardFace+" of "
                                    +p2h.card5.cardSuit+"\n";
                      ph2 = "Player 2 Total: "+p2.pTotal+"\n";
                      mGUI.p2h.append(ph1);
                      pp.pal(ph1);
                       //mGUI.p2h.append(ph2);
                    }else
                        {p2.isSticking();
                        pp.pal("Player 2 is sticking\n");
                        mGUI.p2h.append("Player 2 is sticking\n");
                        mGUI.p2h.append(ph2);
                        p2.turnOver=true;
                        }
                }else
                    {p2.isSticking();
                    pp.pal("Player 2 is sticking\n");
                    mGUI.p2h.append("Player 2 is sticking\n");
                    mGUI.p2h.append(ph2);
                    pp.pal(ph2);
                    p2.turnOver=true;
                    }
            }else
                {p2.isSticking();
                pp.pal("Player 2 is sticking\n");
                mGUI.p2h.append("Player 2 is sticking\n");
                mGUI.p2h.append("Player 2 Total: "+p2.pTotal+"\n");
                pp.pal("Player 2 Total: "+p2.pTotal+"\n");
                p2.turnOver=true;
                }
            if(p2.pTotal>21)
            {
                p2.bust = true;
                mGUI.p2h.setForeground(Color.red);
                mGUI.p2h.append("Player 2 went Bust!\n");
                pp.pal("Player 2 went Bust!\n");
            }
            
            //////////////////////////////////////////Player 3 passive
            pause(2000);
            if( ( p3.pTotal < 16) && !p3.bust) 
            {
                ph3 = "Player 3 twists\n";
                pp.pal(ph3);
                mGUI.p3h.append(ph3);
                p3.isTwisting();
                p3.pTotal = p3.pHand.totalVal;
                ph1 = "Card 3: "+p3h.card3.cardFace+" of "
                                +p3h.card3.cardSuit+"\n";
                ph2 = "Player 3 Total: "+p3.pTotal+"\n";
                mGUI.p3h.append(ph1);
                pp.pal(ph1);
                //if the total is still less than 20
                pause(2000);
                if(p3.pTotal < 16  && !p3.bust) 
                {
                    pp.pal(ph3);
                    mGUI.p3h.append(ph3);
                    p3.isTwisting();
                    p3.pTotal = p3.pHand.totalVal;
                    ph1 = "Card 4: "+p3h.card4.cardFace+" of "
                                    +p3h.card4.cardSuit+"\n";
                    ph2 = "Player 3 Total: "+p3.pTotal+"\n";
                    mGUI.p3h.append(ph1);
                    pp.pal(ph1);
                    //if the total is still less than 20
                    pause(2000);
                    if(p3.pTotal < 16 && !p3.bust) 
                    {
                      System.out.println(ph3);
                      mGUI.p3h.append(ph3);
                      p3.isTwisting();
                      p3.pTotal = p3.pHand.totalVal;
                      ph1 = "Card 5: "+p3h.card5.cardFace+" of "
                                    +p3h.card5.cardSuit+"\n";
                      ph2 = "Player 3 Total: "+p3.pTotal+"\n";
                      mGUI.p3h.append(ph1);
                      pp.pal(ph1);
                    }else
                    {
                        p3.isSticking();
                        pp.pal("Player 3 is sticking\n");
                        mGUI.p3h.append("Player 3 is sticking\n");
                        mGUI.p3h.append(ph2);
                        p3.turnOver = true;
                    }    
                }else
                {
                    p3.isSticking();
                    pp.pal("Player 3 is sticking\n");
                    mGUI.p3h.append("Player 3 is sticking\n");
                    mGUI.p3h.append(ph2);
                    p3.turnOver = true;
                }
              }else
              {
                  p3.isSticking();
                  pp.pal("Player 3 is sticking\n");
                  mGUI.p3h.append("Player 3 is sticking\n");
                  mGUI.p3h.append("Player 3 Total: "+p3.pTotal+"\n");
                  pp.pal("Player 3 Total: "+p3.pTotal+"\n");
                  p3.turnOver = true;
              }
              
            if(p3.pTotal>21)
            {
                p3.bust = true;
                mGUI.p3h.setForeground(Color.red);
                mGUI.p3h.append("Player 3 went Bust!\n");
                pp.pal("Player 3 went Bust!\n");
            }
              
            //////////////////////////////////////////Player 4 safe
            pause(2000);
            if( (p4.pTotal < 14) && !p4.bust)
            {
                ph3 = "Player 4 twists\n";
                pp.pal(ph3);
                mGUI.p4h.append(ph3);
                p4.isTwisting();
                p4.pTotal = p4.pHand.totalVal;
                ph1 = "Card 3: "+p4h.card3.cardFace+" of "
                                +p4h.card3.cardSuit+"\n";
                ph2 = "Player 4 Total: "+p4.pTotal+"\n";
                mGUI.p4h.append(ph1);
                pp.pal(ph1);
                //if the total is still less than 20
                pause(2000);
                if(p4.pTotal<14 && !p4.bust)
                {
                    pp.pal(ph3);
                    mGUI.p4h.append(ph3);
                    p4.isTwisting();
                    p4.pTotal = p4.pHand.totalVal;
                    ph1 = "Card 4: "+p4h.card4.cardFace+" of "
                                    +p4h.card4.cardSuit+"\n";
                    ph2 = "Player 4 Total: "+p4.pTotal+"\n";
                    mGUI.p4h.append(ph1);
                    pp.pal(ph1);
                    //if the total is still less than 20
                    pause(2000);
                    if(p4.pTotal < 14 && !p4.bust)
                    {
                      System.out.println(ph3);
                      mGUI.p4h.append(ph3);
                      p4.isTwisting();
                      p4.pTotal = p4.pHand.totalVal;
                      ph1 = "Card 5: "+p4h.card5.cardFace+" of "
                                      +p4h.card5.cardSuit+"\n";
                      ph2 = "Player 4 Total: "+p4.pTotal+"\n";
                      mGUI.p4h.append(ph1);
                      pp.pal(ph1);
                    }else
                    {
                        p4.isSticking();
                        pp.pal("Player 4 is sticking\n");
                        mGUI.p4h.append("Player 4 is sticking\n");
                        mGUI.p4h.append(ph2);
                        p4.turnOver=true;
                    }
                } else
                    {
                        p4.isSticking();
                        pp.pal("Player 4 is sticking\n");
                        mGUI.p4h.append("Player 4 is sticking\n");
                        mGUI.p4h.append(ph2);
                        p4.turnOver=true;
                    }
              }else
              {
                  p4.isSticking();
                  pp.pal("Player 4 is sticking\n");
                  mGUI.p4h.append("Player 4 is sticking\n");
                  mGUI.p4h.append("Player 4 Total: "+p4.pTotal+"\n");
                  pp.pal("Player 4 Total: "+p4.pTotal+"\n");
                  p4.turnOver=true;
              }
            if(p4.pTotal>21)
            {
                p4.bust = true;
                mGUI.p4h.setForeground(Color.red);
                mGUI.p4h.append("Player 4 went Bust!\n");
                pp.pal("Player 4 went Bust!\n");
            }
}//end of if(rc<4);
        
        //end of game, decide winner
        if(p1.turnOver)
        {
            pp.pal("All players finished, Deciding Winner");
            mGUI.chat.append("All players finished, Deciding Winner");
            //get the totals for each player
            p1t = p1.pTotal;
            p2t = p2.pTotal;
            p3t = p3.pTotal;
            p4t = p4.pTotal;
            //do players have pontoon?
            boolean p1p = p1.pHand.Pontoon;
            boolean p2p = p2.pHand.Pontoon;
            boolean p3p = p3.pHand.Pontoon;
            boolean p4p = p4.pHand.Pontoon;
            //do players have a five card trick?
            boolean p1FCT = p1.pHand.fCT;
            boolean p2FCT = p2.pHand.fCT;
            boolean p3FCT = p3.pHand.fCT;
            boolean p4FCT = p4.pHand.fCT;
            //calculate who has been knocked out using p1 as the root
            if (p1t > 21)
                p1.bust = true;
            if (p2t > 21)
                p2.bust = true;
            if (p3t > 21)
                p3.bust = true;
            if (p4t > 21)
                p4.bust = true;
            
            
            ///////////////// 1 & 2 & 3 & 4
            if(!p1.bust && !p2.bust && !p3.bust && !p4.bust)
            {
                int[] scores = {p1t, p2t, p3t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+","
                //                      +scores[2]+","+scores[3]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                    pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            ///////////////// 1 & 2 & 3
            if(!p1.bust && !p2.bust && !p3.bust && p4.bust)
            {
                int[] scores = {p1t, p2t, p3t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]
                //                                +","+scores[2]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
            }
            
            ///////////////////////1 & 2 & 4
            if(!p1.bust && !p2.bust && p3.bust && !p4.bust)
            {
                int[] scores = {p1t, p2t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]
                //                                +","+scores[2]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                    pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            ///////////////// 1 & 3 & 4
            if(!p1.bust && p2.bust && !p3.bust && !p4.bust)
            {
                int[] scores = {p1t, p3t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]
                //                                +","+scores[2]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    nwinrar=true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                   pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            ///////////////// 2 & 3 & 4
            if(p1.bust && !p2.bust && !p3.bust && !p4.bust)
            {
                int[] scores = {p2t, p3t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]
                //                                +","+scores[2]+")");
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                   pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 1 & 2
            if(!p1.bust && !p2.bust && p3.bust && p4.bust)
            {
                int[] scores = {p1t, p2t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 1 & 3
            if(!p1.bust && p2.bust && !p3.bust && p4.bust)
            {
                int[] scores = {p1t, p3t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 1 & 4
            if(!p1.bust && p2.bust && p3.bust && !p4.bust)
            {
                int[] scores = {p1t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p1t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p1h.setForeground(Color.green);
                    mGUI.p1h.append(tmp);
                    pp.pal("Player 1 Wins!");
                    winrar = true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                    pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 2 & 3 
            if(p1.bust && !p2.bust && !p3.bust && p4.bust)
            {
                int[] scores = {p2t, p3t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 2 & 4
            if(p1.bust && !p2.bust && p3.bust && !p4.bust)
            {
                int[] scores = {p2t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p2t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p2h.setForeground(Color.green);
                    mGUI.p2h.append(tmp);
                    pp.pal("Player 2 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                    pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 3 & 4
            if(p1.bust && p2.bust && !p3.bust && !p4.bust)
            {
                int[] scores = {p3t, p4t};
                Arrays.sort(scores);
                //System.out.println("("+scores[0]+","+scores[1]+")");
                if(p3t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p3h.setForeground(Color.green);
                    mGUI.p3h.append(tmp);
                    pp.pal("Player 3 Wins!");
                    nwinrar=true;
                }
                if(p4t == scores[scores.length-1])
                {
                    String tmp = "You Win!";
                    mGUI.p4h.setForeground(Color.green);
                    mGUI.p4h.append(tmp);
                    pp.pal("Player 4 Wins!");
                    nwinrar=true;
                }
            }
            
            //////////////////////////// 1
            if(!p1.bust && p2.bust && p3.bust && p4.bust)
            {
                String tmp = "You Win!";
                mGUI.p1h.setForeground(Color.green);
                mGUI.p1h.append(tmp);
                pp.pal("Player 1 Wins!");
                nwinrar=true; 
            }
            //////////////////////////// 2
            if(p1.bust && !p2.bust && p3.bust && p4.bust)
            {
                String tmp = "You Win!";
                mGUI.p2h.setForeground(Color.green);
                mGUI.p2h.append(tmp);
                pp.pal("Player 2 Wins!");
                nwinrar=true; 
            }
            //////////////////////////// 3
            if(p1.bust && p2.bust && !p3.bust && p4.bust)
            {
                String tmp = "You Win!";
                mGUI.p3h.setForeground(Color.green);
                mGUI.p3h.append(tmp);
                pp.pal("Player 3 Wins!");
                nwinrar=true; 
            }            
            //////////////////////////// 4
            if(p1.bust && p2.bust && p3.bust && !p4.bust)
            {
                String tmp = "You Win!";
                mGUI.p4h.setForeground(Color.green);
                mGUI.p4h.append(tmp);
                pp.pal("Player 4 Wins!");
                nwinrar=true; 
            }            
            
        }//end of game
        pp.pal("GAME OVER MAN");
    }//end of run()
    
    
    public void pause(int del)
    {
    try
        {
           Thread.sleep(del);
        }
        catch (InterruptedException e)
        { return; }
    }
}//end of class
