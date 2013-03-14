package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

import java.util.HashMap;
import java.util.Map;

public class Upgrades
{

	public static final int MAX_LEVEL = 5;

	// Base Upgrades
	public static final int BASE_ATTACK_RANGE_INDEX = 1;
	public static final int BASE_ATTACK_DAMAGE_INDEX = 2;
	public static final int BASE_ATTACK_SPEED_INDEX = 3;
	public static final int BASE_ENERGY_RATE_INDEX = 4;
	public static final int BASE_ENERGY_MAX_INDEX = 5;
	public static final int BASE_GOLD_RATE_INDEX = 6;
	public static final int BASE_HEALTH_MAX_INDEX = 7;

	// Unit Upgrades will use the Unit ID as index values (the static values in
	// Unit)

	private Map<Integer, Integer> upgradeLevels = new HashMap<Integer, Integer>();

	public Upgrades()
	{
		for (int i = 1; i <= 7; i++)
		{
			upgradeLevels.put(i, 1);
		}
	}

	public Upgrades(int[] values) throws IllegalArgumentException
	{
		if (values.length != 7)
			throw new IllegalArgumentException("Improper array size.");

		for (int i = 0; i < values.length; i++)
		{
			upgradeLevels.put(i + 1, values[i]);
		}
	}

	public int getUpgrade(int index) throws IllegalArgumentException
	{
		if (!upgradeLevels.containsKey(index))
			throw new IllegalArgumentException("Invalid index.");

		return upgradeLevels.get(index);
	}

	public void upgrade(int index) throws IllegalArgumentException
	{
		if (!upgradeLevels.containsKey(index))
			throw new IllegalArgumentException("Invalid index.");
		if (upgradeLevels.get(index) == MAX_LEVEL)
			throw new IllegalArgumentException("Already at max upgrade level.");

		upgradeLevels.put(index, upgradeLevels.get(index) + 1);
	}

	public static boolean isValidUpgrade(int upgrade)
	{
		return (upgrade >= 1 && upgrade <= 7);
	}

	static public int baseUpgradeCost(int upgrade)
			throws IllegalArgumentException
	{
		if (!isValidUpgrade(upgrade))
			throw new IllegalArgumentException("Invalid index.");

		if (upgrade == BASE_ATTACK_RANGE_INDEX)
			return 10;

		else if (upgrade == BASE_ATTACK_DAMAGE_INDEX)
			return 10;

		else if (upgrade == BASE_ATTACK_SPEED_INDEX)
			return 10;

		else if (upgrade == BASE_ENERGY_RATE_INDEX)
			return 10;

		else if (upgrade == BASE_ENERGY_MAX_INDEX)
			return 10;

		else if (upgrade == BASE_GOLD_RATE_INDEX)
			return 10;

		else
			// BASE_HEALTH_MAX_INDEX
			return 10;
	}
}
