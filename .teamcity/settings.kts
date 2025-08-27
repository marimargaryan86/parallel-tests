import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.RunInDockerBuildFeature
import jetbrains.buildServer.configs.kotlin.buildFeatures.runInDocker
import jetbrains.buildServer.configs.kotlin.buildSteps.DockerCommandStep
import jetbrains.buildServer.configs.kotlin.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.buildSteps.gitHubRelease
import jetbrains.buildServer.configs.kotlin.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2025.07"

project {

    buildType(Build)
    buildType(Uuuu)
}

object Build : BuildType({
    name = "build"

    params {
        param("sleep", "sleep 10")
        param("notes", "param_notes")
        select("note_1", "text1",
                options = listOf("text1", "text2"))
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gitHubRelease {
            name = "loca_notes"
            id = "mono"
            enabled = false
            targetVcsRootId = "Monorepo_HttpsGithubComMarimargaryan86parallelTestsGit"
            tagName = "loca_notes"
            releaseNotes = "%notes%"
            draft = true
            preRelease = true
            latest = true
            authType = vcsRoot()
        }
        dockerCommand {
            name = "test"
            id = "test"
            enabled = false
            commandType = build {
                source = file {
                    path = "ParallelTests"
                }
                platform = DockerCommandStep.ImagePlatform.Linux
                commandArgs = "--pull"
            }
        }
        script {
            name = "cmd"
            id = "cmd"
            enabled = false
            scriptContent = "%sleep%"
        }
        gitHubRelease {
            name = "git_personal"
            id = "git_dls"
            targetVcsRootId = "ParallelTests3_7_HttpsGithubComMarimargaryan86parallelTestsRefsHeadsMain"
            tagName = "git_personal"
            releaseName = "%note_1%"
            releaseNotes = "%note_1%"
            authType = vcsRoot()
        }
    }

    features {
        runInDocker {
            enabled = false
            dockerImage = "jetbrains/tc-agent-dind-2"
            dockerImagePlatform = RunInDockerBuildFeature.ImagePlatform.Linux
        }
    }

    requirements {
        exists("docker.server.osType", "RQ_1")
    }
    
    disableSettings("RQ_1")
})

object Uuuu : BuildType({
    name = "uuuu"

    vcs {
        root(DslContext.settingsRoot)
    }
})
