package mikel.herce.kafkaSpringBoot.messages.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;
import mikel.herce.kafkaSpringBoot.disk.repository.DiskRepository;
import mikel.herce.kafkaSpringBoot.disk.repository.EmptyTextToSaveException;
import mikel.herce.kafkaSpringBoot.messages.helper.MessageFormatterHelper;
import mikel.herce.kafkaSpringBoot.messages.repository.MessageRepository;

@SpringBootTest
public class MessageServiceUnitTest {

	@InjectMocks
	MessageService messageService = new MessageServiceImpl();

	@Mock
	MessageRepository mockedMessageRepository;

	@Mock
	DiskRepository mockedDiskRepository;

	@Mock
	MessageFormatterHelper messageFormatter;

	@Mock
	ApplicationConfig appConfig;

	@Test
	void get_empty_messages() {
		// given
		List<String> emptyList = new ArrayList<>();
		Mockito.when(mockedMessageRepository.getAllMessages()).thenReturn(emptyList);
		// when
		List<String> returnedList = messageService.getAllMessages();
		// then
		assertEquals(emptyList, returnedList);
	}

	@Test
	void delete_empty_messages() {
		// given
		Mockito.doNothing().when(mockedMessageRepository).deleteMessages();
		// when
		messageService.deleteAllMessages();
		// then
		Mockito.verify(mockedMessageRepository).deleteMessages();
	}

	@Test
	void saveToDisk_test() throws EmptyTextToSaveException {
		// given
		String formattedText = "formatted text";
		Mockito.when(messageFormatter.formatMessages(Mockito.anyList())).thenReturn(formattedText);
		// when
		messageService.save();
		// then
		Mockito.verify(mockedMessageRepository).deleteMessages();
		Mockito.verify(mockedDiskRepository).saveToDisk("formatted text");
	}

	@Test
	void add_message_without_reaching_limit() throws IOException, EmptyTextToSaveException {
		// given
		String message = "a message";

		List<String> oneMessageList = new ArrayList<>();
		oneMessageList.add(message);

		Mockito.when(messageFormatter.formatMessages(Mockito.anyList())).thenReturn(message);
		Mockito.when(mockedMessageRepository.getAllMessages()).thenReturn(oneMessageList);
		Mockito.when(appConfig.getMessageLimit()).thenReturn(2);
		// when
		messageService.addMessage("a message");
		// then
		Mockito.verify(mockedMessageRepository).addMessage(message);
		Mockito.verify(mockedDiskRepository, Mockito.times(0)).saveToDisk(Mockito.anyString());
	}

	@Test
	void add_message_with_reaching_limit() throws IOException, EmptyTextToSaveException {
		// given
		String message = "a message";
		String formattedMessage = "a message";

		List<String> oneMessageList = new ArrayList<>();
		oneMessageList.add(message);

		Mockito.when(mockedMessageRepository.getAllMessages()).thenReturn(oneMessageList);
		Mockito.when(messageFormatter.formatMessages(Mockito.anyList())).thenReturn(formattedMessage);
		Mockito.when(appConfig.getMessageLimit()).thenReturn(1);
		// when
		messageService.addMessage("a message");
		// then
		Mockito.verify(mockedMessageRepository).addMessage(message);
		Mockito.verify(mockedDiskRepository).saveToDisk(formattedMessage);
	}

}
