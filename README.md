# fantasy-manager Project

## Running the application in dev mode

## Prerequisities:

- Java 17
- Docker or mongodb instance (see below)
- Quarkus cli (optional)


You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

Quarkus CLI:

```shell script
quarkus dev
```


> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```

## Docker Image

Sample Jib configuration for Google Artifact Registry:

```shell script
quarkus.container-image.builder=jib
quarkus.container-image.registry=europe-west8-docker.pkg.dev
quarkus.container-image.group=fantasy-manager-359419/fantasy-manager-registry
```
Run:
```shell script
./gradlew clean  build   -Dquarkus.container-image.build=true  -Dquarkus.container-image.push=true
```

## Sample application-dev.properties file for local development:

```shell script
quarkus.mongodb.database=predictions


quarkus.rest-client.matches-api.url=https://api.football-data.org/
quarkus.rest-client.matches-api.scope=javax.inject.Singleton
matches-api.auth.value=${your-api-key}



quarkus.oidc.provider=google
quarkus.oidc.client-id=${google-oidc-client-id}
quarkus.oidc.credentials.secret=${google-oidc-client-secret}
```

if docker not running (therefore quarkus cannot spin up a mongodb container for you), you will have to provide a mongo url via :



```shell script
quarkus.mongodb.connection-string = mongodb://mongo1:27017,mongo2:27017
```


