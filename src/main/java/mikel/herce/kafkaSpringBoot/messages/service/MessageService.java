package mikel.herce.kafkaSpringBoot.messages.service;

import java.io.IOException;
import java.util.List;

import mikel.herce.kafkaSpringBoot.disk.repository.EmptyTextToSaveException;

public interface MessageService {
	public void addMessage(String message) throws IOException, EmptyTextToSaveException;
	public List<String> getAllMessages();
	public void deleteAllMessages();
	public void save() throws EmptyTextToSaveException;
	
}
