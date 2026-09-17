package com.theemergentnarrative.kmpthemissingintroduction

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

// The heart of KMP. One declaration, shared by every target.
// Each platform supplies its own `actual` body.
expect fun platformName(): String

@OptIn(ExperimentalJsExport::class)
@JsExport
fun greet(): String = "Hello from Kotlin on ${platformName()}"
