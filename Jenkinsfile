pipeline {
    agent any

    environment {
        NEXUS_REPO = 'maven-releases' 
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
                    
                    sh "curl -O http://192.168.1.20:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar"

                    
                    def factureCalculator = new groovy.util.ConfigSlurper().parse(new File('achat-1.0.jar').toURL())
                    def result = factureCalculator.calculateFacture()

                    echo "1"
                    echo "MontantFacture - MontantRemise = ${result}"
                    echo "2"
                }
            }
        }
    }
}
