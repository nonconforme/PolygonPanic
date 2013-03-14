package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.AI;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Base;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.GameState;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.PanicEvent;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.PanicInterface;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.PanicModelListener;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Parallelogram;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Pentagon;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Player;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Spell;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Square;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Star;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Trapezoid;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Triangle;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Unit;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Upgrades;
import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.Z;
import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.SubMenu;

/**
 * The Class GameScreen. The screen where the game is actually played
 */
public class GameScreen extends Activity implements SensorEventListener
{

	/** The game model. */
	private PanicInterface model;

	/** Update timer; every time it ticks, the units update their relevant data. */
	Timer update;

	/** Whether or not a spell is casting. */
	boolean casting = false;

	/** The amount of time that has passed since the cast button was pressed. */
	long castTime;

	/** The limit on how high castTime can go. */
	final long CASTCAP = 4000;

	/** Menu item to quit game. */
	private MenuItem quit;

	/** Submenus for purchasing units and upgrades. */
	private SubMenu units, spells, unitupgrades, /* spellupgrades, */
	castleupgrades;

	/** The components of the unit submenu. */
	private MenuItem triangle, parallelogram, square, trapezoid, pentagon,
			star, zunit;

	/** The components of the spell submenu. */
	private MenuItem earthquake, fling, horror;

	/** The components of the unit upgrade submenu. */
	private MenuItem triup, parup, squp, trapup, pentup, starup, zup;
	// private MenuItem quakeup, flingup, horrorup;

	/** The grate. */
	private MenuItem health, energy, erate, grate;

	/** The Game view. */
	private GameView gView;

	/** The interval between updates. */
	private static int updateInterval = 100;

	/** The boot delay before updates begin. */
	private static int sBootDelay = 4000;

	/** The flag for if attacking is happening. */
	private boolean isAtt = false;

	// Below Variables are for accelerometer, which is currently non functional
	/** The sensor manager. */
	// private SensorManager mSensorManager;

	/** The last update. */
	// private long lastUpdate = -1;

	/** The orientation of the device. */
	// private float x, y, z;

	/** The last orientation of the android. */
	// private float last_x, last_y, last_z;

	/** The threshold at which a shake is determined. */
	// private static final int SHAKE_THRESHOLD = 800;/*

	/** The length of time a spell is cast for. */
	private final long CASTCD = 2000;

	/** The how long the spell has been casting for. */
	private long countdown;

	/** Which player on this phone, 1 or 2. */
	private int playerid;

	/** The AI player, if a single player game. */
	private AI compplayer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// Sets the screen
		setContentView(R.layout.game);

		// Accelerometer Sensor
		// mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

		// Starting properties of the game to theoretically be handled by an
		// options menu of some sort; framework here even though it is not
		// implemented
		// Starting upgrades
		Upgrades ups1 = new Upgrades();
		Upgrades ups2 = new Upgrades();

		// Starting bases
		Base base1 = new Base(1, ups1);
		Base base2 = new Base(-1, ups2);

		// Starting Players
		Player player1 = new Player(base1, ups1);
		Player player2 = new Player(base2, ups2);
		player1.addGold(100);
		player2.addGold(100);

		model = new GameState(player1, player2);

		// Detect if single or multi player
		// If single player, set playerid to 1 and generate an AI player
		if (Lobby.networking == null)
		{
			playerid = 1;

			compplayer = new AI(model);
			System.gc();
		}

		// If multiplayer, set the host to player 1 and the challenger to player
		// 2
		else
		{
			if (Lobby.networking.isHost())
				playerid = 1;
			else
				playerid = 2;
		}

		// Grab the xml generated gameview and store it
		View t = this.findViewById(R.id.GameView);
		if (t instanceof GameView)
		{
			gView = (GameView) t;
		}

		gView.invalidate();

