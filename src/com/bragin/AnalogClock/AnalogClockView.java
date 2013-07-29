package com.bragin.AnalogClock;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.bragin.AnalogClock.objects.ClockFace;
import com.bragin.AnalogClock.objects.HourHand;
import com.bragin.AnalogClock.objects.MinuteHand;
import com.bragin.AnalogClock.objects.SecondsHand;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 20:39
 */
public class AnalogClockView extends View {

	private Calendar time;
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

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		final int currentSeconds = time.get(Calendar.SECOND);
		final int currentMinutes = time.get(Calendar.MINUTE);
		final float currentHour = time.get(Calendar.HOUR) + currentMinutes / 60.f;

		clockFace.draw(canvas);
		hourHand.drawHours(canvas, currentHour);
		minuteHand.drawMinutes(canvas, currentMinutes);
		secondsHand.drawSeconds(canvas, currentSeconds);
	}
}
