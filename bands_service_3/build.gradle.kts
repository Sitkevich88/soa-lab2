plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.wildfly:wildfly-ejb-client-bom:30.0.0.Final")

    implementation("org.slf4j:slf4j-api:1.6.1")
    implementation("org.slf4j:slf4j-simple:1.6.1")

    implementation(files("bands_ejb-0.0.2.jar"))

}

configurations {
    all {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
