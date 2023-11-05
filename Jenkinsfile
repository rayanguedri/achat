pipeline {
    agent any

    environment {
        NEXUS_REPO = 'maven-releases' 
        NEXUS_URL = 'http://192.168.33.10:8080/repository'  
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }

    stages {
       stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'mohamed-nehdi']], userRemoteConfigs: [[url: 'https://github.com/rayanguedri/achat.git']]])
            }
        }

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

         stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=nehdi.1965'
            }
        }

        

stage('Build Docker Image') {
    steps {
        script {
            def dockerImage = docker.build("mohamednehdi/alpine:1.0.0", "-f Dockerfile .")
        }
    }
}


            stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    script {
                        def dockerImageName = 'mohamednehdi/alpine:1.0.0' // Specify the Docker image name and tag here
                        sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                        sh "docker push $dockerImageName"
                    }
                }
            }
        }
        
        stage('Deploy with Docker Compose') {
        steps {
                sh 'docker-compose up -d'  // Use -d to run in detached mode
        
            }
        }




    }
}
