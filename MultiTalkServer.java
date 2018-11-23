package CS3;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MultiTalkServer{
	  static int num=0;
      boolean listening=false;//�������б��
     ServerSocket serverSocket = null;
    //��������������ӵĿͻ��˵�socket
    //public static List<ServerThread>st = new ArrayList<>();
     //���ͻ��ε��߳�ʵ����������<ID,ServerThread>
     public static Map<String,ServerThread> map=new HashMap();//�涨ÿ���ͻ�����Ϣ����֮ǰ��Ϊ�Լ��ĺţ�ʹ���ַ���������ȡ
    public static void main(String[]args) throws IOException {
        new MultiTalkServer().start();
    }
    public void start() throws IOException{
    try {
            serverSocket = new ServerSocket(8888);//����һ��ServerSocket�ڶ˿�8888�����ͻ�����
            listening = true;
            System.out.println("��������¼�ɹ���");
    }catch(IOException e){
            System.out.println("Couldnot listen on port:8888.");
             //������ӡ������Ϣ
             System.exit(-1); //�˳�
    }
    while(listening){ //ѭ������
              //�������ͻ����󣬸��ݵõ���Socket����Ϳͻ��������������̣߳�������֮              
        Socket socket = serverSocket.accept();
        ServerThread serverThread= new ServerThread(socket,num);
        new Thread(serverThread).start();
        //int x=(int) (Math.random()*20);
        num++;
        String id="user"+num;
        System.out.println(id+"���ڵ�¼��");      
        map.put(id, serverThread);
             }
        serverSocket.close(); //�ر�ServerSocket
}
}
