#!/usr/bin/env groovy

def gv

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        
        stage('Check Committer') {
            steps {
                script {
                    // Check if the build was triggered manually
                    def isManualBuild = currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')

                    // Skip the committer check for manual builds
                    if (!isManualBuild) {
                        // Get the committer's email or username
                        def committerEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
                        def committerName = sh(script: 'git log -1 --pretty=format:"%an"', returnStdout: true).trim()

                        // Stop the build if the commit was made by Jenkins
                        if (committerEmail == "jenkins@example.com" || committerName == "jenkins") {
                            error "Stopping build because the commit was made by Jenkins."
                        } else {
                            echo "Proceeding with the build because the commit was not made by Jenkins."
                        }
                    } else {
                        echo "Build was triggered manually. Skipping committer check."
                    }
                }
            }
        }

        
        stage('increment version') {
            steps {
                script {
                    echo 'incrementing app version...'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }

        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        
        stage("build and push image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }

    /*    
        stage("deploy to ec2") {
            steps {
                script {
                    def dockercmd= 'docker run -d --name app -p 8080:8080 bensassiahmed/project989:jma-${IMAGE_NAME} '
                        sshagent(['ec2-server-key']) {
                            sh "ssh -o StrictHostKeyChecking=no ubuntu@34.228.11.54 ${dockercmd}"
                        }
                    }
                }
            }
*/


        
    
        stage("update deployementservice.yml") {
            steps {
                script {
                    echo "Updating deployementservice.yml with the new image tag"
                    sh "sed -i 's/jma-*/${env.IMAGE_NAME}/g' deployementservice.yml"  
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    sh 'kubectl get nodes '
                    sh 'kubectl create -f deployementservice.yml'
                    sh 'sleep 10'
                    sh 'kubectl get all '
                }
            }
        } 
        

        
        stage('commit version update') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        // git config here for the first time run
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "jenkins"'

                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/BenSassiAhmed/java-maven-app.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version bump"'
                        sh 'git push origin HEAD:main'
                    }
                }
            }
    }
 }
}
