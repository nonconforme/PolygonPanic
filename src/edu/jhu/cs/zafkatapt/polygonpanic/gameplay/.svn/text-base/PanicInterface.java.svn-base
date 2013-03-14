package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

import java.util.List;
import java.util.Queue;

public interface PanicInterface
{
	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            the listener to be added
	 */
	void addListener(PanicModelListener listener);

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the listener to be removed
	 */
	void removeListener(PanicModelListener listener);

	/**
	 * Casts a spell
	 * 
	 * @param player
	 *            the player to cast a spell
	 * @param spell
	 *            the spell
	 * @return <code>true</code> if the spell is cast and <code>false</code>
	 *         otherwise
	 */
	boolean castSpell(int player, Spell spell);

	/**
	 * Purchase a unit
	 * 
	 * @param player
	 *            the player to purchase a unit
	 * @param unitID
	 *            the unit ID
	 * @return <code>true</code> if the unit is purchased and <code>false</code>
	 *         otherwise
	 */
	boolean purchaseUnit(int player, int unitID);

	/**
	 * Purchase a unit upgrade
	 * 
	 * @param player
	 *            the player to purchase a unit upgrade
	 * @param unitID
	 *            the unit ID
	 * @return <code>true</code> if the unit upgrade is purchased
	 *         <code>false</code> otherwise
	 */
	boolean purchaseUnitUpgrade(int player, int unitID);

	/**
	 * Purchase a base upgrade
	 * 
	 * @param player
	 *            the player to purchase a base upgrade
	 * @param upgrade
	 *            the base upgrade index
	 * @return <code>true</code> if the base upgrade is purchased
	 *         <code>false</code> otherwise
	 */
	boolean purchaseBaseUpgrade(int player, int upgrade);

	/**
	 * Runs the game logic for one time step
	 * 
	 * @return the updated list of units
	 */
	public List<Unit> update();

	/**
	 * Returns a list of locations where units have died since this method was
	 * called last
	 * 
	 * @return a list of locations where units have died since this method was
	 *         called last
	 */
	public List<Double> getUnitDeathLocatons();

	/**
	 * Returns the locations and health values for the two teams' front lines
	 * 
	 * @return the locations and health values for the two teams' front lines
	 */
	double[] getFrontLineData();

	/**
	 * Returns a list of player 1's units
	 * 
	 * @return a list of player 1's units
	 */
	public List<Unit> getP1Units();

	/**
	 * Returns a list of player 2's units
	 * 
	 * @return a list of player 2's units
	 */
	public List<Unit> getP2Units();

	/**
	 * Sets player 1's units
	 * 
	 * @param p1Units
	 *            player 1's units
	 */
	public void setP1Units(List<Unit> p1Units);

	/**
	 * Sets player 2's units
	 * 
	 * @param p2Units
	 *            player 2's units
	 */
	public void setP2Units(List<Unit> p2Units);

	/**
	 * Gets player 1's front line
	 * 
	 * @return player 1's front line
	 */
	public Queue<Unit> getP1FrontLine();

	/**
	 * Gets player 2's front line
	 * 
	 * @return player 1's front line
	 */
	public Queue<Unit> getP2FrontLine();

	/**
	 * Sets player 1's front line
	 * 
	 * @param p1FrontLine
	 *            the front line
	 */
	public void setP1FrontLine(Queue<Unit> p1FrontLine);

	/**
	 * Sets player 2's front line
	 * 
	 * @param p2FrontLine
	 */
	public void setP2FrontLine(Queue<Unit> p2FrontLine);

	/**
	 * Get's the player's gold
	 * 
	 * @param player
	 *            the player
	 * @return the player's gold
	 */
	public int getPlayerGold(int player);

	/**
	 * Gets the player's energy
	 * 
	 * @param player
	 *            the player
	 * @return the player's energy
	 */
	public double getPlayerEnergy(int player);

	/**
	 * Gets the red base's health
	 * 
	 * @return the red base's health
	 */
	public double getRedBaseHealth();

	/**
	 * Gets the blue base's health
	 * 
	 * @return the blue base's health
	 */
	public double getBlueBaseHealth();
}
