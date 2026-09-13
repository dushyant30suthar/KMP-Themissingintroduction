import com.theemergentnarrative.kmpthemissingintroduction.greet

// Kotlin/Native links an executable from a top-level main() in the root
// package of the target's own source set.
fun main() {
    println(greet())
}