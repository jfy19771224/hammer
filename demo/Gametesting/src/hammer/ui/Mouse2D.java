package hammer.ui;

import android.view.MotionEvent;

/**
 *  Û±Í¿‡
 * @author apple
 *
 */
public class Mouse2D {
	private static MotionEvent motionEvent;
	public Mouse2D()
	{
		Mouse2D.getMotionEvent().getX();
	}
	public static MotionEvent getMotionEvent() {
		return motionEvent;
	}
	public static void setMotionEvent(MotionEvent motionEvent) {
		Mouse2D.motionEvent = motionEvent;
	}
}
