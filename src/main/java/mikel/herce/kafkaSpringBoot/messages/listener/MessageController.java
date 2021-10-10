package mikel.herce.kafkaSpringBoot.messages.listener;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mikel.herce.kafkaSpringBoot.messages.buffer.MessageBufferImpl;
import mikel.herce.kafkaSpringBoot.messages.writer.MessageAppender;


@RestController
@RequestMapping("messages")
public class MessageController {

	@GetMapping("/addMessage/{message}")
    public void addMessage(@PathVariable String message) throws IOException {
        MessageAppender mr = new MessageAppender();
        mr.addMessage(message, new MessageBufferImpl());
    }
	
}
