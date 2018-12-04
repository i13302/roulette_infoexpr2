
all: class exe 

# Fclass:=Progress.class Table.class Util.class
MainClass:=Progress.java

class:
	cd ./src && javac -g -d ../bin -encoding UTF-8 $(MainClass)

exe:
	cd ./bin && java Progress

jar:
	jar cvfm Roulette.jar META-INF/MANIFEST.MF -C bin/ .

clean:
	rm ./bin/*.class
