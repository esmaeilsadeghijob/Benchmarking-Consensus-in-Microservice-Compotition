## SHST_RAFT

---

```markdown
#  SHST Blockchain with Raft Consensus

This project implements a blockchain-style data pipeline using the **Single-Head-Single-Tail (SHST)** microservices composition pattern, enhanced with the **Raft consensus algorithm** to ensure distributed reliability and fault tolerance.

---

##  Architecture Overview

### Architecture: SHST + Raft Blockchain
```
[Client]
   |
   v
[transaction-service]      # Head: initiates a transaction
   |
   v
[validation-service]       # Validates and signs the transaction
   |
   v
[block-builder-service]    # Builds a block from validated data
   |
   v
[raft-node-service]        # Participates in Raft consensus
   |
   v
[ledger-service]           # Tail: stores the final blockchain ledger

```

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

##  Service Roles & Responsibilities

| Service Name         | Role in Architecture                                                                 |
|----------------------|---------------------------------------------------------------------------------------|
| `content-service`    | Provides content metadata (e.g. movies, shows, etc.)                                 |
| `reviews-service`    | Supplies user reviews and ratings                                                    |
| `artists-service`    | Delivers artist-related data                                                         |
| `crd-service`        | Handles CRUD operations for shared resources                                         |
| `genres-service`     | Supplies genre classifications                                                       |
| `labels-service`     | Provides label or publisher information                                              |
| `years-service`      | Offers release year data                                                             |
| `aggregation-service`| Collects data from all above services and builds a block                             |
| `raft-node-service`  | Receives blocks, runs Raft consensus, and forwards validated blocks                  |
| `ledger-service`     | Stores finalized blocks in a blockchain ledger                                       |
| `eureka-server`      | Service discovery for all microservices                                              |
| `config-server`      | Centralized configuration management                                                 |
| `gateway-service`    | API gateway for routing external requests to internal services                       |
| `docker-compose.yml` | Orchestrates all services for local deployment                                       |

---

## Project Structure
```
shst-raft-blockchain/
├── transaction-service/
├── validation-service/
├── block-builder-service/
├── raft-node-service/
│   ├── RaftController.java
│   ├── RaftState.java
│   └── Block.java
├── ledger-service/
│   └── LedgerController.java
├── common/
│   └── VoteRequest.java
│   └── Block.java
└── docker-compose.yml
```

```
microservices/
├── content-service
├── reviews-service
├── artists-service
├── crd-service
├── genres-service
├── labels-service
├── years-service
├── aggregation-service
├── raft-node-service        
├── ledger-service            
├── eureka-server
├── config-server
├── gateway-service
└── docker-compose.yml

```

## Microservice Interactions

### 1. Data Flow

- `content-service`, `reviews-service`, `artists-service`, `genres-service`, `labels-service`, `years-service`, and `crd-service`  
  → Send data to → `aggregation-service`

- `aggregation-service`  
  → Builds a block and sends it to → `raft-node-service`

- `raft-node-service`  
  → Runs Raft consensus among nodes  
  → Sends validated block to → `ledger-service`

- `ledger-service`  
  → Stores block in blockchain  
  → Exposes API to view the chain

---

### 2. Infrastructure Flow

- All services register with → `eureka-server`  
- All configurations are loaded from → `config-server`  
- External requests go through → `gateway-service`

---

## Architectural Highlights

- **Microservice-based**: Each domain is isolated and independently deployable.
- **Raft Consensus**: Ensures reliable agreement before storing blocks.
- **Blockchain Ledger**: Immutable and traceable storage of aggregated data.
- **Service Discovery & Gateway**: Simplifies routing and scaling.

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