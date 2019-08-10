package com.madaboutdodo.kafkademoapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/kafka")
public class KafkaDemoApplication {

	List<String> messages = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@KafkaListener(topics = "foo", groupId = "consume-id1", containerFactory = "kafkaListenerContainerFactory")
	public List<String> consume(String message) {
		System.out.println("consumed message " + message);
		messages.add(message);
		return messages;
	}

	@GetMapping("/consume")
	public List<String> consumeMessage() {
		return messages;
	}
}
