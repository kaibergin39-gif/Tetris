import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

plugins {
    base
    checkstyle
    alias(libs.plugins.spotbugs) apply false
    alias(libs.plugins.javafx) apply false
}

private fun VersionCatalog.library(alias: String) =
    findLibrary(alias).orElseThrow {
        IllegalArgumentException("Library with alias '$alias' not found in version catalog")
    }

private fun VersionCatalog.version(alias: String) =
    findVersion(alias).orElseThrow {
        IllegalArgumentException("Version with alias '$alias' not found in version catalog")
    }.requiredVersion

allprojects {
    group = "io.github.tetris"
    version = "0.1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "checkstyle")
    apply(plugin = "com.github.spotbugs")

    extensions.configure<org.gradle.api.plugins.JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
        withSourcesJar()
        withJavadocJar()
    }

    tasks.withType<org.gradle.api.tasks.compile.JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    val libs = rootProject.extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        "testImplementation"(platform(libs.library("junit-bom")))
        "testImplementation"(libs.library("junit-jupiter"))
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<org.gradle.api.tasks.testing.Test>().configureEach {
        useJUnitPlatform()
        testLogging {
            events(
                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            )
        }
    }

    extensions.configure<org.gradle.api.plugins.quality.CheckstyleExtension> {
        toolVersion = "10.18.2"
        config = resources.text.fromFile(rootProject.file("config/checkstyle/checkstyle.xml"))
    }

    tasks.withType<org.gradle.api.plugins.quality.Checkstyle>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(false)
        }
    }

        pluginManager.withPlugin("com.github.spotbugs") {
            extensions.configure<com.github.spotbugs.snom.SpotBugsExtension> {
                toolVersion.set(libs.version("spotbugs"))
                effort.set(com.github.spotbugs.snom.Effort.MAX)
                reportLevel.set(com.github.spotbugs.snom.Confidence.LOW)
            }

    }
}
