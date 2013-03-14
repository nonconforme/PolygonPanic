package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Win Screen
 */
public class GameWon extends Activity
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.win);
	}

	/**
	 * Quit application.
	 * 
	 * @param view
	 *            Standard parameter for button callbacks
	 */
	public void back(View view)
	{
		// finish();
		System.exit(0);
	}

}
