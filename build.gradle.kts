import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

allprojects {
	group = "com.team.innergrim"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")

	tasks.withType<JavaCompile> {
		sourceCompatibility = JavaVersion.VERSION_21.toString()
		targetCompatibility = JavaVersion.VERSION_21.toString()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")

		// Spring Boot Starter Validation
		implementation("org.springframework.boot:spring-boot-starter-validation")

		// Spring security
		implementation("org.springframework.boot:spring-boot-starter-security")

		// JPA
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")

		// Webflux
		implementation("org.springframework.boot:spring-boot-starter-webflux")

		// H2 Database
		runtimeOnly("com.h2database:h2")

		// Mysql
		runtimeOnly("mysql:mysql-connector-java:8.0.33")

		// SpringDoc (Swagger)
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

		// Redis
		implementation("org.springframework.boot:spring-boot-starter-data-redis")

		// Embeded Redis (Local용)
		implementation("it.ozimov:embedded-redis:0.7.2")

		// AWS
//		compileOnly("software.amazon.awssdk:aws-sdk-java:2.0.2")
		implementation("io.awspring.cloud:spring-cloud-aws-s3:3.0.2")
		implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:3.2.1"))
		implementation("io.awspring.cloud:spring-cloud-aws-starter-parameter-store")

		// JWT
		implementation("com.auth0:java-jwt:4.4.0")

		// kotest
		testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
		testImplementation("io.kotest:kotest-assertions-core:5.4.2")
		testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

		// mockk
		testImplementation("io.mockk:mockk:1.13.8")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Disable the bootJar task
// 빌드 시 mainclass 문제 해결방법 : https://stackoverflow.com/questions/56861256/gradle-build-failed-main-class-name-has-not-been-configured-and-it-could-not-be
tasks.named<BootJar>("bootJar") {
	enabled = false
}

// Ensure that the jar task is enabled
// final class 해결 방법 https://velog.io/@kdohyeon/may-not-be-final-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0
tasks.named<Jar>("jar") {
	enabled = true
}




