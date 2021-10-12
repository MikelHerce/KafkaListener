package mikel.herce.kafkaSpringBoot.disk.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DiskRespositoryImpl implements DiskRepository {

	private static Logger LOG = LoggerFactory.getLogger(DiskRespositoryImpl.class);

	@Override
	public void saveToDisk(String text) {
		if (text == null || text == "") {
			LOG.info("Nothing to save");
		} else {
			LOG.info("saved: " + text);
		}

	}
}
