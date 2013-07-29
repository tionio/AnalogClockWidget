package com.bragin.AnalogClock;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;
import com.bragin.AnalogClock.objects.ClockFace;
import com.bragin.AnalogClock.objects.HourHand;
import com.bragin.AnalogClock.objects.MinuteHand;
import com.bragin.AnalogClock.objects.SecondsHand;
import com.bragin.AnalogClock.utils.TimeUtils;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 20:39
 */
public class AnalogClockView extends ImageView {

	private Calendar time;
	private ClockFace clockFace;
	private HourHand hourHand;
	private MinuteHand minuteHand;
	private SecondsHand secondsHand;

	{
		time = new TimeUtils().getCurrentTime();
		clockFace = new ClockFace();
		hourHand = new HourHand();
		minuteHand = new MinuteHand();
		secondsHand = new SecondsHand();
	}

	public AnalogClockView(Context context) {
		super(context);
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
