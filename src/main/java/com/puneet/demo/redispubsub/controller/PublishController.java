package com.puneet.demo.redispubsub.controller;

import com.puneet.demo.redispubsub.service.RedisPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private RedisPublisherService redisPublisherService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("channel") String channel, @RequestParam("message") String message) {
        redisPublisherService.publish(channel, message);
        return "Message published to channel " + channel;
    }
}