package org.devops

//构建集成

def Build(buildType,buildShell){
    build_dic = ['mvn':'M2','gradle':'GRADLE','npm':'NPM']
    
    toolsHome = tool build_dic["${buildType}"]
    
    if ("${buildType}" == "npm"){
        sh """
            export NODEJS_HOME=/data/devops/node-v12.18.4-linux-x64 
            export PATH=\$PATH:\$NODEJS_HOME/bin
            ${toolsHome}/bin/npm ${buildShell}
            """
    } else {
        sh "${toolsHome}/bin/${buildType} ${buildShell}"
    }
}
