package mikel.herce.kafkaSpringBoot.messages.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mikel.herce.kafkaSpringBoot.messages.service.MessageService;

@RestController
@RequestMapping("messages")
public class MessageController {

	@Autowired
	MessageService messageService;

	@GetMapping("/addMessage/{message}")
	public void addMessage(@PathVariable String message) throws IOException {
		messageService.addMessage(message);
	}

	@GetMapping("/getMessages")
	public List<String> getAllMessages() throws IOException {
		return messageService.getAllMessages();
	}

	@GetMapping("/deleteMessages")
	public void deleteAllMessages() throws IOException {
		messageService.deleteAllMessages();
	}

}
