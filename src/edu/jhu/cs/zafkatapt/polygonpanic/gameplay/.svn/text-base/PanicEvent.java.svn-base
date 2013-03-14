package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

public class PanicEvent
{
	private boolean unitDied, startAttack, stopAttack;
	private int gameOver;

	/**
	 * Constructor
	 * 
	 * @param unitDied
	 *            has a unit died
	 * @param startAttack
	 *            has an attack started
	 * @param stopAttack
	 *            has an attack stopped
	 * @param isGameOver
	 *            returns 0 if the game has not ended, 1 if player 1 has won, or
	 *            2 if player 2 has won
	 */
	public PanicEvent(boolean unitDied, boolean startAttack,
			boolean stopAttack, int gameOver)
	{
		this.unitDied = unitDied;
		this.startAttack = startAttack;
		this.stopAttack = stopAttack;
		this.gameOver = gameOver;
	}

	public boolean isUnitDied()
	{
		return unitDied;
	}

	public boolean isStartAttack()
	{
		return startAttack;
	}

	public boolean isStopAttack()
	{
		return stopAttack;
	}

	public int isGameOver()
	{
		return gameOver;
	}
}
