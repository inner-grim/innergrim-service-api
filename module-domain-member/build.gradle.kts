import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
	implementation(project(":module-core"))
}

// Disable the bootJar task
tasks.named<BootJar>("bootJar") {
	enabled = false
}

// Ensure that the jar task is enabled
tasks.named<Jar>("jar") {
	enabled = true
}