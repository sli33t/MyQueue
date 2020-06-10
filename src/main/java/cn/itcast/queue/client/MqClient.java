package cn.itcast.queue.client;

import cn.itcast.queue.center.MsgCenterServer;
import cn.itcast.queue.entity.QsMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MqClient {

    //生产消息
    public static void produce(String message) throws Exception {
        //本地的的 MsgCenterServer.SERVICE_PORT 创建SOCKET
        Socket socket = new Socket(InetAddress.getLocalHost(), MsgCenterServer.SERVICE_PORT);
        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            QsMap param = new QsMap();
            param.put("type", 1);
            param.put("msg", message);

            out.println(param.toString());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //消费消息
    public static String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), MsgCenterServer.SERVICE_PORT);
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            QsMap param = new QsMap();
            param.put("type", 2);
            param.put("msg", "");

            //先向消息队列发送命令
            out.println(param.toString());
            out.flush();

            //再从消息队列获取一条消息
            String message = in.readLine();
            return message;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
