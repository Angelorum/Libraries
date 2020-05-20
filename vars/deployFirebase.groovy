#!/usr/bin/env groovy
/**
 *
 *  testSonar.groovy
 *  Build android code with gradle
 *  use deployFirebase(workspace)
 *
 */

def call(String workspace, variants, String app){
    say.debug("Enter function deployFirebase")
    config(workspace, app)
    installFastlane(workspace)
    variants.each{
        deploy(workspace,it)
    }
    say.debug("End of deployFirebase")
}

def config(String workspace, String app){
    dir(workspace){
        say.debug("Enter function config in workspace: ${workspace}")
        def token;
        def appID;
        def ruta = "${env.WORKSPACE}/${workspace}"
        def pathFile = "${ruta}/fastlane/Fastfile"
        say.debug("Get Credentials")
        withCredentials([
                string(credentialsId: 'FIREBASE_TOKEN',
                        variable: 'tokenFirebase'),
                string(credentialsId: app,
                        variable: 'appFirebase')]) {
            token = "$tokenFirebase";
            appID = "$appFirebase";
        }
        editFile(pathFile, "TOKEN", token)
        editFile(pathFile, "APP_ID", appID)
    }
}

def deploy(String workspace,String variant) {
    dir(workspace) {
        say.debug("Enter function deploy in workspace: ${workspace} for ${variant} variant")
        def ruta = "${env.WORKSPACE}/${workspace}"
        def pathFile = "${ruta}/fastlane/Fastfile"
        editFile(pathFile, "VARIANT", variant)
        say.debug("Start deploy")
        sh "fastlane deployApp"
        say.debug("End of deploy")
    }
}

def installFastlane(String workspace){
    dir(workspace){
        say.debug("Enter function installFastlane in workspace: ${workspace}")
        sh  """ 
            apt-get update -y
            apt-get install ruby libgmp-dev libc6-dev build-essential dh-autoreconf ruby-dev -y
            gem install fastlane
            """
        say.debug("End of installFastlane")
    }
}
