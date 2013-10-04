HOW TO COMPILE AND RUN MY CODE

COMPILING

cd lab1

mkdir build

javac -d build source/*.java

RUNNING

cd build

java edu.iastate.coms572.lab1.WebSearch /path/to/intranet1/ beam

NOTE: the path MUST end in / I don't insert it for you in the code

valid search types (arg2):
	breadth
	depth
	best
	beam