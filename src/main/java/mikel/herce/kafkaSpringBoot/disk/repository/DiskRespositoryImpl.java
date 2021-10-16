package mikel.herce.kafkaSpringBoot.disk.repository;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;
import mikel.herce.kafkaSpringBoot.disk.helper.FileNameHelper;
import mikel.herce.kafkaSpringBoot.exceptions.EmptyTextToSaveException;

@Component
public class DiskRespositoryImpl implements DiskRepository {

	private static Logger LOG = LoggerFactory.getLogger(DiskRespositoryImpl.class);

	@Autowired
	ApplicationConfig appConfig;

	@Autowired
	FileNameHelper fileNameHelper;

	@Override
	public void saveToDisk(String text) throws EmptyTextToSaveException {
		if (text == null || text == "") {
			throw new EmptyTextToSaveException();
		}

		Path path = Paths.get(appConfig.getPathToSave() + fileNameHelper.generateFileName() + ".gzip");

		try (GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(path.toFile()))) {
			gos.write(text.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			LOG.error("Error saving to disk: " + e.getMessage());
		}

	}
}
