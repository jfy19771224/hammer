package hammer.ui;

import android.view.MotionEvent;
import hammer.display.DisplayerObject2D;

/**
 * �������
 * @-ʽ��-
 *
 */
public class MouseEvent2D {
	/**
	 * ��ǰ�������
	 */
	private DisplayerObject2D target;
	
	/**
	 * ��ǰ�������
	 */
	private DisplayerObject2D targetSprite;
	
	/**
	 * ע�����
	 */
	private DisplayerObject2D currentTarget;
	private String type;
	
	private MotionEvent motionEvent;
	
	public MouseEvent2D()
	{
		setTarget(null);
	}

	public DisplayerObject2D getTarget() {
		return target;
	}

	public void setTarget(DisplayerObject2D target) {
		this.target = target;
	}

	public DisplayerObject2D getTargetSprite() {
		return targetSprite;
	}

	public void setTargetSprite(DisplayerObject2D targetSprite) {
		this.targetSprite = targetSprite;
	}

	public DisplayerObject2D getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(DisplayerObject2D currentTarget) {
		this.currentTarget = currentTarget;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MotionEvent getMotionEvent() {
		return motionEvent;
	}

	public void setMotionEvent(MotionEvent motionEvent) {
		this.motionEvent = motionEvent;
	}
}
