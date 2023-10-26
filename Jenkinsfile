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
                   echo "1"
                    def facture = new tn.esprit.rh.achat.entities.Facture()

                   
                    facture.setMontantFacture(100.0)
                    facture.setMontantRemise(20.0)

                   echo "2"
                    def difference = facture.getMontantFacture() - facture.getMontantRemise

                    echo "3"
                    echo "MontantFacture - MontantRemise = ${difference}"
			echo "4"
                }
            }
        }
    }
}
