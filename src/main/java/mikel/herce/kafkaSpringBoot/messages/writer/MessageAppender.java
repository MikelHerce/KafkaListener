package mikel.herce.kafkaSpringBoot.messages.writer;

import java.io.IOException;

import mikel.herce.kafkaSpringBoot.messages.buffer.MessageBuffer;

public class MessageAppender {
	public void addMessage(String message, MessageBuffer messageBuffer) throws IOException {
		messageBuffer.addMessageToBuffer(message);
	}
}
