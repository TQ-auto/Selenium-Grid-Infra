pipeline {
    agent any
    tools{
        maven 'Maven 3.9.9'
    }
    stage('Checkout app tests'){
       steps {
            git branch: 'main',
                credentialsId: 'git-creds',
                url: 'https://github.com/TQ-auto/Unity-Task-Tests.git'
            }
       }

        stage('Run Tests') {
            steps {
                    sh 'mvn clean test'
            }
        }
}
