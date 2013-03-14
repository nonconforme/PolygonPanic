package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

public enum Spell
{
	HORROR, GRAV_WELL, QUAKE;

	static public long getCoolDown(Spell spell)
	{
		if (spell == HORROR)
			return 15;

		if (spell == GRAV_WELL)
			return 15;

		if (spell == QUAKE)
			return 30;

		return -1;
	}

	static public double getEnergyCost(Spell spell)
	{
		if (spell == HORROR)
			return 100.0;

		if (spell == GRAV_WELL)
			return 75.0;

		if (spell == QUAKE)
			return 75.0;

		return -1.0;
	}
}