# KafkaListener


This app consumes Kafka topic, saving received messages to disk in gzip files.
The saving action is executed when an amount of messages is received, and every defined time (scheduled action).

## Launching app

- mvn clean install
- java -jar {jarPath} (will use application.properties defined into the jar)
- java -jar {jarPath}  --spring.config.location=file:{customFilePath} (to use external config file)

### Configuration file

    path.to.save={path}
    messages.limit={numberOfMessagesToSave}
    miliseconds.to.save={milisecondsToSave}
        
    spring.kafka.bootstrap-servers={kafkaServerAdress}
    spring.kafka.consumer.group-id={group-id}
    kafka.topic={kafkaTopic}

## Kafka

In my case, kafka server that i have used, is docker based:


### docker-compose.yaml

    version: '2'
    services:
      zookeeper:
        image: confluentinc/cp-zookeeper:latest
        environment:
          ZOOKEEPER_CLIENT_PORT: 2181
          ZOOKEEPER_TICK_TIME: 2000
        ports:
          - 22181:2181
      
      kafka:
        image: confluentinc/cp-kafka:latest
        depends_on:
          - zookeeper
        ports:
          - 29092:29092
        environment:
          KAFKA_BROKER_ID: 1
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
          KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://{kafkaServerHost}
          KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
          KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
          KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
          KAFKA_AUTO_CREATE_TOPICS_ENABLE: "true"

> {kafkaServerHost} must be defined into docker-compose.yaml file.
