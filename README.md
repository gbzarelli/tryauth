# TryAuth

Authorization and authentication sample with resource server (keycloak).

## Technologies

- Kotlin 1.8.22
- JDK 17
- Spring Boot 3.1.1
  - Web 
  - Actuator
  - SpringDoc OpenApi
  - Spring Security
    - Oauth2 Resource Server

## How to run

### Run local stack

In your shell run:

```shell
docker-compose up
```

### Run the project

```shell
./mvnw spring-boot:run
```

## Keycloak - Resource Server

This stack run a KeyCloak already configured with the follow credentials:

- REAM: `tryauth`
- Clients
  - ID: `foo-user`
    - Grant Type: `client_credentials`
    - Scopes: `tryauth:read`
  - ID: `bar-user`
      - Grant Type: `client_credentials`
      - Scopes: `tryauth:read tryauth:write`

### How to generate a token:

By default, the secret is not imported with a value, 
but you can use: `**********` . If you want to reset, just access the panel and regenerate it.
- For `foo-user`: http://localhost:9002/admin/master/console/#/tryauth/clients/9a520b23-531a-4d4b-8684-579cb74a05d7/credentials
- For `bar-user`: http://localhost:9002/admin/master/console/#/tryauth/clients/490a5ec9-8413-490c-8a37-9645378f555a/credentials

#### Generate a token for foo-user (only read):

```shell
curl --location 'http://localhost:9002/realms/tryauth/protocol/openid-connect/token' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=foo-user' \
--data-urlencode 'client_secret=**********' \
--header 'Content-Type: application/x-www-form-urlencoded'
```

#### Generate a token for bar-user (read and write)

```shell
curl --location 'http://localhost:9002/realms/tryauth/protocol/openid-connect/token' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=bar-user' \
--data-urlencode 'client_secret=**********' \
--header 'Content-Type: application/x-www-form-urlencoded'
```

## TryAuth REST API

Access the contract (openapi) in swagger-ui:

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)