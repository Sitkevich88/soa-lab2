import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "2.7.5"
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "com.example"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-config-server")
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
    }
}

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "8.2.1"
}

task("prepareKotlinBuildScriptModel"){}