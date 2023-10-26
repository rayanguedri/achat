pipeline {
    agent any

    environment {
        NEXUS_REPO = 'maven-releases' 
        NEXUS_URL = 'http://192.168.1.20:8081/repository'  // Nexus repository base URL
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
