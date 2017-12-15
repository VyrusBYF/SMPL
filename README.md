# SMPL
SMPL interpreter starter

The Lexer hasn't been tested but it should contain all the lexical rules for the SMPL language outlined in the project document. All the relevant terminals are declared in the cup file as well as some basic grammar--but not enough to parse any valid input. 

I refactored classes from the Fractal project as well as incorporated some aspects of the ArithParser interpreter. We should have all the basic elements, at least enough to start building the interpreter without having create too many of the basic stuff from scratch. Check it out.

To Run:
1. Run jflex on the lexer as usual.
2. Run the parser as you did for the fractal project with the values for the -package and -destdir options.
3. Compile.
4. Run Repl as you did in the fractal project (from the src folder wich contains the SMPL package); SMPL must be in all caps.
