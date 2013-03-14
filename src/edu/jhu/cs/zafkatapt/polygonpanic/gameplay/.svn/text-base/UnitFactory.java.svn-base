package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

public class UnitFactory
{
	/**
	 * Create a unit
	 * 
	 * @param unitID
	 *            the unit ID
	 * @param rank
	 *            the rank
	 * @param direction
	 *            the direction
	 * @return the unit
	 */
	static public Unit createUnit(int unitID, int rank, int direction)
	{
		Unit unit = null;

		if (unitID == Unit.TRIANGLE)
			unit = new Triangle(rank, direction);

		else if (unitID == Unit.SQUARE)
			unit = new Square(rank, direction);

		else if (unitID == Unit.PARALLELOGRAM)
			unit = new Parallelogram(rank, direction);

		else if (unitID == Unit.TRAPEZOID)
			unit = new Trapezoid(rank, direction);

		else if (unitID == Unit.PENTAGON)
			unit = new Pentagon(rank, direction);

		else if (unitID == Unit.STAR)
			unit = new Star(rank, direction);

		else if (unitID == Unit.Z)
			unit = new Z(rank, direction);

		return unit;
	}
}
