package com.bragin.AnalogClockWidget.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 *
 * @author a_bragin
 * @since 28.07.13 21:12
 */
public class ClockFace extends AbstractClockObject {

	// Размер делений шкалы в процентах от размера экрана
	protected final static float sizePercent = 5.f;

	@Override
	public void draw(final Canvas canvas) {

		// Минимальная из размеров экрана min(x, y)
		final int minScreenSize = Math.min(canvas.getWidth(), canvas.getHeight());
		final float strokeWidth = minScreenSize * sizePercent / 1200.f;

		// Задание стиля рисования
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(255, 255, 255, 255);

		// Центр экрана по X
		final float centerX = canvas.getWidth() / 2.f;
		// Центр экрана по Y
		final float centerY = canvas.getHeight() / 2.f;
		// Длина стрелки от центра до края - отступ
		final float lengthFull = minScreenSize / 2.f * (1.f - indentPercent / 100.f);
		// Длина короткого деления
		final float lengthShort = minScreenSize * sizePercent / 100.f;
		// Длина длинного деления (в 2 раза длиннее короткого)
		final float lengthLong = minScreenSize * sizePercent / 50.f;

		// Рисование делений
		for (int k = 0; k < 60; ++k) {

			// Признак пятой секунды
			// для длинного деления
			boolean isFivesSecond = k % 5 == 0;

			paint.setStrokeWidth(isFivesSecond ? 2 * strokeWidth : strokeWidth);

			// Угол наклона деления в радианах
			final float angleInRadians = (float) (2.f * Math.PI * (k / 60.f));

			// Вычисление начальной точки деления (ближе к центру)
			float startX = (float) (centerX + Math.sin(angleInRadians) * (lengthFull - (isFivesSecond ? lengthLong : lengthShort)));
			float startY = (float) (centerY + Math.cos(angleInRadians) * (lengthFull - (isFivesSecond ? lengthLong : lengthShort)));

			// Вычисление конечной точки деления (ближе к краям экрана)
			float endX = (float) (centerX + Math.sin(angleInRadians) * lengthFull);
			float endY = (float) (centerY + Math.cos(angleInRadians) * lengthFull);

			// Отрисовка линии
			canvas.drawLine(startX, startY, endX, endY, paint);
		}

		// Рисование круга в центре циферблата
		final float centralCircleRadius = minScreenSize * sizePercent / 500.f;
		canvas.drawCircle(centerX, centerY, centralCircleRadius, paint);
	}
}
