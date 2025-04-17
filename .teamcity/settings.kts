import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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

version = "2025.03"

project {

    vcsRoot(HttpsGithubComMarimargaryan86parallelTestsGitRefsHeadsMain)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "json%build.counter%"

    vcs {
        root(HttpsGithubComMarimargaryan86parallelTestsGitRefsHeadsMain)
    }

    steps {
        script {
            name = "parallel"
            id = "parallel"
            scriptContent = """
                # This step runs unit tests in parallel
                dotnet test -- NUnit.NumberOfTestWorkers=4
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

object HttpsGithubComMarimargaryan86parallelTestsGitRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/marimargaryan86/parallel-tests.git#refs/heads/main"
    url = "https://github.com/marimargaryan86/parallel-tests.git"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "marimargaryan86"
        password = "credentialsJSON:edd62282-766b-4b07-a488-8324b5d23206"
    }
})
