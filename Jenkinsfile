pipeline {
    agent any
    tools {
        maven 'Maven 3.5.2'
        jdk 'jdk8'
    }
    stages {
        stage ('Build') {
            steps {
                sh 'pwd'
                sh 'ls -la'
                sh 'cd polyeventbackend/commun'
                sh 'mvn clean install'
            }
        }
    }
}