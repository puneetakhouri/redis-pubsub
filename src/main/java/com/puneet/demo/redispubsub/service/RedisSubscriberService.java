package com.puneet.demo.redispubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class RedisSubscriberService implements MessageListener {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Value("${redis.pubsub.channels}")
    private String channels;

    @PostConstruct
    public void init() {
        Arrays.stream(channels.split(","))
                .map(String::trim)
                .forEach(channel -> redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(channel)));
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Received message: " + new String(message.getBody()) + " on channel: " + new String(message.getChannel()));
    }
}