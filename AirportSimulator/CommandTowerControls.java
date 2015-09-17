import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.LinkedList;
import java.lang.NumberFormatException;

/**
   Program Designer: Param A Ri
   Class: CSCI-1302
   Date: 2/12/15
   Instructor: Mrs. Jackson
*/
public class CommandTowerControls extends JFrame
{
   private static final int FRAME_WIDTH = 850;
   private static final int FRAME_HEIGHT = 500;
   
   private static final int AREA_ROWS = 20;
   private static final int AREA_COLUMNS = 30;  
     
   private JLabel labelFlightNumber;
   private JTextField FlightNumberField;
   private JButton Land, Takeoff, Next, Quit;
   private JTextArea landingArea, takeoffArea, nextArea;
   
   private LinkedList<Integer> landing = new LinkedList<Integer>();
   private LinkedList<Integer> takeoff = new LinkedList<Integer>();
   
   private CommandTower tower = new CommandTower();
 
   public CommandTowerControls()
   {  
      landingArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      landingArea.setText("Landing Queue" + "\t\t" + "Flight Number" + "\n");
      landingArea.setEditable(false);
      
      takeoffArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      takeoffArea.setText("Takeoff Queue" + "\t\t" + "Flight Number" + "\n");
      takeoffArea.setEditable(false);
      
      nextArea = new JTextArea(6,60);
      nextArea.setText("Recent Actions");
      nextArea.setEditable(false);
            
      createTextField();
      createButtons();
      createPanel();

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createTextField()
   {
      labelFlightNumber = new JLabel("Flight Number");

      final int FIELD_WIDTH = 10;
      FlightNumberField = new JTextField(FIELD_WIDTH);
      FlightNumberField.setText("");
   }
   
   class commandListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
   		
         String menu = event.getActionCommand();
         
         if(menu.equals("Land"))
         {
            try
            {
               tower.land(Integer.parseInt(FlightNumberField.getText()));
            }catch(NumberFormatException e)
            { System.err.println("NumberFormatError landing Flight"); }
            try
            {
               landing.add(Integer.parseInt(FlightNumberField.getText()));
            }catch(NumberFormatException e)
            {
               System.err.println("NumberFormatError landing Flight");
            }
            
         }
         else if(menu.equals("Takeoff"))
         {
            try
            {
               tower.takeoff(Integer.parseInt(FlightNumberField.getText()));
            }catch(NumberFormatException e)
            { System.err.println("NumberFormatError Takeoff Flight"); }
            try
            {
               takeoff.add(Integer.parseInt(FlightNumberField.getText()));
            }catch(NumberFormatException e)
            { System.err.println("NumberFormatError Takeoff Flight"); }
         }
         else if(menu.equals("Next"))
         {
            String nextAction = tower.next();
            nextArea.append("\n" + nextAction);
            if( nextAction.substring(0,1).equals("D"))
            {
               takeoff.remove(0);
            }
            else if(nextAction.substring(0,1).equals("L"))
            {
               
               landing.remove(0);
            }
         }
         else if(menu.equals("Quit"))
         {
            landing.clear();
            takeoff.clear();
            tower.quit();
            nextArea.setText("Recent Actions");
         }
         landingArea.setText(null);
         landingArea.append("Landing Queue" + "\t\t" + "Flight Number" + "\n");
         for(int i = 0; i < landing.size(); i++)
         {
            landingArea.append("Flight " + landing.get(i) + " is ready to land \n");
         }
         

         takeoffArea.setText(null);
         takeoffArea.append("Takeoff Queue" + "\t\t" + "Flight Number" + "\n");
         for(int j = 0; j < takeoff.size(); j++)
         {
            takeoffArea.append(" Flight " + takeoff.get(j) + " is awaiting takeoff \n");
         }
         FlightNumberField.setText("");
      }            
   }

   private void createButtons()
   {
      Land = new JButton("Land"); 
      Takeoff = new JButton("Takeoff");
      Next = new JButton("Next");
      Quit = new JButton("Quit");
      
      ActionListener listener = new commandListener();
      Land.addActionListener(listener);
      Takeoff.addActionListener(listener);
      Next.addActionListener(listener);
      Quit.addActionListener(listener);
   }
   /**
      panel crates the TextArea 'billArea' that prints the restaurant bill. 
      
      buttonPanel holds the buttons for the menu items and the lables
      that show the price and a description of each item.
      GridBagLayout is used to control the size and position of 
      each button / label.  
   */
   private void createPanel()
   {
      JPanel panel = new JPanel();
      JScrollPane scrollPane = new JScrollPane(takeoffArea);
      panel.add(scrollPane);
      JScrollPane scrollPane2 = new JScrollPane(landingArea);
      panel.add(scrollPane2);      
      add(panel,BorderLayout.NORTH);
      
      JPanel nextPanel = new JPanel();
      JScrollPane scrollpane3 = new JScrollPane(nextArea);
      nextPanel.add(scrollpane3);
      add(nextPanel,BorderLayout.CENTER);
      
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 1;
      buttonPanel.add(Land,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 1;
      buttonPanel.add(Takeoff,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 3;
      c.gridy = 1;
      buttonPanel.add(Next,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 4;
      c.gridy = 1;
      buttonPanel.add(Quit,c);
                  
      c.gridx = 0;
      c.gridy = 1;
      c.gridwidth = 1;
      buttonPanel.add(FlightNumberField,c);
      
      add(buttonPanel, BorderLayout.SOUTH);
   }
}
