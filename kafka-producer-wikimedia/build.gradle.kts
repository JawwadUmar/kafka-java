plugins {
    id("java")
}

group = "io.conduktor.demos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
    implementation("org.apache.kafka:kafka-clients:4.1.1")
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.17")
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    // Source: https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:5.3.2")
    // Source: https://mvnrepository.com/artifact/com.launchdarkly/okhttp-eventsource
    // Source: https://mvnrepository.com/artifact/com.launchdarkly/okhttp-eventsource
    implementation("com.launchdarkly:okhttp-eventsource:2.5.0")
}

tasks.test {
    useJUnitPlatform()
}