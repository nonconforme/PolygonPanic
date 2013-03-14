package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Base (Castle) for a Player. Destroy enemy base to win
 * 
 * @author S. Clawson-Simons
 * 
 */

public final class Base implements Attackable
{

	private int maxHealth; // maximum health
	private double currHealth; // current health
	private int attackRange; // range of attack
	private int attackDamage; // damage dealt per attack
	private int attackSpeed; // speed (rate) of attack
	private int direction; // direction of attack (red team vs blue team)
	private int goldRate;
	private double energy;
	private int energyRate;
	private int energyMax;
	private Upgrades upgrades;

	/**
	 * Base Constructor
	 * 
	 * @param dir
	 *            the direction the base faces (left side of screen or the right
	 *            screen)
	 * @param ups
	 *            Base Upgrades for initialization
	 */
	public Base(int dir, Upgrades ups)
	{
		// UpgradeLevels
		upgrades = ups;

		// Health
		maxHealth = calcMaxHealth();
		currHealth = maxHealth;

		// Attack
		attackRange = calcAttackRange();
		attackDamage = calcAttackDamage();
		attackSpeed = calcAttackSpeed();
		direction = dir;

		// Generating
		energy = 0;
		energyRate = calcRateEnergy();
		energyMax = calcMaxEnergy();

		goldRate = calcRateGold();

	}// end Base();

	/**
	 * Takes attack damage from enemy unit
	 * 
	 * @param damage
	 *            the damage dealt to the base
	 */
	public void takeDamage(double damage)
	{
		currHealth -= damage;
	}

	/**
	 * Upgrades the Base as specified
	 * 
	 * @param upgradeIndex
	 *            the index of which upgrade to apply
	 */
	public void upgrade(int upgradeIndex)
	{
		if (!Upgrades.isValidUpgrade(upgradeIndex))
			throw new IllegalArgumentException("Not a valid upgrade.");
		else
			upgrades.upgrade(upgradeIndex);

		if (upgradeIndex == Upgrades.BASE_HEALTH_MAX_INDEX)
		{
			maxHealth = calcMaxHealth();
			currHealth = maxHealth;
		} else if (upgradeIndex == Upgrades.BASE_ATTACK_DAMAGE_INDEX)
			attackDamage = calcAttackDamage();
		else if (upgradeIndex == Upgrades.BASE_ATTACK_RANGE_INDEX)
			attackRange = calcAttackRange();
		else if (upgradeIndex == Upgrades.BASE_ATTACK_SPEED_INDEX)
			attackSpeed = calcAttackSpeed();
		else if (upgradeIndex == Upgrades.BASE_ENERGY_RATE_INDEX)
			energyRate = calcRateEnergy();
		else if (upgradeIndex == Upgrades.BASE_ENERGY_MAX_INDEX)
			energyMax = calcMaxEnergy();
		else if (upgradeIndex == Upgrades.BASE_GOLD_RATE_INDEX)
			goldRate = calcRateGold();
	}

	/**
	 * getter function for Base's current health
	 * 
	 * @return the base's current health
	 */
	public double getCurrHealth()
	{
		return currHealth;
	}

	/**
	 * setter function for Base's current health
	 * 
	 * @param currHealth
	 *            the value to set the current health
	 */
	public void setCurrHealth(int currHealth)
	{
		this.currHealth = currHealth;
	}

	/**
	 * getter function for Base's direction
	 * 
	 * @return the base's direction
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * setter function for Base's direction
	 * 
	 * @param direction
	 *            the base's direction
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	/**
	 * getter function for Base's current energy
	 * 
	 * @return the base's current energy
	 */
	public double getEnergy()
	{
		return energy;
	}

	/**
	 * setter function for Base's current energy
	 * 
	 * @param energy
	 *            the base's current energy
	 */
	public void setEnergy(double energy)
	{
		this.energy = energy;
	}

	/**
	 * Generates energy for purchasing units
	 * 
	 * @param delta
	 *            the amount of time passed
	 */
	public void generateEnergy(long delta)
	{
		if (energy == energyMax)
			return;

		energy = Math.min(energyMax, energy + ((double) (energyRate))
				* (((double) (delta)) / 1000.0));
	}

	/**
	 * getter function for Base's upgrades
	 * 
	 * @return the upgrade objects of the base's upgrades
	 */
	public Upgrades getUpgrades()
	{
		return upgrades;
	}

	/**
	 * setter function for Base's upgrades
	 * 
	 * @param upgrades
	 *            the upgrade object for the base
	 */
	public void setUpgrades(Upgrades upgrades)
	{
		this.upgrades = upgrades;
	}

	/**
	 * setter function for Base's maximum health
	 * 
	 * @param maxHealth
	 *            the value of the base's maxHealth
	 */
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	/**
	 * setter function for Base's attack range
	 * 
	 * @param attackRange
	 *            the base's attack range
	 */
	public void setAttackRange(int attackRange)
	{
		this.attackRange = attackRange;
	}

	/**
	 * setter function for Base's attack damage
	 * 
	 * @param attackDamage
	 *            the base's attack damage
	 */
	public void setAttackDamage(int attackDamage)
	{
		this.attackDamage = attackDamage;
	}

	/**
	 * setter function for Base's attack speed
	 * 
	 * @param attackSpeed
	 *            the base's attack speed
	 */
	public void setAttackSpeed(int attackSpeed)
	{
		this.attackSpeed = attackSpeed;
	}

	/**
	 * setter function for Base's gold income rate
	 * 
	 * @param goldRate
	 *            the base's gold income rate
	 */
	public void setGoldRate(int goldRate)
	{
		this.goldRate = goldRate;
	}

