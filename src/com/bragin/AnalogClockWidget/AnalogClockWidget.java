package com.bragin.AnalogClockWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.RemoteViews;
import com.bragin.AnalogClockWidget.utils.TimeUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 29.07.13 23:09
 */
public class AnalogClockWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		final RemoteViews views = new RemoteViews(context.getApplicationContext().getPackageName(), R.layout.main);
//		final AnalogClockView view = new AnalogClockView(context);
//		view.setTime(view.getTime());
//		view.measure(500, 500);
//		view.layout(0, 0, 500, 500);
//		final Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
//		view.draw(new Canvas(bitmap));
//
//		views.setImageViewBitmap(R.id.imageView1, bitmap);
		for (int appWidgetId : appWidgetIds) {
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}

		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new CustomAnalogClockTimer(context, appWidgetManager), 1, 60000);
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

			remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);
			appWidgetManager.updateAppWidget(thisWidget, remoteViews);
		}
	}
}
