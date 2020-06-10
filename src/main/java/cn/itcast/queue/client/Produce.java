package cn.itcast.queue.client;

import cn.itcast.queue.entity.QsMap;

public class Produce {

    public static void main(String[] args) throws Exception {
        MqClient client = new MqClient();

        QsMap param = new QsMap();
        param.put("name", "fuyong");
        param.put("age", 27);
        param.put("dept", "研发部");

        //发送消息
        client.produce(param.toString());
    }
}
