## Single Head Single Tail

This implementation demonstrates the **Single-Head-Single-Tail (SHST)** microservice composition pattern using Spring Boot and Spring Cloud. The workflow begins with a single head microservice and concludes with a single tail microservice, passing through a linear chain of intermediate services.

---

## Architectural Pattern: SHST

- **Head Microservice**: Initiates the workflow and sends the request downstream.
- **Intermediate Microservices**: Process and enrich the data sequentially.
- **Tail Microservice**: Aggregates the final result and returns the response.

---

## Microservices and Their Roles

| Microservice       | Role           | Type        | Description                                                                 |
|--------------------|----------------|-------------|-----------------------------------------------------------------------------|
| `content-service`  | 🟢 Entry Point  | **Head**    | Receives the initial request and starts the workflow.                      |
| `crd-service`      | 🔵 Processor    | Intermediate| Processes content-related metadata.                                        |
| `genres-service`   | 🔵 Processor    | Intermediate| Adds genre classification to the content.                                  |
| `labels-service`   | 🔵 Processor    | Intermediate| Attaches label information to the content.                                 |
| `reviews-service`  | 🔵 Processor    | Intermediate| Fetches and processes user reviews.                                        |
| `years-service`    | 🔴 Aggregator   | **Tail**    | Consolidates all data and returns the final response.                      |
| `discovery-server` | ⚙️ Infrastructure | N/A       | Eureka server for dynamic service registration and discovery.              |

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

## How to Run

Make sure Docker is installed. Then run:

```bash
docker-compose up --build

