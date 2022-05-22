Project name       : Rummy 
Class              : CCOM4029 High Level Languagues 
Programmer         : Eliam Ruiz Agosto  801-18-7235
Purpose of Program : This program is a game of Rummy with custom rules dictated by professor Ordoñez. 

How to run the program?

    The program should be run in a terminal with java se 13 or newer with SDK 18 or newer. The commands to properly run the program is :
        javac proj2.java 
        java proj2.java > output.txt 
    This commands will run the program, make the gui appear and also store the game session logs in a file saved in the project directory called output.txt. 

How to use the gui?

    The gui consists of two main columns of 4 buttons. One labeled for player one and the other for player two. The Stack button allows a player to grab a card from the stack of discarded cards if one is available. The deck button allows a player to get a card from the play sassion deck of cards. The lay button allows a player to lay on the table a group of cards he selected or a random one. The LayOnStack button allows a player to discard a card in the stack. Below each column lie the list of card thta each player has in the current hand. Below the column for player two there is a button for a very simple draw and discard automatic function from which the computer can have a game session with itself. Above the column for player one there is a line of text that indictes the current turn (the player to ehich t corresponds to) and it will interactively change with the play session. In the middle top there is the stack of cards and below it the deck of cards. 

How can you win?

    A player wins when he manages to get a hand of empty cards. Which means he has zero points (the player with least points wins teh game). Or if the deck runs out of cards then the player with the least amount of points on hand wins.The GUI will let the players know when this happens by poping a window with the player points and winner (or tie) announcement in the middle of the screen. When the announcement window is closed the GUI finishes. 

Files that are part of the program:
    Card.java - A class for the card    object which is the object manipulated in the game.
    CardInterface.java - an interface for card methods
    Deck.java - This is a clas for the object used to manipulate a deck. Certain amount of card that can be drawn from.
    DeckInterface.java -  an interface for deck methods
    Hand.java -  a class for object of kind hand that allows us to manage and track the cards in the hands throught a game session.
    HandInterface.java - Interface for hand methods
    Mystack,java - a class inplementimg a object of type stack to manage the discarded cards in the layonstack option.
    proj2.java - file that contains the main method and from which the game is initiated and run. 
    set.java - a class that defines objects of type set to hekp count for points, sort and identify groups of cards.
    Table.java - this fiel contains teh gui and the definitions for how the program shpould behave upon user interaction with it. 
    README.md - it is the file you arte reading and contains a description of the program.  
    cards folder - contains all the images for the cards in the gui