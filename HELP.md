# Getting Started

There are some prerequisites to run this code, please install the following on your device:
* Java JDK 22, if you have a unix/linux machine try SDKMAN
* Maven (3.9.6 for example)
* Docker Desktop or free alternative
* EDI IntelliJ IDEA or Eclipse
* cmd/terminal/bash, I love the git bash, use git bash
* minikube https://minikube.sigs.k8s.io/docs/start/

### Creating a docker container from the project

First of all you need to start with a clean state of the project:

* start the docker app for desktop or an alternative
* set your EDI or Build tool to JDK 22
* build the project in your terminal/cmd with mvn install
* start the application with mvn spring-boot:run
* check the status of the spring boot application in your browser: http://localhost:8090/actuator/health/
* stop the application by sending a shutdown signal in the terminal (ctrl+c)
* clean the application by typing mvn clean in terminal/cmd
* create a file named "Dockerfile" in your project root so ./BookStoreServer/src/main/docker/Dockerfile
* we need a couple information for the docker daemon to run our app:
  * a JDK version: 
    * as we are using a 22 version we can write FROM openjdk:22-jdk-slim
    * NOTE: you can use any JDK version you want, as long as its 22 :)
  * a working directory for the container:
    * WORKDIR /app
    * /app is generally a default value, you can use anything you want
  * specify a jar file to copy into the docker working directory:
    * COPY target/*.jar bookstore.jar
  * a port for the docker container to communicate:
    * EXPOSE 8090
  * choose an entrypoint for the jar file:
    * ENTRYPOINT ["java", "-jar", "bookstore.jar"]
  * save the dockerfile
* to create a .jar file, we need to run mvn clean package in the terminal
  * NOTE: if you are experiencing errors:
    * port already in use: check docker desktop if a container is already running on this port
    * release version 22 not supported: if you type mvn -v you probably have an older java version
    * its recommended to use the edi mvn tool, for example in intellij, because you can set the java version globally
* execute "docker build -f src/main/docker/Dockerfile -t bookstoreserver ." in the directory of the dockerfile
* execute "docker run -p 8090:8090 bookstore-app"
* if you are seeing now an error message that your application cant connect to the database you are done with this part of the tutorial :)

### Creating a Kubernetes cluster for the database and the app

After creating a docker image download and install of minikube like described here https://minikube.sigs.k8s.io/docs/start/ you can start the dashboard
* after installation type minikube start in terminal
* load the bookstore image into minikube: minikube image load bookstore
* if you need a web frontend and dont want to continue you can open a dashboard with minikube dashboard
* first we want to create a database to access our java application with:
  * change to the kube folder of the project
  * you can now create the postgresdb by entering kubectl apply -f postgresdb.yaml
  * check the status by typing kubectl get pods
* create the pod for the app by typing kubectl apply -f bookstoreserver.yaml
  * to access the pod from outside you need a service config you can type kubectl apply -f service_bookstore.yaml

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#actuator)
* [Prometheus](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#actuator.metrics.export.prometheus)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)
* [Kubernetes](https://kubernetes.io/docs/tutorials/hello-minikube/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


