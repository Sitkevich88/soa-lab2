plugins {
    java
    id("org.springframework.boot") version "2.7.15"
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "ru.itmo"
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
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.15")
    implementation("org.modelmapper:modelmapper:3.1.1")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.5")
    implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0")


    implementation("org.wildfly:wildfly-ejb-client-bom:30.0.0.Final")

    implementation("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    implementation("com.example:bands_ejb:7.12")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")


    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.projectlombok:lombok")
}