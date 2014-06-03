/**
 * 
 */
package com.aoeng.degu.views;

import com.aoeng.degu.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Jun 3, 2014 1:44:48 PM
 * 
 */
public class SuDoKuView extends View {
	private float width;
	private float hight;

	/**
	 * @param context
	 */
	public SuDoKuView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		this.width = w / 9f;
		this.hight = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint bgPaint = new Paint();
		bgPaint.setColor(getResources().getColor(R.color.aliceblue));
		canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
	}

}
