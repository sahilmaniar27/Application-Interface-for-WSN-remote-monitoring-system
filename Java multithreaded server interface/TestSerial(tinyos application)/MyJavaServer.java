import java.io.*;
import java.net.*;
import java.io.IOException;

import net.tinyos.message.*;
import net.tinyos.packet.*;
import net.tinyos.util.*;

public class MyJavaServer implements Runnable,MessageListener{
	
	int msg_recieved = 0;
	int data_recieved;
	int test1,test2,test3,test4,test5,test6,test7,test8;
	TestSerialMsg msg = null;

	@Override
	    public void run() {
			
			
			
			int i=0;
			int j=0;
	        System.out.println("Runnable Thread.");
					BufferedReader br2 = null;
					BufferedWriter bw2 = null;
					Socket s = null;
					try {
						s = new Socket("localhost",1234);
			
						br2 =    new BufferedReader(new InputStreamReader(s.getInputStream()));
						bw2 =    new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
						
        				while(true) {
        					//System.out.println("First loop");
							while(msg_recieved==1){
						
			//			Socket s = new Socket("localhost",1234);
			
			//			BufferedReader br2 =    new BufferedReader(new InputStreamReader(s.getInputStream()));
			//			BufferedWriter bw2 =    new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
						//try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
							System.out.println(i);
					
							//data_recieved=(msg.get_counter());
							System.out.println("Entering for loop.");
							bw2.write("Data:" + msg.get_counter());
							//bw2.write(data_recieved);
							bw2.flush();
							
							
							msg_recieved = 0;
						
			//			bw2.close();
			//			br2.close();
			//			s.close();
						
					}
					try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
				}
					
					}catch(IOException e) {
						e.printStackTrace();
					}	
					finally {
						try {
							bw2.close();
							br2.close();
							s.close();
						}
						catch(Exception e) {}		
					}	

	}




  private MoteIF moteIF;
  
  public MyJavaServer(MoteIF moteIF) {
    this.moteIF = moteIF;
    this.moteIF.registerListener(new TestSerialMsg(), this);
  	}


	public void sendPackets() {
    	int counter = 99;
    	TestSerialMsg payload = new TestSerialMsg();
    
    	try {
     	  //while (true) {
			System.out.println("Sending packet " + counter);
			payload.set_counter(counter);
			moteIF.send(0, payload);
			//counter++;
			try {Thread.sleep(1000);}
			catch (InterruptedException exception) {}
      		//}
    	}
    	catch (IOException exception) {
      	System.err.println("Exception thrown when sending packets. Exiting.");
      	System.err.println(exception);
    	}
	}

	public void messageReceived(int to, Message message) {
    	msg = (TestSerialMsg)message;
    	System.out.println("Received packet sequence number " + msg.get_counter());
    	msg_recieved = 1;
	}
  
	private static void usage() {
    	System.err.println("usage: TestSerial [-comm <source>]");
	}

		
	public static void main(String[] args) {
          int port = 20222;
		  int port2 = 1234;
		  int cnt = 0;

	
         ServerSocket listenSock = null; //the listening server socket
         Socket sock = null;             //the socket that will actually be used for communication
		


		    String source = null;
   			 if (args.length == 2) {
      		if (!args[0].equals("-comm")) {
			usage();
			System.exit(1);
     		 }
      		source = args[1];
    		}
    		else if (args.length != 0) {
      		usage();
      		System.exit(1);
    		}
    
    		PhoenixSource phoenix;
    
    		if (source == null) {
      		phoenix = BuildSource.makePhoenix(PrintStreamMessenger.err);
    		}
    		else {
      		phoenix = BuildSource.makePhoenix(source, PrintStreamMessenger.err);
    		}

    		MoteIF mif = new MoteIF(phoenix);
    	MyJavaServer serial = new MyJavaServer(mif);
    	//	serial.sendPackets();



		//MyJavaServer temp = new MyJavaServer(mif);
		Thread t = new Thread(serial);
	    t.start();
		 
         try {
 
              listenSock = new ServerSocket(port);
 
             while (true) {       //we want the server to run till the end of times
 
 
			
                  sock = listenSock.accept();             //will block until connection recieved
					
				System.out.println("The socket connection is made");
                 BufferedReader br =    new BufferedReader(new InputStreamReader(sock.getInputStream()));
                 BufferedWriter bw =    new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
				 
				
				 
                 String line = "";
				 
				
                 while ((line = br.readLine()) != null) {
					 System.out.printf("line is %s\n",line);
                     bw.write("PHP said: " + line + "\n");
                     bw.flush();
                     serial.sendPackets();
                 }
				   
				
                 //Closing streams and the current socket (not the listening socket!)
                 bw.close(); 
                 br.close();
                 sock.close(); 
             }
         } catch (IOException ex) {
             ex.printStackTrace();
         }
     }
}