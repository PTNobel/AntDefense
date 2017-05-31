
all: AntDefense.jar

AntDefense.jar: *.class *.png
	jar cfe AntDefense.jar AntDefense *.class *.png

%.class: %.java
	javac $?

clean:
	rm *.class
	rm AntDefense.jar
