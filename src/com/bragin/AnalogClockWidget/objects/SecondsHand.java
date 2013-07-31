package com.bragin.AnalogClockWidget.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 21:12
 */
public class SecondsHand extends AbstractClockObject {

	private final static float lengthPercent = 47.5f;
	private int seconds;

	public void drawSeconds(Canvas canvas, int seconds) {
		this.seconds = seconds;
		draw(canvas);
	}

	@Override
	public void draw(Canvas canvas) {

		// Минимальная из размеров экрана min(x, y)
		final int minScreenSize = Math.min(canvas.getWidth(), canvas.getHeight());

		final float strokeWidth = minScreenSize * lengthPercent / 6000.f;

		// Задание стиля рисования
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 255, 0, 0);
		paint.setStrokeWidth(strokeWidth);

		// Длина стрелки от центра до края - отступ
		final float length = minScreenSize * lengthPercent / 100.f;
//		final float length = minScreenSize / 2.f * (1.f - indentPercent / 100.f);

		// Угол наклона стрелки в радианах
		final float angleInRadians = (float) (Math.PI * (-2.f * (seconds / 60.f) + 1.f));

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
