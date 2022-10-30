# Fiserv Order Delivery
API to schedule card machine order delivery. Follow the instructions below to run this API locally.

## Requiriments

- PostgreSQL Server 13.5
`https://www.enterprisedb.com/downloads/postgres-postgresql-downloads`
- GIT
- Spring Tools Suite (or Eclipse)

## Import the project

- Clone this repository:
```bash
  git clone https://github.com/lnmesquita1/fiserv.git
```
- Open STS or Eclipse and click on File > Import > Maven > Existing Maven Projects
- Click next and then browse the repository which you have cloned
- Mark the checkbox pom.xml and click finish

## How to configure

- When you install PostgresSQL Server 13.5, it comes with pgAdmin; a graphical tool for managing and developing your databases. So open pgAdmin 4 and create a database named "fiserv".
- Go to STS or Eclipse and open `application.properties` file
- Change the configurations of the postgreSQL and put your own database configurations in this section:

```bash
## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/fiserv
spring.datasource.username=postgres
spring.datasource.password=123
```

## Run Locally

- Right click on the project > Run As > Spring Boot App

## Swagger
You can find all the endpoints by accessing this URL: http://localhost:8081/swagger-ui.html

## Configure OAuth2 Server
Change keycloak configurations inside file application.properties

```bash
## PostgreSQL
keycloak.realm=fiserv
keycloak.auth-server-url=http://localhost:8080/auth
keycloak.ssl-required=external
keycloak.resource=sboot-delivery
keycloak.credentials.secret=3VFejqmqOXwjZGkxriCs2L8v21JG0fvV
keycloak.use-resource-role-mappings=true
keycloak.public-client=true
keycloak.bearer-only=true
keycloak.securityConstraints[0].authRoles[0] = admin
keycloak.securityConstraints[0].authRoles[1] = user
```


