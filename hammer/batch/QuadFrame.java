package hammer.batch;

import java.lang.reflect.Array;

/**
 * ����֡
 * @-ʽ��-
 *
 */
public class QuadFrame {
	/**
	 * ֡����
	 */
	private String mName;
	
	/**
	 * ֡X����
	 */
	private float mX = 0;
	
	/**
	 * ֡Y����
	 */
	private float mY = 0;
	
	/**
	 * ֡���
	 */
	private float mWidth = 0;
	
	/**
	 * ֡�߶�
	 */
	private float mHeight = 0;
	
	/**
	 * ֡Xƫ������
	 */
	private float mFrameX = 0;
	
	/**
	 * ֡Yƫ������
	 */
	private float mFrameY = 0;
	
	/**
	 * ֡�����
	 */
	private float mFrameWidth = 0;
	
	/**
	 * ֡���߶�
	 */
	private float mFrameHeight = 0;
	public QuadFrame() 
	{
		
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * ��������
	 * @param name
	 */
	public void setName(String name) {
		this.mName = name;
	}
	
	/**
	 * ��ȡX����
	 * @return
	 */
	public float getX() {
		return mX;
	}
	
	/**
	 * ����X����
	 * @param x
	 */
	public void setX(float x) {
		this.mX = x;
	}
	
	/**
	 * ��ȡY����
	 * @return
	 */
	public float getY() {
		return mY;
	}
	
	/**
	 * ����Y����
	 * @param y
	 */
	public void setY(float y) {
		this.mY = y;
	}
	
	/**
	 * ��ȡ���
	 * @return
	 */
	public float getWidth() {
		return mWidth;
	}
	
	/**
	 * ���ÿ��
	 * @param width
	 */
	public void setWidth(float width) {
		this.mWidth = width;
	}
	
	/**
	 * ��ȡ�߶�
	 * @return
	 */
	public float getHeight() {
		return mHeight;
	}
	
	/**
	 * ���ø߶�
	 * @param height
	 */
	public void setHeight(float height) {
		this.mHeight = height;
	}
	
	/**
	 * ��ȡ֡ƫ��X����
	 * @return
	 */
	public float getFrameX() {
		return mFrameX;
	}
	
	/**
	 * ����֡ƫ��X����
	 * @param frameX
	 */
	public void setFrameX(float frameX) {
		this.mFrameX = frameX;
	}
	
	/**
	 * ��ȡ֡ƫ��Y����
	 * @return
	 */
	public float getFrameY() {
		return mFrameY;
	}
	
	/**
	 * ����֡ƫ��Y����
	 * @param frameY
	 */
	public void setFrameY(float frameY) {
		this.mFrameY = frameY;
	}
	
	/**
	 * ��ȡ֡���
	 * @return
	 */
	public float getFrameWidth() {
		return mFrameWidth;
	}
	
	/**
	 * ����֡���
	 * @param frameWidth
	 */
	public void setFrameWidth(float frameWidth) {
		this.mFrameWidth = frameWidth;
	}
	
	/**
	 * ��ȡ֡����
	 * @return
	 */
	public float getFrameHeight() {
		return mFrameHeight;
	}
	
	/**
	 * ����֡����
	 * @param frameHeight
	 */
	public void setFrameHeight(float frameHeight) {
		this.mFrameHeight = frameHeight;
	}
}
