package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mikel.herce.kafkaSpringBoot.constants.LogConstant;
import mikel.herce.kafkaSpringBoot.messages.repository.MessageRespository;

@Service
public class MessageServiceImpl implements MessageService {

	private static Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	MessageRespository messageRepository;

	@Override
	public void addMessage(String message) throws IOException {
		messageRepository.addMessage(message);
		LOG.info(LogConstant.MESSAGE_ADDED + LogConstant.DOUBLE_POINT_SPACE + message);
		if (isMessageLimitReached()) {
			saveToDisk();
		}
	}

	private boolean isMessageLimitReached() {
		return messageRepository.getAllMessages().size() >= 3 ? true : false;
	}

	private void saveToDisk() {
		LOG.info("lo guardamos a disco");
		messageRepository.deleteMessages();
	}

	@Override
	public List<String> getAllMessages() {
		LOG.info(LogConstant.MESSAGE_GET);
		return messageRepository.getAllMessages();
	}

	@Override
	public void deleteAllMessages() {
		LOG.info(LogConstant.MESSAGE_DELETED);
		messageRepository.deleteMessages();
	}

}
