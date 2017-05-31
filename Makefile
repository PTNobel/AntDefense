
all: *.class
	jar cfe AntDefense.jar AntDefense *.class *.png

%.class: %.java
	javac $?

clean:
	rm *.class

