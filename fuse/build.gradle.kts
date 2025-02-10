plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("publication")
}

val artifactGroupId: String by project
group = artifactGroupId

val gitSha = "git rev-parse --short HEAD".runCommand(project.rootDir)?.trim().orEmpty()

val isReleaseBuild: Boolean
    get() = properties.containsKey("release")

val artifactPublishVersion: String by project
version = if (isReleaseBuild) artifactPublishVersion else "master-$gitSha-SNAPSHOT"

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.diskCache)

    api(libs.result)

    testImplementation(libs.test.junit)
}

val sourcesJar by tasks.registering(Jar::class) {
    from(project.extensions.getByType<SourceSetContainer>()["main"].allSource)
    archiveClassifier.set("sources")
}
