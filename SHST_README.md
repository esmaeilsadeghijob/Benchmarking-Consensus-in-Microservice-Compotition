## Single Head Single Tail (SHST)

This implementation demonstrates the **Single-Head-Single-Tail (SHST)** microservice composition pattern using Spring Boot and Spring Cloud. In this architectural pattern, the workflow begins with a single head microservice and concludes with a single tail microservice. The head microservice initiates the process by performing its designated tasks and passing the output to the next microservice. Each subsequent microservice continues the process until the tail microservice receives the aggregated results, consolidating them to deliver the final output. This pattern provides a straightforward, linear flow of tasks from initiation to completion.
---

## Architectural Pattern: SHST

- **Head Microservice**: Initiates the workflow and sends the request downstream.
- **Intermediate Microservices**: Process and enrich the data sequentially.
- **Tail Microservice**: Aggregates the final result and returns the response.

---

## Microservices and Their Roles

| Microservice        | Role            | Type         | Description                                                                |
|---------------------|-----------------|--------------|----------------------------------------------------------------------------|
| `content-service`   | Entry Point     | **Head**     | Receives the initial request and starts the workflow.                      |
| `artists-service`   | Processor       | Intermediate | Provides artist name associated with each review.                          |
| `crd-service`       | Processor       | Intermediate | Processes content-related metadata.                                        |
| `genres-service`    | Processor       | Intermediate | Adds genre classification to the content.                                  |
| `labels-service`    | Processor       | Intermediate | Attaches label information to the content.                                 |
| `reviews-service`   | Processor       | Intermediate | Fetches and processes user reviews.                                        |
| `years-service`     | Aggregator      | **Tail**     | Consolidates all data and returns the final response.                      |
| `discovery-server`  | Infrastructure  | N/A          | Eureka server for dynamic service registration and discovery.              |
| `config-server`     | Infrastructure  | N/A          | Centralized configuration management for all services.                     |
| `gateway-service`   | Infrastructure  | N/A          | API Gateway that routes external requests to appropriate microservices.    |

---

## Workflow Execution

1. A client sends a request to `content-service`.
2. `content-service` forwards the request to `crd-service`.
3. The request flows sequentially through:
   - `genres-service`
   - `labels-service`
   - `reviews-service`
4. Finally, `years-service` receives the enriched data and returns the final output.

All services communicate using **Spring Cloud OpenFeign** and register with **Eureka Discovery Server**.

---

```

[Client]
   |
   v
[reviews-service] ---> [artists-service]
         |                  |
         v                  v
   [genres-service]     [content-service]
         |                  |
         v                  v
   [labels-service]     [years-service]
         \                /
          \              /
           v            v
        [aggregation-service]
               |
               v
           [Final Output]




microservices/
├── content-service         # Head
├── reviews-service         # Head
├── artists-service         # Middle
├── crd-service             # Middle
├── genres-service          # Middle
├── labels-service          # Middle
├── years-service           # Middle
├── aggregation-service     # Tail
├── eureka-server           # Service Discovery
├── config-server           # Central Config
├── gateway-service         # API Gateway 
└── docker-compose.yml      # Run all services



Java 17
Spring Boot 3.2.4
Microservice (SHST)
SQLite
Eureka Client
Spring Boot Actuator

```

---

## How to Run

Make sure Docker is installed. Then run:

```bash
docker-compose up --build

