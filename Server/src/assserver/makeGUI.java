package assserver;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Shinobi
 */
public class makeGUI extends JFrame {
    // makeServer leServer = new makeServer();
     JButton cServer, sGame;
     JTextArea chat, p1h, p2h, p3h, p4h; //player text areas
     JScrollPane cPanel;
     JPanel p1, p2, p3, p4, bp; //panels for each player and button pane
     Border bl;
     Font leFont;
     String p1n, p2n, p3n, p4n;
     TitledBorder p, cb, p1tb, p2tb, p3tb, p4tb;
     private model vModel;
     
     makeGUI(model m)
     {
         vModel = m;
    //create global instances to be used and set initial layout to null
         bl = BorderFactory.createLineBorder(Color.black);
         setLayout(null);
         
         //create the button panel
         bp = new JPanel();
         bp.setBounds(17, 0, 500, 40);
         bp.setBorder(bl);
         //create the buttons and add them to the panel
         cServer = new JButton("Create Server");
         cServer.setBounds(0, 0, 60, 30);
         cServer.setToolTipText("This button creates the server");
         bp.add(cServer);
         sGame = new JButton("Start Game");
         sGame.setBounds(0, 0, 60, 30);
         sGame.setToolTipText("This Button Starts The Game");
         sGame.setEnabled(false);
         //bp.add(sGame);
         
         //create the chat area
         cb = BorderFactory.createTitledBorder(bl, "Chat");
         chat = new JTextArea("");
         chat.setBorder(bl);
         chat.setEditable(false);
         chat.setPreferredSize(new Dimension(500, 1000));
         cPanel = new JScrollPane
                 (chat,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         cPanel.setBounds(17, 45, 500, 200);
         cPanel.setBorder(cb);
         
         //create the player areas
         leFont = new Font("Arial", Font.PLAIN, 10);
         //P1   
         p1n = "Tyrion";
         p1tb = cb = BorderFactory.createTitledBorder(bl, "Player 1:");
         p1 = new JPanel();
         p1.setBounds(15, 250, 250, 200);
         p1.setBorder(p1tb);
         p1h = new JTextArea();
         p1h.setEditable(false);
         p1h.setPreferredSize(new Dimension(200,160));
         p1h.setFont(leFont);
         p1.add(p1h);
         //P2
         p2n = "Hodor";
         p2tb = cb = BorderFactory.createTitledBorder(bl, "Player 2:");
         p2 = new JPanel();
         p2.setBounds(270, 250, 250, 200);
         p2.setBorder(p2tb);
         p2h = new JTextArea();
         p2h.setEditable(false);
         p2h.setPreferredSize(new Dimension(200,160));
         p2h.setFont(leFont);
         p2.add(p2h);
         //P3
         p3n = "Ned Stark";
         p3tb = cb = BorderFactory.createTitledBorder(bl, "Player 3:");
         p3 = new JPanel();
         p3.setBounds(15, 455, 250, 200);
         p3.setBorder(p3tb);
         p3h = new JTextArea();
         p3h.setEditable(false);
         p3h.setPreferredSize(new Dimension(200,160));
         p3h.setFont(leFont);
         p3.add(p3h);         
         //P4
         p4n = "Daenerys";
         p4tb = cb = BorderFactory.createTitledBorder(bl, "Player 4:");
         p4 = new JPanel();
         p4.setBounds(270, 455, 250, 200);
         p4.setBorder(p4tb);
         p4h = new JTextArea();
         p4h.setEditable(false);
         p4h.setPreferredSize(new Dimension(200,160));
         p4h.setFont(leFont);
         p4.add(p4h);         
    
         //Set the main window properties and add all the things
         add(bp);
         add(cPanel);
         add(p1);
         add(p2);
         add(p3);
         add(p4);
         setTitle("Pontoon: Server GUI");
         setSize(550, 700);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null);
      }
    
     
     void chatAppend(String s)
     {
         chat.append(s+"\n");
     }
     
     void setPlayerName(int id, String n)
     {
         if(id==0){p1n = n;}
         else if(id==1){p2n = n;}
         else if(id==2){p3n = n;}
         else{p4n = n;}
      }
     
     public void addStartActionListener(ActionListener m)
     {
         cServer.addActionListener(m);
     }
     
     public void addGameActionListener(ActionListener mt)
     {
         sGame.addActionListener(mt);
     }
     
}
