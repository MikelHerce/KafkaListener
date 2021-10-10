package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikel.herce.kafkaSpringBoot.messages.repository.MessageRespository;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageRespository messageRespository;

	@Override
	public void addMessage(String message) throws IOException {
		messageRespository.addMessageToBuffer(message);

	}

	@Override
	public List<String> getAllMessages() throws IOException {
		return messageRespository.getAllMessages();
	}

}
