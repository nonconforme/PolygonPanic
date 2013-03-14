package edu.jhu.cs.zafkatapt.polygonpanic.gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AI
{
	private final int PLAYER = 2;

	private final long COOL_DOWN = 5000;

	private long lastUpdateTime = 0;

	private PanicInterface model;

	private List<Integer> purchaseList = new ArrayList<Integer>(7);

	private List<Integer> randomOrder = new ArrayList<Integer>(3);

	private Random random = new Random();

	public AI(PanicInterface model)
	{
		this.model = model;
	}

	public void update()
	{
		long time = System.currentTimeMillis();

		if ((time - lastUpdateTime) > COOL_DOWN)
		{
			int rand = random.nextInt(100) + 1;

			if (rand <= 50)
			{
				// Purchase Unit
				for (int i = 7; i >= 5; i--)
					purchaseList.add(i);

				for (int i = 4; i >= 2; i--)
					randomOrder.add(i);

				Collections.shuffle(randomOrder);
				purchaseList.addAll(randomOrder);

				purchaseList.add(1);

				for (int i = 0; i < purchaseList.size(); i++)
				{
					if (model.purchaseUnit(PLAYER, purchaseList.get(i)))
						break;
				}
			}

			else if (rand <= 75)
			{
				// Purchase Base Upgrade
				model.purchaseBaseUpgrade(PLAYER,
						Upgrades.BASE_ENERGY_RATE_INDEX);
				model.purchaseBaseUpgrade(PLAYER,
						Upgrades.BASE_ENERGY_MAX_INDEX);

				for (int i = 1; i <= 7; i++)
				{
					if (i == Upgrades.BASE_ENERGY_MAX_INDEX
							|| i == Upgrades.BASE_ENERGY_MAX_INDEX)
						continue;

					purchaseList.add(i);
				}

				Collections.shuffle(purchaseList);

				for (int i = 0; i < purchaseList.size(); i++)
				{
					if (model.purchaseBaseUpgrade(PLAYER, purchaseList.get(0)))
						break;
				}
			}

			else
			{
				// Purchase Unit Upgrade
				purchaseList.add(1);

				for (int i = 2; i <= 4; i++)
					randomOrder.add(i);

				Collections.shuffle(randomOrder);
				purchaseList.addAll(randomOrder);

				for (int i = 5; i <= 7; i++)
					purchaseList.add(i);

				for (int i = 0; i < purchaseList.size(); i++)
				{
					if (model.purchaseUnitUpgrade(PLAYER, purchaseList.get(i)))
						break;
				}
			}

			purchaseList.clear();
			randomOrder.clear();

			lastUpdateTime = time;
		}
	}
}
