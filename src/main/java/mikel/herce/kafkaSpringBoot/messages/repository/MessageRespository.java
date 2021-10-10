package mikel.herce.kafkaSpringBoot.messages.repository;

import java.io.IOException;
import java.util.List;

public interface MessageRespository {
	public void addMessageToBuffer(String message) throws IOException;
	public List<String> getAllMessages();
}
