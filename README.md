# 🚀 Java Maven App
**By [Ahmed Ben Sassi](https://www.linkedin.com/in/ben-sassi-ahmed-44a5701b0/)**  

---

## 🌟 Overview

This project contains a **simple Java Spring Boot web application** built with **Maven**, fully **Dockerized**, and deployed on **Kubernetes** with a **complete CI/CD pipeline using Jenkins**.  

It demonstrates **automated building, versioning, and deployment**, showcasing **DevOps best practices** for Java microservices applications.

---

## 🔧 Features

- **Spring Boot Application**: Serves a static welcome page.  
- **Maven Build**: Dependency management and JAR packaging via Maven.  
- **Dockerization**: Containerizes the application using a lightweight `openjdk:8-jre-alpine` image.  
- **CI/CD Pipeline**: Declarative `Jenkinsfile` automates build, versioning, Docker push, and deployment.  
- **Kubernetes Deployment**: `deployementservice.yml` manages deployment and exposes the app in the cluster.

---

## 🚀 CI/CD Pipeline Workflow

1. 👨‍💻 **Check Committer**: Prevents CI/CD loops if the last commit was made by Jenkins.  
2. 🔢 **Increment Version**: Automatically updates the patch version in `pom.xml` and generates a new Docker image tag.  
3. 🛠️ **Build JAR**: Compiles Java code and packages it into an executable `.jar` using `mvn package`.  
4. 🐳 **Build & Push Docker Image**: Builds a Docker image with the new tag and pushes it to Docker Hub (`bensassiahmed/project989`).  
5. 📄 **Update Deployment Manifest**: Replaces the Docker image tag in `deployementservice.yml`.  
6. 🚀 **Deploy to Kubernetes**: Applies the updated manifest to deploy the new version.  
7. 💾 **Commit Version Updates**: Commits the updated `pom.xml` and manifest back to GitHub.

---

## ⚙️ Prerequisites

- Java 8 or later  
- Maven  
- Docker (for containerization)  
- Kubernetes cluster configured with `kubectl`  
- Jenkins (optional for full CI/CD pipeline)

---

## 🏗️ Build & Run Locally

1. **Clone the repository:**
    ```bash
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

## 🐳 Docker Containerization

The project can be run as a Docker container. The `Dockerfile` uses an `openjdk:8-jre-alpine` base image to create a lightweight and efficient container.

-   **Build the Docker image:**
    ```sh
    docker build -t java-maven-app .
    ```

-   **Run the Docker container:**
    ```sh
    docker run -p 8080:8080 java-maven-app
    ```

## ☸️ Kubernetes Deployment

The `deployementservice.yml` file contains the manifests for deploying the application on Kubernetes.

-   **Deployment**: Creates a Deployment that manages a single pod running the application container.
-   **Service**: Creates a `NodePort` Service that exposes the application on port `30007` of the cluster nodes.

To deploy to a configured Kubernetes cluster, apply the manifest:
```sh
kubectl apply -f deployementservice.yml
```
---

## 🛠️ Tech Stack

| Category | Tools |
|-----------|-------|
| 💻 **Programming** | Java 8, Spring Boot |
| ⚙️ **Build Tool** | Maven |
| 🐳 **Containerization** | Docker |
| ⚙️ **CI/CD** | Jenkins |
| ☸️ **Orchestration** | Kubernetes |
| 📈 **Monitoring** | Prometheus |
| 🔐 **Version Control** | GitHub |
---

## 👨‍💻 Author

**Ahmed Ben Sassi**  
📍 Teleinformatics Engineering Student  
💼 DevOps Enthusiast

📧 [bensassiahmed03@gmail.com](mailto:bensassiahmed989@gmail.com)  
[LinkedIn](https://www.linkedin.com/in/ben-sassi-ahmed-44a5701b0/)

---

⭐ *If you found this project helpful, don’t forget to give it a star on GitHub!*

