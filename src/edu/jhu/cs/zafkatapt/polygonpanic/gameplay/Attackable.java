package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

public interface Attackable
{
	/**
	 * Take damage from an attack
	 * 
	 * @param damage
	 *            the amount of damage to take
	 */
	public void takeDamage(double damage);
}
