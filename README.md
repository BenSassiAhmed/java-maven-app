# Java Maven App
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/BenSassiAhmed/java-maven-app)

This repository contains a simple Java Spring Boot web application built with Maven. The project is configured with a complete CI/CD pipeline using Jenkins, Docker, and Kubernetes for automated building, versioning, and deployment.

## Features

-   **Spring Boot Application**: A simple web application that serves a static welcome page.
-   **Maven Build**: Uses Maven for dependency management and building the executable JAR file.
-   **Dockerization**: Includes a `Dockerfile` to containerize the application for consistent deployments.
-   **CI/CD Pipeline**: A comprehensive `Jenkinsfile` that automates the entire software delivery process.
-   **Kubernetes Deployment**: A `deployementservice.yml` manifest to deploy and expose the application in a Kubernetes cluster.

## CI/CD Pipeline Workflow

The `Jenkinsfile` defines a declarative pipeline that automates the following stages:

1.  **Check Committer**: Prevents CI/CD loops by stopping the build if the last commit was made by the Jenkins user.
2.  **Increment Version**: Automatically increments the patch version in the `pom.xml` file and creates a unique image tag.
3.  **Build JAR**: Compiles the Java code and packages the application into an executable `.jar` file using `mvn package`.
4.  **Build and Push Docker Image**: Builds a Docker image using the `Dockerfile`, tags it with the new version, and pushes it to a Docker Hub repository (`bensassiahmed/project989`).
5.  **Update Deployment Manifest**: Updates the `deployementservice.yml` with the new Docker image tag.
6.  **Deploy**: Applies the updated Kubernetes manifest (`deployementservice.yml`) to deploy the new version of the application to a Kubernetes cluster.
7.  **Commit Version Update**: Commits the version changes in `pom.xml` and the image tag update in `deployementservice.yml` back to the GitHub repository.

## How to Build and Run Locally

### Prerequisites

-   Java 8 or later
-   Maven

### Steps

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/BenSassiAhmed/java-maven-app.git
    cd java-maven-app
    ```

2.  **Build the application using Maven:**
    ```sh
    mvn clean package
    ```
    This will create an executable JAR file in the `target/` directory.

3.  **Run the application:**
    ```sh
    java -jar target/java-maven-app-*.jar
    ```

4.  **Access the application:**
    Open your web browser and navigate to `http://localhost:8080`. You will see the welcome page.

## Containerization

The project can be run as a Docker container. The `Dockerfile` uses an `openjdk:8-jre-alpine` base image to create a lightweight and efficient container.

-   **Build the Docker image:**
    ```sh
    docker build -t java-maven-app .
    ```

-   **Run the Docker container:**
    ```sh
    docker run -p 8080:8080 java-maven-app
    ```

## Kubernetes Deployment

The `deployementservice.yml` file contains the manifests for deploying the application on Kubernetes.

-   **Deployment**: Creates a Deployment that manages a single pod running the application container.
-   **Service**: Creates a `NodePort` Service that exposes the application on port `30007` of the cluster nodes.

To deploy to a configured Kubernetes cluster, apply the manifest:
```sh
kubectl apply -f deployementservice.yml
