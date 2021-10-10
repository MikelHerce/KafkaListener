package mikel.herce.kafkaSpringBoot.messages.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MessageMemoryListImpl implements MessageRespository {
	static List<String> messagesBuffer = new ArrayList<String>();

	@Override
	public void addMessageToBuffer(String message) throws IOException {
		messagesBuffer.add(message);
	}

	@Override
	public List<String> getAllMessages() {
		return messagesBuffer;
	}

	@Override
	public void deleteMessages() {
		messagesBuffer.clear();
	}

}
