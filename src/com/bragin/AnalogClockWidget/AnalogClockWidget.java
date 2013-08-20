package com.bragin.AnalogClockWidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.bragin.AnalogClockWidget.service.AnalogClockUpdateService;
import com.bragin.AnalogClockWidget.utils.TimeUtils;

import java.util.Calendar;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 29.07.13 23:09
 */
public class AnalogClockWidget extends AppWidgetProvider {

	private PendingIntent service = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//		super.onUpdate(context, appWidgetManager, appWidgetIds);

		final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		final Calendar time = Calendar.getInstance();
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.HOUR, 0);

		final Intent intent = new Intent(context, AnalogClockUpdateService.class);

		if (service == null) {
			service = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		}

		alarmManager.setRepeating(AlarmManager.RTC, time.getTime().getTime(), 1000 * 60 /* ms */, service);
	}

	private class CustomAnalogClockTimer extends TimerTask {

		public static final int bitmapSize = 450;
		private AnalogClockView view;
		private RemoteViews remoteViews;
		private AppWidgetManager appWidgetManager;
		private ComponentName thisWidget;
		private TimeUtils timeUtils;
		private Bitmap bitmap;
		private Canvas canvas;

		public CustomAnalogClockTimer(Context context, AppWidgetManager appWidgetManager) {
			this.view = new AnalogClockView(context);
			this.appWidgetManager = appWidgetManager;
			this.remoteViews = new RemoteViews(context.getApplicationContext().getPackageName(), R.layout.main);
			this.thisWidget = new ComponentName(context, AnalogClockWidget.class);
			this.timeUtils = new TimeUtils();
			final Bitmap bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_8888);
			canvas = new Canvas(bitmap);
		}

		@Override
		public void run() {
			bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_4444);
			canvas.setBitmap(bitmap);
			view.setTime(timeUtils.getCurrentTime());
			view.measure(bitmapSize, bitmapSize);
			view.layout(0, 0, bitmapSize, bitmapSize);
			view.draw(canvas);

//			remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);
			appWidgetManager.updateAppWidget(thisWidget, remoteViews);
		}
	}
}
