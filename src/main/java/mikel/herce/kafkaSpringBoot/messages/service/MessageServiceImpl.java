package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;
import mikel.herce.kafkaSpringBoot.disk.repository.DiskRepository;
import mikel.herce.kafkaSpringBoot.exceptions.EmptyTextToSaveException;
import mikel.herce.kafkaSpringBoot.messages.helper.MessageFormatterHelper;
import mikel.herce.kafkaSpringBoot.messages.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	public static final String DOUBLE_POINT_SPACE = ": ";
	public static final String MESSAGE_ADDED = "Message added";

	private static Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	ApplicationConfig appConfig;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	DiskRepository diskRepository;

	@Autowired
	MessageFormatterHelper messageFomatter;

	@Override
	public void addMessage(String message) throws IOException, EmptyTextToSaveException {
		messageRepository.addMessage(message);
		LOG.info(MESSAGE_ADDED + DOUBLE_POINT_SPACE + message);
		if (isMessageLimitReached()) {
			this.save();
		}
	}

	@Override
	public void save() throws EmptyTextToSaveException {
		String textToSave = messageFomatter.formatMessages(getAllMessages());
		diskRepository.saveToDisk(textToSave);
		messageRepository.deleteMessages();
	}

	@Override
	public List<String> getAllMessages() {
		return messageRepository.getAllMessages();
	}

	@Override
	public void deleteAllMessages() {
		messageRepository.deleteMessages();
	}

	private boolean isMessageLimitReached() {
		return messageRepository.getAllMessages().size() >= appConfig.getMessageLimit() ? true : false;
	}

}
