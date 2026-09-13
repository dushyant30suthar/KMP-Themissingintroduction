package com.theemergentnarrative.kmpthemissingintroduction

// The heart of KMP. One declaration, shared by every target.
// Each platform supplies its own `actual` body.
expect fun platformName(): String

fun greet(): String = "Hello from Kotlin on ${platformName()}"