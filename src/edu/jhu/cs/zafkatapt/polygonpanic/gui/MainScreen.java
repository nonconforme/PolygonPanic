package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Class MainScreen. The Main menu screen for the game.
 */
public class MainScreen extends Activity
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

		setContentView(R.layout.main);
	}

	/**
	 * Single player: opens the single player options menu
	 * 
	 * @param view
	 *            the view
	 */
	public void singlePlayer(View view)
	{
		setContentView(R.layout.loading);
		Intent sp = new Intent(MainScreen.this, GameScreen.class);
		MainScreen.this.startActivity(sp);
		setContentView(R.layout.main);
	}

	/**
	 * High score: opens the highscore menu
	 * 
	 * @param view
	 *            the view =======
	 * 
	 *            Never called as the button is disabled
	 * 
	 * @param view
	 *            Standard parameter for button callbacks.
	 */
	public void highScore(View view)
	{
		Intent hs = new Intent(MainScreen.this, HighScore.class);
		MainScreen.this.startActivity(hs);
	}

	public void multiPlay(View view)
	{
		Intent mp = new Intent(MainScreen.this, Lobby.class);
		MainScreen.this.startActivity(mp);
		finish();
	}

	/**
	 * Multi rec: opens the mutliplayer record
	 * 
	 * Never called as the button is disabled
	 * 
	 * @param view
	 *            Standard parameter for button callbacks.
	 */
	public void multiRec(View view)
	{
		Intent mr = new Intent(MainScreen.this, MultiRecord.class);
		MainScreen.this.startActivity(mr);
	}

	/**
	 * Credits: opens the credits screen
	 * 
	 * Never called as the button is disabled
	 * 
	 * @param view
	 *            Standard parameter for button callbacks.
	 */
	public void credits(View view)
	{
		Intent c = new Intent(MainScreen.this, Credits.class);
		MainScreen.this.startActivity(c);
	}
}
