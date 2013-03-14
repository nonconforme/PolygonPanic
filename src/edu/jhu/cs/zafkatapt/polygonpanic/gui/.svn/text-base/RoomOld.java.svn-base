package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The Class Room. Screen where hosts wait for challengers to join.
 */
public class RoomOld extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.room);
	}

	/**
	 * Refresh. Redisplays the information from the server.
	 */
	public void refresh()
	{
		// TODO Meaningful Code
	}

	public void back(View view)
	{
		finish();
	}

	public void play(View view)
	{
		Intent game = new Intent(RoomOld.this, Options.class);
		RoomOld.this.startActivity(game);
		finish();
	}

	public void kick(View view)
	{
		// TODO Meaningful kickig code
	}
}
