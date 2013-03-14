package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.jhu.cs.zafkatapt.polygonpanic.gameplay.FrontLineData;
import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * The Class GameView- the main game interface for the game Polygon Panic
 */
public class GameView extends View
{

	/** Map Containing all unit graphics. */
	private Map<String, Bitmap> graphics;

	/** Background, base, and spell graphics */
	private Bitmap bg, red, blue, quake, well, attack;

	/** Screen Dimensions */
	private int height, width;

	/** Units to be drawn on screen */
	private List<String> unitStrings;

	/** The locations of the units to be drawn */
	private List<Double> locations;

	/** Flag for whether attacking is happening or not */
	private boolean isAttacking = false;

	/** Data on where the units taking damage are */
	private double frontLine[];

	/** Flags for whether a spell is being cast */
	private boolean isHorror = false, isQuake = false, isWell = false;

	/** Gold & energy */
	private int gold = 0, energy = 0;

	/** Red & Blue Base Health */
	private int bluebase, redbase;

	/**
	 * Instantiates a new game view.
	 * 
	 * See superclass. Also loads the graphics into memory
	 */
	public GameView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		loadGraphics();
	}

	/**
	 * Instantiates a new game view.
	 * 
	 * See superclass Also loads the graphics into memory
	 */
	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		loadGraphics();
	}

	/**
	 * Instantiates a new game view.
	 * 
	 * See superclass Also loads the graphics into memory
	 */
	public GameView(Context context)
	{
		super(context);
		loadGraphics();
	}

	/**
	 * Sets the units to be drawn
	 * 
	 * @param us
	 *            the list of units to be drawn
	 * @param locs
	 *            the locations of units to be drawn
	 */
	public void setUnits(List<String> us, List<Double> locs)
	{
		unitStrings = us;
		locations = locs;
	}

	/**
	 * Sets the units that are attacking each other
	 * 
	 * @param front
	 *            the new units on the front line
	 */
	public void setFront(double[] front)
	{
		this.frontLine = front;
	}

	/**
	 * Sets the horror spell flag
	 * 
	 * @param h
	 *            whether horror is happening or not
	 */
	public void setHorror(boolean h)
	{
		this.isHorror = h;
	}

	/**
	 * Sets the quake spell flag
	 * 
	 * @param q
	 *            whether earthquake is happening or not
	 */
	public void setQuake(boolean q)
	{
		this.isQuake = q;
	}

	/**
	 * Sets the well spell flag.
	 * 
	 * @param w
	 *            whether the gravity well spell is happening or not
	 */
	public void setWell(boolean w)
	{
		this.isWell = w;
	}

	/**
	 * Flip the isAttacking variable.
	 */
	public void flipAttack()
	{
		this.isAttacking = !this.isAttacking;
	}

	/**
	 * Loads graphics into memory.
	 */
	private void loadGraphics()
	{

		// Background
		bg = BitmapFactory
				.decodeResource(getResources(), R.drawable.background);

		// Bases
		red = BitmapFactory.decodeResource(getResources(), R.drawable.base_red);
		blue = BitmapFactory.decodeResource(getResources(),
				R.drawable.base_blue);

		// Spell graphics

		quake = BitmapFactory.decodeResource(getResources(),
				R.drawable.earthquakescenesmall);
		System.gc();

		well = BitmapFactory.decodeResource(getResources(),
				R.drawable.gravwellscenesmall);
		System.gc();

		// Explosion
		attack = BitmapFactory.decodeResource(getResources(),
				R.drawable.explosion);
		System.gc();

		unitStrings = new ArrayList<String>();
		locations = new ArrayList<Double>();

		graphics = new HashMap<String, Bitmap>();

		// UNITS
		/*
		 * Key: p=parallel f=pentagon s=square r=star o=trapezoid t=triangle z=z
		 * 
		 * e=elite r=rookie v=veteran
		 * 
		 * b=blue r=red
		 */

		// Parallelogram
		graphics.put("peb", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_elite_blue_left));
		graphics.put("per", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_elite_red_right));

		graphics.put("prb", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_rookie_blue_left));
		graphics.put("prr", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_rookie_red_right));

		graphics.put("pvb", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_vet_blue_left));
		graphics.put("pvr", BitmapFactory.decodeResource(getResources(),
				R.drawable.parallel_vet_red_right));

		// Pentagon
		graphics.put("feb", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_elite_blue));
		graphics.put("fer", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_elite_red));

		graphics.put("frb", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_rookie_blue));
		graphics.put("frr", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_rookie_red));

		graphics.put("fvb", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_vet_blue));
		graphics.put("fvr", BitmapFactory.decodeResource(getResources(),
				R.drawable.pentagon_vet_red));

		// Square
		graphics.put("seb", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_elite_blue));
		graphics.put("ser", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_elite_red));

		graphics.put("srb", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_rookie_blue));
		graphics.put("srr", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_rookie_red));

		graphics.put("svb", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_vet_blue));
		graphics.put("svr", BitmapFactory.decodeResource(getResources(),
				R.drawable.square_vet_red));

		// Star
		graphics.put("reb", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_elite_blue));
		graphics.put("rer", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_elite_red));

		graphics.put("rrb", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_rookie_blue));
		graphics.put("rrr", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_rookie_red));

		graphics.put("rvb", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_vet_blue));
		graphics.put("rvr", BitmapFactory.decodeResource(getResources(),
				R.drawable.star_vet_red));

		// Trapezoid
		graphics.put("oeb", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_elite_blue));
		graphics.put("oer", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_elite_red));

		graphics.put("orb", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_rookie_blue));
		graphics.put("orr", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_rookie_red));

		graphics.put("ovb", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_vet_blue));
		graphics.put("ovr", BitmapFactory.decodeResource(getResources(),
				R.drawable.trapezoid_vet_red));

		// triangle
		graphics.put("teb", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_elite_blue_right));
		graphics.put("ter", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_elite_red_left));

		graphics.put("trb", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_rookie_blue_right));
		graphics.put("trr", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_rookie_red_left));

		graphics.put("tvb", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_vet_blue_right));
		graphics.put("tvr", BitmapFactory.decodeResource(getResources(),
				R.drawable.triangle_vet_red_left));

		// Z
		graphics.put("zeb", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_elite_blue));
		graphics.put("zer", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_elite_red));

		graphics.put("zrb", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_rookie_blue));
		graphics.put("zrr", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_rookie_red));

		graphics.put("zvb", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_vet_blue));
		graphics.put("zvr", BitmapFactory.decodeResource(getResources(),
				R.drawable.z_vet_red));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas)
	{
		// Draw a red screen if horror is happening
		if (this.isHorror)
		{
			canvas.drawRGB(255, 0, 0);
			return;
		}

		// Draw background & bases
		canvas.drawBitmap(bg, 0, 0, null);
		canvas.drawBitmap(red, 0, height / 2, null);
		canvas.drawBitmap(blue, width - blue.getWidth(), height / 2, null);

		for (int x = 0; x < unitStrings.size(); x++)
		{

			Bitmap drawn = graphics.get(unitStrings.get(x));
			float scaledLocation = (float) ((locations.get(x) * width / 200.0) - (drawn
					.getWidth() / 2));

			canvas.drawBitmap(drawn, scaledLocation,
					(float) (height / 4.0 * 3.0), null);
		}

		// Draw Gold & Energy
		// TODO Define paint!!!
		Paint money = new Paint();
		money.setColor(Color.YELLOW);
		Paint nrg = new Paint();
		nrg.setColor(Color.BLUE);
		Paint health = new Paint();
		health.setColor(Color.RED);

		canvas.drawText("Gold: " + gold, 0, 10, money);
		canvas.drawText("Energy: " + energy, 0, 20, nrg);

		// P1 BASE HEALTH CODE
		canvas.drawText(redbase + "", 0, (float) (height / 8.0 * 3.0), health);

		// P2 BASE HEALTH CODE
		canvas.drawText(bluebase + "", width - blue.getWidth(),
				(float) (height / 8.0 * 3.0), nrg);

		if (isAttacking)
		{
			float scaledred = (float) ((this.frontLine[FrontLineData.p1Position] * width / 200.0) - 2);
			float scaledblue = (float) ((this.frontLine[FrontLineData.p2Position] * width / 200.0) - 2);

			if (this.frontLine[FrontLineData.p1Position] != -1 && this.frontLine[FrontLineData.p2Position] != -1)
			{
				// TODO Paint
				// UNIT HEALTH CODE

				canvas.drawText((int) this.frontLine[FrontLineData.p1Health] + "", scaledred - 10,
						(float) ((height / 4.0 * 3.0)), health);
				canvas.drawText((int) this.frontLine[FrontLineData.p2Health] + "", scaledblue - 10,
						(float) ((height / 4.0 * 3.0)), health);

				// TODO Attack Graphics
				canvas.drawBitmap(attack,
						(float) (scaledblue - attack.getWidth() / 4.0 * 3.0),
						(float) (height / 4.0 * 3.0), null);

			}

		}

		if (this.isQuake)
		{
			// TODO Earthquake code
			canvas.drawBitmap(quake, 0, 0, null);
		}

		if (this.isWell)
		{
			// TODO Well Code
			canvas.drawBitmap(well, 0, 0, null);
		}
	}

	/**
	 * Sets the player resources to be drawn
	 * 
	 * @param g
	 *            the gold of the player
	 * @param e
	 *            the energy of the player
	 */
	public void setResources(int g, double e)
	{
		// TODO Set gold and energy
		this.gold = g;
		this.energy = (int) e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);

		bg = scaleBG(bg);
		System.gc();

		// Scale Quake
		quake = scaleBG(quake);
		System.gc();

		// Scale Grav well
		well = scaleBG(well);
		System.gc();

		// Scale attack sprite
		attack = scaleBitmap(attack, .25);
		System.gc();

		// Scale Bases
		blue = scaleBitmap(blue, .25);
		System.gc();
		red = scaleBitmap(red, .25);
		System.gc();

		// Scale Units
		for (String n : graphics.keySet())
		{
			Bitmap temp = graphics.get(n);
			temp = scaleBitmap(temp, .2);
			graphics.put(n, temp);
		}
		System.gc();
		setMeasuredDimension(width, height);
	}

	/**
	 * Scale graphics to correct size
	 * 
	 * @param b
	 *            the bitmap to be scaled
	 * @param scale
	 *            how much to scale the bitmap by
	 * @return the scaled bitmap
	 */
	private Bitmap scaleBitmap(Bitmap b, double scale)
	{
		Bitmap bcopy = Bitmap.createBitmap(b);
		float bwidth = (float) ((height / ((float) bcopy.getWidth())) * scale);
		float bheight = (float) ((height / ((float) bcopy.getHeight())) * scale);
		Matrix btrans = new Matrix();
		btrans.postScale(bwidth, bheight);
		Bitmap back = Bitmap.createBitmap(b, 0, 0, bcopy.getWidth(),
				bcopy.getHeight(), btrans, true);

		return back;

	}

	/**
	 * Scale an image that needs to fill the screen
	 * 
	 * @param b
	 *            the bitmap to be scaled
	 * @return the scaled bitmap
	 */
	private Bitmap scaleBG(Bitmap b)
	{
		Bitmap bcopy = Bitmap.createBitmap(b);
		float bwidth = (float) ((width / ((float) bcopy.getWidth())));
		float bheight = (float) ((height / ((float) bcopy.getHeight())));
		Matrix btrans = new Matrix();
		btrans.postScale(bwidth, bheight);
		return Bitmap.createBitmap(b, 0, 0, bcopy.getWidth(),
				bcopy.getHeight(), btrans, true);

	}

	public void setBaseHealth(double r, double b)
	{
		this.bluebase = (int) b;
		this.redbase = (int) r;
	}
}