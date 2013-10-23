fsa_acceptor
============

In this program, I use an external Graph class made by my friend Yaohua in CSE 331 class, which is just like 
the popular JGraphT online. It helps me to create a nested HashMap to store all the transitions in the Carmel
formatted FSA files. In addition, I have another two Strings to store both the start state and the final state.

For the NFA problem, my algorithm uses three nested for loops to check each input (For loop 1) with every 
possible start state (For loop 2) and the resulting next or final states (For loop 3) for each given string 
line (already be trimmed as plain number/letters strings).

However, this algorithm's drawback is that, it cannot handle states or inputs that are made up of multiple 
letters or numbers. Moreover, I assume that there is only one start state and only one final state. For those 
with multiple states, I need to create a HashSet to store those states then.


To run:

./fsa_acceptor2.sh carmel_formatted_fsa_files string_line_testing_files
