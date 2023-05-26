import java.io.File

rootProject.name = "algo-challenges-in-kotlin"


pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

fun sanitizeName(name: String): String {
    return name
        .replace("[ /\\\\:<>\"?*|()]".toRegex(), "_")
        .replace("(^[.]+)|([.]+\$)".toRegex(), "")
}

rootProject.projectDir.walkTopDown()
    .filter { isTaskDir(it) && !it.path.contains(".idea") }
    .forEach { dir ->
        val taskRelativePath = rootDir.toPath().relativize(dir.toPath())
        val parts = taskRelativePath.map { sanitizeName(it.toString()) }
        val moduleName = parts.joinToString("-")
        include(moduleName)
        project(":$moduleName").projectDir = dir
    }

fun isTaskDir(dir: File): Boolean {
    return !dir.absolutePath.contains("buildSrc") && File(dir, "src").exists()
}

include("util")
