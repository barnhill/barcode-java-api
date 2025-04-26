tasks {
    wrapper {
        gradleVersion = "8.14"
        distributionType = Wrapper.DistributionType.BIN
    }
}

val gradleVersion: String by extra

plugins {
    id("java")
    id("eclipse")
    id("idea")
    id("war")
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDepManagement)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.allopen)
    alias(libs.plugins.kotlin.jvm)
}

group = "com.pnuema.java.barcode"

dependencies {
    implementation(libs.barcode)
    implementation(libs.spring.starter.web)
    implementation(libs.spring.starter.tomcat)
    implementation(libs.openapi.ui)
    implementation(libs.jackson.kotlin)

    testImplementation(libs.spring.starter.test)
}

tasks {
    wrapper {
        gradleVersion = gradleVersion
        distributionType = Wrapper.DistributionType.BIN
    }

    test {
        useJUnitPlatform()
    }

    java.toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_21.majorVersion.toInt()))

    bootJar {
        archiveFileName.set("barcode-api.jar")
    }

    bootWar {
        archiveFileName.set("barcode-api.war")
    }

    configurations {
        providedRuntime
    }
}
