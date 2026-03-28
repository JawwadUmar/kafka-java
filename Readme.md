# Kafka Stack with Docker Compose

This project sets up a **Kafka stack** using Docker Compose, including Kafka and Conduktor for easy management and visualization.

---

## 🚀 Prerequisites

- Docker installed and running on your machine
- Git installed

---

## 📥 Clone the Repository

```bash
git clone https://github.com/conduktor/kafka-stack-docker-compose.git
cd kafka-stack-docker-compose

```
---

## 📥 Start Kafka and Conduktor

```bash
docker compose -f conduktor-kafka-single.yml up
```

* Kafka will be available at `$DOCKER_HOST_IP:9092`
* Conduktor will be available at `$DOCKER_HOST_IP:8080`

---






