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
import java.util.ArrayList;
import java.util.Collections;

/**
   Programmer: Param A Ri
   Class: CSCI-1301
   Date: 4/18/14
   Instructor: Mrs. Jackson
*/
public class restaurantBill extends JFrame
{
   private static final int FRAME_WIDTH = 850;
   private static final int FRAME_HEIGHT = 600;
   
   private static final int AREA_ROWS = 20;
   private static final int AREA_COLUMNS = 30;

   private double itemPrice = 0;
   private double totalPrice = 0;
	private int count = 0;   
     
   private JLabel labelBurger, labelSteak, labelCake, labelMeltdown, labelFries, labelOther, labelPrice;
   private JTextField otherItemField, otherItemPriceField;
   private JButton burgerOfDeth, bloodSteak, BoozeCake, nuclearMeltdown, fries, addOther, delete, printBill;
   private JTextArea billArea, itemArea;
	private ArrayList<Double> cost;
   private ArrayList<String> items;
 
   public restaurantBill()
   {  
      itemArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      itemArea.setText("ITEM" + "\t\t" + "PRICE" + "\n");
      itemArea.setEditable(false);
      
      billArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      billArea.setText("BILL \n" + "ITEM" + "\t\t" + "PRICE" + "\n");
      billArea.setEditable(false);
      
      cost = new ArrayList<Double>();
      items = new ArrayList<String>();
            
      createTextField();
      createButtons();
      createPanel();

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createTextField()
   {
      labelOther = new JLabel("Other ");

      final int FIELD_WIDTH = 10;
      otherItemField = new JTextField(FIELD_WIDTH);
      otherItemField.setText("Other Item");
      otherItemPriceField = new JTextField(7);
      otherItemPriceField.setText("");
   }
   
   class billListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
   		
         String menu = event.getActionCommand();
         
         if(menu.equals("Burger Of Deth"))
         {
            cost.add(11.49);
            items.add("BurgerOD");
         }
         else if(menu.equals("Blood Steak"))
         {
            cost.add(13.69);
            items.add("Blood Steak");
         }
         else if(menu.equals("Booze Cake"))
         {
            cost.add(7.69);
            items.add("Booze Cake");
         }
         else if(menu.equals("Nuclear Meltdown"))
         {
            cost.add(5.79);
            items.add("Nclr Mltdwn");
         }
         else if(menu.equals("Fries"))
         {
            cost.add(3.89);
            items.add("Fries");
         }
         else if(menu.equals("Delete"))
         {
            cost.remove(cost.size()-1);
            items.remove(items.size()-1);
         }
         else if(menu.equals("Other"))
         {
            try
            {
               cost.add(Double.parseDouble(otherItemPriceField.getText() ) );
               items.add(otherItemField.getText() );
            }catch(NumberFormatException e){
               cost.add(0.00);
               items.add("N/A");
            }
         }
         else if(menu.equals("Print Bill"))
         {
            totalPrice = 0;
            billArea.setText(null);
            billArea.append("____RESTAURANT BILL_____ \n");
            for(int i = 0; i < cost.size(); i++ )
            {    
      			billArea.append(items.get(i) + "\t\t $" + cost.get(i) + "\n" );
               totalPrice = totalPrice + cost.get(i);
            }
            double tax = totalPrice * 0.07;
            double tip = totalPrice * 0.15;
            billArea.append("___________________");
            billArea.append( " \n SUBTOTAL: \t\t $" + String.format("%.2f", totalPrice) + "\n" + "+ \n" + "TAX: \t\t $" + String.format("%.2f", tax) +"\n");
            billArea.append( "TOTAL: \t\t $" + String.format("%.2f", (totalPrice + tax)) + "\n" );
            billArea.append("___________________ \n");
            billArea.append("Suggested Tip: \t\t $" + String.format("%.2f", tip) + "\n" + "___________________ \n" + "____COME BACK SOON!!!____ \n");
         }
         totalPrice = 0;
         itemArea.setText(null);
         for(int i = 0; i < cost.size(); i++ )
         {    
            itemArea.append(items.get(i) + "\t\t $" + cost.get(i) + "\n" );
            totalPrice = totalPrice + cost.get(i);
         }       
         itemArea.append("\t TOTAL: \t $" + totalPrice);
      }            
   }

   private void createButtons()
   {
      burgerOfDeth = new JButton("Burger Of Deth"); 
      bloodSteak = new JButton("Blood Steak");
      BoozeCake = new JButton("Booze Cake");
      nuclearMeltdown = new JButton("Nuclear Meltdown");
      fries = new JButton("Fries");
      addOther = new JButton("Other");
      delete = new JButton("Delete");
      printBill = new JButton("Print Bill");
      
      labelBurger = new JLabel("$11.49 - A patty that has been marinated and grilled over hickory wood, served on a black-sesame seed bun.");
      labelSteak = new JLabel("$13.69 - Dont' even think of ordering this 'well-done'.");
      labelCake = new JLabel("$7.69 - A slice of German Chocolate Cake that has been soaked \n in Rum and Coffee Liquor");
      labelMeltdown = new JLabel("$5.79 - Two scoops of our famous Mango/Kiwi Icecream \n served over a hot bed of caramelized apples.");
      labelFries = new JLabel("$3.89 - a side of fries");
      labelPrice = new JLabel("    Enter Price: $");
      
      ActionListener listener = new billListener();
      burgerOfDeth.addActionListener(listener);
      bloodSteak.addActionListener(listener);
      BoozeCake.addActionListener(listener);
      nuclearMeltdown.addActionListener(listener);
      fries.addActionListener(listener);
      addOther.addActionListener(listener);
      delete.addActionListener(listener);
      printBill.addActionListener(listener);
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
      JScrollPane scrollPane = new JScrollPane(itemArea);
      panel.add(scrollPane);
      JScrollPane scrollPane2 = new JScrollPane(billArea);
      panel.add(scrollPane2);      
      add(panel,BorderLayout.SOUTH);
      
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 1;
      buttonPanel.add(burgerOfDeth,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 2;
      buttonPanel.add(bloodSteak,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 3;
      buttonPanel.add(BoozeCake,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 4;
      buttonPanel.add(nuclearMeltdown,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 5;
      buttonPanel.add(fries,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 6;
      buttonPanel.add(otherItemField,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 7;
      buttonPanel.add(delete,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 8;
      buttonPanel.add(printBill,c);
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 1;
      c.gridwidth = 3;
      buttonPanel.add(labelBurger,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 2;
      c.gridwidth = 3;
      buttonPanel.add(labelSteak,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 3;
      c.gridwidth = 3;
      buttonPanel.add(labelCake,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 4;
      c.gridwidth = 3;
      buttonPanel.add(labelMeltdown,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 5;
      c.gridwidth = 3;
      buttonPanel.add(labelFries,c);
      c.gridx = 1;
      c.gridy = 6;
      c.gridwidth = 1;
      buttonPanel.add(addOther,c);
      c.gridx = 2;
      c.gridy = 6;
      buttonPanel.add(labelPrice, c);
      c.gridx = 3;
      c.gridy = 6;
      c.gridwidth = 1;
      buttonPanel.add(otherItemPriceField,c);
      
      add(buttonPanel, BorderLayout.NORTH);
   }
}
