pipeline {
    agent any
    tools {
        maven 'Maven 3.5.2'
        jdk 'jdk8'
    }
    stages {
        stage ('Build') {
            steps {
                sh './compilation-all.sh'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}