#!/usr/bin/env groovy
/**
 *
 *  buildCode.groovy
 *  Build android code with gradle
 *  use buildCode(workspace, variants)
 *
 */

def call(String workspace, variants){
    dir(workspace){
        say.debug("Enter function buildCode in workspace: ${workspace}")
        say.debug("Wrapper Phase and init phase")
        sh "gradle init clean wrapper"
        variants.each{
            if(it =="release" && params.BUILD_TYPE != "RELEASE"){
                say("Skip release Build")
            }
            else{
                build(it)
                makeAPK(it)
            }
        }
        say.debug("Archive all artifacts")
        if (params.BUILD_TYPE == "STAGING" || params.BUILD_TYPE == "DEPLOY" || params.BUILD_TYPE == "RELEASE"){
            archiveArtifacts (artifacts: "app/**/*.apk", fingerprint: true)
        }
        say.debug("Finish function buildCode on ${workspace}")
    }
}

def build(variant = "debug"){
    say.debug("Build ${variant} variant")
    sh  """
        ./gradlew build${variant}
        """
    say.debug("Finish Build ${variant} variant")

}

def makeAPK(variant = "debug"){
    say.debug("Make APK of ${variant} variant")
    sh  """
        ./gradlew build${variant}
        """
    say.debug("Finish make APK ${variant} variant")
}