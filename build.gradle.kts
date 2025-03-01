plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "rklab"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
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
}

tasks.withType<Test> {
	useJUnitPlatform()
}
