package cn.itcast.queue.center;

import java.util.concurrent.ArrayBlockingQueue;

public class MsgCenter {

    // 队列存储消息的最大数量
    private final static int MAX_SIZE = 10;

    // 保存消息数据的容器
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    // 生产消息放入队列
    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.println("消息处理中心处理消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            System.out.println("消息处理中心内暂存的消息超过最大值！");
        }
        System.out.println("=======================");
    }

    // 消费消息从队列中拉取
    public static String consume() {
        String msg = messageQueue.poll();
        if (msg != null) {
            // 消费条件满足情况，从消息容器中取出一条消息
            System.out.println("已经消费消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            System.out.println("消息处理中心内没有消息！");
        }
        System.out.println("=======================");

        return msg;
    }
}
