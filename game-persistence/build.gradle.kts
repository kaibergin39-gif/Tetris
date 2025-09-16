plugins {
    `java-library`
}

dependencies {
    implementation(project(":game-core"))
    implementation(libs.jackson.databind)
    implementation(libs.jackson.jsr310)
    testImplementation(libs.assertj.core)
}
