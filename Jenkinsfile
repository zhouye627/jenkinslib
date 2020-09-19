#!groovy

@Library('jenkinslib') _

def tools = new org.devops.tools()

String workspace = "/opt/jenkins/workspace"

pipeline {
    agent {
	    node {
		    label "master"
			customWorkspace "${workspace}"
		}
	}
	
	options {
	    timestamps() //日志添加时间
		skipDefaultCheckout() //删除隐式checkout scm语句
		disableConcurrentBuilds() //禁止并行
		timeout(time: 1, unit: 'HOURS') //超时设置1h
	}
	
	parameters {
        string(name: 'test', defaultValue: 'abcd', description: 'string?')
    }
	
	stages {
		//下载代码
		stage ("下载代码"){
		    when { environment name: 'test', value: 'abcd' }
			steps{
			    input id: 'Test', message: '继续？', ok: '是', parameters: [choice(choices: ['一级', '二级'], description: '', name: 'text')], submitter: 'admin,text'
				timeout(time:5,unit:"MINUTES"){  //步骤超时设置
					script{
						println("获取代码")
						tools.PrintMes("获取代码",'blue')
						
					}
				}
			}
		}
		
		//构建
		stage ("构建"){
			steps{
				timeout(time:30,unit:"MINUTES"){
					script{
						println("应用打包")
						tools.PrintMes("应用打包",'green')
					}
				}
			}
		}
		
		//代码扫描
		stage ("代码扫描"){
			steps{
				timeout(time:20,unit:"MINUTES"){
					script{
						println("代码扫描")
						println("${test}")
						tools.PrintMes("代码扫描",'green1')
						tools.PrintMes("${test}",'red')
					}
				}
			}
		}
		
	}
	
	post {
		always {
			script{
				println("always")
			}
		}
		
		success {
			script{
				currentBuild.description += "\n 构建成功！"
			}
		}
		
		failure {
			script{
				currentBuild.description += "\n 构建失败！"
			}
		}
		aborted {
			script{
				currentBuild.description += "\n 构建取消！"
			}
		}
	}

}

