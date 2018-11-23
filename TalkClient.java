package CS3;
import java.io.*;

import java.net.*;
import java.net.*;
import java.util.*;
public class TalkClient {  
      public static void main(String[]args){
             //向本机的8888端口发出客户请求
             Socket socket;
			 try {
				 socket = new Socket("127.0.0.1",8888);			
				 //由系统标准输入设备构造BufferedReader对象
				 BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
				 //由Socket对象得到输出流，并构造PrintWriter对象
				 PrintWriter os=new PrintWriter(socket.getOutputStream());                       
				 Thread tRecv = new Thread(new ClientThread(socket)); 
				 tRecv.start();		         
				 while(true)
				 {
                   String s=sin.readLine();
                   String []str=s.split(",");
                   if(str[2].equals("file")) { //文件发送格式：user,user,file,filename
                	  String filename=str[3].trim();
                	  File file=new File(str[3]);
                	  
                	  FileReader fis=new FileReader(file);
                	  os.print(s);  //加,的原因是：
                	  os.print(",");
                	  System.out.println("Start sending file!");
                	  BufferedReader rf=new BufferedReader(fis);
                	  String readline;
                	  while((readline=rf.readLine())!=null) {
                		  os.println(readline);
                	  }
                	  System.out.println("Complete sending file");
                	  rf.close();
                   }
                   else {
                	   os.println(s);
                	   os.flush();
                   }                  
             }
			 }catch(Exception e) {
				 System.out.println("服务器异常");  
			 }             
      }            
      public static void show(int id) {
    	  System.out.println("Your id is : user"+id);
      }
}