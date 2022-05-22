/* Programmed Eliam Ruiz Agosto
   801-18-7235
   Purpose  : This program is the table class which contains the GUI for the rummy game. Most of the game and interactions
              are handled or instantiated within this class. 
   Dictionary of variables: 
                - Many JFrame, JPanel, JLabel variables - used to control the gui
				- boolean Drew - Helps us now if a card has been drawn from deck or stack in a given turn
				- boolean layed - Helps us now if a card has been layed from deck or stack in a given turn
				- boolean auto - Helps us know if the Automatic Play button has been pressed
				- Hand cardsPlayer1 - This hand object is used to manipulate and keep track of the hand of player1 at any time
	            - Hand cardsPlayer2 - This hand object is used to manipulate and keep track of the hand of player2 at any time
				- DefaultListModel p1Hand - Mimics the cardsplayer1 hand moves but allows the changes to be visible in the gui
	            - DefaultListModel p2Hand - Mimics the cardsplayer2 hand moves but allows the changes to be visible in the gui
				- Deck cardDeck - This is a deck object that helps us manage the deck of cards in a game session
	            - Deck stackDeck - This is a deck object that helps us smanage the stack of cards that the players discard\
				- Many local variables for each function and in each class

			  */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/

// }
public class Table extends JFrame implements ActionListener
{
	// variables to handle the gui and some of the game logic
	final static int numDealtCards = 9;
	int turn; 
	JPanel player1;
	JPanel player2;
	JLabel points;
	JLabel gameOver;
	JPanel finished;
	JPanel playerTurn;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	Deck stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;
	JButton automatic;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	DefaultListModel p1Hand;
	DefaultListModel p2Hand;

	Hand cardsPlayer1;
	Hand cardsPlayer2;

	boolean drew;
	boolean layed;
	boolean auto = false;
// function to deal cards to players (nine per player)
	private void deal(Hand cards)
	{
		
		for(int i = 0; i < numDealtCards; i ++){
		    
			cards.addCard((Card)cardDeck.dealCard());
		}
		cards.sort();
	}
// public functions to access and set class variables
	public void SetTurn (int curr) { // setter for turn variable
		 this.turn = curr;
		 this.drew = false;
		 this.layed = false;
	}
	public int GetTurn() { // getter for turn variable
		return this.turn;
	}	
	public boolean OutOfCards() {  // notifier of end of deck
		return this.cardDeck.isEmpty();
	}
	public boolean Player1OutOfCards() {  // notifier of player 1 out of cards
		return this.cardsPlayer1.isEmpty();
	}
	public boolean Player2OutOfCards() {  // notifier of player two out of cards
		return this.cardsPlayer2.isEmpty(); 
	}
	public Hand getPlayer1Cards() {  // getter for player one's cards in hand
		return this.cardsPlayer1;
	}
	public Hand getPlayer2Cards() { // getter for playes two's cards in hand
		return this.cardsPlayer2;
	}
	public boolean getAutoFlag() { // getter for automatric play button pressed flag
		return this.auto;
	}
	// constructor for table object
	public Table()
	{
		// sets the window title
		super("The Card Game of the Century");
        // sets the wondow standard size and layout
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width,dim.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates an empty deck using its default constructor
		cardDeck = new Deck();
        // fills the stack with cards (how many? 13 of each suit? 13(4) = 52 cards)
		for(int i = 0; i < Card.suit.length; i++){
			for(int j = 0; j < Card.rank.length; j++){
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}
		
		// shuffles cards in deck
		cardDeck.shuffle();
		// creates a new empty deck
		stackDeck = new Deck();
        //  creates j panel for top of window
		JPanel top = new JPanel();
        // sets panels per card rank
		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));
	
