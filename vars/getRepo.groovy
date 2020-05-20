#!/usr/bin/env groovy
/**
 *
 *  getRepo.groovy
 *  Get repository of gitHub
 *  use getRepo(workspace, repo, branch, credentialID)
 *
 */

def call(String workspace, String repo, String branch, credentialID){
    dir(workspace){
        say.debug("Enter function getRepo on ${workspace}")
        if (params.DEBUG) say.simple("Workspace of this ${repo}: ${workspace}")
        git branch: branch, credentialsId: credentialID, url: repo
        say.debug("Finish function getRepo on ${workspace}")
    }
}
