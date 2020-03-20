# unSQL Web tool

A command line tool to run SQL on unstructured data. (Json and XML files)

## Getting Started


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a UnSQL Web on your local machine. One way is to execute the `main` method in the `com.github.villanianalytics.unsqlweb.UnSqlWebApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```bash
git clone https://github.com/villanianalytics/unSQL-web.git
cd unSQL-web
mvn spring-boot:run
```

## Deploying to Heroku

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/villanianalytics/unSQL-web)

## License

This project is licensed under the MIT License

