//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    id ("checkstyle")
    id("org.sonarqube") version "6.2.0.5505"
    //kotlin("jvm") version "1.9.22"
}

//sonar {
//    properties {
//        property("sonar.projectKey", "TelAndr_java-project-71")
//        property("sonar.organization", "telandr1987")
//        property("sonar.host.url", "https://sonarcloud.io")
//    }
//}

sonarqube {
    properties {
        property ("sonar.projectKey", "TelAndr_java-project-71")
        property ("sonar.host.url", "https://sonarcloud.io")
        property ("sonar.login", "${System.getenv('SONAR_TOKEN')}") // Используйте переменную окружения для вашего токена
        property ("sonar.coverage.jacoco.xmlReportPaths", file("build/reports/jacoco/test/jacocoTestReport.xml"))
    }
}

//jacocoTestReport {
//    dependsOn test // тесты должны быть запущены перед созданием отчета
//            reports {
//                xml.enabled true
//                csv.enabled false
//                html.enabled true
//            }
//}

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
    implementation("org.testng:testng:7.1.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    //testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.13.1")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "8.45.1" // укажите актуальную версию
    configDirectory.set(file("$rootDir/config/checkstyle"))
}
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    jvmTarget = "20"
//}
//val compileTestKotlin: KotlinCompile by tasks
//compileTestKotlin.kotlinOptions {
//    jvmTarget = "20"
//}