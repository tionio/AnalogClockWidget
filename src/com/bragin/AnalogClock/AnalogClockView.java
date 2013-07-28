package com.bragin.AnalogClock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.bragin.AnalogClock.objects.ClockFace;
import com.bragin.AnalogClock.objects.HourHand;
import com.bragin.AnalogClock.objects.MinuteHand;
import com.bragin.AnalogClock.objects.SecondsHand;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 20:39
 */
public class AnalogClockView extends View {

	private ClockFace clockFace;
	private HourHand hourHand;
	private MinuteHand minuteHand;
	private SecondsHand secondsHand;

	public AnalogClockView(Context context) {
		super(context);
		clockFace = new ClockFace();
		hourHand = new HourHand();
		minuteHand = new MinuteHand();
		secondsHand = new SecondsHand();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		clockFace.draw(canvas);
		hourHand.draw(canvas);
		minuteHand.draw(canvas);
		secondsHand.draw(canvas);
	}
}
