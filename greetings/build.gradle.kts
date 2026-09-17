plugins {
    alias(libs.plugins.kotlinMultiplatform)
    // This article: publishing. The Kotlin plugin creates one publication
    // per target when maven-publish is applied.
    `maven-publish`
}

// Maven coordinates: group:artifact:version. The artifact is the project
// name, plus a suffix per target (greetings, greetings-jvm, greetings-js, ...).
group = "com.theemergentnarrative"
version = "1.0.0"

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