package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import java.util.List;

import edu.jhu.cs.zafkatapt.polygonpanic.network.Client;
import edu.jhu.cs.zafkatapt.polygonpanic.network.NetworkEvent;
import edu.jhu.cs.zafkatapt.polygonpanic.network.NetworkListener;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The Class Lobby. Provides a list of available games to join.
 */
public class Lobby extends Activity
{

	/** The list of available games */
	Spinner lobbylist;

	/** Client to server communication object */
	public static Client networking;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.lobby);

		// Grab the spinner from the xml (Spinner is a drop down list)
		// Also add a listener to the spinner
		lobbylist = (Spinner) findViewById(R.id.LobbySpinner);
		lobbylist.setPrompt("Select a game!");
		lobbylist.setOnItemSelectedListener(new PanicOnItemSelectedListener());

		// Grab the ip address and set the networking object
		Lobby.networking = new Client(this.getLocalIpAddress());
		Lobby.networking.addListener(new NetworkListener()
		{

			@Override
			public void receiveEvent(NetworkEvent event)
			{
				if (event.equals(NetworkEvent.startHosting))
				{
					Lobby.this.setContentView(R.layout.loading);
					// finish();
				} else if (event.equals(NetworkEvent.hostReceivedConnection))
				{
					Intent h = new Intent(Lobby.this, GameScreen.class);
					Lobby.this.startActivity(h);
					finish();
				} else if (event.equals(NetworkEvent.connectedToHost))
				{
					Lobby.this.setContentView(R.layout.loading);
					Intent c = new Intent(Lobby.this, GameScreen.class);
					Lobby.this.startActivity(c);
					finish();
				} else if (event.equals(NetworkEvent.receivedP2Units))
				{
					/**
					 * client has received a list of units from other player.
					 * Call networking.getP2Units() and use that to update the
					 * model
					 */
				}
			}
		});

	}

	/**
	 * Stops the activity.
	 * 
	 * @param view
	 *            standard parameter for button callback
	 */
	public void back(View view)
	{
		finish();
		System.exit(0);
	}

	/**
	 * Refresh. Refreshes the screen to display the current active lobbies.
	 * 
	 * @param view
	 *            standard parameter for button callback
	 */
	public void refresh(View view)
	{
		ArrayAdapter<String> games = new ArrayAdapter<String>(
				this.getApplicationContext(),
				android.R.layout.simple_spinner_item);

		// Workaround. If the spinner is populated and is clicked to select an
		// option, it autmatically calls a click on the default item. Thus, the
		// default item must be meaningless.
		List<String> hosts = Lobby.networking.getHosts();

		// Adds items to the spinner
		games.add("Select a Game");
		for (String i : hosts)
		{
			games.add(i);
		}

		lobbylist.setAdapter(games);

	}

	/**
	 * Tells the server to host a game.
	 * 
	 * @param view
	 *            standard parameter for button callback
	 */
	public void host(View view)
	{
		Lobby.networking.host();
		// Intent room = new Intent(Lobby.this, Room.class);
		// Lobby.this.startActivity(room);
	}

	/**
	 * The listener interface for receiving panicOnItemSelected events. The
	 * class that is interested in processing a panicOnItemSelected event
	 * implements this interface, and the object created with that class is
	 * registered with a component using the component's
	 * <code>addPanicOnItemSelectedListener<code> method. When
	 * the panicOnItemSelected event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see PanicOnItemSelectedEvent
	 */
	public class PanicOnItemSelectedListener implements OnItemSelectedListener
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
		 * android.widget.AdapterView, android.view.View, int, long)
		 */
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id)
		{
			System.out.println(parent.getItemAtPosition(pos).toString());
			if (parent.getItemAtPosition(pos).toString()
					.equals("Select a Game"))
				return;
			Lobby.networking.connect(parent.getItemAtPosition(pos).toString());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.widget.AdapterView.OnItemSelectedListener#onNothingSelected
		 * (android.widget.AdapterView)
		 */
		public void onNothingSelected(AdapterView<?> parent)
		{
			// Do nothing.

		}
	}

	/**
	 * Gets the local ip address.
	 * 
	 * @return the local ip address
	 */
	public String getLocalIpAddress()
	{
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();
		String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),
				(ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
				(ipAddress >> 24 & 0xff));
		return ip;
	}
}
