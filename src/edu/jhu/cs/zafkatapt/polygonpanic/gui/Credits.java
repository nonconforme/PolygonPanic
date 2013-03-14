package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Credits extends Activity
{
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.credits1);
	}

	public void forward1(View view)
	{
		setContentView(R.layout.credits2);
	}

	public void forward2(View view)
	{
		setContentView(R.layout.credits3);
	}

	public void back(View view)
	{
		finish();
	}
}
