# This makefile is defined to give you the following targets:
#
#    default: The default target: Compiles the program in package db61b.
#    style: Run our style checker on the project source files.  Requires that
#           the source files compile.
#    check: Compiles the db61b package, if needed, and then performs the
#           tests described in testing/Makefile.
#    clean: Remove regeneratable files (such as .class files) produced by
#           other targets and Emacs backup files.
#
# In other words, type 'make' to compile everything; 'make check' to 
# compile and test everything, and 'make clean' to clean things up.


# Name of package containing main procedure 
PACKAGE = hanoi

STYLEPROG = style61b

# Targets that don't correspond to files, but are to be treated as commands.
.PHONY: default check integration unit clean style jar

default:
	$(MAKE) -C $(PACKAGE) default

check: unit

unit: default
	$(MAKE) -C $(PACKAGE) unit

style:
	$(MAKE) -C $(PACKAGE) STYLEPROG=$(STYLEPROG) style

# 'make clean' will clean up stuff you can reconstruct.
clean:
	$(RM) *~
	$(MAKE) -C $(PACKAGE) clean
	$(MAKE) -C testing clean


