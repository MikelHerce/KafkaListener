package mikel.herce.kafkaSpringBoot.messages.helper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageFormatterHelper {

	public String formatMessages(List<String> messageList) {
		String text = "";
		for (String message : messageList) {
			text = text + message + System.lineSeparator();
		}
		return text;
	}
}
