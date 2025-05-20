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
include(":feature:locations:api:contract")
include(":feature:locations:api:impl")
include(":feature:episodes:api:contract")
include(":feature:episodes:api:impl")
include(":feature:characters:presentation")
include(":feature:episodes:presentation")
include(":feature:locations:presentation")
include(":feature:characters:usecases:contract")
include(":feature:characters:usecases:impl")
include(":feature:locations:usecases:contract")
include(":feature:locations:usecases:impl")
include(":feature:episodes:usecases:contract")
include(":feature:episodes:usecases:impl")
include(":feature:characters:repositories:contract")
include(":feature:characters:entities")
include(":feature:episodes:entities")
include(":feature:locations:entities")
include(":feature:characters:repositories:impl")
include(":api:models:in")
include(":theme")
