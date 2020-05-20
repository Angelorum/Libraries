#!/usr/bin/env groovy
/**
 * configCode.groovy
 *  Ger repository of gitHub
 *  use call(message)
 */

def call(String workspace, String buildNumber){
    dir(workspace){
        def ruta = ${env.workspace}/{workspace}
        def origin = readfile("${ruta}/build.gradle")
        def target = origin.replaceAll("NUMBERCHANGE", buildNumber)
        writeFile(file: origin, target)
        say.simple(target)
    }
}