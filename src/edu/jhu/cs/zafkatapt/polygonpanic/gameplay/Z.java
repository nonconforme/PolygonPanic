package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Z (for ZAFKATAPT) Unit Subclass.
 * 
 * @authors S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Z extends Unit
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3052366385219915503L;

	/**
	 * Z Constructor
	 * 
	 * @param rnk
	 *            the rank of Z to Spawn
	 * @param dir
	 *            the direction of Z
	 */
	public Z(int rank, int direction)
	{
		this.rank = rank;
		this.direction = direction;
		ID = Unit.Z;

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
			maxHealth = 250;
			goldValue = 50;
			attackSpeed = 25;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 220;
			goldValue = 40;
			attackSpeed = 18;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 180;
			goldValue = 30;
			attackSpeed = 18;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 180;
			goldValue = 20;
			attackSpeed = 15;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 150;
			goldValue = 10;
			attackSpeed = 15;
		} // end Rookie 1

		movementSpeed = 5;
		currHealth = maxHealth;
		upgradeCost = 500;

	}// end init()

}
