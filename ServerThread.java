package CS3;
import java.io.*;
import java.util.*;
import java.net.Socket;
public class ServerThread implements Runnable {
    //��������������ӵĿͻ��˵�socket 
	  int num;
      Socket socket=null; //�����뱾�߳���ص�Socket����
      private BufferedReader is = null;
      private PrintWriter os = null;
      public ServerThread(Socket s,int num) throws IOException{//���캯��
         // super();
          this.socket=s; //��ʼ��socket����
          this.is=new BufferedReader(new InputStreamReader(socket.getInputStream()));//��Socket����õ��������������PrintWriter����                      
          this.os=new PrintWriter(socket.getOutputStream());
          //��ϵͳ��׼�����豸����BufferedReader����
          this.num=num+1;
       }
       public void send(String str) throws IOException{
    	     //System.out.println(str);
    	     //���û�����file������ݣ�˵��û�д�����
             os.println(str);
             os.flush();
    	     
     }
       public static void showid(String msg) throws IOException {
    	   for(Iterator i=MultiTalkServer.map.keySet().iterator();i.hasNext();) {
    		   Object obj=i.next();//��������õ�map<String,ServerThread>��key
    		   Socket socket=MultiTalkServer.map.get(obj).socket;//�õ���Ӧ��socket
    		   PrintWriter oss=new PrintWriter(socket.getOutputStream());
    		   oss.println(msg);
    		   oss.flush();
    		   
    				  
    	   }
       }
       public void run() { //�߳�����
                try{
                	  String userid="Welome"+","+"you"+","+"user"+num;
                	  
                      showid(userid); //�÷����Ǳ������û������Լ���id��           
                      while (true) {                           
                              String str=is.readLine(); 
                              String[] msg=str.split(",");                                                                                                                                                               
                              System.out.println(str); 
                              ServerThread serverThread1=MultiTalkServer.map.get(msg[1]);
                              serverThread1.send(str);                                                          
                      }
                }catch(Exception e){
                           System.out.println("Error:"+e);//������ӡ������Ϣ
                    }
}
}