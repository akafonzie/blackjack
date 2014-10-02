package assplayer;
import java.awt.Color;
import java.net.*;
import java.io.*;
/**
 *
 * @author Shinobi
 */
public class connect implements Runnable{
    //int port = 55555;
   // String ip = "192.168.0.10";
    int port = 22;
    String ip = "139.222.12.121";
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket sConn = null;
    String msg, conInf;
    private makeGUI mp;
    connect(makeGUI m)
    {
        mp = m;
    }//end of connect constructor
   
public void run()
    {
    try{
        System.out.println("Connecting on port: " +port);
        InetAddress server = InetAddress.getByName(ip);
        sConn = new Socket (server, port);
        conInf=("Connected to: "+sConn.getInetAddress().getHostName()
                                                    +" on port"+port);
        mp.chat.append(conInf+"\n"); 
        //System.out.println(conInf);
        out = new ObjectOutputStream(sConn.getOutputStream());
        out.flush();
        in = new ObjectInputStream(sConn.getInputStream());
        do{
            try{
                msg = (String)in.readObject();
                //System.out.println("Server sent:> "+msg);
                
                //for recieving the player hand
                if(msg.contains("ph="))
                {
                    String[] tmp = msg.split("=");
                    String ph = tmp[1];
                    mp.hand.append(ph+"\n");
                }
                
                //for recieving the player total
                if(msg.contains("pt="))
                {
                    String[] tmp = msg.split("=");
                    String pt = tmp[1];
                    mp.pTotal.setText(pt+"\n");
                }
                if(msg.contains("bust="))
                {
                    String[] tmp = msg.split(("="));
                    String pt = tmp[1];
                    mp.hand.setForeground(Color.red);
                    mp.hand.append(pt);
                    mp.twist.setEnabled(false);
                    mp.stick.setEnabled(false);
                }
                }catch(ClassNotFoundException e){
            System.out.println("Client data received in "
                    + "unknown format");
            }
        }
        while(!msg.equals("serverpls"));
              }
              catch(IOException e)
              {
                  System.out.println("IOException in player "
                          + "connect: "+e);
                  e.printStackTrace();
              }
              finally{
                    //Closing connection
                    try{
                        in.close();
                        out.close();
                        sConn.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
        }  
    } //end of run();
    
void sendMsg(String m)
    {
        try{
            out.writeObject(m);
            out.flush();
            System.out.println("client=>  " + m);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}//end of connect class
