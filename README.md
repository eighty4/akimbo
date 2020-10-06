## Akimbo
This is (eventually to be) an application stack built on `reactor-netty` (for networking and scheduling) and `dagger` (for DI) and additional annotation processors to generate application boilerplate at compile time. This is a big 2020 learning exercise to explore the JDK compiler, Gradle tooling, `GraalVM` and `javapackager`.

Short-term goals for the project include generating HTTP, TCP and event-streaming services from declarative annotations. Right now it's only a convoluted Hello, World application :)

#### Get started by publishing the Akimbo gradle plugin
Before you can run any gradle tasks on this project, you need to build the gradle plugin. The current hacky way to do that is to comment out `include 'sandbox'` from `settings.gradle` and run `./gradlew publishToMavenLocal`. That module can't depend on the plugin until it's been built and will break gradle builds. After that, you can return the `sandbox` module and run `./gradlew sandbox:akimboRun` from the project root.
