package cn.itcast.queue.client;

public class Consumer {

    public static void main(String[] args) throws Exception {
        MqClient client = new MqClient();
        //接收消息
        String message = client.consume();
        System.out.println("获取的消息为：" + message);
    }
}
