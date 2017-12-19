# SMPL

If you already have the SMPL zip you can replace the src directory with the one above. The program should now work for simple expressions such as addition, subtraction, etc. as well as expressions including the comparison operators. So far I've only implemented these functions for integers.

I refactored classes from the Fractal project as well as incorporated some aspects of the ArithParser interpreter. We should have all the basic elements, at least enough to start building the interpreter without having create too many of the basic stuff from scratch. Check it out.

To Run:
1. Run jflex on the lexer as usual.
2. Run the parser as you did for the fractal project with the values for the -package and -destdir options.
3. Compile.
4. Run Repl as you did in the fractal project (from the src folder which contains the SMPL package); SMPL must be in all caps.
