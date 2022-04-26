import java.util.*;

public class proj2 extends Table {

	public static void main(String args[])
	{
		Table t = new Table();
		t.setVisible(true);
		t.SetTurn(1);


		// trying to make game be played by machine
		while (!t.cardDeck.isEmpty())
		{   
			Hand automatic = new Hand();
			// System.out.println(t.GetTurn());
			automatic.play(t);
		}
		System.out.println("Game over!");
	}

}