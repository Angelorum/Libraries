#!/usr/bin/env groovy
/**
 * editFile.groovy
 *  change a string in a file
 *  use editFile(pathFile, stringToSearch, stringReplacement)
 */


def call(String pathFile, String stringToSearch, String stringReplacement){
    say.debug("Enter function editFile")
    say.debug("Open file: ${pathFile}")
    def origin = readFile pathFile
    say.debug("Search on file ${pathFile}")
    def target = origin.replaceAll(stringToSearch, stringReplacement)
    say.debug("Write file ${pathFile}")
    writeFile file: pathFile, text: target
    say.debug("Finish function editFile")
}