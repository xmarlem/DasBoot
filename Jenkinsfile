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
    }

    stage('Unit Testing'){
        sh "'${mvnHome}/bin/mvn' -Dtest=DasBootUnitTests test"
    }

    stage('Archive UT results') {
        junit '**/target/surefire-reports/TEST-*.xml'
    }

    stage('Build application') {
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore -Dmaven.test.skip=true -Dspring.profiles.active=dev clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore -Dmaven.test.skip=true -Dspring.profiles.active=dev clean package/)
        }
    }

    stage('Web Int. Pre-Testing-> Run App Stack'){
        sh 'docker-compose up db &'
    }

    stage('Web Int. Testing-> run Test') {
        sh "dockerize -wait tcp://192.168.99.102:5432 -timeout 240s '${mvnHome}/bin/mvn' -Dtest=ShipwreckControllerWebIntegrationTest -Dspring.profiles.active=test test"
    }

    stage('Web Int. Post-Testing-> Stop App Stack'){
        sh 'docker-compose down &'
    }

    stage('Build Docker Image'){
        app = docker.build("xmarlem/das-boot:${env.BUILD_ID}")
    }

    stage("Archive Artifacts"){
        archive 'target/*.jar'
    }

    stage('Push image to Docker HUB') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }

    stage('Push image to AZURE Registry') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://ubspocreg.azurecr.io', 'docker-registry-azure') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }

}

def hostIp(container) {
    sh "docker inspect -f {{.NetworkSettings.IPAddress}} ${container.id} > host.ip"
    readFile('host.ip').trim()
}
