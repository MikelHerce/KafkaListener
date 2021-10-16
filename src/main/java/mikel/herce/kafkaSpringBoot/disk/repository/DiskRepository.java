package mikel.herce.kafkaSpringBoot.disk.repository;

import mikel.herce.kafkaSpringBoot.exceptions.EmptyTextToSaveException;

public interface DiskRepository {
	public void saveToDisk(String text) throws EmptyTextToSaveException;
}
