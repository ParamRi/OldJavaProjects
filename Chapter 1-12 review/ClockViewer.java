import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
   Programmer: Param A Ri
   Class: CSCI-1302
   Date: 1/14/15
   Instructor: Mrs. Jackson
*/

public class ClockViewer extends JFrame
{
   public static void main(String[] args)
   {
      JFrame frame = new ClockFrame();
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   
}

class ClockFrame extends JFrame
{
   private ClockAnimation clock;
   private JPanel colorPanel;
   private Date now;
   private DateFormat today;
   class TimerListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         now = new Date();
         today = new SimpleDateFormat("HH:mm:ss");
         clock.StartClock(now, today);
      }
   }
   public ClockFrame()
   {
      clock = new ClockAnimation();
      add(clock);
      
      setSize(650,200);
      
      clock.setFocusable(true);
      
      ActionListener listener = new TimerListener();
      
      final int DELAY = 100; 
      Timer t = new Timer(DELAY, listener);
      t.start();
   }
}

class ClockAnimation extends JComponent
{
   private int xLeft;
   private int yTop;
   private String currentTime;
   
   public ClockAnimation()
   {
      
   }

   public void paintComponent(Graphics g)
   {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, 650, 200);
      g.setFont(new Font("Arial", Font.BOLD, 120));
      g.setColor(Color.BLACK);
      g.drawString(currentTime, 60,110);

   }
      
   

   public void StartClock(Date now, DateFormat today)
   {
      currentTime = today.format(now);
      repaint();
   }
}

