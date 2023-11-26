//import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "2.5.0"
    //id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

//apply(plugin = "org.springframework.boot")
//apply(plugin = "io.spring.dependency-management")

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
    implementation("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    //implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul:2.2.7.RELEASE")
}


/*configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.3")
    }
}*/

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "6.0.1"
}