node {
    def mvnHome
    def dockerHome
    def app
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/xmarlem/DasBoot.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
        //dockerHome = tool 'docker'
    }

    stage('Test'){
        sh "'${mvnHome}/bin/mvn' -Dtest=DasBootApplicationTests test"

    }

    stage('Test Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
    }

    stage('Build') {
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }

    stage('Build Docker Image'){
        app = docker.build("xmarlem/das-boot:${env.BUILD_ID}")
    }

    stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }
}

def hostIp(container) {
    sh "docker inspect -f {{.NetworkSettings.IPAddress}} ${container.id} > host.ip"
    readFile('host.ip').trim()
}