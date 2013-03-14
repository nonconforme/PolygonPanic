package edu.jhu.cs.zafkatapt.polygonpanic.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Base;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Upgrades;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Player;

public class PlayerTest
{
	Player player;

	@Test
	public void testPlayer()
	{
		player = new Player(new Base(1, new Upgrades()), new Upgrades());
		assertNotNull(player);
	}

	@Test
	public void testGetGold()
	{
		player = new Player(new Base(1, new Upgrades()), new Upgrades());
		int gold = player.getGold();
		player.setGold(gold + 1);
		assertFalse(player.getGold() == gold);
	}

	@Test
	public void testGetColor()
	{
		player = new Player(new Base(1, new Upgrades()), new Upgrades());
		int color = player.getColor();
		player.setGold(color + 1);
		assertFalse(player.getColor() == color);
	}

	@Test
	public void testGetDirection()
	{
		player = new Player(new Base(1, new Upgrades()), new Upgrades());
		int direction = player.getDirection();
		player.setGold(direction + 1);
		assertFalse(player.getDirection() == direction);
	}
}
