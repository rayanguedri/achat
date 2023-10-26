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
                    def artifactUrl = "${NEXUS_URL}/${NEXUS_REPO}/tn/esprit/rh/achat/1.0/achat-1.0.jar"
                    sh "curl -O $artifactUrl"
                    
                    // Load the JAR file
                    def loader = new GroovyClassLoader()
                    def jar = new JarFile('achat-1.0.jar')
                    def entries = jar.entries()

                    while (entries.hasMoreElements()) {
                        def entry = entries.nextElement()
                        def entryName = entry.getName()

                        if (entryName.endsWith('.class')) {
                            def className = entryName.replaceAll('/', '.').replace(".class", "")
                            def loadedClass = loader.parseClass(jar.getInputStream(entry), className)
                            
                           
                        }
                    }
                }
            }
        }
    }
}
