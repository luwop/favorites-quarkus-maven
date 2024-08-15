# movies-quarks-kotlin




//to use mongo:
docker run -ti --rm -p 27017:27017 mongo:latest

//make sure to include this line in the application.properties
quarkus.mongodb.connection-string = mongodb://localhost:27017












This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```




//to use dynamodb

//run a local amazonDynamoDB container
docker run --publish 8000:8000 amazon/dynamodb-local:1.11.477 -jar DynamoDBLocal.jar -inMemory -sharedDb

This starts a DynamoDB instance that is accessible on port 8000. You can check it’s running by accessing the web shell on http://localhost:8000/shell.

Have a look at the Setting Up DynamoDB Local guide for other options to run DynamoDB.

Open http://localhost:8000/shell in your browser.

Copy and paste the following code to the shell and run it:


var params = {
TableName: 'Movies',
KeySchema: [{ AttributeName: 'id', KeyType: 'HASH' }],
AttributeDefinitions: [{  AttributeName: 'id', AttributeType: 'S', }],
ProvisionedThroughput: { ReadCapacityUnits: 1, WriteCapacityUnits: 1, }
};

dynamodb.createTable(params, function(err, data) {
if (err) ppJson(err);
else ppJson(data);

});














You can then execute your native executable with: `./target/movies-quarks-kotlin-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes
  with Swagger UI
- RESTEasy Reactive Links ([guide](https://quarkus.io/guides/resteasy-reactive#web-links-support)): Web Links support
  for RESTEasy Reactive. Inject web links into response HTTP headers by annotating your endpoint resources.
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- Amazon
  DynamoDB ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-dynamodb.html)):
  Connect to Amazon DynamoDB datastore

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)



localhost:8080/beers/
POST
{"name":"TestName", "hop":"TestHop", "malt":"TestMalt", "style":"TestStyle",
"yeast":"TestYeast"}

localhost:8080/beers/
PUT
{"id":"64ad9d0136a6f71d17d36453", "name":"TestName", "hop":"TestHop", "malt":"TestMalt", "style":"TestStyle",
"yeast":"TestYeast"}


&&&&&&&&&&&&&&&&

http://localhost:8080/q/swagger-ui/
