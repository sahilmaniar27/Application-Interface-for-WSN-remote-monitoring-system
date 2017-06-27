
import java.io.*;
import java.net.*;

public class test{
	
	
		
     public static void main(String[] args) {

		  int cnt = 0x00001452;
		  
		  int test1,test2,test3,test4;
		  
		  test4 = (cnt & 0x0000000F);
		  test3 = (cnt & 0x000000F0);
		  test2 = (cnt & 0x00000F00);
		  test1 = (cnt & 0x0000F000);
		  test3 = test3>>4;
		  test2 = test2>>8;
		  test1 = test1>>12;
		  
		  System.out.println(test4);
		  System.out.println(test3);
		  System.out.println(test2);
		  System.out.println(test1);

	
         
     }
}