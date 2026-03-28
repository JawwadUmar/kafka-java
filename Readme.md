# Kafka Stack with Docker Compose [Beginner]

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

## 📡 Kafka Producer: Wikimedia Stream

This project also includes a Kafka producer that streams live changes from Wikimedia into Kafka.

### ▶️ Steps to Run

1. **Navigate to the project directory**

```bash
cd conductor-platform
```

2. **Start required services (if not already running)**

```bash
docker compose -f your-file.yml up -d
```

3. **Run the Wikimedia Producer**

Once everything is up and running, start your `WikiMediaChangesProducer` application from your IDE or terminal.

---

### 📌 What this does

* Connects to the Wikimedia live stream
* Processes real-time change events
* Publishes them into a Kafka topic

---

### ✅ Verify

* Open Conduktor at `http://localhost:8080`
* Check your Kafka topics
* You should see live messages flowing in 🎉

---





