// Node consumer: require the Kotlin-compiled JS module and call greet().
// The Kotlin/JS output is a UMD module (works as CommonJS / AMD / browser global).
//
// Run after `./gradlew :greetings:assemble`, pointing at the built module:
//   node consumers/node-consumer.js greetings/build/dist/js/developmentLibrary/KMP-Themissingintroduction-greetings.js
const path = process.argv[2] ||
  'greetings/build/dist/js/developmentLibrary/KMP-Themissingintroduction-greetings.js';
const lib = require(path);

// Kotlin/JS exposes its public functions on the module. Find and call greet().
const keys = Object.keys(lib);
console.log('module exports keys:', keys.join(', '));

// greet is exported (possibly under Kotlin/JS export names). Resolve it.
let greet = lib.greet || (lib.$_$ && lib.$_$.a) || (lib.kotlin && lib.kotlin.greet);
if (typeof greet === 'function') {
  console.log('Node called the Kotlin-compiled greet():');
  console.log('  ' + greet());
} else {
  console.log('greet not found at top level; dumping:', JSON.stringify(keys));
}