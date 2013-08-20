package com.bragin.AnalogClockWidget.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.bragin.AnalogClockWidget.AnalogClockView;
import com.bragin.AnalogClockWidget.AnalogClockWidget;
import com.bragin.AnalogClockWidget.R;
import com.bragin.AnalogClockWidget.utils.TimeUtils;

/**
 * Created with IntelliJ IDEA.
 * User: alexey.bragin
 * Date: 19.08.13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class AnalogClockUpdateService extends Service {

	public static final int bitmapSize = 250;
	private static final TimeUtils timeUtils = new TimeUtils();
	private AnalogClockView view;
	private RemoteViews remoteViews;
	private Bitmap bitmap;
	private Canvas canvas = new Canvas();

	@Override
	public void onCreate() {
		view = new AnalogClockView(getApplication().getApplicationContext());
		remoteViews = new RemoteViews(getPackageName(), R.layout.main);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		buildUpdate();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void buildUpdate() {

		bitmap = Bitmap.createBitmap(bitmapSize, bitmapSize, Bitmap.Config.ARGB_4444);
		canvas.setBitmap(bitmap);
		view.setTime(timeUtils.getCurrentTime());
		view.measure(bitmapSize, bitmapSize);
		view.layout(0, 0, bitmapSize, bitmapSize);
		view.draw(canvas);
		remoteViews.setImageViewBitmap(R.id.imageView1, bitmap);

		// Push update for this widget to the home screen
		ComponentName thisWidget = new ComponentName(this, AnalogClockWidget.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, remoteViews);
	}
}
