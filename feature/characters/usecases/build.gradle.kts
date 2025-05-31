plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {

    androidLibrary {
        namespace = "xyz.aranhapreta.rickAndMorty.feature.characters.usecases"
        compileSdk = 35
        minSdk = 24
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "feature:characters:usecasesKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.feature.characters.entities)
                implementation(projects.feature.characters.repositories)
                implementation(libs.koin)
                implementation(libs.coroutines)
            }
        }

        androidMain
        iosMain
    }
}