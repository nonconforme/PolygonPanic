package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Trapezoid Unit Subclass.
 * 
 * @authors S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Trapezoid extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 679468581340596702L;

	/**
	 * Trapezoid Constructor
	 * 
	 * @param rnk
	 *            the rank of Trapezoid to Spawn
	 * @param dir
	 *            the direction of Trapezoid
	 */
	public Trapezoid(int rank, int direction)
	{
		this.rank = rank;
		this.direction = direction;
		ID = Unit.TRAPEZOID;

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
			maxHealth = 140;
			attackSpeed = 14;
			goldValue = 24;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 140;
			attackSpeed = 11;
			goldValue = 18;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 110;
			attackSpeed = 11;
			goldValue = 12;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 110;
			attackSpeed = 9;
			goldValue = 6;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 90;
			attackSpeed = 9;
			goldValue = 3;
		} // end Rookie 1

		currHealth = maxHealth;
		movementSpeed = 1;
		upgradeCost = 75;

	}// end init()

}
