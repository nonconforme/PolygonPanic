package edu.jhu.cs.zafkatapt.polygonpanic.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Base;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Upgrades;

public class BaseTest
{

	Base test = new Base(1, new Upgrades());

	@Test
	public void testGetCurrHealth()
	{
		assertTrue(test.getCurrHealth() == 10000);
		test.setCurrHealth(5000);
		assertTrue(test.getCurrHealth() == 5000);
	}

	@Test
	public void testGetDirection()
	{
		assertTrue(test.getDirection() == 1);
		test.setDirection(-1);
		assertTrue(test.getDirection() == -1);
	}

	@Test
	public void testGetEnergy()
	{
		assertTrue(test.getEnergy() == 100);
		test.setEnergy(200);
		assertTrue(test.getEnergy() == 200);
	}

	@Test
	public void testGetMaxHealth()
	{
		assertTrue(test.getMaxHealth() == 10000);
		test.setMaxHealth(20000);
		assertTrue(test.getMaxHealth() == 20000);
	}

	@Test
	public void testGetAttackDamage()
	{
		assertTrue(test.getAttackDamage() == 10);
		test.setAttackDamage(20);
		assertTrue(test.getAttackDamage() == 20);
	}

	@Test
	public void testGetAttackSpeed()
	{
		assertTrue(test.getAttackSpeed() == 1);
		test.setAttackSpeed(2);
		assertTrue(test.getAttackSpeed() == 2);
	}

	@Test
	public void testGetAttackRange()
	{
		assertTrue(test.getAttackRange() == 100);
		test.setAttackRange(200);
		assertTrue(test.getAttackRange() == 200);
	}

	@Test
	public void testGetEnergyRate()
	{
		assertTrue(test.getEnergyRate() == 1);
		test.setEnergyRate(2);
		assertTrue(test.getEnergyRate() == 2);
	}

	@Test
	public void testGetEnergyMax()
	{
		assertTrue(test.getEnergyMax() == 500);
		test.setEnergyMax(1000);
		assertTrue(test.getEnergyMax() == 1000);
	}

	@Test
	public void testGetGoldRate()
	{
		assertTrue(test.getGoldRate() == 1);
		test.setGoldRate(2);
		assertTrue(test.getGoldRate() == 2);
	}

}
