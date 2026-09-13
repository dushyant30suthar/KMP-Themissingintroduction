package com.theemergentnarrative.kmpthemissingintroduction

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTest {
    @Test
    fun greetNamesThePlatform() {
        val g = greet()
        println(">>> " + g)
        assertTrue(g.startsWith("Hello from Kotlin on "))
    }
}