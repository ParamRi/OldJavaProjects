import javax.swing.JFrame;

/**
   Programmer: Param A Ri
   Class: CSCI-1301
   Date: 4/18/14
   Instructor: Mrs. Jackson 
*/
public class rastaurantBillFrame
{  
   public static void main(String[] args)
   {  
      JFrame frame = new restaurantBill();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Restaurant Bill");
      frame.setVisible(true);
   }
}