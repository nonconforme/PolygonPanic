package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Square Unit Subclass.
 * 
 * @authors S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Square extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5067143142652213171L;

	/**
	 * Square Constructor
	 * 
	 * @param rnk
	 *            the rank of Square to Spawn
	 * @param dir
	 *            the direction of Square
	 */
	public Square(int rank, int direction)
	{
		this.rank = rank;
		this.direction = direction;
		ID = Unit.SQUARE;

		init();
		determinePosition(); // set starting coordinates

		isAlive = true;
	}

	/**
	 * Private, Internal initialization function -- accounds for rank
	 */
	private void init()
	{
		if (rank > 5 || rank < 1)
		{
			rank = 1;
		}

		// Elite
		if (rank == 5)
		{
			maxHealth = 120;
			attackSpeed = 12;
			goldValue = 24;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 120;
			attackSpeed = 9;
			goldValue = 18;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 90;
			attackSpeed = 9;
			goldValue = 12;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 90;
			attackSpeed = 7;
			goldValue = 6;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 70;
			attackSpeed = 7;
			goldValue = 3;
		} // end Rookie 1

		upgradeCost = 75;
		currHealth = maxHealth;
		movementSpeed = 3;

	}// end init()
}
