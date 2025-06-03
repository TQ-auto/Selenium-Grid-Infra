pipeline {
    agent any
    stages {
        stage('Checkout js app') {
            steps {
                    git branch: 'main',
                    credentialsId: 'unity-git-token',
                    url: 'https://github.com/TQ-auto/testing-app.git'
            }
        }

        stage('Deploy JS App Image') {
            steps {
                    sh 'docker compose up -d'
                    sh 'sleep 45'
                }
        }

        stage('Checkout app tests'){
            steps {
                    git branch: 'main',
                    credentialsId: 'unity-git-token',
                    url: 'https://github.com/TQ-auto/Unity-Task-Tests.git'
            }
        }

        stage('Start App & Selenium') {
            steps {
                sh 'docker-compose up -d'
                sh 'sleep 30'
            }
        }

        stage('Run Selenium Tests') {
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

    post {
        always {
            echo "Cleaning up..."
            sh 'docker-compose down || true'
        }
    }
}
