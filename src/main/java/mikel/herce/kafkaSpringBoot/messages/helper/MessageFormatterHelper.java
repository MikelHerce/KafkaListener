package mikel.herce.kafkaSpringBoot.messages.helper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageFormatterHelper {

	public String formatMessages(List<String> messageList) {
		if (null == messageList) {
			return "";
		}
		if (messageList.size() == 1) {
			return messageList.get(0);
		}
		
		String text = "";
		int index = 0;
		for (String message : messageList) {
			index++;
			text = text + message;
			if (messageList.size() != index) {
				text = text + System.lineSeparator();
			}
			
		}
		return text;
	}
}
