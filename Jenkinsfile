pipeline {
    agent any

    tools {
        maven 'Maven 4.0.0'
        }
    stages {
        stage('Checkout js app') {
            steps {
                    git branch: 'main',
                    credentialsId: 'git-creds',
                    url: 'https://github.com/TQ-auto/testing-app.git'
            }
        }
        stage('Create docker network'){
            steps{
                sh 'docker network inspect shared-net >/dev/null 2>&1 || docker network create shared-net'
            }
        }
        
        stage('Deploy JS App Image') {
            steps {
                    sh 'docker compose up -d --no-recreate'
                }
        }

        stage('Checkout app tests + Selenium grid up'){
            steps {
                    git branch: 'main',
                    credentialsId: 'git-creds',
                    url: 'https://github.com/TQ-auto/Unity-Task-Tests.git'
                    sh 'docker compose up -d --no-recreate'
                    sh 'sleep 30'
            }
        }

        stage('Run Tests') {
            steps {
                dir('selenium-tests') {
                    sh 'mvn clean test'
                }
            }
        }

        stage('Tear Down') {
            steps {
                sh 'docker-compose down'
            }
        }
    }
}
