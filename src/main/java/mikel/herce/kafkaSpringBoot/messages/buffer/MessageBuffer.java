package mikel.herce.kafkaSpringBoot.messages.buffer;

import java.io.IOException;

public interface MessageBuffer {
	public void addMessageToBuffer(String message) throws IOException;
}
