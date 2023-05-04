#!/bin/bash

# Compile all Java files in the project directory and its subdirectories

find . -name "*.java" > sources.txt
javac @sources.txt
rm sources.txt

