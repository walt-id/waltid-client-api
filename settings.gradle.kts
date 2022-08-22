pluginManagement {
    repositories {
        google()
        //jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:7.2.0")
            }
        }
    }
}
rootProject.name = "waltid-client-api"

