plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.entities"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "feature:characters:entitiesKit"
        }
    }

    sourceSets {
        commonMain
        androidMain
        iosMain
    }
}