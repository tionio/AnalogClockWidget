package com.bragin.AnalogClock.objects;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 21:12
 */
public abstract class AbstractClockObject extends Drawable {

	// Отступ от краев экрана в процентах
	protected final static float indentPercent = 5.f;
	protected int alpha;
	protected ColorFilter colorFilter;

	@Override
	public void setAlpha(int i) {
		alpha = i;
	}

	@Override
	public void setColorFilter(ColorFilter colorFilter) {
		this.colorFilter = colorFilter;
	}

	@Override
	public int getOpacity() {
		return 0;
	}
}
