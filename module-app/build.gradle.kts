import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
	implementation(project(":module-core"))
	implementation(project(":module-domain-health-check"))
	implementation(project(":module-domain-member"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21"
	}
}

tasks.withType<JavaCompile> {
	sourceCompatibility = "21"
	targetCompatibility = "21"
}

springBoot {
	mainClass.set("com.team.innergrim.innergrimapi.InnergrimapiApplicationKt")
}