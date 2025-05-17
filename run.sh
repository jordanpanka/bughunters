#!/bin/bash

# Lépj be a forráskód könyvtárba
cd jatek/src/main/java/bughunters || exit

# Fordítsd le az összes Java fájlt az almenükben, és tedd a class mappába
javac -d class */*.java

# Futtasd a fő osztályt
java -cp class bughunters.Egyeb.Parancskezelok