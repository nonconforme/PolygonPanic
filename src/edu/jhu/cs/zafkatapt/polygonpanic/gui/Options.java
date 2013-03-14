package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Class Options. Works as the options screen for both single and
 * multiplayer games.
 * 
 * Non functional as of this build. Hope to eventually implement.
 */
public class Options extends Activity
{

	String options = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
	}

	public void back(View view)
	{
		finish();
	}

	public void play(View view)
	{
		Intent game = new Intent(Options.this, GameScreen.class);
		Options.this.startActivity(game);
		finish();
	}

	/**
	 * Gets the options applicable to the game
	 * 
	 * @return the options for the game
	 */
	public String getOptions()
	{
		// TODO Implement
		return "";
	}

}
