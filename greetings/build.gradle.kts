plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    // --- Native Machine platform ---
    // The previous article used macosArm64 (builds only on a Mac).
    // On this Linux host the buildable native target is linuxX64.
    linuxX64 {
        binaries {
            executable()
        }
    }

    // --- JVM platform ---
    jvm()

    // --- Web platform ---
    js {
        browser()
        nodejs()
    }
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}