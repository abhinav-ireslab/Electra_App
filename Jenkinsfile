pipeline {
  agent none
  stages {
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.5.0'
        }
      }
      steps {
		echo 'Making build.'
		sh 'mvn clean install'
      }
    } 
  stage('Docker Build') {
      agent any
      steps {
      sh 'docker build -t docker030303/electraapp:latest .'   
      }
    }
       
       stage('Docker push') {
       agent any
       steps {
       sh 'docker login -u docker030303 -p Abhinav@123Ires'
       sh 'docker push docker030303/electraapp'
     }
   }
       stage('Docker run'){
       agent any
       steps {
       sh 'docker run -p 192.168.1.29:8088:8088 -t docker030303/electraapp'

     }
   } 
 }
}
