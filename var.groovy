import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class Repo {

    def name
    def remote
    def branchToSync
    def lastSyncCommit

    def sourceRepo
    def sourceBranch
    def lastCommit

    def checkChanges() {
        println 'check'
        return false
    }

}

filename = "repos.json"

def fileSource = new File(filename)


if (!fileSource.exists()) {

    Repo newRepo = new Repo()

    newRepo.name = "test repo"
    newRepo.remote = "origin"
    newRepo.sourceBranch = "master"
    newRepo.lastCommit = "8d7b0f"

    def jsonStr = JsonOutput.toJson("repos": [newRepo, newRepo])
    def jsonFormat = JsonOutput.prettyPrint(jsonStr)

    File file = new File(filename)
    file.write(jsonFormat)

} else {

    def jsonSlurper = new JsonSlurper()
    jsonData = jsonSlurper.parse(fileSource)

    for (repo in jsonData.repos) {

        println repo
        Repo repoObj = new Repo(repo)
        repoObj.checkChanges()
        print(repoObj.lastCommit)

    }

}


//println new JsonBuilder(newRepo).toPrettyString()





