package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Triangle Unit Subclass.
 * 
 * @authors S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Triangle extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5533389774879590464L;

	/**
	 * Triangle Constructor
	 * 
	 * @param rnk
	 *            the rank of Triangle to Spawn
	 * @param dir
	 *            the direction of Triangle
	 */
	public Triangle(int rnk, int dir)
	{
		rank = rnk;
		direction = dir;
		ID = Unit.TRIANGLE;

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
			maxHealth = 100;
			attackSpeed = 10;
			goldValue = 20;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 100;
			attackSpeed = 7;
			goldValue = 16;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 75;
			attackSpeed = 7;
			goldValue = 8;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 75;
			attackSpeed = 5;
			goldValue = 4;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 50;
			attackSpeed = 5;
			goldValue = 2;
		} // end Rookie

		upgradeCost = 50;
		currHealth = maxHealth;
		movementSpeed = 3;

	}// end init()

} // end Triangle