		// Add listener for model events
		this.model.addListener(new PanicModelListener()
		{

			@Override
			public void receiveEvent(PanicEvent e)
			{
				// Plays a graphic when a unit dies
				if (e.isUnitDied())
				{
					// TODO
					// Play death animation
				}

				// Attacks have started happening
				if (e.isStartAttack())
				{
					// Start displaying health
					gView.setFront(model.getFrontLineData());
					isAtt = true;
					gView.flipAttack();
				}

				// Attacks have stopped happening
				if (e.isStopAttack())
				{
					// Stop Displaying health
					isAtt = false;
					gView.flipAttack();
				}

				if (e.isGameOver() != 0)
				{
					Lobby.networking.end();
					if (playerid == e.isGameOver())
					{
						// Start win
						Intent w = new Intent(GameScreen.this, GameWon.class);
						GameScreen.this.startActivity(w);
						finish();
					} else
					{
						// Start lost
						Intent l = new Intent(GameScreen.this, GameLost.class);
						GameScreen.this.startActivity(l);
						finish();
					}
				}

			}
		});

		// Schedule a timer to update the gamescreen
		update = new Timer();
		update.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				GameScreen.this.updateMethod();
			}
		}, sBootDelay, updateInterval);

	}

	/**
	 * This method drives the games. It gets updated game information from the
	 * model and tells the gameview to display it on the screen
	 */
	private void updateMethod()
	{

		// Tell the computer player to take his turn
		if (compplayer != null)
		{
			compplayer.update();
			System.gc();
		}

		// Set opposing sets of units
		else
		{
			// NETWORKING CODE!
			if (playerid == 1)
			{
				try
				{
					Lobby.networking.setP1Units(model.getP1Units());
					model.setP2Units(Lobby.networking.getP2Units());
					Lobby.networking.setP1Front(model.getP1FrontLine());
					model.setP2FrontLine(Lobby.networking.getP2Front());
				} catch (Exception e)
				{
				}
			}

			else if (playerid == 2)
			{
				try
				{
					Lobby.networking.setP2Units(model.getP2Units());
					model.setP1Units(Lobby.networking.getP1Units());
					Lobby.networking.setP2Front(model.getP2FrontLine());
					model.setP1FrontLine(Lobby.networking.getP1Front());
				} catch (Exception e)
				{
				}
			}
		}

		// Grab the updates from the model
		List<Unit> units;
		units = model.update();
		List<String> toDraw = new ArrayList<String>();
		List<Double> locations = new ArrayList<Double>();

		// Tick down the spell cooldown timer
		double timepassed = System.currentTimeMillis() - this.countdown;
		if (timepassed >= this.CASTCD)
		{
			gView.setHorror(false);
			gView.setQuake(false);
			gView.setWell(false);
		}

		// Tell the view attacks are happening if so
		if (this.isAtt)
		{
			gView.setFront(model.getFrontLineData());
		}

		// Generate the unit strings so the view can draw them
		// This is a workaround so that the view does not have to hold a copy of
		// the model, as there is no reliable way to do that.
		for (Unit i : units)
		{
			// Draw the units
			String u = "";
			double loc = i.getPosition();

			if (i instanceof Triangle)
				u += "t";
			else if (i instanceof Square)
				u += "s";
			else if (i instanceof Parallelogram)
				u += "p";
			else if (i instanceof Trapezoid)
				u += "o";
			else if (i instanceof Pentagon)
				u += "f";
			else if (i instanceof Star)
				u += "r";
			else if (i instanceof Z)
				u += "z";

			int r = i.getRank();
			if (r <= 2)
				u += "r";
			else if (r >= 5)
				u += "e";
			else
				u += "v";

			int d = i.getDirection();
			if (d > 0)
				u += "r";
			else
				u += "b";

			toDraw.add(u);
			locations.add(loc);
		}

		gView.setBaseHealth(model.getRedBaseHealth(), model.getBlueBaseHealth());

		// Tell the view what to draw
		gView.setUnits(toDraw, locations);
		gView.setResources(model.getPlayerGold(playerid),
				model.getPlayerEnergy(playerid));

		// Invalidate the view to make it redraw itself
		gView.postInvalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Create the menus
		units = menu.addSubMenu("Units");

		// Disable if multiplayer
		if (Lobby.networking == null)
		{
			spells = menu.addSubMenu("Spells");
		}

		unitupgrades = menu.addSubMenu("Unit Upgrades");
		// spellupgrades = menu.addSubMenu("Spell Upgrades");
		castleupgrades = menu.addSubMenu("Castle Upgrades");
		quit = menu.add("Quit");

		// Units
		triangle = units.add("Triangle (10 Energy)");
		square = units.add("Square (50 Energy)");
		parallelogram = units.add("Parallelogram (50 Energy)");
		trapezoid = units.add("Trapezoid (50 Energy)");
		pentagon = units.add("Pentagon (100 Energy)");
		star = units.add("Star (250 Energy)");
		zunit = units.add("Z (500 Energy)");

		// Spells
		// Disable if multiplayer
		if (Lobby.networking == null)
		{
			earthquake = spells.add("Earthquake (75 Energy)");
			fling = spells.add("Gravity Well (75 Energy)");
			horror = spells.add("Unspeakable Horror (150 Energy)");
		}

		// Unit Upgrades
		triup = unitupgrades.add("Triangle (50 Gold)");
		squp = unitupgrades.add("Square (75 Gold)");
		parup = unitupgrades.add("Parallelogram (75 Gold)");
		trapup = unitupgrades.add("Trapezoid (75 Gold)");
		pentup = unitupgrades.add("Pentagon (125 Gold)");
		starup = unitupgrades.add("Star (200 Gold)");
		zup = unitupgrades.add("Z (500 Gold)");

		// Spell Upgrades
		// quakeup = spellupgrades.add("Earthquake");
		// flingup = spellupgrades.add("Fling");
		// horrorup = spellupgrades.add("Unspeakable Horror");

		// Castle Upgrades
		health = castleupgrades.add("Max Health (10 Gold)");
		// castleupgrades.add("Attack Range");
		// castleupgrades.add("Attack Damage");
		// castleupgrades.add("Attack Speed");
		energy = castleupgrades.add("Max Energy (10 Gold)");
		erate = castleupgrades.add("Energy Rate (10 Gold)");
		grate = castleupgrades.add("Gold Rate (10 Gold)");

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		// Quit Function
		if (item.equals(quit))
		{
			finish();
			return true;
		}

		// Unit Purchase Functions
		if (item.equals(triangle))
		{
			model.purchaseUnit(playerid, 1);
		}

		else if (item.equals(square))
		{
			model.purchaseUnit(playerid, 2);
		}

		else if (item.equals(parallelogram))
		{
			model.purchaseUnit(playerid, 3);
		}

		else if (item.equals(trapezoid))
		{
			model.purchaseUnit(playerid, 4);
		}

		else if (item.equals(pentagon))
		{
			model.purchaseUnit(playerid, 5);
		}

		else if (item.equals(star))
		{
			model.purchaseUnit(playerid, 6);
		}

		else if (item.equals(zunit))
		{
			model.purchaseUnit(playerid, 7);
		}

		// Spell purchase functions
		else if (item.equals(earthquake))
		{
			model.castSpell(playerid, Spell.QUAKE);
			this.countdown = System.currentTimeMillis();
			gView.setQuake(true);
		}

		else if (item.equals(fling))
		{
			model.castSpell(playerid, Spell.GRAV_WELL);
			this.countdown = System.currentTimeMillis();
			gView.setWell(true);
		}

		else if (item.equals(horror))
		{
			model.castSpell(playerid, Spell.HORROR);
			this.countdown = System.currentTimeMillis();
			gView.setHorror(true);
		}

		// Unit Upgrades
		else if (item.equals(triup))
		{
			model.purchaseUnitUpgrade(playerid, 1);
		}

		else if (item.equals(squp))
		{
			model.purchaseUnitUpgrade(playerid, 2);
		}

		else if (item.equals(parup))
		{
			model.purchaseUnitUpgrade(playerid, 3);
		}

		else if (item.equals(trapup))
		{
			model.purchaseUnitUpgrade(playerid, 4);
		}

		else if (item.equals(pentup))
		{
			model.purchaseUnitUpgrade(playerid, 5);
		}

		else if (item.equals(starup))
		{
			model.purchaseUnitUpgrade(playerid, 6);
		}

		else if (item.equals(zup))
		{
			model.purchaseUnitUpgrade(playerid, 7);
		}

		/*
		 * / Spell upgrades else if(item.equals(quakeup)) { //
		 * model.spellupgrade }
		 * 
		 * else if(item.equals(flingup)) { // model.spellupgrade }
		 * 
		 * else if(item.equals(horrorup)) { // model.spellupgrade }
		 */

		// Base upgrades

		// Base Health
		else if (item.equals(health))
		{
			model.purchaseBaseUpgrade(playerid, 7);
		}

		// Energy Max
		else if (item.equals(energy))
		{
			model.purchaseBaseUpgrade(playerid, 5);
		}

		// Energy Rate
		else if (item.equals(erate))
		{
			model.purchaseBaseUpgrade(playerid, 4);
		}

		// Gold Rate
		else if (item.equals(grate))
		{
			model.purchaseBaseUpgrade(playerid, 6);
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.hardware.SensorEventListener#onSensorChanged(android.hardware
	 * .SensorEvent)
	 */
	@Override
	public void onSensorChanged(SensorEvent sensor)
	{
		/*
		 * Cannot get the sensor to work properly. Will examine in the future
		 * 
		 * 
		 * // Only do anything if the casting button has been pressed
		 * if(casting) {
		 * if(sensor.sensor.getType()==SensorManager.SENSOR_ACCELEROMETER) {
		 * long curTime = System.currentTimeMillis(); // only allow one update
		 * every 100ms. if((curTime-lastUpdate)>=100) { float spin[] = new
		 * float[3];
		 * 
		 * // ADD UNSPEAKABLE HORROR CODE spin =
		 * SensorManager.getOrientation(null, spin);
		 * if(spin[0]<Math.PI/8.0||spin[0]<Math.PI*7.0/8.0) { // UNSPEAKABLE
		 * HORROR model.castSpell(playerid, Spell.HORROR); this.countdown =
		 * this.CASTCD; casting = false; gView.setHorror(true); }
		 * 
		 * // EARTHQUAKE long diffTime = (curTime-lastUpdate); lastUpdate =
		 * curTime;
		 * 
		 * x = sensor.values[SensorManager.DATA_X]; y =
		 * sensor.values[SensorManager.DATA_Y]; z =
		 * sensor.values[SensorManager.DATA_Z];
		 * 
		 * float speed = Math.abs(x+y+z-last_x-last_y-last_z)/diffTime10000;
		 * if(speed>=SHAKE_THRESHOLD) { // SHAKE CODE model.castSpell(playerid,
		 * Spell.QUAKE); this.countdown = this.CASTCD; casting = false;
		 * gView.setQuake(true); } last_x = x; last_y = y; last_z = z;
		 * 
		 * // Gravity Well if(curTime-this.castTime>this.CASTCAP) { // GRAVITY
		 * WELL model.castSpell(playerid, Spell.GRAV_WELL); this.countdown =
		 * System.currentTimeMillis(); casting = false; gView.setWell(true); } }
		 * } }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		/*
		 * Accelerometer isn't working if(keyCode==KeyEvent.KEYCODE_SEARCH) {
		 * this.casting = true; this.castTime = System.currentTimeMillis();
		 * 
		 * //Casting toast Toast toast= Toast.makeText(getApplicationContext(),
		 * "CAST!", 4000); toast.show(); return true; }
		 */
		if (keyCode == KeyEvent.KEYCODE_HOME)
		{
			finish();
			System.exit(0);
			return super.onKeyDown(keyCode, event);
		} else
			return super.onKeyDown(keyCode, event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause()
	{
		finish();
		System.exit(0);
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop()
	{
		finish();
		System.exit(0);
		super.onPause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.hardware.SensorEventListener#onAccuracyChanged(android.hardware
	 * .Sensor, int)
	 */
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1)
	{
		// Don't do anything on accuracy changed.
		return;

	}

}
