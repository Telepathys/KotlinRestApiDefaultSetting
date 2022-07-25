import org.gradle.internal.FileUtils
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "2.6.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
//	id("org.springframework.experimental.aot") version "0.11.3"

	kotlin("plugin.allopen") version "1.6.10"
	id("com.google.cloud.tools.jib") version "3.1.4"
	kotlin("kapt") version "1.6.10"
}

group = "com.teamsnowball.decakill.api"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	maven { url = uri("https://repo.spring.io/release") }
	mavenCentral()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

ext {
	set("springCloudVersion", "2021.0.1") // https://spring.io/projects/spring-cloud
//	set("log4j2.version", "2.17.1") // https://spring.io/blog/2021/12/10/log4j2-vulnerability-and-spring-boot
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
	implementation("org.projectlombok:lombok:1.18.20")
//	implementation("org.springdoc:springdoc-openapi-security:1.6.6")

	runtimeOnly("com.h2database:h2")/*runtimeOnly*/
	implementation("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("io.github.microutils:kotlin-logging:1.12.5")
	implementation("com.github.maricn:logback-slack-appender:1.4.0")
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
	implementation("commons-codec:commons-codec:1.15")
	implementation("com.querydsl:querydsl-jpa")
	kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
	sourceSets.main {
		withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
			kotlin.srcDir("$buildDir/generated/source/kapt/main")
		}
	}
	implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
	implementation("com.google.code.findbugs:jsr305:3.0.2")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
	implementation("com.squareup.okhttp3:okhttp:3.8.1")
	implementation("com.googlecode.json-simple:json-simple:1.1.1")
	implementation("com.google.code.gson:gson:2.8.9")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
	implementation("me.paulschwarz:spring-dotenv:2.5.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// https://github.com/jhipster/generator-jhipster/issues/13733
tasks.withType<BootBuildImage> {
	builder = "paketobuildpacks/builder:tiny"
	imageName = "decakillweb"
	environment = mapOf("BP_NATIVE_IMAGE" to "true", "BP_NATIVE_IMAGE_BUILD_ARGUMENTS" to """
		-H:+ReportExceptionStackTraces
		-H:TraceClassInitialization=true
		--initialize-at-run-time=org.apache.tomcat.util.threads.res.LocalStrings
		--report-unsupported-elements-at-runtime
		--trace-class-initialization=org.springframework.util.unit.DataSize
		""".trimIndent())
//		-H:+ReportExceptionStackTraces
}
jib {
	from {
		image = "adoptopenjdk/openjdk11:jre-11.0.13_8"
	}
	to {
		image = "551660765733.dkr.ecr.ap-northeast-2.amazonaws.com/decakillweb"
		tags = setOf("latest")
	}
	container {
		creationTime = "USE_CURRENT_TIMESTAMP"
		jvmFlags = listOf("-XX:+UseContainerSupport", "-Dfile.encoding=UTF-8")
//		jvmFlags = listOf("-Dspring.profile.active=local", "-XX:UseContainerSupport", "-Dserver.port=8080", "-Dfile.encoding=UTF-8")
		ports = listOf("8080", "80", "8088", "9090")
	}
}