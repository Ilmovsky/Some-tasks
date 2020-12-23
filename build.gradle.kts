plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    //Dagger
    implementation("com.google.dagger:dagger:2.19")
    annotationProcessor("com.google.dagger:dagger-compiler:2.19")
    //test
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.mockito:mockito-core:3.3.3")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}