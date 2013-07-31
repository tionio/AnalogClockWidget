package com.bragin.AnalogClockWidget.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 21:12
 */
public class MinuteHand extends AbstractClockObject {

	private final static float lengthPercent = 35.f;
	private int minutes;

	public void drawMinutes(Canvas canvas, int minutes) {
		this.minutes = minutes;
		draw(canvas);
	}

	@Override
	public void draw(Canvas canvas) {

		// Минимальная из размеров экрана min(x, y)
		final int minScreenSize = Math.min(canvas.getWidth(), canvas.getHeight());

		final float strokeWidth = minScreenSize * lengthPercent / 2500.f;

		// Задание стиля рисования
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 255, 255, 255);
		paint.setStrokeWidth(strokeWidth);

		// Длина стрелки от центра до края - отступ
		final float length = minScreenSize * lengthPercent / 100.f;

		// Угол наклона стрелки в радианах
		final float angleInRadians = (float) (Math.PI * (-2.f * (minutes / 60.f) + 1.f));

		// Центр экрана по X
		final float centerX = canvas.getWidth() / 2.f;
		// Центр экрана по Y
		final float centerY = canvas.getHeight() / 2.f;

		// Вычисление конечной точки деления (ближе к краям экрана)
		float endX = (float) (centerX + Math.sin(angleInRadians) * length);
		float endY = (float) (centerY + Math.cos(angleInRadians) * length);

		// Отрисовка линии
		canvas.drawLine(centerX, centerY, endX, endY, paint);
	}
}
