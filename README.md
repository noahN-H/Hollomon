**DISCLAIMER:** This project is a coursework project - the program is written with specific guidelines and practices in place.

# Premise
Hollomon is a network based trading card game where a user can 
1. View current cards
2. Buy cards
3. Sell cards
4. Auto-trade
   
The User starts with 100 credits, and free random cards are given out upon reconnection every 2√n minutes,
where n is the number of free cards already received.

There are 4 ranks of card `common`, `uncommon`, `rare`, `unique`


The Hollomon Game was first devised and implemented by Prof. Johannes Kinder.
# Code Overview
**Main** – file that the code is run from. All the methods are used in main,
and everything is compiled there for readability and design practice.

**ServerConnection** – file that contains all methods used for server
connection and handles the stream from the server. Put into a separate
file for the sake of readability and good design practice. This is also
so we do not have to open more than one socket which would increase the
likelihood of errors being thrown.

**Card** – contains the card class used to format the sort each card, put in
a separate file for good design practice as well as maintainability.


# Key Design Concepts
**Enums** – used for Rank to be able to compare each rank easily, as Strings
do not have order otherwise. An alternative approach would have been to
assign UNIQUE = 4 RARE = 3 etc. and compare the numbers that way, however
this is a less favourable option from a readability and understanding
standpoint.

**Try-Catch and Exceptions** – used to catch IOExceptions to help with the
debugging process, and used try-catch to handle these exception cases.
There’s no real alternative to using try-catch and exceptions.

**HashSet** – used for the auto-trading system to keep track of cardId for
the buy method and keeps track of cardName for the auto-sell method.

**ArrayList** – used in the cardHelper method used to create the format for
the cards when they are displayed. An ArrayList is used here because it
can also be sorted easily which is necessary as we want the cards
displayed by rank.

**Comparable** – comparable interface used to compare the different fields in
the card class, so the system can automatically compare and sort cards.
It does this by using Collections.sort() function from the collection
class.

# Testing Strategy
The methods used to test my code included: testing alterative usernames
and passwords to log on the Hollomon server, on the menu providing
different inputs to what was expected, for the buy and sell features,
providing cardId’s that don’t exist. I also used System.out.println() to
test if particular sections were being run, so I could systematically
test the code.

# Use of AI
AI used – Claude by Anthropic – ver. Claude Sonnet 4.6 -
https://claude.ai
AI was used in this project to help with the debugging process to save
time, I have specifically requested that Claude should never give me a
coded solution and only ever “nudge me in the right direction”. It was
also used to simplify some concepts I found through the Java
documentation if I did not fully understand it and could not understand
it from the previous lectures.


# To run/verify
To compile run `$ javac *.java` then `$ java main`.<br>
**NOTE:** The code will not be able to run as it has to be on a specific university server. This is due to the project being a coursework project.
