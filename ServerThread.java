package CS3;
import java.io.*;
import java.util.*;
import java.net.Socket;
public class ServerThread implements Runnable {
    //下面这个表储存链接的客户端的socket 
	  int num;
      Socket socket=null; //保存与本线程相关的Socket对象
      private BufferedReader is = null;
      private PrintWriter os = null;
      public ServerThread(Socket s,int num) throws IOException{//构造函数
         // super();
          this.socket=s; //初始化socket变量
          this.is=new BufferedReader(new InputStreamReader(socket.getInputStream()));//由Socket对象得到输出流，并构造PrintWriter对象                      
          this.os=new PrintWriter(socket.getOutputStream());
          //由系统标准输入设备构造BufferedReader对象
          this.num=num+1;
       }
       public void send(String str) throws IOException{
    	     //System.out.println(str);
    	     //这边没有输出file里的内容，说明没有传过来
             os.println(str);
             os.flush();
    	     
     }
       public static void showid(String msg) throws IOException {
    	   for(Iterator i=MultiTalkServer.map.keySet().iterator();i.hasNext();) {
    		   Object obj=i.next();//这个遍历得到map<String,ServerThread>的key
    		   Socket socket=MultiTalkServer.map.get(obj).socket;//得到相应的socket
    		   PrintWriter oss=new PrintWriter(socket.getOutputStream());
    		   oss.println(msg);
    		   oss.flush();
    		   
    				  
    	   }
       }
       public void run() { //线程主体
                try{
                	  String userid="Welome"+","+"you"+","+"user"+num;
                	  
                      showid(userid); //该方法是表明向用户发送自己的id号           
                      while (true) {                           
                              String str=is.readLine(); 
                              String[] msg=str.split(",");                                                                                                                                                               
                              System.out.println(str); 
                              ServerThread serverThread1=MultiTalkServer.map.get(msg[1]);
                              serverThread1.send(str);                                                          
                      }
                }catch(Exception e){
                           System.out.println("Error:"+e);//出错，打印出错信息
                    }
}
}