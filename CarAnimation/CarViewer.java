import javax.swing.JFrame;

/**
   Programmer: Param A Ri
   Class: CSCI-1301
   Date: 4/25/14
   Instructor: Mrs. Jackson
*/
public class CarViewer extends JFrame
{
   public static void main(String[] args)
   {
      JFrame frame = new CarFrame();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}