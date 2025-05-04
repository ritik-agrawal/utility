plugins {
	java
	id("java-library")
	id("org.springframework.boot") version "3.4.3" apply false
	id("io.spring.dependency-management") version "1.1.7"
	id("maven-publish")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

group = "com.rklab"
version = "utility-0.0.5-SNAPSHOT"
description = "Initial version of utility"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencyManagement {
	imports{
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}

publishing {
	repositories {
		val user: String? = project.findProperty("username") as String? ?: System.getenv("GITHUB_USERNAME")
		val token: String? = project.findProperty("token") as String? ?: System.getenv("GITHUB_TOKEN")
		val repo = "utility"
		val gitUrl = "https://maven.pkg.github.com/${user}/${repo}"
		maven {
			name = "GitHubPackages"
			url = uri(gitUrl)
			credentials {
				username = user
				password = token
			}
		}
	}
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])
		}
	}
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:3.4.3")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//added
	implementation("org.apache.commons:commons-lang3:3.14.0")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("jakarta.validation:jakarta.validation-api:3.0.1")
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.0")
	implementation ("org.modelmapper:modelmapper:3.1.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.glassfish.expressly:expressly:5.0.0")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	implementation ("org.apache.poi:poi:5.3.0")
	implementation ("org.apache.poi:poi-ooxml:5.3.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
