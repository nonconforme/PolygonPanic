package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

import java.io.Serializable;

/**
 * Superclass for all fighting units. An Unit is never created; only it's
 * children
 * 
 * @author S. Clawson-Simons
 * 
 */
public abstract class Unit implements Attackable, Serializable
{
	private static final long serialVersionUID = -394620046792592306L;

	public static final int[] COST = { 0, 10, 50, 50, 50, 100, 250, 500 };

	public static final int TRIANGLE = 1;
	public static final int SQUARE = 2;
	public static final int PARALLELOGRAM = 3;
	public static final int TRAPEZOID = 4;
	public static final int PENTAGON = 5;
	public static final int STAR = 6;
	public static final int Z = 7; // ZAFKATAPT's Z

	protected int maxHealth; // maximum health of the unit
	protected double currHealth; // current health of the unit
	protected int attackSpeed; // rate of attack
	protected int movementSpeed; // movement speed of this unit
	protected int direction; // the direction this unit moves (red team vs blue
								// team)
	protected int rank; // the rank of the unit (in terms of upgrades)
	protected boolean isAlive; // whether this unit is alive or not
	protected int goldValue; // how much gold this unit pays off when killed
	protected int purchaseCost; // how much energy this unit costs to buy
	protected int upgradeCost; // how much gold this unit type costs to upgrade
	protected int ID; // ID of which type of unit this is
	protected double position; // The position of the unit on the screen

	protected boolean isAttacking = false;

	/**
	 * Move the unit along the screen
	 * 
	 * @param delta
	 *            the change in time (in milliseconds)
	 */
	public void move(long delta)
	{
		// this.position+= 10.0*this.direction;
		// System.out.println(this.position);
		position += ((double) (direction)) * ((double) (movementSpeed))
				* (((double) (delta)) / 1000.0);
	}// end move()

	/**
	 * Gets the position the unit would be in given time
	 * 
	 * @param delta
	 *            the change in time (in milliseconds)
	 * @return the position of the unit
	 */
	public double getMovementPosition(long delta)
	{
		return position + ((double) (direction)) * ((double) (movementSpeed))
				* (((double) (delta)) / 1000.0);
	}

	/**
	 * Sets the position of the unit
	 * 
	 * @param position
	 *            the given position
	 */
	public void setPosition(double position)
	{
		this.position = position;
	}

	/**
	 * Gets the position of the unit
	 * 
	 * @return the position of the unit
	 */
	public double getPosition()
	{
		return position;
	}

	/**
	 * Determines the starting position of a unit (left or right)
	 */
	public void determinePosition()
	{
		if (direction == 1)
			position = 0;
		else if (direction == -1)
			position = 200;
	}

	/**
	 * Checks whether or not the unit is attacking
	 * 
	 * @return whether or not the unit is attacking something
	 */
	public boolean isAttacking()
	{
		return isAttacking;
	}

	/**
	 * Set whether or not the unit is attacking something
	 * 
	 * @param isAttacking
	 *            whether or not the unit is attacking something
	 */
	public void setAttacking(boolean isAttacking)
	{
		this.isAttacking = isAttacking;
	}

	/**
	 * Attacks the enemy Unit by dealing damage to it.
	 * 
	 * @param enemy
	 *            the enemy to attack
	 */
	public void attack(Attackable enemy, long delta)
	{
		enemy.takeDamage(((double) (attackSpeed)) * ((double) (delta)) / 1000.0);
	}// end attack

	/**
	 * Takes damage that is dealt to this Unit
	 * 
	 * @param damage
	 *            the damage inflicted
	 */
	public void takeDamage(double damage)
	{
		currHealth = Math.max(currHealth - damage, 0.0);

		if (currHealth == 0.0)
			isAlive = false;
	}// end takeDamage

	/**
	 * Checks if this unit is Alive or dead
	 * 
	 * @return true if Unit is still alive, false if killed
	 */
	public boolean isAlive()
	{
		return isAlive;
	}// end isAlive

	/**
	 * Gets the maximum health of the unit
	 * 
	 * @return the maximum health
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * Sets the maximum health of the unit
	 * 
	 * @param maxHealth
	 *            the new maximum health
	 */
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	/**
	 * Gets the current health of the unit
	 * 
	 * @return the current health of the unit
	 */
	public double getCurrHealth()
	{
		return currHealth;
	}

	/**
	 * Sets the current health of the unit
	 * 
	 * @param currHealth
	 *            the new current health
	 */
	public void setCurrHealth(double currHealth)
	{
		this.currHealth = currHealth;
	}

	/**
	 * Gets the attack speed of the unit
	 * 
	 * @return the attack speed
	 */
	public int getAttackSpeed()
	{
		return attackSpeed;
	}

	/**
	 * Sets the attack speed of the unit
	 * 
	 * @param attackSpeed
	 *            the new attack speed
	 */
	public void setAttackSpeed(int attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}

	/**
	 * Gets the movement speed of the unit
	 * 
	 * @return the movement speed of the unit
	 */
	public int getMovementSpeed()
	{
		return movementSpeed;
	}

	/**
	 * Sets the movement speed of the unit
	 * 
	 * @param movementSpeed
	 *            the new movement speed of the unit
	 */
	public void setMovementSpeed(int movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}

	/**
	 * Gets the direction of the unit
	 * 
	 * @return the direction of the unit
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * Sets the direction of the unit
	 * 
	 * @param direction
	 *            the new direction of the unit
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	/**
	 * Get the rank of the unit
	 * 
	 * @return the rank of the unit
	 */
	public int getRank()
	{
		return rank;
	}

	/**
	 * Set the rank of the unit
	 * 
	 * @param rank
	 *            the new rank of the unit
	 */
	public void setRank(int rank)
	{
		this.rank = rank;
	}

	/**
	 * Sets whether or not this unit is alive
	 * 
	 * @param isAlive
	 *            whether or not this unit is alive
	 */
	public void setAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}

	/**
	 * Gets the gold value of this unit if killed for the enemy to collect
	 * 
	 * @return the gold value of this unit
	 */
	public int getGoldValue()
	{
		return goldValue;
	}// end goldValue

	/**
	 * Sets the gold value for this unit
	 * 
	 * @param goldValue
	 *            the new gold value of this unit
	 */
	public void setGoldValue(int goldValue)
	{
		this.goldValue = goldValue;
	}

	/**
	 * Gets the ID of this unit
	 * 
	 * @return the ID of this unit
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * Gets the cost to upgrade this unit type to a higher rank
	 * 
	 * @return the upgrade cost
	 */
	public int getUpgradeCost()
	{
		return upgradeCost;
	}

	/**
	 * Sets the cost to upgrade this unit type to a higher rank
	 * 
	 * @param upgradeCost
	 *            the new upgrade cost
	 */
	public void setUpgradeCost(int upgradeCost)
	{
		this.upgradeCost = upgradeCost;
	}

	/*
	 * public void setID(int iD) { ID = iD; }
	 */

	/**
	 * Checks whether or not this unit is a valid unit type
	 */
	static public boolean isValidUnitID(int unitID)
	{
		return (unitID >= 1 && unitID <= 7);
	}

	/**
	 * Compares two units to see if they are the same
	 * 
	 * @param u
	 *            an unit for comparison
	 * @return whether or not these units are the same
	 */
	public boolean equals(Unit u)
	{
		if (u.getID() == ID && u.getRank() == rank
				&& u.getDirection() == direction && u.getPosition() == position
				&& u.getCurrHealth() == currHealth)
		{
			return true;
		}

		return false;
	} // end equals
}// end Unit