        //  add panels to teh top of window (J FRAME)
		top.add(setPanels[0]); 
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);
        // j label and panel to notify the current turn in the gui
		points = new JLabel("Player's one Turn");
        playerTurn = new JPanel();
		playerTurn.add(points);
        // j label and panel to notify game over in the gui
		gameOver = new JLabel ("Game over!!");
		finished = new JPanel();
		finished.add(gameOver);
        // adding player turn to player one area i nthe gui         
		player1 = new JPanel();
		player1.add(playerTurn);
		// setting player one area in the gui
		player1.add(top);
		add(player1, BorderLayout.NORTH);
		// j panel to set bottom area in the gui
		JPanel bottom = new JPanel( );
		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);
	//****************************************************************************** */
		//bottom.add(finished);
	//********************************************************************************* */
        // creating j panel for player two
		player2 = new JPanel();
        // adding player two to bottom
		player2.add(bottom);
		add(player2, BorderLayout.SOUTH); 

		// creates buttons for player interactions and adds action listeners for the action performed function to catch them
		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);
		automatic = new JButton("Automatic Play");
		automatic.addActionListener(this);
		

        // creates the player_one hand initial cards
		cardsPlayer1 = new Hand();
		deal(cardsPlayer1);
		cardsPlayer1.sort();
		p1Hand = new DefaultListModel();

		String InitString = "Initial player one: "; // print the initial deck of cards for each player
		for(int i = 0; i < cardsPlayer1.getNumberOfCards(); i++)
		{
			p1Hand.addElement(cardsPlayer1.getCard(i));
			// print the player one first hand 
            InitString += cardsPlayer1.getCard(i); // part of ^
			InitString += ", "; // part of  ^
		}
		System.out.println(InitString); //  up to here 

		p1HandPile = new JList(p1Hand);

		
		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blankStack.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);
		automatic = new JButton("Automatic Play");
		automatic.addActionListener(this);

		

		cardsPlayer2 = new Hand();
		deal(cardsPlayer2);
		cardsPlayer2.sort();
		p2Hand = new DefaultListModel();

		String InitString2 = "Initial player two: "; // print the initial deck of cards for each player
		for(int i = 0; i < cardsPlayer2.getNumberOfCards(); i++)
		{

			p2Hand.addElement(cardsPlayer2.getCard(i));
			InitString2 += cardsPlayer2.getCard(i); // part of
			InitString2 += ", "; // part of 
		}
		System.out.println(InitString2); //  up to here 

		p2HandPile = new JList(p2Hand);

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack, automatic));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);

		//System.out.print(cardsPlayer1.getNumberOfCards());

	}

	public void actionPerformed(ActionEvent e)
	{
		// will try to make it work so it sorts the cards (just by rank? just by suit? the whole suit??)
        

		Object src = e.getSource();
		// this if statement deals with the action corrresponding to the deck button for player one
		if(p1Deck == src  && turn == 1 && drew == false){ 
				Card card = cardDeck.dealCard();
			if (card != null){
					cardsPlayer1.addCard(card);
					cardsPlayer1.sort();
					System.out.print("Player 1" + "\n");
					System.out.println("	Added: " + card);
			}
			if(cardDeck.getSizeOfDeck() == 0)
				deckPile.setIcon(new ImageIcon(Card.directory + "blankDeck.gif"));

            // update the action on screen
			p1Hand.removeAllElements();
			for (int i = 0; i < cardsPlayer1.getNumberOfCards(); i++){
				p1Hand.addElement(cardsPlayer1.getCard(i));
			}
			drew = true;
		}
        // this if statement deals with the action corresponding to the deck button for player two
		if(p2Deck == src  && turn == 2 && drew == false){
			Card card = cardDeck.dealCard();

		if (card != null){
				// p2Hand.addElement(card);
				cardsPlayer2.addCard(card);
				cardsPlayer2.sort();
				System.out.print("Player 2" + "\n");
				System.out.println("	Added: " + card);
				// System.out.println("Player 2 Added: " + card);
		}
		if(cardDeck.getSizeOfDeck() == 0)
			deckPile.setIcon(new ImageIcon(Card.directory + "blankDeck.gif"));

			p2Hand.removeAllElements();
			for (int i = 0; i < cardsPlayer2.getNumberOfCards(); i++){
				p2Hand.addElement(cardsPlayer2.getCard(i));
			}
			drew = true;

	}
        // this if statement deals with the action corresponding to the stack button for player one
		if(p1Stack == src && turn == 1 && drew == false){
			Card card = stackDeck.removeCard();

			if(card != null){
				Card topCard = stackDeck.peek();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blankStack.gif"));
				
				cardsPlayer1.addCard(card);
				cardsPlayer1.sort();
				p1Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer1.getNumberOfCards(); i++){
					p1Hand.addElement(cardsPlayer1.getCard(i));
				}
				drew = true;
		}
	}
    // this if statement deals with the action corresponding to the stack button for player two
		if(p2Stack == src && turn == 2 && drew == false){
			Card card = stackDeck.removeCard();

			if(card != null){
				Card topCard = stackDeck.peek();
				if (topCard != null)
					topOfStack.setIcon(topCard.getCardImage());
				else
					topOfStack.setIcon(new ImageIcon(Card.directory + "blankStack.gif"));
				
				cardsPlayer2.addCard(card);
				cardsPlayer2.sort();
				p2Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer2.getNumberOfCards(); i++){
					p2Hand.addElement(cardsPlayer2.getCard(i));
				}
				drew = true;
		}
	}
    //this if statement deals with the action corresponding to the lay button for player one
		if(p1Lay == src && turn == 1 && layed == false){
			Object [] cards = p1HandPile.getSelectedValues();
			if (cards.length > 0)
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					cardsPlayer1.removeCard(card);
				}
				p1Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer1.getNumberOfCards(); i++){
					p1Hand.addElement(cardsPlayer1.getCard(i));
				}
				layed = true;
				SetTurn(2);
				points.setText("Player two's turn");
		}
        //this if statement deals with the action corresponding to the lay  button for player two
		if(p2Lay == src && turn == 2 && layed == false){
			Object [] cards = p2HandPile.getSelectedValues();
			if (cards.length > 0)
				for(int i = 0; i < cards.length; i++)
				{
					Card card = (Card)cards[i];
					layCard(card);
					cardsPlayer2.removeCard(card);
				}
				p2Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer2.getNumberOfCards(); i++){
					p2Hand.addElement(cardsPlayer2.getCard(i));
				}
				layed = true;
				SetTurn(1);
				points.setText("Player one's turn");
		}
        // this if statement deals with the action corresponding to the layonstack button for player one
		if(p1LayOnStack == src && turn == 1 && layed == false){
			int [] num  = p1HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p1HandPile.getSelectedValue();
				if (obj != null)
				{   cardsPlayer1.removeCard((Card)obj);
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					System.out.println("	Discarded: " + card);
				}
				p1Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer1.getNumberOfCards(); i++){
					p1Hand.addElement(cardsPlayer1.getCard(i));
				}
				layed = true;
				SetTurn(2);
				points.setText("Player two's turn");
			}
			// if no cards are selected and the lay on stack button is pressed discard a random card from player hand
			else if (num.length == 0) 
			{        
				    int cardNums = cardsPlayer1.getNumberOfCards(); // get the number of cards in player hand
					Random rand = new Random(); //instance of random class
					int discCard = rand.nextInt(cardNums); // generate the random value for card to be discarted
                    Card toremove = cardsPlayer1.getCardAt(discCard); // get the element that will be removed from hand
					cardsPlayer1.removeCard(discCard); // remove the element from hand 
                    System.out.println("	Discarded: " + toremove);
					stackDeck.addCard(toremove); // add card removed from player hand to stack in table
					topOfStack.setIcon(toremove.getCardImage()); // add the image for that card

					p1Hand.removeAllElements();
					for (int i = 0; i < cardsPlayer1.getNumberOfCards(); i++){
					p1Hand.addElement(cardsPlayer1.getCard(i));
					}
					layed = true;
					SetTurn(2); 
					points.setText("Player two's turn");


			}
			System.out.print("	Hand now: ");
			for (int i = 0; i < p1Hand.getSize();i++)
			{ // should append to a continous string for each click? need to sort out how to manage turns in table.java
				System.out.print(p1Hand.getElementAt(i));
				System.out.print(", ");
				// System.out.println("hererereerer");
			}
			System.out.println(); 
			// SetTurn(2); ?????????????????
		}
	    // this if statement deals with the action corresponding to the layonstack button for player two
		if(p2LayOnStack == src && turn == 2 && layed == false){
			int [] num  = p2HandPile.getSelectedIndices();
			if (num.length == 1)
			{
				Object obj = p2HandPile.getSelectedValue();
				if (obj != null)
				{
					cardsPlayer2.removeCard((Card) obj);
					Card card = (Card)obj;
					stackDeck.addCard(card);
					topOfStack.setIcon(card.getCardImage());
					System.out.println("	Discarded: " + card);
				}
				p2Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer2.getNumberOfCards(); i++){
					p2Hand.addElement(cardsPlayer2.getCard(i));
				}
				layed = true;
				SetTurn(1);
				points.setText("Player one's turn");
			}
			else if (num.length == 0)
			{
				int cardNums = cardsPlayer2.getNumberOfCards();
				//int cardNums = p2Hand.getSize(); // get the number of cards in player hand
				Random rand = new Random(); //instance of random class
				int discCard = rand.nextInt(cardNums); // generate the random value for card to be discarted
				Card toremove = cardsPlayer2.getCardAt(discCard);
				//Object toremove = p2Hand.getElementAt(discCard); // get the element that will be removed from hand 
				//p2Hand.removeElementAt(discCard); // remove the element from hand
				cardsPlayer2.removeCard(toremove);
                System.out.println("	Discarded: " + toremove);
				Card card = (Card)toremove; // create card object
				stackDeck.addCard(card); // add card removed from payer hand to stack in table
				topOfStack.setIcon(card.getCardImage()); // add the image for that card

				p2Hand.removeAllElements();
				for (int i = 0; i < cardsPlayer2.getNumberOfCards(); i++){
					p2Hand.addElement(cardsPlayer2.getCard(i));
				}
				layed = true;
				SetTurn(1);
				points.setText("Player one's turn");
		    }
			System.out.print("	Hand now: ");
			for (int i = 0; i < p2Hand.getSize();i++)
			{ // should append to a continous string for each click? need to sort out how to manage turns in table.java
				System.out.print(p2Hand.getElementAt(i));
				System.out.print(", ");
				// System.out.println("hererereerer");
			}
			System.out.println();
		}
		if (automatic == src){
             auto = true;
		}
	
	}
// this function defines the way to lay cards
	void layCard(Card card)
	{
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		System.out.println("	Laying " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}

}

class HandPanel extends JPanel
{

	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack, JButton automatic)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
		automatic.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(automatic);
		add(Box.createGlue());
	}
	// added a second constructor of hand panel in order to be able to just have one automatic play button
	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack)
	{
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	
	}

}
class SetPanel extends JPanel
{
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index)
	{
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++){
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}

}
