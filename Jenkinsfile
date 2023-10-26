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
                    // Include the target directory in the classpath
                    def classpath = ["target/classes"]
                    currentBuild.classLoader.addURLs(classpath.collect { it as URL })

                    // Now you should be able to access the Facture class
                    def facture = new tn.esprit.rh.achat.entities.Facture()

                    // Set the values of montantFacture and montantRemise (you can set them based on your data)
                    facture.setMontantFacture(100.0)
                    facture.setMontantRemise(20.0)

                    // Calculate the difference
                    def difference = facture.getMontantFacture() - facture.getMontantRemise

                    // Print the result
                    echo "MontantFacture - MontantRemise = ${difference}"
                }
            }
        }
    }
}
