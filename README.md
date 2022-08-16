# fantasy-manager Project

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```
CLI

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

