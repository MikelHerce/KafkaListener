package mikel.herce.kafkaSpringBoot.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;
import mikel.herce.kafkaSpringBoot.exceptions.EmptyTextToSaveException;
import mikel.herce.kafkaSpringBoot.messages.service.MessageService;

@Component
public class KafkaConsumer {

	@Autowired
	MessageService messageService;

	@Autowired
	static ApplicationConfig appConfig;

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${kafka.topic}")
	public void consumeMessage(final String message) {
		System.out.println("Received: " + message);
		try {
			messageService.addMessage(message);
		} catch (IOException e) {
			LOG.error("ERROR saving message: " + message);
			e.printStackTrace();
		} catch (EmptyTextToSaveException e) {
			LOG.warn("Nothing to save");
		}
	}
}
