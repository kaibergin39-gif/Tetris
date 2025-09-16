plugins {
    `java-library`
}

dependencies {
    compileOnly(libs.spotbugs.annotations)
    testImplementation(libs.assertj.core)
}
