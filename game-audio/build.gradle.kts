plugins {
    `java-library`
}

dependencies {
    api(project(":game-core"))
    testImplementation(libs.assertj.core)
}
