plugins {
    application
    alias(libs.plugins.javafx)
}

dependencies {
    implementation(project(":game-core"))
    implementation(libs.javafx.controls)
    implementation(libs.javafx.graphics)
    implementation(libs.javafx.base)
    testImplementation(libs.assertj.core)
}

application {
    mainClass.set("io.github.tetris.ui.TetrisApplication")
}

javafx {
    version = libs.versions.javafx.get()
    modules("javafx.controls", "javafx.graphics")
}
