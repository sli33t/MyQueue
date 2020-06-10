package cn.itcast.queue;

import cn.itcast.queue.center.MsgCenterServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
public class MyQueueApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MyQueueApplication.class, args);

        ServerSocket server = new ServerSocket(MsgCenterServer.SERVICE_PORT);
        while (true) {
            MsgCenterServer msgCenterServer = new MsgCenterServer(server.accept());
            new Thread(msgCenterServer).start();
        }
    }
}
