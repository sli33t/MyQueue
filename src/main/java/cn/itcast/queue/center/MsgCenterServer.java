package cn.itcast.queue.center;

import cn.itcast.queue.client.MqClient;
import cn.itcast.queue.entity.QsMap;
import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MsgCenterServer implements Runnable {

    //先来个端口号
    public static int SERVICE_PORT = 9999;

    private final Socket socket;

    /**
     * 构造方法
     * @param socket
     */
    public MsgCenterServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("线程接收到消息：" + str);

                QsMap revMap = QsMap.fromObject(str);
                if (revMap.getInt("type")==1){
                    //生产消息放到消息队列中
                    MsgCenter.produce(revMap.getString("msg"));
                }else if (revMap.getInt("type")==2){
                    //从消息队列中消费一条消息
                    String message = MsgCenter.consume();
                    out.println(message);
                    out.flush();
                }else {
                    System.out.println("接收到的消息："+str+"没有type");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
