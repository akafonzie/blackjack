package assserver;

import java.io.*;
import java.net.*;
/**
 *
 * @author Shinobi
 */
public class makeServer implements Runnable{
    int port; // the port for the server to listen on
    private static int maxConn; //the max connections to accept
    int tID=0;
    boolean upD, gameStart=false, gameOver=false, isStopped;
    String temp;
    makeGUI mp;
    Thread t, gt;
    connMan[] connections = new connMan[4];
    ServerSocket listener;
    game leGame;
    public pdt pp = new pdt();
    makeServer(makeGUI m)
    {
        port = 22;
        //port = 55555;
        maxConn = 2; //need to change back to 4!!!!!!!1111
        mp = m;
    }
    
    public void run()
    {
    try
    {
        listener = new ServerSocket(port);
        Socket server;
        String ss = ("Server started...Waiting for Connections\n");
        pp.pal(ss);
        //System.out.println(ss);
        mp.chat.append(ss);
        while(tID < maxConn)
        {
            server = listener.accept();
            connMan c_conn = new connMan(server, mp, this);
            t = new Thread(c_conn);
            temp=("Connection received from: "
            +server.getInetAddress().getHostName()+" on port "+port+"\n");
            pp.pal(temp);
            mp.chat.append(temp);
            c_conn.tID = tID;
            tID++;
            connections[tID] = c_conn;
            t.start();
        }
        leGame = new game(connections[1].tPlayer, null, null, null, mp);
        gt = new Thread(leGame);

        
    }//end of try
    catch(IOException e)
    {
        System.out.println("Error on listen server" +e);
        pp.pal("Error on listen server" +e);
        e.printStackTrace();
    }
        
}//end of run
    
 
    public String getS()
    {
        return temp;
    }

}//end of class
