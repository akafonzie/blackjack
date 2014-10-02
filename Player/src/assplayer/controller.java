package assplayer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Shinobi
 */
public class controller {
    private model       m_Model;
    private makeGUI     m_View;
    private connect     m_Conn;
    
    controller(model m, makeGUI v, connect s)
    {
        m_Model     = m;
        m_View      = v;
        m_Conn      = s;
        //stick action listener
        v.addStickActionListener(new stickActionListener()); 
        //chat submit action listener
        v.addSubActionListener(new subActionListener());     
        //twist action listener
        v.addTwistActionListener(new twistActionListener()); 
        //new game action listener
        v.addNGActionListener(new NGActionListener());       
        //name submit action listener
        v.addNSubActionListener(new nSubActionListener());
        //connect action listener
        v.addConnActionListener(new connActionListener());     
    }
    
    class stickActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        String msg = "stick";
        m_Conn.sendMsg("st="+msg);
        m_View.chat.append(m_View.playerName+": "+msg+"\n");
        m_View.chat.setCaretPosition
                (m_View.chat.getDocument().getLength());
        m_View.stick.setEnabled(false);
        m_View.twist.setEnabled(false);
        }
    }//end innner stickActionListener class.
    
    
    class subActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        String text = m_View.input.getText();
        String msg = "cMsg=";
        m_Conn.sendMsg(msg+""+m_View.playerName+": "+text);
        m_View.chat.append(m_View.playerName+": "+text+"\n");
        m_View.chat.setCaretPosition
                (m_View.chat.getDocument().getLength());
        m_View.input.setText("");    
        }
    }//end innner subActionListener class.
    
    
    class twistActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        String msg = "twist";
        m_Conn.sendMsg("tw="+msg);
        m_View.chat.append(m_View.playerName+": "+msg+"\n");
        m_View.chat.setCaretPosition
                (m_View.chat.getDocument().getLength());
        }
    }//end innner twistActionListener class.
    
    
    class nSubActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            m_View.truncateName(); 
            m_View.nameFrame.setVisible(false);
            String msg = "New Game";
            m_Conn.sendMsg(msg);
            m_Conn.sendMsg("tID="+m_View.playerName);
            m_View.twist.setEnabled(true);
            m_View.stick.setEnabled(true);
            m_View.game.setEnabled(false);
        }
    }//end innner nSubActionListener class.
    
    
    class NGActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
         m_View.nameFrame.setVisible(true);
        }
    }//end innner NGActionListener class.
    
    
    class connActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
         Thread t = new Thread(m_Conn);
         t.start();
         m_View.conn.setEnabled(false);
         m_View.game.setEnabled(true);
        }
    }//end innner connActionListener class.
}
