# Location of trees.
SOURCE_DIR  := src
OUTPUT_DIR  := build/classes

# Unix tools
AWK         := awk
FIND        := /bin/find
MKDIR       := mkdir -p
RM          := rm -rf
SHELL       := /bin/bash

# Java tools
JAVA        := java
JAVAC       := javac

JFLAGS      := -sourcepath $(SOURCE_DIR)        \
               -d $(OUTPUT_DIR)

JVMFLAGS    := -ea                              \
               -esa                             \
               -Xfuture

JVM         := $(JAVA) $(JVMFLAGS)

JAVADOC     := javadoc
JDFLAGS     := -sourcepath $(SOURCE_DIR)        \
               -d $(OUTPUT_DIR)                 \
               -link http://java.sun.com/products/jdk/1.4/docs/api


# make-directories - Ensure output directory exists.
make-directories := $(shell $(MKDIR) $(OUTPUT_DIR))

FILES := $(SOURCE_DIR)/main/java/soccerball/*.java $(SOURCE_DIR)/main/java/soccerball/utils/*.java $(SOURCE_DIR)/main/java/soccerball/piece/*.java $(SOURCE_DIR)/main/java/soccerball/piece/exceptions/*.java

# help - The default goal
.PHONY: help
help:
	@$(call brief-help, $(CURDIR)/Makefile)

# all - Perform all tasks for a complete build
.PHONY: all
all: compile javadoc

# compile - Compile the source
.PHONY: compile
compile:
	$(JAVAC) $(JFLAGS) $(FILES)

# compile - Compile the source
.PHONY: execute
execute:
	$(JVM) -cp build/classes/ soccerball.SoccerBall

# javadoc - Generate the Java doc from sources
.PHONY: javadoc
javadoc:
	$(JAVADOC) $(JDFLAGS) $(FILES)

.PHONY: clean
clean:
	$(RM) $(OUTPUT_DIR)

.PHONY: classpath
classpath:
	@echo CLASSPATH='$(CLASSPATH)'
