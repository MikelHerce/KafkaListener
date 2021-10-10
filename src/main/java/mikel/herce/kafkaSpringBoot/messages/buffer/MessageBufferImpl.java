package mikel.herce.kafkaSpringBoot.messages.buffer;

import java.io.IOException;
import java.util.List;

public class MessageBufferImpl implements MessageBuffer {
	static List<String> messagesBuffer;

	@Override
	public void addMessageToBuffer(String message) throws IOException {
		messagesBuffer.add(message);
	}

	public static List<String> getBufferedMessages() {
		return messagesBuffer;
	}

}
