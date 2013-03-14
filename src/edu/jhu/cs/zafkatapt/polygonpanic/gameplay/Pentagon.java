package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Pentagon Unit Subclass.
 * 
 * @author S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Pentagon extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1220180147872779653L;

	/**
	 * Pentagon Constructor
	 * 
	 * @param rnk
	 *            the rank of Pentagon to Spawn
	 * @param dir
	 *            the direction of Pentagon
	 */
	public Pentagon(int rnk, int dir)
	{
		rank = rnk;
		direction = dir;
		ID = Unit.PENTAGON;

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

			maxHealth = 150;
			attackSpeed = 15;
			goldValue = 30;

		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{

			maxHealth = 150;
			attackSpeed = 12;
			goldValue = 22;

		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{

			maxHealth = 120;
			attackSpeed = 12;
			goldValue = 16;

		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 120;
			attackSpeed = 10;
			goldValue = 8;

		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 100;
			attackSpeed = 10;
			goldValue = 4;

		} // end Rookie

		currHealth = maxHealth;
		movementSpeed = 4;
		upgradeCost = 125;

	}// end init()

} // end Pentagon
