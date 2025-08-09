## SHST_RAFT

---

```markdown
#  SHST Blockchain with Raft Consensus

This project implements a blockchain-style data pipeline using the **Single-Head-Single-Tail (SHST)** microservices composition pattern, enhanced with the **Raft consensus algorithm** to ensure distributed reliability and fault tolerance.

---

##  Architecture Overview

### SHST Composition

The system follows a linear flow of microservices:

```
[content-service] → [reviews-service] → [artists-service] → [crd-service] → 
[genres-service] → [labels-service] → [years-service] → [aggregation-service]
→ [raft-node-service] → [ledger-service]
```

- **Head**: `content-service` initiates the workflow.
- **Tail**: `ledger-service` stores the final blockchain ledger.
- Each service enriches or validates the data before passing it forward.

### Raft Consensus

- `raft-node-service` implements the Raft algorithm to ensure consensus before committing blocks.
- Multiple Raft nodes can be deployed for leader election and log replication.
- Only committed blocks are forwarded to the `ledger-service`.

---

##  Microservices

| Service              | Description                                      |
|---------------------|--------------------------------------------------|
| `content-service`    | Starts the data flow with raw content            |
| `reviews-service`    | Adds user reviews                                |
| `artists-service`    | Enriches data with artist metadata               |
| `crd-service`        | Handles copyright and rights data                |
| `genres-service`     | Adds genre classification                        |
| `labels-service`     | Adds label and publisher info                    |
| `years-service`      | Adds release year metadata                       |
| `aggregation-service`| Aggregates all data into a single payload        |
| `raft-node-service`  | Runs Raft consensus and commits blocks           |
| `ledger-service`     | Stores the blockchain ledger                     |
| `eureka-server`      | Service discovery                                |
| `config-server`      | Centralized configuration                        |
| `gateway-service`    | API gateway and routing                          |

---

##  Block Structure

Each block contains:

```json
{
  "index": 1,
  "previousHash": "abc123",
  "data": "{...aggregated music metadata...}",
  "timestamp": 1691572800000,
  "hash": "def456"
}
```

---

##  Getting Started

### Prerequisites

- Docker & Docker Compose
- Java 17+
- Maven

### Run with Docker Compose

```bash
docker-compose up --build
```

### Access Services

| Service              | Port  |
|---------------------|-------|
| Eureka Server        | 8761  |
| Config Server        | 8888  |
| Gateway              | 8080  |
| Raft Node            | 8090  |
| Ledger               | 8091  |
| Aggregation Service  | 8092  |

---

##  Workflow Example

1. `content-service` receives a new music entry.
2. Data flows through SHST services, each enriching the payload.
3. `aggregation-service` builds a block and sends it to `raft-node-service`.
4. Raft nodes reach consensus and commit the block.
5. `ledger-service` stores the block in the blockchain.

---

##  Testing

Use Postman or curl to simulate a transaction:

```bash
curl -X POST http://localhost:8080/content/initiate \
     -H "Content-Type: application/json" \
     -d '{"title": "Song A", "artist": "Artist X"}'
```

Check committed blocks:

```bash
curl http://localhost:8091/ledger/chain
```

---

##  Reliability

- Raft ensures fault-tolerant consensus.
- SHST guarantees linear and predictable data flow.
- Each microservice is independently deployable and scalable.

---

##  Design Principles

- **Separation of concerns**: Each service has a single responsibility.
- **Resilience**: Raft handles node failures gracefully.
- **Extensibility**: New services can be added to the SHST chain easily.