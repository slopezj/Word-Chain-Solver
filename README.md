# Word-Chain-Solver
Contents
I.	Introduction	
II.	Resources	
III.	The implementation	
	The approach	
	The program	

 
I.	Introduction

Capita IBS has requested me (by means of Computer Features) to develop in Java the following task: a Word Chain Solver.

Word chains are a simple game often found in the games and puzzles section of 
newspapers. The aim of the game is to get from one word to another by changing 
one letter of the word each time. Each change of letter must result in a legal 
intermediate word. For example, we can change LEAD into GOLD through the 
following steps: 
LEAD → LOAD → GOAD → GOLD 
Using Java, create an automated solver method that accepts the start and end words and prints a list of steps required to do the transform. There’s a dictionary of legal 
words to use in your solver in this zip file. You can assume the dictionary will be in 
the same directory as your executable. Start and end words should be the same 
length as each other. 


 
II.	Resources

The program has been developed using Eclipse Java EE IDE for Web Developers version Neon.1a Release (4.6.1) on windows 8.1. 




 
III.	The implementation

The approach

There are 2 classes:
•	CapitaExercise (main())
•	WordChainSolver

I have decided to use Lists to handle all words.
I have decided to go over the whole dictionary previously to do anything to delete all words with different length to the words entered by the user. This approach might take a lot of time if the dictionary is very big.
I have decided that the algorithm will stop searching as soon as it finds a valid combination. The algorithm is not trying to find the shortest list.

The program

From the main() method it askes the user to enter 2 words. It verifies that both have the same length. It remains waiting input while these 2 words have not the same length.
Then it deletes any word in the dictionary with a length different from the words entered by the user. Then it verifies if the 2 given words are valid by themselves. If not it calls method NavigateAllList(). 
NavigateAllList is a recursive method. It loops through all valid words in the dictionary. It starts with the starting word given by the user and it compares with the first word in the dictionary.
The comparison of 2 words is done character to character (method ValidateWord()). Each match increments a counter. At the end of the word based on that counter will return 3 possible values:
•	‘Repeated’. It will do nothing in NavigateAllList() method.
•	‘Not yet’. This option will call the recursivity.
•	‘Not valid word’. It will do nothing in NavigateAllList() method.
