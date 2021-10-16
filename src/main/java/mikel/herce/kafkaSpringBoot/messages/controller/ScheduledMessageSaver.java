package mikel.herce.kafkaSpringBoot.messages.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;
import mikel.herce.kafkaSpringBoot.messages.service.MessageService;

@Component
@EnableScheduling
public class ScheduledMessageSaver {

	@Autowired
	MessageService messageService;
	
	@Autowired
	static ApplicationConfig appConfig;

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledMessageSaver.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	

	@Scheduled(fixedDelayString = "${miliseconds.to.save}")
	private void saveToDisk() {
		LOG.info("Save scheduled time {}", dateFormat.format(new Date()));
		try {
			messageService.save();
			
		} catch (Exception e) {
			LOG.warn("Nothing to save");
		}

	}
}
