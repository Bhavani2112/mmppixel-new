pipeline {
    agent any

    parameters {
        string(name: 'branch_name', defaultValue: 'main', description: 'Git branch to build')
    }

    stages {
        stage('pixel_mmp_healthcheck') {
            steps {
                script {
                    try {
                        git branch: "${params.branch_name}", url: 'https://github.com/Bhavani2112/mmppixel-new.git'
                        bat label: 'mmpbatchscript', script: 'mmphealthcheck.bat'
                    } catch (err) {
                        echo "pixel_mmp_healthcheck job failed"
                        echo "Caught: ${err}"
                    }
                }
            }
        }

        stage('pixel_mmp_regtests') {
            steps {
                script {
                    // Clone the repo into workspace
                    git branch: "${params.branch_name}", url: 'https://github.com/Bhavani2112/mmppixel-new.git'
                    
                    // Navigate to mmppixel and run Maven
                    dir('mmppixel') {
                        withEnv(["PATH+MAVEN=${tool 'mvn_home'}/bin"]) {
                            bat 'mvn clean test'
                        }
                    }
                }
            }
        }
    }
}
