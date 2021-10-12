package mikel.herce.kafkaSpringBoot.messages.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageFormatterUnitTest {

	@Autowired
	MessageFormatterHelper messageFormatter;

	@Test
	void text_empty_when_null() {
		// given
		List<String> nullList = null;
		// when
		String formattedMessage = messageFormatter.formatMessages(nullList);
		// then
		assertEquals("", formattedMessage);
	}

	@Test
	void text_empty_when_empty_list() {
		// given
		List<String> emptyList = new ArrayList<>();
		// when
		String formattedMessage = messageFormatter.formatMessages(emptyList);
		// then
		assertEquals("", formattedMessage);
	}

	@Test
	void text_formatted_when_one_message() {
		// given
		List<String> oneMessageList = new ArrayList<>();
		oneMessageList.add("first message");
		// when
		String formattedMessage = messageFormatter.formatMessages(oneMessageList);
		// then
		assertEquals("first message", formattedMessage);
	}

	@Test
	void text_formatted_when_two_messages() {
		// given
		List<String> twoMessageList = new ArrayList<>();
		twoMessageList.add("first message");
		twoMessageList.add("second message");
		// when
		String formattedMessage = messageFormatter.formatMessages(twoMessageList);
		// then
		assertEquals("first message" + System.lineSeparator() + "second message", formattedMessage);
	}

	@Test
	void text_formatted_when_three_messages() {
		// given
		List<String> threeMessageList = new ArrayList<>();
		threeMessageList.add("first message");
		threeMessageList.add("second message");
		threeMessageList.add("third message");
		// when
		String formattedMessage = messageFormatter.formatMessages(threeMessageList);
		// then
		assertEquals("first message" + System.lineSeparator() + "second message"  + System.lineSeparator() + "third message", formattedMessage);
	}

}
