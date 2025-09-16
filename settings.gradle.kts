dependencyResolutionManagement {
    repositoriesMode.set(org.gradle.api.initialization.resolve.RepositoriesMode.PREFER_SETTINGS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "tetris"

include("game-core")
include("game-ui")
include("game-audio")
include("game-persistence")
include("game-tests")