	/**
	 * setter function for Base's energy generation rate
	 * 
	 * @param energyRate
	 *            the energy generation rate
	 */
	public void setEnergyRate(int energyRate)
	{
		this.energyRate = energyRate;
	}

	/**
	 * setter function for Base's maximum energy limit
	 * 
	 * @param energyMax
	 *            the base's maxumum energy limit
	 */
	public void setEnergyMax(int energyMax)
	{
		this.energyMax = energyMax;
	}

	/**
	 * Shoots a Circle unit at given trajectory
	 * 
	 * @param angle
	 *            the angle at which to shoot the Circle
	 */
	public void attack(int angle)
	{

	}

	/**
	 * calculates the base's maximum health based on upgrade level
	 * 
	 * @return what will become the maximum health
	 */
	private int calcMaxHealth()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_HEALTH_MAX_INDEX))
		{
		case 5:
			return 20000;
		case 4:
			return 17500;
		case 3:
			return 15000;
		case 2:
			return 12500;
		default:
			return 10000;
		}// end switch

	}// end calcAttackDamage

	/**
	 * calculates the base's attack damage based on upgrade level
	 * 
	 * @return the value that will become the base attack damage
	 */
	private int calcAttackDamage()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_ATTACK_DAMAGE_INDEX))
		{
		case 5:
			return 30;
		case 4:
			return 25;
		case 3:
			return 20;
		case 2:
			return 15;
		default:
			return 10;
		}// end switch

	}// end calcAttackDamage

	/**
	 * calculates the base's attack speed based on upgrade level
	 * 
	 * @return the value that will become the base attack speed
	 */
	private int calcAttackSpeed()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_ATTACK_SPEED_INDEX))
		{
		case 5:
			return 5; // fires 5 circles per second
		case 4:
			return 4; // fires 4 circles per second
		case 3:
			return 3; // fires 3 circles per second
		case 2:
			return 2; // fires 2 circles per second
		default:
			return 1; // fires 1 circle per second
		}// end switch

	}// end calcAttackSpeed

	/**
	 * calculates the base's attack range based on upgrade level
	 * 
	 * @return the value that will become the base attack range
	 */
	private int calcAttackRange()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_ATTACK_RANGE_INDEX))
		{
		case 5:
			return 300;
		case 4:
			return 250;
		case 3:
			return 200;
		case 2:
			return 150;
		default:
			return 100;
		}// end switch

	}// end calcAttackRange

	/**
	 * calculates the base's energy generation rate based on upgrade level
	 * 
	 * @return the value that will become the energy generation rate
	 */
	private int calcRateEnergy()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_ENERGY_RATE_INDEX))
		{
		case 5:
			return 15;
		case 4:
			return 12;
		case 3:
			return 9;
		case 2:
			return 6;
		default:
			return 3;
		}// end switch

	}// end calcRateEnergy

	/**
	 * calculates the base's maximum energy limit based on upgrade level
	 * 
	 * @return the value that will become the maximum energy limit
	 */
	private int calcMaxEnergy()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_ENERGY_MAX_INDEX))
		{
		case 5:
			return 2000;
		case 4:
			return 1000;
		case 3:
			return 500;
		case 2:
			return 250;
		default:
			return 100;
		}// end switch

	}// end calcMaxEnergy

	/**
	 * calculates the base's gold income rate based on upgrade level
	 * 
	 * @return the value that will become the gold income limit
	 */
	private int calcRateGold()
	{
		switch (upgrades.getUpgrade(Upgrades.BASE_GOLD_RATE_INDEX))
		{
		case 5:
			return 5;
		case 4:
			return 4;
		case 3:
			return 3;
		case 2:
			return 2;
		default:
			return 1;
		}// end switch

	}// end calcRateEnergy

	/**
	 * Gets the maximum health of the base for the Player. Player can tell how
	 * upgraded it is by this value
	 * 
	 * @return the maximum health of the base
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}

	/**
	 * Gets the attack damage of the base for the Player. Player can tell how
	 * upgraded it is by this value
	 * 
	 * @return the attack damage of the base
	 */
	public int getAttackDamage()
	{
		return attackDamage;
	}

	/**
	 * Gets the attack speed (rate of fire) of the base for the Player. Player
	 * can tell how upgraded it is by this value
	 * 
	 * @return the attack speed of the base
	 */
	public int getAttackSpeed()
	{
		return attackSpeed;
	}

	/**
	 * Gets the attack range of the base for the Player. Player can tell how
	 * upgraded it is by this value
	 * 
	 * @return the attack range of the base
	 */
	public int getAttackRange()
	{
		return attackRange;
	}

	/**
	 * Gets the rate of energy generation of the base for the Player. Player can
	 * tell how upgraded it is by this value
	 * 
	 * @return the rate of energy generation of the base
	 */
	public int getEnergyRate()
	{
		return energyRate;
	}

	/**
	 * Gets the maximum amount of energy (cap of how much a base can have) of
	 * the base for the Player. Player can tell how upgraded it is by this value
	 * 
	 * @return the maximum amount of energy the base
	 */
	public int getEnergyMax()
	{
		return energyMax;
	}

	/**
	 * Gets the rate of gold generation of the base for the Player. Player can
	 * tell how upgraded it is by this value
	 * 
	 * @return the rate of gold generation of the base
	 */
	public int getGoldRate()
	{
		return goldRate;
	}

	/**
	 * Spends energy to create Units
	 * 
	 * @param cost
	 *            the cost of the energy to spend
	 * @return whether or not the transaction was successful (enough energy to
	 *         spend)
	 */
	public boolean spendEnergy(double cost)
	{

		if (cost <= energy)
		{
			energy -= cost;
			return true;
		} else
			return false;
	}

} // end Base
