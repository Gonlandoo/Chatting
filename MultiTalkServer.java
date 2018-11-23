package CS3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MultiTalkServer{
	  static int num=0;
      boolean listening=false;//进程运行标记
     ServerSocket serverSocket = null;
    //下面这个表储存链接的客户端的socket
    //public static List<ServerThread>st = new ArrayList<>();
     //将客户段的线程实例对象放在里。<ID,ServerThread>
     public static Map<String,ServerThread> map=new HashMap();//规定每个客户发消息，，之前的为自己的号，使用字符串方法提取
    public static void main(String[]args) throws IOException {
        new MultiTalkServer().start();
    }
    public void start() throws IOException{
    try {
            serverSocket = new ServerSocket(8888);//创建一个ServerSocket在端口8888监听客户请求
            listening = true;
            System.out.println("服务器登录成功！");
    }catch(IOException e){
            System.out.println("Couldnot listen on port:8888.");
             //出错，打印出错信息
             System.exit(-1); //退出
    }
    while(listening){ //循环监听
              //监听到客户请求，根据得到的Socket对象和客户计数创建服务线程，并启动之              
        Socket socket = serverSocket.accept();
        ServerThread serverThread= new ServerThread(socket,num);
        new Thread(serverThread).start();
        //int x=(int) (Math.random()*20);
        num++;
        String id="user"+num;
        System.out.println(id+"正在登录！");      
        map.put(id, serverThread);
             }
        serverSocket.close(); //关闭ServerSocket
}
}
