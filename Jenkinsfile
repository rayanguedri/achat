pipeline {
    agent any
    
    stage('gitPull') {
  steps {
    git clone url: 'https://github.com/rayanguedri/achat.git', branch: 'youssef-chahab'
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
