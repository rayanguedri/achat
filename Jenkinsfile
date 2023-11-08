pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
    }
    stages {
        stage('Building') {
            steps {
                script {
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    sh "${mvnHome}/bin/mvn clean compile"
                }
            }
        }

        stage('Unit Testing') {
            steps {
                script {
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
            post {
                always {
                    junit "**/target/surefire-reports/TEST*.xml"
                }
            }
        }
        stage('Sonarqube Analysis') {
            steps {
                script {
                    jacoco()
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    withSonarQubeEnv('sonar') {
                        sh "${mvnHome}/bin/mvn verify sonar:sonar"
                    }
                }
            }
        }

        stage("Quality Gate") {
            steps {
                script {
                    sleep(10)
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Building jar') {
            steps {
                script {
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }


        stage("publish to nexus") {
            steps {
                script {
                    artifactPath = "target/achat-1.0.jar";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: 'tn.esprit.rh',
                            version: '1.0',
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: 'achat',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );

                }
            }
        }


        stage('Email Notification') {
            steps {
                script {
                    mail bcc: '', body: '''Hi,
                        Welcome to jenkins email alerts.
                        The continuous integration of your code has ben achieved successfully.
                        Thanks,
                        Achat App''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'wassim.hilali@esprit.tn'
                }
            }
        }



        stage('Build And Deploy Docker Image') {
            steps {
                script {
                    echo "deploying the application"
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker build -t wassimhl1920/achat:1.0 ."
                        sh "docker push wassimhl1920/achat:1.0"
                    }
                }
            }
        }

        stage('Docker Compose') {
                    steps {
                        script {

                            sh "docker compose up -d"
                        }
                    }
                }

    }
}
