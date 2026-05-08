# Student Portal DevOps Project

A DevOps project for deploying a Java Spring Boot Student Portal application on AWS using Docker, Jenkins, Terraform, and Kubernetes (EKS).

This project demonstrates:
- CI/CD automation with Jenkins
- Infrastructure provisioning with Terraform
- Docker containerisation
- Kubernetes deployment on AWS EKS
- Monitoring with Prometheus and Grafana

---

# Project Overview

The application is packaged into a Docker container and deployed to an AWS EKS cluster.

Terraform is used to provision the AWS infrastructure, while Jenkins automates the deployment pipeline.

The pipeline performs:
- Maven build
- Unit testing
- Docker image build
- Push to AWS ECR
- Deployment to EKS
- Monitoring setup with Prometheus and Grafana

---

# Architecture

```text
Developer Push
      │
      ▼
 GitHub Repository
      │
      ▼
 Jenkins Pipeline
  ├── Build Application
  ├── Run Tests
  ├── Build Docker Image
  ├── Push to AWS ECR
  └── Deploy to EKS
                    │
                    ▼
             Kubernetes Cluster
                    │
         ┌──────────┴──────────┐
         │                     │
    Student Portal       Monitoring Stack
                         (Prometheus + Grafana)
```

---

# Tech Stack

| Tool | Purpose |
|---|---|
| Java Spring Boot | Backend application |
| Maven | Build tool |
| Docker | Containerisation |
| Terraform | Infrastructure as Code |
| Jenkins | CI/CD automation |
| AWS ECR | Docker image registry |
| AWS EKS | Kubernetes cluster |
| Kubernetes | Container orchestration |
| Prometheus | Monitoring |
| Grafana | Dashboard visualisation |
| AWS | Cloud platform |

---

# Repository Structure

```text
student-portal-java/
├── src/
├── terraform/
│   ├── main.tf
│   ├── variables.tf
│   ├── outputs.tf
│   └── script.sh
├── Dockerfile
├── deployment.yaml
├── monitoring.yaml
├── Jenkinsfile
└── pom.xml
```

---

# Prerequisites

Before running the project, ensure you have:

- AWS account
- AWS CLI installed and configured
- Terraform installed
- Docker installed
- kubectl installed
- Jenkins installed
- Existing AWS EC2 key pair in `us-east-1`

Configure AWS locally:

```bash
aws configure
```

---

# Deployment Steps

## 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/student-portal-java.git
cd student-portal-java
```

---

## 2. Provision Infrastructure with Terraform

Navigate to the Terraform directory:

```bash
cd terraform
```

Initialize Terraform:

```bash
terraform init
```

Review the infrastructure plan:

```bash
terraform plan
```

Create the infrastructure:

```bash
terraform apply -auto-approve
```

Terraform creates:
- VPC
- Public subnets
- Internet gateway
- EKS cluster
- Node group
- ECR repository
- Jenkins EC2 instance
- Security groups
- IAM roles

---

## 3. Access Jenkins

Open Jenkins in the browser:

```text
http://<jenkins-public-ip>:8080
```

Retrieve the initial Jenkins admin password:

```bash
ssh -i Damolak.pem ubuntu@<jenkins-public-ip>
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

---

# Jenkins Setup

Install the following plugins:
- Docker Pipeline
- AWS Credentials
- Kubernetes CLI

Add Jenkins credentials:
- AWS credentials
- Snyk token

Create a Pipeline job and connect it to the GitHub repository.

---

# CI/CD Pipeline

The Jenkins pipeline performs the following stages:

```text
Checkout Code
      ↓
Build Application
      ↓
Run Tests
      ↓
Security Scan
      ↓
Build Docker Image
      ↓
Push Image to ECR
      ↓
Deploy to EKS
      ↓
Deploy Monitoring Stack
```

---

# Docker

The application uses a multi-stage Docker build:
- First stage builds the application using Maven
- Second stage runs the application using a lightweight Java image

Build Docker image manually:

```bash
docker build -t studentportal .
```

Run locally:

```bash
docker run -p 8080:8080 studentportal
```

---

# Kubernetes Deployment

Deploy the application:

```bash
kubectl apply -f deployment.yaml
```

Check running pods:

```bash
kubectl get pods
```

Check services:

```bash
kubectl get svc
```

---

# Monitoring

## Prometheus

Prometheus collects metrics from the Kubernetes cluster and application pods.

Check Prometheus service:

```bash
kubectl get svc prometheus-service -n monitoring
```

---

## Grafana

Grafana visualises monitoring metrics using dashboards.

Check Grafana service:

```bash
kubectl get svc grafana-service -n monitoring
```

Default login:

```text
Username: admin
Password: admin123
```

---

# AWS Services Used

- EC2
- EKS
- ECR
- IAM
- VPC
- CloudWatch

---

# Security

Basic security practices used:
- IAM roles for AWS access
- Docker image scanning with Snyk
- Kubernetes namespaces
- Security groups for network access

---

# Future Improvements

Possible improvements for the project:
- Store Terraform state in S3
- Use GitHub Actions alongside Jenkins
- Add HTTPS with Ingress Controller
- Use Kubernetes Secrets
- Add Horizontal Pod Autoscaler
- Improve monitoring dashboards
- Restrict SSH access to specific IPs

---

# Learning Outcomes

This project helped demonstrate:
- Infrastructure provisioning with Terraform
- Docker containerisation
- Kubernetes deployment on AWS EKS
- Jenkins CI/CD pipeline creation
- Monitoring with Prometheus and Grafana
- Cloud deployment workflow

---

Author + OKONKOH OBINNA


