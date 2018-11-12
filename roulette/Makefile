
all: class exe

# Fclass:=Progress.class Table.class Util.class
# Fjava:=Progress.java Table.java Util.java

class:
	javac -g -d ./bin -encoding UTF-8 ./src/*.java

exe:
	cd ./bin && java Progress
	
clean:
	rm ./bin/*.class
