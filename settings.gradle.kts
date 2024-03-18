pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()



    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url=uri( "https://maven.mapmyindia.com/repository/mapmyindia/")}
    }
}

rootProject.name = "My Application"
include(":app")
 