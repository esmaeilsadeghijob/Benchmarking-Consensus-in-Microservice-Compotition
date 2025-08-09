## SHST_2PC

---

```markdown
#  SHST Architecture with Two-Phase Commit (2PC)

This project implements a distributed transaction mechanism using the **Two-Phase Commit (2PC)** protocol within a **Single-Head-Single-Tail (SHST)** microservices architecture. It ensures atomicity and consistency across services in a linear workflow.

---

## 🏗 Architecture Overview

```
[Client]
   |
   v
[content-service]         # Head & Transaction Coordinator
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

- **Head (content-service)** initiates the transaction and coordinates 2PC.
- **Tail (aggregation-service)** consolidates final results.
- Intermediate services participate in the transaction lifecycle.

---

## ⚙ Two-Phase Commit Protocol

### Phase 1: Prepare
- The coordinator sends a `prepare` request to all participants.
- Each service validates the request and stores data temporarily.
- If all respond with success, the coordinator proceeds to Phase 2.

### Phase 2: Commit / Rollback
- If all participants are ready, the coordinator sends a `commit` request.
- If any participant fails, the coordinator sends a `rollback` request to all.

---

##  API Endpoints

### Coordinator (`content-service`)
```http
POST /transaction/start
{
  "reviewId": "r101",
  "data": {
    "genre": "Jazz",
    "label": "Blue Note",
    "year": "1965"
  }
}
```

### Participant Services (`genres-service`, `labels-service`, etc.)
```http
POST /2pc/prepare
POST /2pc/commit
POST /2pc/rollback
```

Each participant implements these endpoints to handle transaction phases.

---

## 🧪 Testing Instructions

1. Start all services using `docker-compose up`.
2. Confirm service registration with Eureka.
3. Send a transaction request to `content-service`.
4. Monitor logs for `prepare`, `commit`, or `rollback` actions.
5. Verify final aggregation in `aggregation-service`.

---

## 📁 Project Structure

```
shst-2pc/
├── content-service/
│   └── TransactionCoordinator.java
├── genres-service/
│   └── TwoPhaseController.java
├── labels-service/
│   └── TwoPhaseController.java
├── reviews-service/
│   └── TwoPhaseController.java
├── years-service/
│   └── TwoPhaseController.java
├── aggregation-service/
│   └── FinalAggregator.java
├── common/
│   └── TransactionPayload.java
├── docker-compose.yml
└── SHST_2PC_README.md
```

---

## 🛡 Reliability Considerations

- Temporary data is stored in memory or Redis during `prepare`.
- Timeout and retry logic can be added for resilience.
- For long-running workflows, consider Saga or event-driven alternatives.
- 
