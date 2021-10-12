package mikel.herce.kafkaSpringBoot.messages.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mikel.herce.kafkaSpringBoot.messages.repository.MessageRepository;

@SpringBootTest
public class MemoryMessageRepositoryTest {

	@Autowired
	MessageRepository messageRepository;

	@AfterEach
	void after() {
		messageRepository.deleteMessages();
	}

	@Test
	void get_empty_messages() {
		// given
		List<String> emptyList = new ArrayList<>();
		List<String> returnedList = messageRepository.getAllMessages();
		assertEquals(emptyList, returnedList);
	}

	@Test
	void get_one_element_list() throws IOException {
		// given
		List<String> oneElementList = new ArrayList<>();
		oneElementList.add("first message");
		// when
		messageRepository.addMessage("first message");
		List<String> returnedList = messageRepository.getAllMessages();
		// then
		assertEquals(oneElementList, returnedList);
	}

	@Test
	void get_two_element_list() throws IOException {
		// given
		List<String> twoElementList = new ArrayList<>();
		twoElementList.add("first message");
		twoElementList.add("second message");
		// when
		messageRepository.addMessage("first message");
		messageRepository.addMessage("second message");
		List<String> returnedList = messageRepository.getAllMessages();
		// then
		assertEquals(twoElementList, returnedList);
	}

}
