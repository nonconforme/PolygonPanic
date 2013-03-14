package edu.jhu.cs.zafkatapt.polygonpanic.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Star;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Unit;

public class UnitTest
{
	Unit unit = new Star(1, 1);

	@Test
	public void testMoveLong()
	{
		double position = unit.getPosition();
		unit.move(1);
		assertFalse(position == unit.getPosition());
	}

	@Test
	public void testMoveDouble()
	{
		double position = unit.getPosition();
		unit.setPosition(8.88);
		assertFalse(position == unit.getPosition());
	}

	@Test
	public void testGetMovementPosition()
	{
		assertFalse(unit.getPosition() == unit.getMovementPosition(1));
	}

	@Test
	public void testGetPosition()
	{
		unit.setPosition(88.88);
		double position = unit.getPosition();
		unit.setPosition(77.77);
		assertFalse(unit.getPosition() == position);
	}

	@Test
	public void testDeterminePosition()
	{
		unit = new Star(1, -1);
		assertTrue(unit.getPosition() == 200);
		unit = new Star(1, 1);
		assertTrue(unit.getPosition() == 0);
	}

	@Test
	public void testTakeDamage()
	{
		double currHealth = unit.getCurrHealth();
		unit.takeDamage(10);
		assertFalse(unit.getCurrHealth() == currHealth);
	}

	@Test
	public void testIsAlive()
	{
		unit = new Star(1, 1);
		assertTrue(unit.isAlive());
		unit.takeDamage(Integer.MAX_VALUE);
		assertFalse(unit.isAlive());
	}

	@Test
	public void testGetMaxHealth()
	{
		unit = new Star(1, 1);
		int maxHealth = unit.getMaxHealth();
		unit.setMaxHealth(Integer.MAX_VALUE);
		assertFalse(unit.getMaxHealth() == maxHealth);
	}

	@Test
	public void testGetCurrHealth()
	{
		unit = new Star(1, 1);
		int currHealth = unit.getMaxHealth();
		unit.setCurrHealth(Integer.MAX_VALUE);
		assertFalse(unit.getMaxHealth() == currHealth);
	}

	@Test
	public void testGetAttackSpeed()
	{
		int attackSpeed = unit.getAttackSpeed();
		unit.setAttackSpeed(attackSpeed - 1);
		assertFalse(unit.getAttackSpeed() == attackSpeed);
	}

	@Test
	public void testGetMovementSpeed()
	{
		int movementSpeed = unit.getMovementSpeed();
		unit.setMovementSpeed(movementSpeed - 1);
		assertFalse(unit.getMovementSpeed() == movementSpeed);
	}

	@Test
	public void testGetDirection()
	{
		int direction = unit.getDirection();
		unit.setDirection(direction - 1);
		assertFalse(unit.getDirection() == direction);
	}

	@Test
	public void testGetRank()
	{
		int rank = unit.getRank();
		unit.setRank(rank - 1);
		assertFalse(unit.getRank() == rank);
	}

	@Test
	public void testGetGoldValue()
	{
		int goldValue = unit.getGoldValue();
		unit.setRank(goldValue - 1);
		assertFalse(unit.getGoldValue() == goldValue);
	}

	/*
	 * @Test public void testGetID() { int ID = unit.getID(); unit.setID(ID -
	 * 1); assertFalse(unit.getID() == ID); }
	 */
}
