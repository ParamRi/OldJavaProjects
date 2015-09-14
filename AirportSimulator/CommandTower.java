import java.util.LinkedList;
import java.util.Queue;

public class CommandTower
{
   private LinkedList list;
   
   private Queue<Airplane> Landing = new LinkedList<Airplane>(); //stated the specic kind of object that would be stored in queue
   private Queue<Airplane> Takeoff = new LinkedList<Airplane>();
   
   //create Landing queue
   //create Takeoff queue

   /*
      create a "land" method with parameters(flightNumber) that 
      is used to create an airplane object and places it in the 
      Landing queue
   */
   public void land(int flightNum)
   {
      Airplane newPlane = new Airplane(flightNum);
      Landing.add(newPlane);
   }
   
   /*
      create a "takeoff" method with parameters(flightNumber/flightSymbol) 
      that creates airplane object and places it in the Takeoff queue.
   */
   public void takeoff(int flightNum)
   {
      Airplane newPlane = new Airplane(flightNum);
      Takeoff.add(newPlane);
   }
   /*
      create a 'next' method that will return or print a flight object 
      from one of the two queues.  Landing queue takes priority so
      takeoff queue will only be used if Landing queue is empty.
      
   */
   public String next()
   {
      int waiting = Landing.size(); //puting landing.size() into an integer seems to work
      int atBay = Takeoff.size();
      if(waiting == 0 && atBay == 0)
      {
         return "There are no flights in either queue.";
      }
      else if(waiting == 0)
      {
         
         return "D: Flight " + Takeoff.remove().getFlight() + " has taken off.";
      }
      else
      {
         return "L: Flight " + Landing.remove().getFlight() + " has landed";
      }
      
   }
   public void quit()
   {
      while(!Landing.isEmpty())
      {
         Landing.remove();
      }
      while(!Takeoff.isEmpty())
      {
         Takeoff.remove();
      }
   }
}