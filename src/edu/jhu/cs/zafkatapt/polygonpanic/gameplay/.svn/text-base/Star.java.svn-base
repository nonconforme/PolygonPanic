package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Star Unit Subclass.
 * 
 * @authors S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Star extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772475639565118133L;

	/**
	 * Star Constructor
	 * 
	 * @param rnk
	 *            the rank of Star to Spawn
	 * @param dir
	 *            the direction of Star
	 */
	public Star(int rnk, int dir)
	{
		rank = rnk;
		direction = dir;
		ID = Unit.STAR;

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
			maxHealth = 170;
			attackSpeed = 17;
			goldValue = 35;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 170;
			attackSpeed = 13;
			goldValue = 25;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 130;
			attackSpeed = 13;
			goldValue = 20;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 130;
			attackSpeed = 11;
			goldValue = 10;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 110;
			attackSpeed = 11;
			goldValue = 5;
		} // end Rookie

		currHealth = maxHealth;
		upgradeCost = 200;
		movementSpeed = 5;

	}// end init()
}
