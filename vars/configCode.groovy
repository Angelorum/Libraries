#!/usr/bin/env groovy
/**
 *
 *  configCode.groovy
 *  Config repository for build
 *  use configCode(workspace, buildNumber)
 *
 */

def call(String workspace, String buildNumber){
    dir(workspace){
        say.debug("Enter function configCode on ${workspace}")
        def ruta = "${env.workspace}/${workspace}"
        def configFile = "${ruta}/app/build.gradle"
        def signingFile = "${ruta}/app/storeFile.jks"
        def keyAlias
        def keyPasswd
        def storePasswd;
        say.debug("Open credentials")
        withCredentials([
                string(credentialsId: 'KEYALIAS',
                        variable: 'alias'),
                string(credentialsId: 'KEYPASSWD',
                        variable: 'key'),
                string(credentialsId: 'STOREPASSWD',
                        variable: 'store'),
                file(credentialsId: 'storeFile',
                        variable: 'file')]) {
            keyAlias = "$alias"
            keyPasswd = "$key"
            storePasswd = "$store"
            say.debug("Add signing file: ${signingFile}")
            sh "cp ${file} ${signingFile}"
        }
        say.debug("Start edit config file: ${configFile}")
        editFile(configFile, "VERSIONCODE", buildNumber)
        editFile(configFile, "KEYALIAS", keyAlias)
        editFile(configFile, "KEY_PASSWD", keyPasswd)
        editFile(configFile, "STORE_PASSWD", storePasswd)
        say.debug("End edit config file: ${configFile}")
        say.debug("Finish function configCode on ${workspace}")
    }
}

