# Java Coursework – Super Bowl Explorer & Cruise Management System

Two console applications written in Java for my second-year programming coursework (2024/25).

## Task 1 – Super Bowl Explorer (`task1-superbowls`)
Reads every Super Bowl result from a text file and lets you:
- **List** all Super Bowls between two years in a table
- **Select** one year and see the full game details (venue, score, MVP)
- **Search** by team (appearances, wins and runner-up finishes) or by U.S. state

Concepts: file reading and parsing, classes and objects, `ArrayList`, `HashMap`, formatted output.

## Task 2 – Cruise Ship Management System (`task2-cruise-system`)
Manages cruises, ships, cabins, passengers and excursions:
- View cruise, excursion and passenger information
- Book a passenger onto an excursion (with capacity checks)
- Move a passenger and their cabin mates to a vacant cabin

Concepts: abstract classes, inheritance and polymorphism (`Cabin` → `Suite` / `StandardCabin`), packages separating model / controller / menu, Java Streams and lambdas.

## How to run
Each task is a NetBeans project, so you can open the folder in NetBeans and press Run.

Or from a terminal:
```bash
# Task 1 (run from inside the task folder so it can find superbowls.txt)
cd task1-superbowls
javac -d out src/task1_coursework2/*.java
java -cp out task1_coursework2.Task1

# Task 2
cd task2-cruise-system
javac -d out $(find src -name "*.java")
java -cp out Main.Task2
```
