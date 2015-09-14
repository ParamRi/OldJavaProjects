import java.util.Scanner;
import java.util.Random;
import javax.swing.JFrame;

public class AirportSim
{
   
   public static Random rand = new Random();
   public static Scanner in = new Scanner(System.in);
   
   public static void main(String[]args)
   {
      
      JFrame frame = new CommandTowerControls();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CommandTower");
      frame.setVisible(true);
   }
   
}