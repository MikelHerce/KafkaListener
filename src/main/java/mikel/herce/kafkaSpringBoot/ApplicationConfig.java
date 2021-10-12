package mikel.herce.kafkaSpringBoot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

	@Value("${messages.limit}")
	private int messageLimit;

	@Value("${path.to.save}")
	private String pathToSave;

	@Value("${miliseconds.to.save}")
	private long secondsToSave;

	public int getMessageLimit() {
		return messageLimit;
	}

	public String getPathToSave() {
		return pathToSave;
	}

	public long getSecondsToSave() {
		return secondsToSave;
	}

}