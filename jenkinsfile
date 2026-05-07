pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker build -t student-portal-java .'
                sh 'docker stop student-portal || true'
                sh 'docker rm student-portal || true'
                sh 'docker run -d --name student-portal -p 8080:8080 student-portal-java'
            }
        }
        stage('Run snyk code analysisis for security') {
            steps {
                withcredentials([string(credentialsId: 'snyk_token', variable: 'Snyk_token')]) {
                    sh 'mvn snyk:test'
                }
            }
        }

    }
}