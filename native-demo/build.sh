#!/usr/bin/env bash
# Native code journey demo: source -> pre-processing -> compilation -> linking.
# Shows the static (.a) vs dynamic (.so) split and DT_NEEDED.
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p out
cd out

echo "## Pre-processing (gcc -E) -> .i"
gcc -E -I../include ../src/add.c -o add.i
file add.i

echo "## Compilation (gcc -c) -> .o"
gcc -c -I../include ../src/add.c -o add.o
gcc -c -I../include ../src/mul.c -o mul.o
gcc -c -I../include ../src/main.c -o main.o
gcc -c -I../include ../src/main2.c -o main2.o
gcc -c -I../include ../src/ext.c -o ext.o
file add.o

echo "## Symbol table (nm)"
nm add.o
nm main.o

echo "## Static library (ar) -> .a, then link"
ar rcs libmath.a add.o mul.o
nm -s libmath.a
gcc main.o libmath.a -o app-static
nm app-static | grep -E ' [TW] (add|mul)$'
ldd app-static

echo "## Dynamic library (gcc -shared -fPIC) -> .so, then link"
gcc -shared -fPIC add.o mul.o -o libmath.so
gcc main.o -L. -lmath -Wl,-rpath,./ -o app-so
nm app-so | grep ' U add'
readelf -d app-so | grep -E 'NEEDED|RUNPATH'
ldd app-so

echo "## A .so that depends on another .so (DT_NEEDED chain)"
gcc -shared -fPIC ext.o -o libext.so
gcc main2.o -L. -lmath -lext -Wl,-rpath,./ -o app-two
readelf -d app-two | grep NEEDED
./app-two