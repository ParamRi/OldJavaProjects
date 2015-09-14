import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

/*
   Programmer: Param A Ri
   Class: CSCI-1301
   Date: 4/25/14
   Instructor: Mrs. Jackson
**/

public class CarFrame extends JFrame
{
   private MovingCar animation;
   
   class KeyStrokeListener implements KeyListener
   {
      public void keyPressed(KeyEvent event) 
      {
         String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 
         if (key.equals("LEFT"))
         {
            animation.moveCarBy(-2,0);          
         }
         else if (key.equals("RIGHT"))
         {
            animation.moveCarBy(2,0);            
         }
      }
      public void keyTyped(KeyEvent event) {}
      public void keyReleased(KeyEvent event) {}
   }

   
   public CarFrame()
   {
      animation = new MovingCar();
      add(animation);
      
      setSize(1200,400);
      
      animation.addKeyListener(new KeyStrokeListener());
      animation.setFocusable(true);

   }
}