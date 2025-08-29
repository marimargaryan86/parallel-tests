import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.gitHubRelease
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubAppConnection

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

    buildType(HookRelase)

    features {
        githubAppConnection {
            id = "PROJECT_EXT_9"
            displayName = "TeamCity-hook"
            appId = "1813985"
            clientId = "Iv23lilT6QBqL8cfXX2M"
            clientSecret = "credentialsJSON:015bd06c-70fc-44d9-aacb-3a3e7c3f13e5"
            privateKey = "credentialsJSON:43f0572b-017b-4b58-8e73-29f98be913f7"
            webhookSecret = "credentialsJSON:3d35b381-3234-4de6-92fd-3931e008cc39"
            ownerUrl = "https://github.com/marimargaryan86"
            useUniqueCallback = true
        }
    }
}

object HookRelase : BuildType({
    name = "hook-relase"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gitHubRelease {
            name = "hook"
            id = "hook"
            targetVcsRootId = "Hooks_Hook"
            tagName = "hook-8"
            releaseName = "hook"
            latest = true
            authType = vcsRoot()
        }
    }
})
