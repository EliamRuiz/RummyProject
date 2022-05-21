import java.util.*;

public class proj2 extends Table {

	public static void main(String args[])
	{
		
		Table t = new Table();
		t.setVisible(true);
		t.SetTurn(1);


		// trying to make game be played by machine
		while (!(t.OutOfCards()) && !(t.Player1OutOfCards()) && !(t.Player2OutOfCards()))
		{   
			Hand automatic = new Hand();
			// System.out.println(t.GetTurn());
			// automatic.play(t);
		}
		t.SetTurn(0); // doesn't work properlyyyyyyyyyy
		int p1Player = t.getPlayer1Cards().evaluateHand();
		int p2player = t.getPlayer2Cards().evaluateHand();

		System.out.println("Game over!");
		System.out.print("Player 1 has: ");
		System.out.print(p1Player);
		System.out.print(" points.");
		System.out.println();

		System.out.print("Player 2 has: ");
		System.out.print(p2player);
		System.out.print(" points.");
		System.out.println();

		if ((p1Player - p2player) > 0){
			System.out.print("Player 2 wins!");
		}
		else if ((p1Player - p2player) == 0) {
			System.out.print("It's a tie!");
		}
		else {
            System.out.print("Player 1 wins!");
		}
	}

}