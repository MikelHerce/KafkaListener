package mikel.herce.kafkaSpringBoot.disk.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FileNameHelper {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");

	public String generateFileName() {
		return dateFormat.format(new Date());
	}
}
