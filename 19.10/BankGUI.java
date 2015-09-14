import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.IOException;
import java.util.Scanner;

/**
   Programmer: Param A Ri
   Class: CSCI-1302
   Date: 4/14/15
   Instructor: Mrs. Jackson
*/
public class BankGUI extends JFrame
{
   private static final int FRAME_WIDTH = 700;
   private static final int FRAME_HEIGHT = 350;
   
   private static final int AREA_ROWS = 10;
   private static final int AREA_COLUMNS = 40;  
     
   private JLabel labelEnterAccount, labelEnterAmount;
   private JTextField AccountNumberField, AmountNumberField;
   private JButton Deposit, Withdraw, PrintReceipt, Quit;
   private JTextArea receiptArea;
   
   Scanner in = new Scanner(System.in);
   BankData data = new BankData();
 
   public BankGUI()
   {  
      receiptArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      receiptArea.setText("Transaction" + "\t\t" + "Amount" + "\t\t" + "Balance" + "\n");
      receiptArea.setEditable(false);
            
      createTextField();
      createButtons();
      createPanel();

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createTextField()
   {
      labelEnterAccount = new JLabel("Enter Account Number");
      
      labelEnterAmount = new JLabel("Enter Account Number");

      final int FIELD_WIDTH = 10;
      AccountNumberField = new JTextField(FIELD_WIDTH);
      AccountNumberField.setText("Account Number");
      AccountNumberField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                AccountNumberField.setText("");
            }});
      
      AmountNumberField = new JTextField(FIELD_WIDTH);
      AmountNumberField.setText("Amount $$$$");
      AmountNumberField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                AmountNumberField.setText("");
            }});
   }
   
   class commandListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
   		BankStream bank = new BankStream();
         String menu = event.getActionCommand();
         int anAccount;
         double anAmount = 0;
         
         if(menu.equals("Deposit"))
         {
            try
            {
               anAccount = Integer.parseInt(AccountNumberField.getText());
               anAmount = Double.parseDouble(AmountNumberField.getText());
               bank.inputData(anAccount, anAmount);
            }catch(IllegalArgumentException e){}
            
         }
         else if(menu.equals("Withdraw"))
         {
            try
            {
               anAccount = Integer.parseInt(AccountNumberField.getText());
               anAmount = Double.parseDouble(AmountNumberField.getText());
               bank.inputData(anAccount, - anAmount);
            }catch(IllegalArgumentException e){}
         }
         else if(menu.equals("PrintReceipt"))
         {
            //next method
         }
         else if(menu.equals("Quit"))
         {
            System.exit(0);
         }
         receiptArea.setText(null);
         //print out the receipt
         String balance = "";
         try
         {
            balance = bank.getBalance();
         }catch(IOException e){}
         receiptArea.append("Transaction" + "\t" + "Amount" + "\t" + "Balance" + "\n");
         receiptArea.append( menu + "\t $" + anAmount + "\t $" + balance + "\n");
         receiptArea.append("\n" + " \t \"Come back soon!\"");
      }            
   }
   
   class BankStream
   {
      int accountNum;
      double amountIn;
      
      public void BankStream()
      {
      
      }
      
      public void inputData(int inAccount, double inputAmount)
      {
         accountNum = inAccount;
         amountIn = inputAmount;
      }
      
      public String getBalance() throws IOException
      {
         String output;
         try
         {  
            data.open("bank.dat");
   
            int accountNumber = accountNum;
            double amount = amountIn;
   
            int position = data.find(accountNumber);
            BankAccount account;
            if (position >= 0)
            {
               account = data.read(position);
               account.deposit(amount);
               output =  String.valueOf(account.getBalance());
            }
            else // Add account
            {  
               account = new BankAccount(accountNumber, amount);
               position = data.size();
               output = "*" + String.valueOf(account.getBalance());
            }
            data.write(position, account);
                  
         }
         finally
         {
            data.close();
         }
         
         return output;
      }
   }

   private void createButtons()
   {
      Deposit = new JButton("Deposit"); 
      Withdraw = new JButton("Withdraw");
      PrintReceipt = new JButton("PrintReceipt");
      Quit = new JButton("Quit");
      
      ActionListener listener = new commandListener();
      Deposit.addActionListener(listener);
      Withdraw.addActionListener(listener);
      PrintReceipt.addActionListener(listener);
      Quit.addActionListener(listener);
   }
   /**
      panel crates the TextArea 'receiptArea' that prints the receipt. 
       
   */
   private void createPanel()
   {
      JPanel panel = new JPanel();
      JScrollPane scrollPane = new JScrollPane(receiptArea);
      panel.add(scrollPane);     
      add(panel,BorderLayout.SOUTH);
      
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 1;
      buttonPanel.add(Deposit,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 1;
      buttonPanel.add(Withdraw,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 3;
      c.gridy = 1;
      buttonPanel.add(PrintReceipt,c);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 4;
      c.gridy = 1;
      buttonPanel.add(Quit,c);
                  
      c.gridx = 0;
      c.gridy = 1;
      c.gridwidth = 1;
      buttonPanel.add(AccountNumberField,c);
      
      c.gridx = 0;
      c.gridy = 2;
      c.gridwidth = 1;
      buttonPanel.add(AmountNumberField,c);
      
      add(buttonPanel, BorderLayout.CENTER);
   }
}