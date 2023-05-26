import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly


plugins {
    java
    kotlin("jvm")
    application
}

buildscript {
    val kotlinVersion: String by properties
    extra["kotlin-version"] = kotlinVersion

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

fun printOutput(output: CharSequence): Task {
    return tasks.create("printOutput") {
        println("#educational_plugin_checker_version 1")
        val separator = System.lineSeparator()
        val lines = output.toString().split("(?<=$separator)|(?=$separator)")
        for (line in lines) {
            println("#educational_plugin$line")
        }
    }
}

subprojects {
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    sourceSets {
        main {
            java.srcDir("src")
        }
        test {
            java.srcDir("test")
        }
    }

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

        withType<Test> {
            useJUnitPlatform()
            outputs.upToDateWhen { false }
            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.lines()
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }

    if (project.hasProperty("educationalRun") && "true" == project.property("educationalRun").toString()
            .toLowerCaseAsciiOnly()
    ) {
        val runOutput = StringBuilder()
        tasks {
            named("run") {
                logging.addStandardOutputListener {
                    runOutput.append(it)
                }
                doLast {
                    printOutput(runOutput.toString())
                }
            }
        }
    }
}

project(":util") {
    dependencies {
        implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    }
}

configure(subprojects.filter { it.name != "util" }) {
    dependencies {
        testImplementation(project(":util"))
        tasks.register("buildTaskMD") {
            val templateFile = File(projectDir, "task_template.md")
            val targetFile = File(projectDir, "task.md")
            if (templateFile.exists()) {
                ResolveTemplate.resolveTemplate(templateFile, targetFile, rootDir)
            }
        }
    }
}
