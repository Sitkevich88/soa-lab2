import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "2.7.15"
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "ru.itmo.soa"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

task("prepareKotlinBuildScriptModel"){}

repositories {
    mavenCentral()
}   

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.2.10.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-config-client")
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("org.apache.httpcomponents:httpcore:4.4.16")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
    }
}

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "8.2.1"
}