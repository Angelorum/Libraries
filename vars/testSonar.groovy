#!/usr/bin/env groovy
/**
 *
 *  testSonar.groovy
 *  Build android code with gradle
 *  use testSonar(workspace, projectKey)
 *
 */

def call(String workspace, String projectKey) {
    dir(workspace) {
        say.debug("Enter function testSonar in workspace: ${workspace}, for the project ${projectKey}")
        def url
        def login
        say.debug("Start sonarque environment with credentials sonarqube_token")
        withSonarQubeEnv(credentialsId: 'sonarqube_token_local') {
            say.debug("Get parameters of server")
            withCredentials([
                    string(credentialsId: 'URL_SONAR',
                            variable: 'urlSonar'),
                    string(credentialsId: 'LOGIN_SONAR',
                            variable: 'loginSonar')]) {
                url = urlSonar
                login = loginSonar
            }
            say.debug("Start sonarque test")
            sh """
                gradle sonarqube \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.host.url=${url} \
                -Dsonar.login=${login} \
                -Dsonar.sources=**/src/** \
                -Dsonar.sourceEncoding=UTF-8 \
                -Dsonar.exclusions=**resources**,**test**,**Test**
                """
            say.debug("finish function testSonar")
        }
    }
}