pipeline {
    agent any
    tools{
        maven 'Maven 3.9.9'
    }
    
    stages{
        stage('Checkout E2E tests'){
           steps {
                git branch: 'main',
                    credentialsId: 'git-creds',
                    url: 'https://github.com/TQ-auto/Unity-Task-Tests.git'
                }
           }
        stage('Run E2E Tests') {
                steps {
                        sh 'mvn clean test'
                }
            }
        /*
        stage('Checkout Performance tests'){
            steps{
                git branch: 'main',
                    credentialsId: 'git-creds',
                    url: 'https://github.com/TQ-auto/performance-tests-unity.git'
            }
        }
        
        stage('Run Performance tests'){
            steps {
                sh '/opt/apache-jmeter-5.6.3/bin/jmeter.sh -Jmeter.saveservice.output_format=xml -n -t Jmeter-testplans/get-posts-list-performance-test.jmx -l results.csv -o output_results'
            }
        }
        */
    }
}
