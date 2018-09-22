# spring-boot-kafka-producer-consumer

This little project demonstrate the easiest and simplest way to create kafka producer and consumer.

### What frameworks are used in this project?

* Spring Boot: "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"." Read more (https://spring.io/projects/spring-boot)
* Apache Kafka: Apache Kafka is a distributed streaming platform, based on subscribed-publish-consumer model to exchange messages between producer and consumer. Read more (https://kafka.apache.org/intro)
* Apache Zookeeper: "ZooKeeper is a centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services." (https://zookeeper.apache.org/)
* Docker-compose: Docker Compose is a tool for defining and running multi-container Docker applications. Read more (https://docs.docker.com/compose/)


### Prerequisites

This project uses docker-compose to run Zookeeper and Kafka docker containers, which means you don't need to install Kafka and Zookeeper locally.

### Walk through the code
Don't worry about complex configurations for Kafka, Spring Boot provides some properties to configure Kafka Producer and Consumer.

```
server:
  port: 5555

application:
  api:
    version: v1
  topic:
    message-topic: demo-kafka-topic

spring:
  kafka:
    bootstrap-servers: 127.0.0.1:9092
    consumer:
      group-id: demo-kafka-consumer
      enable-auto-commit: true
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

I used String as format of message to exchange that is why use StringSerializer and StringDeserializer. On receiving th message consumer unmarshals the message into Message class object.

Message class is simple POJO,

```
public class Message {

    @NotNull
    private String uuid;

    @NotBlank
    private String from;

    @NotBlank
    private String to;
} 
```

Now we left with just to make a KafkaProducer class to send the message and a KafkaConsumer class to receive that message.

```
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${application.topic.message-topic}")
    private String topic;

    public void sendMessage() {
        kafkaTemplate.send(topic, createMessage());
    }

    private String createMessage() {
        return "{  \n" +
                "   \"uuid\":\"kdfe25b9-akda-49bf-ab3a-6482a19ahshs\",\n" +
                "   \"from\":\"Sender\",\n" +
                "   \"to\":\"Receiver\"\n" +
                "}";
    }
}
```

Spring Boot reads the properties and configure the Producer and Consumer for you, all you need to Autowired the KafkaTemplate into your Producer class,

```
@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
```

and then send the message,

```
kafkaTemplate.send(topic, createMessage());
```

Ofcourse we need to mention kafka topic we want to send message to.

On receiving end, I have a KafkaConsumer class,

```
@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = "${application.topic.message-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(final ConsumerRecord<String, String> consumerRecord) throws IOException {
        String value = consumerRecord.value();
        log.trace("Received Messasge: " + value);
        Message message = new ObjectMapper().readValue(value, Message.class);
        log.trace(message.toString());
    }
}
```

You see KafkaConsumer has one very simple onMessage callback method. @KafkaListner annotation does the magic for you, you need to override it into your class.

ConsumerRecord is the data structure which carries all the necessary information for the exchanged message.

### How to run?

Run the docker compose by following command,

First run the docker compose

```bash
docker-compose up -d
```

Stop docker-compose (once you are done)
```bash
docker-compose down
```

Then run the KafkaDemoApplication to exchange the message.
