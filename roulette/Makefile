
all: class exe 

# Fclass:=Progress.class Table.class Util.class
MainClass:=Progress

class:
	cd ./src && javac -g -d ../bin -encoding UTF-8 $(MainClass).java

exe:
	cd ./bin && java Progress

jar:./bin/$(MainClass).class
	jar cvfm Roulette.jar META-INF/MANIFEST.MF -C bin/ .

clean:
	-rm ./bin/*.class
