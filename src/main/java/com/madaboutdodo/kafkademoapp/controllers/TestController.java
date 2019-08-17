package com.madaboutdodo.kafkademoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madaboutdodo.kafkademoapp.entity.User;

@RestController
@RequestMapping("/kafka")
public class TestController {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	private Environment env;
	
//	private final String TOPIC = "test";
	
	@GetMapping("/publish/{message}")
	public String publishMessage(@PathVariable("message") final String message) {
		
		kafkaTemplate.send(env.getProperty("producer.topic"), new User(1, message + " Test User", "test@test.com"));
		
		return "message published successfully";
	}
}
