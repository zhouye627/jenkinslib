package org.devops

def ansibleDeploy(hosts,func){
    sh "ansible ${func} ${hosts}"
}
