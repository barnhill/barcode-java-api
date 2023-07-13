buildscript {
    extra["gradleVersion"] = "8.2.1"
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
version "1.17"

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

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

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
