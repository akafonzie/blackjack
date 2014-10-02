package assplayer;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author Shinobi
 */

public class makeGUI extends JFrame{
       connect bum;
       JFrame nameFrame;
       JButton conn, stick, twist, game, submit, nSub;
       JTextArea hand, chat, input, entry, pTotal;
       JScrollPane cPanel;
       String playerName;
       Border bl;
       TitledBorder hb, cb, ib, pt;
       boolean addName;
       private model vModel;
    makeGUI(model m)
    {
        vModel = m;
        setLayout(null);
        setFocusable(true);
            //create some buttons and add them to the layout
        //connect button
        conn = new JButton("Connect");
        conn.setBounds(176,10,100,20);
        conn.setToolTipText("Connect to the server!");
        //new game button
        game = new JButton("New Game");
        game.setBounds(176,40,100,20);
        game.setToolTipText("Start a New Game!");
        game.setEnabled(false);
        //twist button
        twist = new JButton("Twist");
        twist.setBounds(176,70,100,20);
        twist.setToolTipText("Click to Twist!");
        twist.setEnabled(false);
        //stick button
        stick = new JButton("Stick");
        stick.setBounds(176, 100, 100, 20);
        stick.setToolTipText("Click to Stick!");
        stick.setEnabled(false);

        
        //              add text layouts
        
        //hand text panel
        bl = BorderFactory.createLineBorder(Color.black);
        hb = BorderFactory.createTitledBorder(bl, "Player Hand");
        hb.setTitleJustification(TitledBorder.ABOVE_TOP);
        JPanel hPanel = new JPanel();
        hand = new JTextArea("");
        hand.setBorder(hb);
        hand.setEditable(false);
        hand.setPreferredSize(new Dimension(160,112));
        hPanel.add(hand);
        hPanel.setBounds(3, 5, 160, 115);
        
        //chat text panel
        chat = new JTextArea("");
        cb = BorderFactory.createTitledBorder(bl, "Chat");
        cb.setTitleJustification(TitledBorder.ABOVE_TOP);
        chat.setBorder(cb);
        chat.setEditable(false);
        chat.setPreferredSize(new Dimension(272, 1000));
        cPanel = new JScrollPane
                (chat,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cPanel.setBounds(5, 170, 272, 135);
        
        //player score display
        pTotal = new JTextArea("");
        pt = BorderFactory.createTitledBorder(bl, "Total");
        JPanel ptp = new JPanel();
        pTotal.setEditable(false);
        pTotal.setPreferredSize(new Dimension(130, 45));
        pTotal.setBorder(pt);
        ptp.setBounds(28, 115, 140, 50);
        ptp.add(pTotal);
        
        //chat entry panel
        input = new JTextArea();
        input.setToolTipText("Enter your text to chat");
        ib = BorderFactory.createTitledBorder(bl, "Enter chat text");
        input.setBorder(ib);
        input.setBounds(5, 310, 240, 40);
        submit = new JButton("...");
        submit.setBounds(245,318,30,30);
        submit.setToolTipText("Enter yo text");
        //submit.addActionListener(this);
        //set the window properties & add tings
        add(conn);
        add(game);
        add(twist);
        add(stick);
        add(hPanel);
        add(ptp);
        add(cPanel);
        add(input);
        add(submit);
        
        setTitle("Pontoon: player GUI");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
      
        nameFrame = new JFrame();
        nameFrame.setLocation(this.getLocation().x,
                        this.getLocation().y+300);
        nameFrame.setSize(300, 80);
        nameFrame.setVisible(false);
        nameFrame.setTitle("Enter your name, plx");
        nameFrame.setLayout(null);

        nSub = new JButton("Submit");
        entry = new JTextArea();
        //submit button
        nSub.setToolTipText("submit your name, plx");
        nSub.setBounds(190, 10, 80, 20);
        //entry field
        
        entry.setBounds(10, 10, 140, 20);
        entry.setBorder(bl);
        entry.setEditable(true);
        entry.setToolTipText("Maximum of 16 characters, "
                + "anything more than "
                + "this willl be truncated!");

        //set the main frame properties
        nameFrame.add(nSub);
        nameFrame.add(entry);
    }

void addConnActionListener(ActionListener e)
{
    conn.addActionListener(e);
}

void addNGActionListener(ActionListener e)
{
    game.addActionListener(e);
}

void addTwistActionListener(ActionListener e)
{
    twist.addActionListener(e);
}

void addStickActionListener(ActionListener e)
{
    stick.addActionListener(e);
}

void addSubActionListener(ActionListener e)
{
    submit.addActionListener(e);
}

void addNSubActionListener(ActionListener e)
{
    nSub.addActionListener(e);
}
    
void truncateName()
    {
        String t = entry.getText();
        if(t.length() > 16)
        {
            t = t.substring(0, 16);
            playerName = t;
            entry.setText("");
        }
        else
        {
            playerName = t;
            entry.setText("");
        }
    }
}