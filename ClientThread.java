package CS3;
import java.io.*;
import java.net.*;
public class ClientThread implements Runnable {
	BufferedReader is;
	Socket socket;
	public ClientThread(Socket socket) throws IOException {
		this.socket=socket;
		this.is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public void run() {
		 try {
		 BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));  
   		  while(true) {   			 
   			  String msg=is.readLine();
   			  System.out.println(msg);
   			  String []str=msg.split(",");
   			  if(str[2].equals("file")) {
   				  //System.out.println(str); 
   				  System.out.println(str[0]+" send a file "+str[1]);
   				  File directory=new File("D:\\");
   				  if(!directory.exists()) {
   					  directory.mkdir();
   				  }
   				  str[3]=str[3].trim();
   				  File file=new File(directory,str[3]);
   				  System.out.println(file.getPath());
   				  FileWriter fos=new FileWriter(file.getPath(),true);
   				  String s=msg.replaceFirst(str[0]+","+str[1]+","+str[2]+","+str[3]+",", "");
   				  System.out.println(s);
   				  fos.write(s);
   				  System.out.println("receive file successfully");
   				  fos.close();
   			  }
   		  } 
   	  }catch(Exception e1) {
   		  e1.printStackTrace();
   	  }
	}
}
