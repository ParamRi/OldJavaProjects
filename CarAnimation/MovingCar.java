import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
/*
   Programmer: Param A Ri
   Class: CSCI-1301
   Date: 4/25/14
   Instructor: Mrs. Jackson
**/
public class MovingCar extends JComponent
{
   private int xLeft;
   private int yTop;
   
   public MovingCar()
   {
      xLeft = 25;
      yTop = 150;
   }
   
   public void paintComponent(Graphics g)
   {
      g.setColor(Color.BLUE);
      g.fillRect(0, 0, 800, 400);
      
      g.setColor(Color.RED);
      g.fillRect(xLeft + 45, yTop -40, 100, 40);//top of car
      g.fillRect(xLeft,yTop,200,50);//car body
      g.setColor(Color.BLACK);
      g.drawString("Use Arrow Keys to Move Car.", 70,225);
      g.fillOval(xLeft + 20, yTop + 20, 45, 45); //back wheel
      g.fillOval(xLeft + 150, yTop + 20, 45, 45); //front wheel
      g.fillRect(xLeft + 50, yTop - 35, 40, 35); //back window
      g.fillRect(xLeft + 100, yTop -35, 45, 35); //front window
      
      g.setColor(Color.GREEN);
      g.fillRect(0, 215, 500, 200);
      
      g.setColor(Color.BLACK);
      if(xLeft == 25)
      {
         g.drawString("Use Arrow Keys to Move Car.", 70,225);
      }
      
      if(xLeft > 300 && xLeft < 400)
      {
         g.setColor(Color.RED);
         g.drawString("Oh no, we gonna die!!1!.", 400,100);
      }
      if(xLeft > 500 && xLeft < 600)
      {
         g.setColor(Color.WHITE);
         g.drawString("Oh look!  We're flying!!!1!!", 600, 200);
      }
      
      if(xLeft > 800)
      {
         g.setColor(Color.BLACK);
         g.drawString("Welcom 2 WhiteSpAcE!",850, 100);
      }
      
   }
   
   public void moveCarBy( int dx, int dy)
   {
      xLeft = xLeft + dx;
      yTop = yTop + dy;
      repaint();
   }
}
