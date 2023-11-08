pipeline {
    agent any
    
    stages {
        stage('gitPull') {
            steps {
                git branch: 'youssef-chahab', url: 'https://github.com/rayanguedri/achat.git'
            }
        }
        
        stage('Clean') {
            steps {
                // Clean the project
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                // Compile the project
                sh 'mvn compile'
            }
        }
        
        stage('UnitTest') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SonarToken', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
                }
            }
}
           

    }
}
