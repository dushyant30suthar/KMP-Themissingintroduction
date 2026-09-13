// Native C consumer: link against the Kotlin-compiled shared library and
// call greet() through the generated C ABI (libgreetings_api.h).
#include <stdio.h>
#include "libgreetings_api.h"

int main(void) {
    // The Kotlin/Native shared library exports one C symbol: a pointer to
    // a struct of all exported Kotlin functions, grouped by package.
    libgreetings_ExportedSymbols *sym = libgreetings_symbols();

    // Our shared code: greet() and platformName(), both returning const char*.
    const char *greeting = sym->kotlin.root.com.theemergentnarrative.kmpthemissingintroduction.greet();
    const char *platform = sym->kotlin.root.com.theemergentnarrative.kmpthemissingintroduction.platformName();

    printf("C program called the Kotlin-compiled libgreetings.so:\n");
    printf("  greet()        = %s\n", greeting);
    printf("  platformName() = %s\n", platform);

    // Free the Kotlin-allocated strings (the ABI provides DisposeString).
    sym->DisposeString(greeting);
    sym->DisposeString(platform);
    return 0;
}