plugins {
  base
  id("com.github.node-gradle.node") version "7.1.0"
}

node {
  version = "22.11.0"
  npmVersion = "10.9.0"
  download = true
}

val installDependencies by tasks.registering(com.github.gradle.node.npm.task.NpmTask::class) {
  args.set(listOf("install"))
}

val serve by tasks.registering(com.github.gradle.node.npm.task.NpmTask::class) {
  dependsOn(installDependencies)
  args.set(listOf("run", "start"))
}
