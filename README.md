# KMP — Hello World!

A Kotlin Multiplatform (KMP) hello world: a shared Kotlin library that compiles to a native
`Greetings.framework` for macOS (Apple Silicon), consumed by a plain native SwiftUI app.

The business logic lives in the shared Kotlin module `greetings`. The module targets
`macosArm64` and produces a static framework — the library format macOS apps link against.
A native Xcode app imports the framework and calls Kotlin code as if it were written in Swift.

```
Kotlin (commonMain)  ──gradle──▶  Greetings.framework  ──xcode──▶  native macOS app
```

## Project layout

```
.
├── apps
│   └── apple
│       └── greetings
│           ├── greetings
│           │   ├── ContentView.swift        # calls Greetings().greet()
│           │   ├── greetingsApp.swift       # SwiftUI app entry point
│           │   └── Greetings.framework      # the built Kotlin framework
│           └── greetings.xcodeproj
├── greetings                                # the shared Kotlin library module
│   ├── src
│   │   └── commonMain
│   │       └── kotlin
│   │           └── com
│   │               └── theemergentnarrative
│   │                   └── kmpthemissingintroduction
│   │                       └── Greetings.kt
│   └── build.gradle.kts
├── build.gradle.kts
├── gradle
│   └── libs.versions.toml
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

## The shared library

`greetings/src/commonMain/kotlin/com/theemergentnarrative/kmpthemissingintroduction/Greetings.kt`:

```kotlin
package com.theemergentnarrative.kmpthemissingintroduction

class Greetings {

    fun greet(): String {
        return "Hello, from Kotlin!"
    }

}
```

The module's build file declares the target and the binary it produces — a static
`Greetings.framework` for Apple Silicon Macs:

```kotlin
kotlin {
    macosArm64().binaries.framework {
        baseName = "Greetings"
        isStatic = true
    }

    sourceSets {
        commonMain.dependencies {
        }
    }
}
```

## Build the library

From the project root:

```bash
./gradlew clean :greetings:build
```

The build produces:

1. Kotlin IR for the `macosArm64` target.
2. A `.klib` — a Kotlin library consumed by other Kotlin tooling.
3. The final native binary, because the `binaries` block declares a framework:

```
greetings/build/bin/macosArm64/releaseFramework/Greetings.framework
```

The framework contains machine code for this CPU and OS. `file` on the binary reports a
static archive of Mach-O object code with an Objective-C header — the linker consumes it
exactly as it would a Swift-authored static framework.

## Consume and run

The native macOS app in Swift links the framework and calls the code from `commonMain`:

```swift
import Greetings

// in ContentView
Text(Greetings().greet())
```

Wiring the app to the library:

1. **Link the framework.** In Xcode, under *General → Frameworks, Libraries, and Embedded
   Content*, add `Greetings.framework`.
2. **Point the compiler at it.** In *Build Settings → Framework Search Paths*, add the
   folder containing the framework:

   ```
   $(PROJECT_DIR)/greetings
   ```

Because the framework is static, its object code compiles straight into the app binary —
there is nothing to embed at runtime.

With the framework in place, select the `greetings` scheme and hit **Run** in Xcode.
The app window shows:

```
Hello, from Kotlin!
```

## Native to the target

The library is native to the target it points at. Change the target, and the same
`commonMain` code compiles to that platform's own artifact. Kotlin is the language, the
target is the platform, and Kotlin Multiplatform is the tooling around the language for
the platform.

## Requirements

- macOS on Apple Silicon
- Xcode
- JDK (used by Gradle)

## References

This project accompanies the article "KMP — Hello world!".