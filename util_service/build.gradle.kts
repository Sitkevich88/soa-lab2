plugins {
    java
    id("org.springframework.boot") version "2.7.15"
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "ru.itmo.soa"
version = "0.0.1-SNAPSHOT"

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
    //implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}