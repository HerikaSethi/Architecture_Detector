SRC = ArchitectureDetector.java
CLASS = ArchitectureDetector.class

compile:
	javac $(SRC)

run: compile
	java ArchitectureDetector

clean:
	del /Q *.class
