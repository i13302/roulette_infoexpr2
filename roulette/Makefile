
all: class exe 

# Fclass:=Progress.class Table.class Util.class
MainClass:=Progress.java

class:
	cd ./src && javac -g -d ../bin -encoding UTF-8 $(MainClass)

exe:
	cd ./bin && java Progress
	
clean:
	rm ./bin/*.class
