import java.util.Random;


public class Airplane
{
   /** 
      Create an object Airplane that has a flight number
      Create methods to construct and access the number
   
   */
   private int flightNum;
   
   // constructor for the Airplane
   public Airplane(int aFlight)
   {
      flightNum = aFlight;  // just "flightNum" instead of "int flightNum"  no need to redeclare variables
   }
   
   // Accessor method for the flightNum 
   public int getFlight()
   {
      return flightNum;
   }
   
}