/* Programmed Eliam Ruiz AGosto
   801-18-7235
   Purpose  : This program contains the main function that invokes and instantiates the table object in which 
              the rummy game events occur. This was created for ccom4029 class.
   Dictionary of variables: 
              Table t - esta varaible es un objeto de la calse Table en la cual ocurren todos los eventos del juego.
              Hand automatic - Object of class hand used to call function automatic that allows the ga,e to be
			                   played by the computer
			  p1Player - it represents the points a player obtained in his/her last hand. it is
			             an integer used to determine the game winner
			  p2Player - it represents the points a player obtained in his/her last hand. it is
			             an integer used to determine the game winner
			  Various JPanel, JFrame amd JButton variables - control the gui 

			  */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.*;
import javax.swing.*;

public class proj2 extends Table {

	public static void main(String args[])
	{
		
		Table t = new Table();  // table object instance
		t.setVisible(true); 
		t.SetTurn(1);


		// While loop that helps computer play with itself
		while (!(t.OutOfCards()) && !(t.Player1OutOfCards()) && !(t.Player2OutOfCards()))
		{   
			Hand automatic = new Hand();
            if (t.getAutoFlag() == true){
			automatic.play(t);
			}
		}

		t.SetTurn(0); // sets turn to no player so that the game can't be played anymore
		int p1Player = t.getPlayer1Cards().evaluateHand();  // store the values of the last hand of player 1
		int p2player = t.getPlayer2Cards().evaluateHand();  // store the values of the last hand of player 1

		// print to terminal to summarize the game
		System.out.println("Game over!");
		System.out.print("Player 1 has: ");
		System.out.print(p1Player);
		System.out.print(" points.");
		System.out.println();

		// print to terminal to summarize the game 
		System.out.print("Player 2 has: ");
		System.out.print(p2player);
		System.out.print(" points.");
		System.out.println();

		// if statements that determine and announce the game winner in terminal
		if ((p1Player - p2player) > 0){
			System.out.print("Player 2 wins!");
		}
		else if ((p1Player - p2player) == 0) {
			System.out.print("It's a tie!");
		}
		else {
            System.out.print("Player 1 wins!");
		}
	
    // variables and code for connection with the gui, specifically to announce winner and game over 
	JFrame frame = new JFrame();
    frame.setSize(600,450);
	frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel template;
	template = new JPanel(new GridLayout(3,1));
	JLabel player1points;
	JLabel player2points;
	JLabel winner;
	player1points = new JLabel("Player 1 got " + p1Player + " points.");
	player2points = new JLabel("Player 2 got " + p2player + " points.");
	player1points.setFont(new Font("Serif", Font.BOLD, 20));
	player2points.setFont(new Font("Serif", Font.BOLD, 20));
	template.add(player1points);
	template.add(player2points);
    // if statement that decides the winner and displays it on gui with a new announcement window
	if ((p1Player - p2player) > 0){
		winner = new JLabel("Player two is the winner!");
	}
	else if ((p1Player - p2player) == 0) {
		winner = new JLabel("Everyone wins, it's a tie!");
	}
	else {
		winner = new JLabel("Player one is the winner!");
	}
	
	winner.setFont(new Font("Serif", Font.BOLD, 20));
	template.add(winner);
	frame.add(template);

    frame.setTitle("The game is over");
    frame.setVisible(true);

	}
}