plugins {
    alias(libs.plugins.kotlinMultiplatform)

}

kotlin {
    macosArm64().binaries.framework {
        baseName = "Greetings"
        isStatic = true
    }


    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}