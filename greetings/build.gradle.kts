plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    // --- Native Machine platform ---
    // A shared library: machine code another program links against.
    // (The previous article built an executable here; this one builds a library.)
    linuxX64 {
        binaries {
            sharedLib()
        }
    }

    // --- JVM platform ---
    // A jar of Java bytecode, consumed by any JVM program.
    jvm()

    // --- Web platform ---
    // JavaScript and WebAssembly, shipped as npm packages.
    js {
        binaries.library()
        browser()
        nodejs()
    }
    wasmJs {
        binaries.library()
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