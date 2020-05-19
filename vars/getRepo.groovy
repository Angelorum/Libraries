#!/usr/bin/env groovy
/**
 * getRepo.groovy
 *  Ger repository of gitHub
 *  use call(message)
 */

def call(String workspace, String repo, String branch, credentialID){
    dir(workspace){
        say.simple("Workspace of this ${repo}: ${workspace}")
        git branch: branch, credentialsId: credentialID, url: repo
    }
}
