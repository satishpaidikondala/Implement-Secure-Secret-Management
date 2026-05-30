# Implement Secure Secret Management

A Spring Boot microservice that integrates with HashiCorp Vault for secure secret management. Secrets like database credentials and JWT signing keys are stored in Vault and injected at runtime via Spring Cloud Vault Config.

## Architecture

- **vault** - HashiCorp Vault in development mode (port 8200)
- **vault-init** - One-shot container that populates secrets on startup
- **app** - Spring Boot application (port 8080) consuming secrets from Vault

## Prerequisites

- Docker & Docker Compose installed

## How to Run

```bash
docker-compose up --build
```

All services start automatically. Vault is seeded with secrets before the app boots.

## Secrets Stored in Vault

| Key              | Path                          | Purpose                 |
|------------------|-------------------------------|-------------------------|
| `db.username`    | `secret/user-profile-service` | H2 database username    |
| `db.password`    | `secret/user-profile-service` | H2 database password    |
| `api.signing-key`| `secret/user-profile-service` | JWT HMAC-SHA256 key     |

## API Endpoints

### POST /api/profile
Creates a user profile and returns a JWT in the `Authorization` header.

**Request:**
```json
{"userId": "u-001", "username": "alice", "email": "alice@test.com"}
```

**Response:** `201 Created` with `Authorization: Bearer <jwt>` header.

### GET /api/profile/{userId}
Retrieves a user profile by ID.

**Response:** `200 OK` with profile body, or `404 Not Found`.

### GET /api/admin/vault-status
Confirms Vault connectivity and resolved properties.

**Response:**
```json
{
  "vaultConnected": true,
  "secretPath": "secret/user-profile-service",
  "resolvedDbUsername": "sa"
}
```

## Fail-Fast Behavior

The application will not start if Vault is unreachable, preventing runtime with missing or default credentials.

## Project Structure

```
.
├── docker-compose.yml
├── Dockerfile
├── .env.example
├── pom.xml
├── scripts/
│   └── setup-vault.sh
├── src/
│   └── main/
│       ├── java/com/example/userprofileservice/
│       │   ├── UserProfileServiceApplication.java
│       │   ├── model/UserProfile.java
│       │   ├── repository/UserProfileRepository.java
│       │   ├── dto/VaultStatusResponse.java
│       │   ├── service/JwtService.java
│       │   ├── controller/ProfileController.java
│       │   └── controller/AdminController.java
│       └── resources/application.yml
└── README.md
```
