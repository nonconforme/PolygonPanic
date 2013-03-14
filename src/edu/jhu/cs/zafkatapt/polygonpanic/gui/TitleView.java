package edu.jhu.cs.zafkatapt.polygonpanic.gui;

import edu.jhu.cs.zafkatapt.polygonpanic.gui.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class TitleView extends View
{

	Bitmap title;

	public TitleView(Context context)
	{
		super(context);
		makeTitle();
	}

	public TitleView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		makeTitle();
	}

	public TitleView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		makeTitle();
	}

	private void makeTitle()
	{
		title = BitmapFactory.decodeResource(getResources(),
				R.drawable.paniclogo);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO Auto-generated method stub
		canvas.drawBitmap(title, 0, 0, null);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		Bitmap copy = Bitmap.createBitmap(title);
		float swidth = (float) (MeasureSpec.getSize(widthMeasureSpec) / (copy
				.getWidth() * 1.0));
		float sheight = (float) (MeasureSpec.getSize(heightMeasureSpec)
				/ (copy.getHeight() * 1.0) * 3.0 / 4.0);
		Matrix trans = new Matrix();
		trans.postScale(swidth, sheight);
		title = Bitmap.createBitmap(title, 0, 0, copy.getWidth(),
				copy.getHeight(), trans, true);

		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec) * 3 / 4);

	}

}