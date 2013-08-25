package hammer.display;

import hammer.geom.Matrix2D;
import hammer.geom.Point2D;
import hammer.textures.Texture2D;

/**
 * ��ʾ�������
 * @-ʽ��-
 *
 */
public class DisplayerObject2D {
	private boolean mouseEnabled=true;
	private DisplayerObject2D parent;
	private Matrix2D matrix2D;
	private boolean mouseAccurate;
	private boolean pivotMiddle;
	
	protected float mX;
	protected float mY;
	protected float mR;
	protected float mG;
	protected float mB;
	protected float mA;
	protected float mWidth;
	protected float mHeight;
	protected float mScaleX;
	protected float mScaleY;
	protected float mAlpha;
	protected float mPivotX;
	protected float mPivotY;
	protected float mBiasX;
	protected float mBiasY;
	protected float mRotation;
	protected boolean mVisible;
	
	public DisplayerObject2D() {
		mR=1f;
		mG=1f;
		mB=1f;
		mA=1;
		mAlpha=1;
		
		mRotation=0;
		mX=0;
		mY=0;
		
		mScaleX=1;
		mScaleY=1;
		mPivotX=0;
		mPivotY=0;
		mBiasX=0;
		mBiasY=0;
		
		mVisible=true;
		setMouseEnabled(true);
		setMouseAccurate(false);
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public DisplayerObject2D getParent()
	{
		return this.parent;
	}
	
	/**
	 * ���ø�������
	 * @param value
	 */
	public void setParent(DisplayerObject2D value)
	{
		this.parent=value;
	}
	
	/**
	 * ȫ������ת��
	 * @param point
	 * @return
	 */
	public Point2D localToGlobal()
	{
		Point2D point = null;
		if(this.parent!=null)
		{
			 point=new Point2D(this.getX()+this.parent.localToGlobal().getX(),this.getY());
		}
	
		return point;
	}
	
	
	/**
	 * ���ö���Ŀ��
	 * @param value
	 */
	public void setHeight(float value)
	{
		this.mHeight=value;
	}
	
	/**
	 * ��ȡ����Ŀ��
	 * @return
	 */
	public float getHeight()
	{
		return this.mHeight*this.mScaleY;
	}
	
	
	/**
	 * ���ö���Ŀ��
	 * @param value
	 */
	public void setWidth(float value)
	{
		this.mWidth=value;
	}
	
	/**
	 * ��ȡ����Ŀ��
	 * @return
	 */
	public float getWidth()
	{
		return this.mWidth*this.mScaleX;
	}
	
	
	/**
	 * �Ƿ�������ʾ����
	 * @param value
	 */
	public void setVisible(boolean value)
	{
		this.mVisible=value;
	}
	
	/**
	 * ��ȡ��ʾ���Ӱ���״̬
	 * @return
	 */
	public boolean getVisible()
	{
		return this.mVisible;
	}
	/**
	 * ��ö������ת�Ƕ�
	 * @return
	 */
	public float getRotation()
	{
		return this.mRotation;
	}
	
	/**
	 * ���ö������ת�Ƕ�
	 * @param value
	 */
	public void setRotation(float value)
	{
		this.mRotation=value;
	}
	
	/**
	 * ��ȡ����X����бֵ
	 */
	public float getBiasX()
	{
		return this.mBiasX;
	}
	/**
	 * ���ö����X����бֵ
	 * @param value
	 */
	public void setBiasX(float value)
	{
		this.mBiasX=value;
	}
	
	/**
	 * ��ȡ����Y����бֵ
	 */
	public float getBiasY()
	{
		return this.mBiasY;
	}
	/**
	 * ���ö����Y����бֵ
	 * @param value
	 */
	public void setBiasY(float value)
	{
		this.mBiasY=value;
	}
	
	/**
	 * ��ȡ�����X��ת����
	 * @return
	 */
	public float getPivotX()
	{
		return this.mPivotX;
	}
	
	/**
	 * ���ö����X��ת����
	 * @param value
	 */
	public void setPivotX(float value)
	{
		this.mPivotX=value;
	}
	
	
	/**
	 * ��ȡ�����Y��ת����
	 * @return
	 */
	public float getPivotY()
	{
		return this.mPivotY;
	}
	
	/**
	 * ���ö����Y��ת����
	 * @param value
	 */
	public void setPivotY(float value)
	{
		this.mPivotY=value;
	}
	/**
	 * ��ȡ�����Aͨ��ֵ
	 * @return
	 */
	public float getA()
	{
		return this.mA;
	}
	
	/**
	 * ���ö����Aͨ��ֵ
	 * @param value
	 */
	public void setA(float value)
	{
		this.mA=value;
	}
	
	/**
	 * ��ȡ�����Bͨ��ֵ
	 * @return
	 */
	public float getB()
	{
		return this.mB;
	}
	
	/**
	 * ���ö����Bͨ��ֵ
	 * @param value
	 */
	public void setB(float value)
	{
		this.mB=value;
	}
	
	/**
	 * ��ȡ�����Gͨ��ֵ
	 * @return
	 */
	public float getG()
	{
		return this.mG;
	}
	
	/**
	 * ���ö����Gͨ��ֵ
	 * @param value
	 */
	public void setG(float value)
	{
		this.mG=value;
	}
	
	
	/**
	 * ��ȡ�����Rͨ��ֵ
	 * @return
	 */
	public float getR()
	{
		return this.mR;
	}
	
	/**
	 * ���ö����Rͨ��ֵ
	 * @param value
	 */
	public void setR(float value)
	{
		this.mR=value;
	}
	
	/**
	 * ���ö����͸����
	 * @param value
	 */
	public void setAlpha(float value)
	{
		this.mAlpha=value;
	}
	
	/**
	 * ��ȡ�����͸����
	 * @return
	 */
	public float getAlpha()
	{
		return this.mAlpha;
	}
	
	/**
	 * ��ȡ�����Y�����ֵ
	 * @return
	 */
	public float getScaleY()
	{
		return this.mScaleY;
	}
	
	/**
	 * ���ö����Y�����ֵ
	 * @param value
	 */
	public void setScaleY(float value)
	{
		this.mScaleY=value;
	}
	/**
	 * ��ȡ�����X����
	 * @return
	 */
	public float getScaleX()
	{
		return mScaleX;
	}
	
	/**
	 * ���ö����X����
	 * @param value
	 */
	public void setScaleX(float value)
	{
		this.mScaleX=value;
	}
	
	/**
	 * ��ȡ�����X�������
	 * @return
	 */
	public float getX() {
		return mX;
	}

	/**
	 * ���ö���X�������
	 * @param x
	 */
	public void setX(float value) {
		this.mX = value;
	}
	
	/**
	 * ��ȡ����Y�������
	 * @return
	 */
	public float getY() {
		return mY;
	}

	/**
	 * ���ö����Y�������
	 * @param x
	 */
	public void setY(float value) {
		this.mY = value;
	}

	/**
	 * ����Ƿ�֧������¼�
	 * @return
	 */
	public boolean isMouseEnabled() {
		return mouseEnabled;
	}

	/**
	 * �������֧�ֺ���
	 * @param mouseEnabled
	 */
	public void setMouseEnabled(boolean mouseEnabled) {
		this.mouseEnabled = mouseEnabled;
	}

	/**
	 * ��ȡ�Ƿ�ȷ���
	 * @return
	 */
	public boolean isMouseAccurate() {
		return mouseAccurate;
	}

	/**
	 * ������꾫ȷ����ж�
	 * @param mouseAccurate
	 */
	public void setMouseAccurate(boolean mouseAccurate) {
		this.mouseAccurate = mouseAccurate;
	}

	/**
	 * �Ƿ���ж���
	 * @return
	 */
	public boolean isPivotMiddle() {
		return pivotMiddle;
	}

	/**
	 * ���þ��ж���
	 * @param pivotMiddle
	 */
	public void setPivotMiddle(boolean pivotMiddle) {
		this.pivotMiddle = pivotMiddle;
	}

	/**
	 * ��ȡ������Ϣ
	 * @return
	 */
	public Matrix2D getMatrix2D() {
		return matrix2D;
	}

	/**
	 * ���þ���
	 * @param matrix2D
	 */
	public void setMatrix2D(Matrix2D matrix2D) {
		this.matrix2D = matrix2D;
	}
}
