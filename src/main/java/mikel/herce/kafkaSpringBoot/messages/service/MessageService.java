package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

public interface MessageService {
	public void addMessage(String message) throws IOException;
	public List<String> getAllMessages();
	public void deleteAllMessages();
	public void saveToDisk();
	
}
