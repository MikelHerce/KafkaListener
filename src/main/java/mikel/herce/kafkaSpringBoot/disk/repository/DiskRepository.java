package mikel.herce.kafkaSpringBoot.disk.repository;

public interface DiskRepository {
	public void saveToDisk(String text) throws EmptyTextToSaveException;
}
