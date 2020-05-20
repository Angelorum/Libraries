#!/usr/bin/env groovy
/**
 *
 *  say.groovy
 *  Example of jenkins function
 *  use say(message) or say.simple(message)
 *
 */

def call(String message = "Default"){
    echo "************************************ ${message} ************************************"
}

def simple(String message = "Default"){
    echo message
}

def debug(String message = "Default"){
    if (params.DEBUG){
        echo message
    }
}