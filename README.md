# LAB-6-advanced-2002238-
# AUTOSAR FILE SORTING 
This file sorts AUTOSAR files by their containers' 
<SHORT-NAME> attribute.
The output file is the same name as the input file but with "_mod.arxml".

# I/O 
-Input file is of type "arxml".
-Output file is of "arxml".

# Documentation
- Container class:
Arrtibutes: - Container
            - Short name
            - Long name 
            - Footer
Methods: - Getters

Constructor: 
Container(string container,
string shortname,string longname,string footer)
- EmptyFileException class:
Exception thrown when the input file is empty.
- InvalidFileException class:
Exception thrown when the input file isn't of type "arxml".
