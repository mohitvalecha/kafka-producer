package com.madaboutdodo.kafkademoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madaboutdodo.kafkademoapp.entity.User;

@RestController
@RequestMapping("/kafka")
public class TestController {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	private final String TOPIC = "test";
	
	@RequestMapping("/publish/{message}")
	public String publishMessage(@PathVariable("message") final String message) {
		
		kafkaTemplate.send(TOPIC, new User(1, message + " Test User", "test@test.com"));
		
		return "message published successfully";
	}
}
