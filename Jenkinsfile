pipeline {
    agent any

    environment {
        NEXUS_REPO = 'maven-releases' 
        NEXUS_URL = 'http://192.168.1.20:8081/repository'  // Nexus repository base URL
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }

    stages {
       stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'rayan-guedri']], userRemoteConfigs: [[url: 'https://github.com/rayanguedri/achat.git']]])
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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=rayan'
            }
        }

        stage('Publish Artifacts to Nexus') {
            steps {
                script {
                    nexusArtifactUploader artifacts: [[
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/achat-1.0.jar',
                        type: 'jar']],
                        credentialsId: 'nexus-server',
                        groupId: 'tn.esprit.rh',
                        nexusUrl: '192.168.1.20:8081',
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        repository: 'maven-releases/',
                        version: '1.0'
                }
            }
        }

stage('Build Docker Image') {
    steps {
        script {
            def dockerImage = docker.build("metis9/alpine:1.0.0", "-f Dockerfile .")
        }
    }
}


      stage('Push Docker Image to Docker Hub') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
            script {
                def dockerImageName = 'metis9/alpine:1.0.0' // Specify the Docker image name and tag here
                sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                sh "docker push $dockerImageName"
            }
        }
    }
}







        stage('Calculate Facture') {
            steps {
                script {
                    def montantFacture = 100.0
                    def montantRemise = 20.0
                    def difference = montantFacture - montantRemise

                    echo "MontantFacture - MontantRemise = ${difference}"
                }
            }
        }
    }
}
