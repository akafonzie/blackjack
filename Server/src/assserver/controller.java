package assserver;

import java.awt.event.*;
/*
 * @author Shinobi
 */
public class controller {
    private model       m_Model;
    private makeGUI     m_View;
    private makeServer  m_Server;
    controller(model mm, makeGUI view, makeServer srv)
    {
        m_Model     = mm;
        m_View      = view;
        m_Server    = srv;
        //add some listeners
       view.addStartActionListener(new startActionListener());   
       view.addGameActionListener(new gameActionListener());
    }
    
    public void addStringToGUI(String s)
    {
        m_View.chat.append(s);
    }
    
    class startActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == m_View.cServer)
            {
                Thread s = new Thread(m_Server);
                //m_Server.run();
                m_View.sGame.setEnabled(true);
                s.start();
                m_View.cServer.setEnabled(false);
            }
        }
    }//end innner startActionListener class.
    
       class gameActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() ==m_View.sGame)
            {
                m_View.sGame.setEnabled(false);
                m_Server.gameStart=true;
            }      
        }
    }//end innner gameActionListener class.
    
}
