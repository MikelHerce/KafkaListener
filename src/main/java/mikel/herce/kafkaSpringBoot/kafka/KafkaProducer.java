package mikel.herce.kafkaSpringBoot.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mikel.herce.kafkaSpringBoot.ApplicationConfig;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, String> producer;
	
	@Autowired
	private ApplicationConfig appConfig;

	@GetMapping("/produceMessage/{message}")
	public void produceMessage(@PathVariable String message) throws IOException {
		try {
			producer.send(appConfig.getKafkaTopic(), message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
