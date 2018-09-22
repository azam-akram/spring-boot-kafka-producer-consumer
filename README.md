# spring-boot-kafka-producer-consumer

This little project demonstrate the easiest and simplest way to create kafka producer and consumer.

### What frameworks are used in this project?

* Spring Boot: "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"." Read more (https://spring.io/projects/spring-boot)
* Apache Kafka: Apache Kafka is a distributed streaming platform, based on subscribed-publish-consumer model to exchange messages between producer and consumer. Read more (https://kafka.apache.org/intro)
* Apache Zookeeper: "ZooKeeper is a centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services." (https://zookeeper.apache.org/)
* Docker-compose: Docker Compose is a tool for defining and running multi-container Docker applications. Read more (https://docs.docker.com/compose/)


### Prerequisites

This project uses docker-compose to run Zookeeper and Kafka docker containers, which means you don't need to install Kafka and Zookeeper locally.

Run the docker compose by following command,

```bash
docker-compose up -d
```

Stop docker-compose (once you are done)
```bash
docker-compose down
```

Continue ...
