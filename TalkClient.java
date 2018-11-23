package CS3;
import java.io.*;

import java.net.*;
import java.net.*;
import java.util.*;
public class TalkClient {  
      public static void main(String[]args){
             //�򱾻���8888�˿ڷ����ͻ�����
             Socket socket;
			 try {
				 socket = new Socket("127.0.0.1",8888);			
				 //��ϵͳ��׼�����豸����BufferedReader����
				 BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
				 //��Socket����õ��������������PrintWriter����
				 PrintWriter os=new PrintWriter(socket.getOutputStream());                       
				 Thread tRecv = new Thread(new ClientThread(socket)); 
				 tRecv.start();		         
				 while(true)
				 {
                   String s=sin.readLine();
                   String []str=s.split(",");
                   if(str[2].equals("file")) { //�ļ����͸�ʽ��user,user,file,filename
                	  String filename=str[3].trim();
                	  File file=new File(str[3]);
                	  
                	  FileReader fis=new FileReader(file);
                	  os.print(s);  //��,��ԭ���ǣ�
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
				 System.out.println("�������쳣");  
			 }             
      }            
      public static void show(int id) {
    	  System.out.println("Your id is : user"+id);
      }
}