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

        // stage('Test') {
        //     steps {
        //         sh 'mvn test'
        //     }
        // }

        // stage('Deploy') {
        //     steps {
        //         sh 'docker build -t student-portal-java .'
        //         sh 'docker stop student-portal || true'
        //         sh 'docker rm student-portal || true'
        //         sh 'docker run -d --name student-portal -p 8080:8080 student-portal-java'
        //     }
        // }

        stage('Run snyk code analysis for security') {
            steps {
                withCredentials([string(credentialsId: 'snyk_token', variable: 'SNYK_TOKEN')]) {
                    sh 'mvn snyk:test -fn'
                }
            }
        }

        stage('PushToECR') {
            steps {
                script {
                    def app = docker.build("student-portal-java")
                    docker.withRegistry(
                        "https://533266960414.dkr.ecr.us-east-1.amazonaws.com/javaappus-east-1",
                        "ecr:us-east-1:aws-credentials"
                    ) {
                        app.push("latest")
                    }

                    //deploy to kubernetes cluster

                stage('DeployToKubernetes'){
                    steps {
                        withkubeconfig([credentialsId: 'kubeconfig']) {
                            sh 'kubectl delete all --all -n devsecops'    
                            sh 'kubectl apply -f deployment.yaml -n devsecops'
                        }
                    }

                }
            }
        }

    }
}