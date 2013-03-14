package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Parallel Unit Subclass.
 * 
 * @author S. Clawson-Simons and M. Zaccardo
 * 
 */
public class Parallelogram extends Unit
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7231932407530084088L;

	/**
	 * Parallelogram Constructor
	 * 
	 * @param rnk
	 *            the rank of Parallelogram to Spawn
	 * @param dir
	 *            the direction of Parallelogram
	 */
	public Parallelogram(int rnk, int dir)
	{
		rank = rnk;
		direction = dir;
		ID = Unit.PARALLELOGRAM;

		init();
		determinePosition(); // set starting coordinates

		isAlive = true;

		/*
		 * protected int maxHealth; // maximum health of the unit protected int
		 * currHealth; // current health of the unit protected int attackDamage;
		 * // the damage dealt by this unit per attack protected int
		 * attackSpeed; // rate of attack protected int movementSpeed; //
		 * movement speed of this unit protected int direction; // the direction
		 * this unit moves (red team vs blue team) protected int rank; // the
		 * rank of the unit (in terms of upgrades) protected boolean isAlive; //
		 * whether this unit is alive or not protected int goldValue; // how
		 * much this unit pays off when killed protected int ID; // ID of which
		 * type of unit this is protected int position; // The position of the
		 * unit on the screen
		 */

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
			goldValue = 24;
		} // end Elite

		// Veteran 2
		else if (rank == 4)
		{
			maxHealth = 100;
			attackSpeed = 7;
			goldValue = 18;
		} // end Vet 2

		// Veteran
		else if (rank == 3)
		{
			maxHealth = 75;
			attackSpeed = 7;
			goldValue = 12;
		} // end Vet

		// Rookie 2
		else if (rank == 2)
		{
			maxHealth = 75;
			attackSpeed = 6;
			goldValue = 6;
		} // end Rookie 2

		// Rookie
		else
		{
			maxHealth = 60;
			attackSpeed = 6;
			goldValue = 3;
		} // end Rookie

		movementSpeed = 5;
		currHealth = maxHealth;
		upgradeCost = 75;

	}// end init()

} // end Parallelogram
