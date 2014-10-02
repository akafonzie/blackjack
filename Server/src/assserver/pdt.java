package assserver;
import java.io.*;
import java.text.*;
import java.util.*;
/**
 * @author tdw10kcu
 */
public class pdt {
   BufferedWriter writer;
   pdt()
   {

   }
   
   public void pal(String m)
   {
    try{
        
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //get current date time with Calendar()
    Calendar cal = Calendar.getInstance();
    String dt = dateFormat.format(cal.getTime());
    writer =new BufferedWriter(new FileWriter("log.txt", true));
    writer.write(dt +": "+ m);
    writer.newLine();
    System.out.println(dt +": "+ m);
   }catch(IOException ex)
   {
       ex.printStackTrace();
   }finally{
       try{writer.close();}catch(Exception ex){ex.printStackTrace();}
   }
   }
}
