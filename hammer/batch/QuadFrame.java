package hammer.batch;

import java.lang.reflect.Array;

/**
 * 动画帧
 * @-式神-
 *
 */
public class QuadFrame {
	/**
	 * 帧名称
	 */
	private String mName;
	
	/**
	 * 帧X坐标
	 */
	private float mX = 0;
	
	/**
	 * 帧Y坐标
	 */
	private float mY = 0;
	
	/**
	 * 帧宽度
	 */
	private float mWidth = 0;
	
	/**
	 * 帧高度
	 */
	private float mHeight = 0;
	
	/**
	 * 帧X偏移坐标
	 */
	private float mFrameX = 0;
	
	/**
	 * 帧Y偏移坐标
	 */
	private float mFrameY = 0;
	
	/**
	 * 帧最大宽度
	 */
	private float mFrameWidth = 0;
	
	/**
	 * 帧最大高度
	 */
	private float mFrameHeight = 0;
	public QuadFrame() 
	{
		
	}
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.mName = name;
	}
	
	/**
	 * 获取X坐标
	 * @return
	 */
	public float getX() {
		return mX;
	}
	
	/**
	 * 设置X坐标
	 * @param x
	 */
	public void setX(float x) {
		this.mX = x;
	}
	
	/**
	 * 获取Y坐标
	 * @return
	 */
	public float getY() {
		return mY;
	}
	
	/**
	 * 设置Y坐标
	 * @param y
	 */
	public void setY(float y) {
		this.mY = y;
	}
	
	/**
	 * 获取宽度
	 * @return
	 */
	public float getWidth() {
		return mWidth;
	}
	
	/**
	 * 设置宽度
	 * @param width
	 */
	public void setWidth(float width) {
		this.mWidth = width;
	}
	
	/**
	 * 获取高度
	 * @return
	 */
	public float getHeight() {
		return mHeight;
	}
	
	/**
	 * 设置高度
	 * @param height
	 */
	public void setHeight(float height) {
		this.mHeight = height;
	}
	
	/**
	 * 获取帧偏移X坐标
	 * @return
	 */
	public float getFrameX() {
		return mFrameX;
	}
	
	/**
	 * 设置帧偏移X坐标
	 * @param frameX
	 */
	public void setFrameX(float frameX) {
		this.mFrameX = frameX;
	}
	
	/**
	 * 获取帧偏移Y坐标
	 * @return
	 */
	public float getFrameY() {
		return mFrameY;
	}
	
	/**
	 * 设置帧偏移Y坐标
	 * @param frameY
	 */
	public void setFrameY(float frameY) {
		this.mFrameY = frameY;
	}
	
	/**
	 * 获取帧宽度
	 * @return
	 */
	public float getFrameWidth() {
		return mFrameWidth;
	}
	
	/**
	 * 设置帧宽度
	 * @param frameWidth
	 */
	public void setFrameWidth(float frameWidth) {
		this.mFrameWidth = frameWidth;
	}
	
	/**
	 * 获取帧长度
	 * @return
	 */
	public float getFrameHeight() {
		return mFrameHeight;
	}
	
	/**
	 * 设置帧长度
	 * @param frameHeight
	 */
	public void setFrameHeight(float frameHeight) {
		this.mFrameHeight = frameHeight;
	}
}
