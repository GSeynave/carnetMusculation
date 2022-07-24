package com.muscu.carnetMusculation.controllers;

import java.util.UUID;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {
	@Autowired
	private MyBasic
	
	@RequestMapping("")
	public Message home() {
		return new Message("Logged in");
	}
	
	class Message {
		  private String id = UUID.randomUUID().toString();
		  private String content;
		  public Message(String content) {
		    this.content = content;
		  }
	}
}
