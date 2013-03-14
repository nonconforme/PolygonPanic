package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

/**
 * Player Class. Used merging Networking and Gameplay
 * 
 * @author S. Clawson-Simons
 * 
 */
public class Player
{
	private int gold = 0;
	private int color;
	private int direction;
	private Base myBase;
	private Upgrades unitUpgrades;

	/*
	 * private boolean isAttackingUnit = false; private boolean isAttackingBase
	 * = false;
	 * 
	 * public boolean isAttackingUnit() { return isAttackingUnit; }
	 * 
	 * public void setAttackingUnit(boolean isAttackingUnit) {
	 * this.isAttackingUnit = isAttackingUnit; }
	 * 
	 * public boolean isAttackingBase() { return isAttackingBase; }
	 * 
	 * public void setAttackingBase(boolean isAttackingBase) {
	 * this.isAttackingBase = isAttackingBase; }
	 */

	/**
	 * Player Constructor
	 * 
	 * @param base
	 *            the Player's Base object
	 * @param unitUpgrades
	 *            the unit upgrades for the player
	 */
	public Player(Base base, Upgrades unitUpgrades)
	{
		myBase = base;
		direction = base.getDirection();
		this.unitUpgrades = unitUpgrades;
	}

	/**
	 * Gets the amount of gold the player has
	 * 
	 * @return the amount of gold the player has
	 */
	public int getGold()
	{
		return gold;
	}

	/**
	 * Sets the amount of gold the player has
	 * 
	 * @param gold
	 *            the amount of gold to give the player
	 */
	public void setGold(int gold)
	{
		this.gold = gold;
	}

	/**
	 * Adds an amount of gold to the player's total
	 * 
	 * @param gold
	 *            the amount of gold to give
	 */
	public void addGold(int gold)
	{
		this.gold += gold;
	}

	/**
	 * Spends gold (on an upgrade)
	 * 
	 * @param gold
	 *            the amount of gold
	 * @return whether or not the transaction is valid
	 */
	public boolean spendGold(int gold)
	{
		if (this.gold - gold < 0)
			return false;
		else
			this.gold -= gold;

		return true;
	}

	/**
	 * Sets the energy level of the player's base
	 * 
	 * @param energy
	 *            the amount of energy for the base to have
	 */
	public void setEnergy(double energy)
	{
		this.myBase.setEnergy(energy);
	}

	/**
	 * Gets the player's Base object
	 * 
	 * @return the player's Base object
	 */
	public Base getMyBase()
	{
		return myBase;
	}

	/**
	 * Sets the player's Base
	 * 
	 * @param myBase
	 *            the base to give the player
	 */
	public void setMyBase(Base myBase)
	{
		this.myBase = myBase;
	}

	/**
	 * Gets the player's color
	 * 
	 * @return the player's color
	 */
	public int getColor()
	{
		return color;
	}

	/**
	 * Sets the player's color
	 * 
	 * @param color
	 *            the player's new color
	 */
	public void setColor(int color)
	{
		this.color = color;
	}

	/**
	 * Gets the direction (which side of the screen) of the player
	 * 
	 * @return the player's direction
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 * Sets the player's direction (which side of the screen)
	 * 
	 * @param direction
	 *            the direction to give the player
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	/**
	 * Gets the player's unit upgrades
	 * 
	 * @return the player's unit upgrades
	 */
	public Upgrades getUnitUpgrades()
	{
		return unitUpgrades;
	}

	/**
	 * Upgrades a player's unit type
	 * 
	 * @param unitID
	 *            the ID of which unit type to upgrade
	 * @return whether or not the upgrade was successful
	 */
	public boolean upgradeUnit(int unitID)
	{
		if (Upgrades.isValidUpgrade(unitID)
				&& unitUpgrades.getUpgrade(unitID) < Upgrades.MAX_LEVEL
				&& spendGold(Unit.COST[unitID]))
		{
			unitUpgrades.upgrade(unitID);
			return true;
		}

		return false;
	}

	/**
	 * Upgrades a player's Base
	 * 
	 * @param upgrade
	 *            the index of which upgrade to apply to the base
	 * @return whether or not the upgrade was successful
	 */
	public boolean upgradeBase(int upgrade)
	{
		if (Upgrades.isValidUpgrade(upgrade)
				&& getMyBase().getUpgrades().getUpgrade(upgrade) < Upgrades.MAX_LEVEL
				&& spendGold(Upgrades.baseUpgradeCost(upgrade)))
		{
			getMyBase().upgrade(upgrade);
			return true;
		}

		return false;
	}
}
