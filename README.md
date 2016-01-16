# tower-of-hanoi-gui
This directory contains my implementaion of the Tower of Hanoi puzzle, including a GUI.

CONTENTS:

ReadMe                  This file.
Makefile                A makefile (for the 'make' program) that will compile
                        your files and run tests.  You must turn in a Makefile,
                        'make' must compile all your files, and 
                        'make check' must perform all your tests.  Currently,
                        this makefile is set up to do just that with our
                        skeleton files.  Be sure to keep it up to date.

hanoi                   A subdirectory containing code for the 
                        hanoi package:

  Main.java             The main program.
  Game.java             The Model (MVC) for the puzzle. Conatains all of the data about all 3 towers and their disks.
  HanoiTower.java       The contents of individual towers, including a stack of disks and other instances.
  GUI.java              The Controller (MVC) for the puzzle.
  HanoiDisplay.java     The View (MVC) for the puzzle, displays the app.
  GameTest.java         Tests the functionality of the Game class.
  HanoiTowerTest.java   Tests the functionality of the HanoiTower class.
  UnitTest.java         Tests all of the Unit Tests in the directory.
  Makefile              A makefile that controls compilation and style checking.
  
