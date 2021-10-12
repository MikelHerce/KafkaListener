package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikel.herce.kafkaSpringBoot.constants.LogConstant;
import mikel.herce.kafkaSpringBoot.disk.repository.DiskRespository;
import mikel.herce.kafkaSpringBoot.messages.helper.MessageFormatterHelper;
import mikel.herce.kafkaSpringBoot.messages.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	private static Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	DiskRespository diskRepository;

	@Autowired
	MessageFormatterHelper messageFomatter;

	@Override
	public void addMessage(String message) throws IOException {
		messageRepository.addMessage(message);
		LOG.info(LogConstant.MESSAGE_ADDED + LogConstant.DOUBLE_POINT_SPACE + message);
		if (isMessageLimitReached()) {
			this.saveToDisk();
		}
	}

	@Override
	public void saveToDisk() {
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
		return messageRepository.getAllMessages().size() >= 10 ? true : false;
	}

}
