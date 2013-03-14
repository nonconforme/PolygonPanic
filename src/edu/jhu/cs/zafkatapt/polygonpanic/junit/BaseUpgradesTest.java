package edu.jhu.cs.zafkatapt.polygonpanic.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Upgrades;

public class BaseUpgradesTest
{
	@Test
	public void testBaseUpgrades()
	{
		Upgrades bu = new Upgrades();
		assertNotNull(bu);
	}

	@Test
	public void testBaseUpgradesIntArray()
	{
		Upgrades bu = new Upgrades(new int[] { 1, 1, 1, 1, 1, 1, 1 });
		assertNotNull(bu);
	}

	@Test
	public void testGetUpgrade()
	{
		Upgrades bu = new Upgrades();
		int level = bu.getUpgrade(Upgrades.BASE_ATTACK_RANGE_INDEX);
		bu.upgrade(Upgrades.BASE_ATTACK_RANGE_INDEX);
		assertFalse(bu.getUpgrade(Upgrades.BASE_ATTACK_RANGE_INDEX) == level);
	}
}
