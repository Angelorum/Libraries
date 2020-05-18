#!/usr/bin/env groovy
/**
 * say.groovy
 *  Example of jenkins function
 *  use call(message)
 *  or
 *  call.simple(message)
 */

def call(String message = "Default"){
    echo "************************************ ${message}************************************"
}

def simple(String message = "Default"){
    echo message
}
