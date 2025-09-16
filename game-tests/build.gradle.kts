plugins {
    `java`
}

dependencies {
    testImplementation(project(":game-core"))
    testImplementation(project(":game-persistence"))
    testImplementation(project(":game-audio"))
    testImplementation(project(":game-ui"))
    testImplementation(libs.assertj.core)
    testImplementation(libs.mockito.core)
}
