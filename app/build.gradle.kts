plugins {
    id("java")
    id("application")
    id ("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    // Входная точка
    mainClass.set("hexlet.code.App")
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.3")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "8.45.1" // укажите актуальную версию
    configDirectory.set(file("$rootDir/config/checkstyle"))
}