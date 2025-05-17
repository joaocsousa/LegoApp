rootProject.name = "LegoAppKMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":api:core")
include(":feature:characters:api:contract")
include(":feature:characters:api:impl")
include(":api:models:in")
include(":feature:locations:api:contract")
include(":feature:locations:api:impl")
include(":feature:episodes:api:contract")
include(":feature:episodes:api:impl")
include(":feature:landing:ui")
