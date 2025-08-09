## SHST_SAGA_README.md

---

```markdown
# SHST Saga Pattern Implementation

This project demonstrates a distributed transaction workflow using the **Saga Pattern** within a **Single-Head-Single-Tail (SHST)** microservices architecture. It is designed to ensure data consistency and fault tolerance across multiple services without relying on two-phase commit (2PC).

---

## Architecture Overview

```
[Client]
   |
   v
[content-service]         # Head & Saga Initiator
   |
   v
[crd-service]             # Processor
   |
   v
[genres-service]          # Processor
   |
   v
[labels-service]          # Processor
   |
   v
[reviews-service]         # Processor
   |
   v
[years-service]           # Processor
   |
   v
[aggregation-service]     # Tail
```

Each service performs its task and passes the result to the next. If any service fails, compensating actions are triggered in reverse order to roll back the transaction.

---

## Technologies Used

- **Spring Boot** (Java)
- **REST APIs**
- **Docker & Docker Compose**
- **SQLite** (lightweight DB for each service)
- **Saga Pattern** (manual orchestration)

---

## Services

| Service             | Role                     |
|---------------------|--------------------------|
| `content-service`   | Saga initiator (head)    |
| `crd-service`       | Metadata processor       |
| `genres-service`    | Genre handler            |
| `labels-service`    | Label handler            |
| `reviews-service`   | Review handler           |
| `years-service`     | Year handler             |
| `aggregation-service` | Final result aggregator |

---

##  Running the Project

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/shst-saga.git
cd shst-saga
```

### 2. Start All Services

```bash
docker-compose up --build
```

Each service will be exposed on a different port (e.g., `8081`, `8082`, ...).

---

## 🧪 Testing the Saga Flow

### 1. Start a Saga

Send a `POST` request to the `content-service`:

```http
POST /saga/start
Content-Type: application/json

{
  "reviewId": "rev-123",
  "data": {
    "genre": "Jazz",
    "label": "Blue Note",
    "year": "1965",
    "review": "Classic album",
    "crd": "metadata"
  }
}
```

### 2. Observe the Flow

- If all services succeed, the data is persisted across all microservices.
- If any service fails, previous services execute their `compensate` endpoints to roll back.

---

## Compensation Logic

Each service exposes a `/saga/compensate` endpoint to undo its operation. This ensures eventual consistency without locking resources.

Example in `genres-service`:

```java
@PostMapping("/compensate")
public void compensate(@RequestBody TransactionPayload payload) {
    repository.deleteById(payload.getReviewId());
}
```

---

## Project Structure

```
shst-saga/
├── content-service/
├── crd-service/
├── genres-service/
├── labels-service/
├── reviews-service/
├── years-service/
├── aggregation-service/
├── common/
│   └── TransactionPayload.java
├── docker-compose.yml
└── SHST_SAGA_README.md
```