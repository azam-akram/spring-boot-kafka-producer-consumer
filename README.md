# spring-boot-kafka-producer-consumer

This little demo project demonstrate the easiest and simplest way to create kafka producer and consumer.

### What frameworks are used in this project

* Spring Boot
* Apache Kafka
* Apache Zookeeper
* Docker-compose

### What is Kafka?

Apache Kafka is a distributed streaming platform, based on subscribed-publish-consumer model to exchange messages between producer and consumer.
Get an introduction of Apache Kafka,
https://kafka.apache.org/intro

### What is Apache Zookeeper

"ZooKeeper is a centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services."
https://zookeeper.apache.org/


### Prerequisites

This projects uses docker-compose to run Zookeeper and Kafka docker containers, which means you don't need to install Kafka and Zookeeper locally.

Run the docker compose by following command,

```bash
docker-compose up -d
```

Stop docker-compose (once you are done)
```bash
docker-compose down
```

Continue ...