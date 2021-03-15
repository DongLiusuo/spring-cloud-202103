package org.example.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class ReceiveMsgListener {

    @Value("${server.port}")
    private String port;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> msg) {
        System.out.println("收到消息：" + msg.getPayload() + "\tport：" + port);
    }
}
