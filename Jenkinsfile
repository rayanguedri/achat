pipeline {
    agent any
    stages{
    stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'youssef-chahab']], userRemoteConfigs: [[url: 'https://github.com/rayanguedri/achat.git']]])
            }
}
}
}
       
