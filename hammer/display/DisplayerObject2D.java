package hammer.display;

import hammer.geom.Matrix2D;
import hammer.geom.Point2D;
import hammer.textures.Texture2D;

/**
 * 显示对象基类
 * @-式神-
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
	 * 获取父级对象
	 * @return
	 */
	public DisplayerObject2D getParent()
	{
		return this.parent;
	}
	
	/**
	 * 设置父级对象
	 * @param value
	 */
	public void setParent(DisplayerObject2D value)
	{
		this.parent=value;
	}
	
	/**
	 * 全局坐标转换
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
	 * 设置对象的宽度
	 * @param value
	 */
	public void setHeight(float value)
	{
		this.mHeight=value;
	}
	
	/**
	 * 获取对象的宽度
	 * @return
	 */
	public float getHeight()
	{
		return this.mHeight*this.mScaleY;
	}
	
	
	/**
	 * 设置对象的宽度
	 * @param value
	 */
	public void setWidth(float value)
	{
		this.mWidth=value;
	}
	
	/**
	 * 获取对象的宽度
	 * @return
	 */
	public float getWidth()
	{
		return this.mWidth*this.mScaleX;
	}
	
	
	/**
	 * 是否隐藏显示对象
	 * @param value
	 */
	public void setVisible(boolean value)
	{
		this.mVisible=value;
	}
	
	/**
	 * 获取显示对子昂的状态
	 * @return
	 */
	public boolean getVisible()
	{
		return this.mVisible;
	}
	/**
	 * 获得对象的旋转角度
	 * @return
	 */
	public float getRotation()
	{
		return this.mRotation;
	}
	
	/**
	 * 设置对象的旋转角度
	 * @param value
	 */
	public void setRotation(float value)
	{
		this.mRotation=value;
	}
	
	/**
	 * 获取对象X轴倾斜值
	 */
	public float getBiasX()
	{
		return this.mBiasX;
	}
	/**
	 * 设置对象的X轴倾斜值
	 * @param value
	 */
	public void setBiasX(float value)
	{
		this.mBiasX=value;
	}
	
	/**
	 * 获取对象Y轴倾斜值
	 */
	public float getBiasY()
	{
		return this.mBiasY;
	}
	/**
	 * 设置对象的Y轴倾斜值
	 * @param value
	 */
	public void setBiasY(float value)
	{
		this.mBiasY=value;
	}
	
	/**
	 * 获取对象的X旋转中心
	 * @return
	 */
	public float getPivotX()
	{
		return this.mPivotX;
	}
	
	/**
	 * 设置对象的X旋转中心
	 * @param value
	 */
	public void setPivotX(float value)
	{
		this.mPivotX=value;
	}
	
	
	/**
	 * 获取对象的Y旋转中心
	 * @return
	 */
	public float getPivotY()
	{
		return this.mPivotY;
	}
	
	/**
	 * 设置对象的Y旋转中心
	 * @param value
	 */
	public void setPivotY(float value)
	{
		this.mPivotY=value;
	}
	/**
	 * 获取对象的A通道值
	 * @return
	 */
	public float getA()
	{
		return this.mA;
	}
	
	/**
	 * 设置对象的A通道值
	 * @param value
	 */
	public void setA(float value)
	{
		this.mA=value;
	}
	
	/**
	 * 获取对象的B通道值
	 * @return
	 */
	public float getB()
	{
		return this.mB;
	}
	
	/**
	 * 设置对象的B通道值
	 * @param value
	 */
	public void setB(float value)
	{
		this.mB=value;
	}
	
	/**
	 * 获取对象的G通道值
	 * @return
	 */
	public float getG()
	{
		return this.mG;
	}
	
	/**
	 * 设置对象的G通道值
	 * @param value
	 */
	public void setG(float value)
	{
		this.mG=value;
	}
	
	
	/**
	 * 获取对象的R通道值
	 * @return
	 */
	public float getR()
	{
		return this.mR;
	}
	
	/**
	 * 设置对象的R通道值
	 * @param value
	 */
	public void setR(float value)
	{
		this.mR=value;
	}
	
	/**
	 * 设置对象的透明度
	 * @param value
	 */
	public void setAlpha(float value)
	{
		this.mAlpha=value;
	}
	
	/**
	 * 获取对象的透明度
	 * @return
	 */
	public float getAlpha()
	{
		return this.mAlpha;
	}
	
	/**
	 * 获取对象的Y轴比例值
	 * @return
	 */
	public float getScaleY()
	{
		return this.mScaleY;
	}
	
	/**
	 * 设置对象的Y轴比例值
	 * @param value
	 */
	public void setScaleY(float value)
	{
		this.mScaleY=value;
	}
	/**
	 * 获取对象的X比例
	 * @return
	 */
	public float getScaleX()
	{
		return mScaleX;
	}
	
	/**
	 * 设置对象的X比例
	 * @param value
	 */
	public void setScaleX(float value)
	{
		this.mScaleX=value;
	}
	
	/**
	 * 获取对象的X轴的坐标
	 * @return
	 */
	public float getX() {
		return mX;
	}

	/**
	 * 设置对象X轴的坐标
	 * @param x
	 */
	public void setX(float value) {
		this.mX = value;
	}
	
	/**
	 * 获取对象Y轴的坐标
	 * @return
	 */
	public float getY() {
		return mY;
	}

	/**
	 * 设置对象的Y轴的坐标
	 * @param x
	 */
	public void setY(float value) {
		this.mY = value;
	}

	/**
	 * 监测是否支持鼠标事件
	 * @return
	 */
	public boolean isMouseEnabled() {
		return mouseEnabled;
	}

	/**
	 * 设置鼠标支持函数
	 * @param mouseEnabled
	 */
	public void setMouseEnabled(boolean mouseEnabled) {
		this.mouseEnabled = mouseEnabled;
	}

	/**
	 * 获取是否精确点击
	 * @return
	 */
	public boolean isMouseAccurate() {
		return mouseAccurate;
	}

	/**
	 * 设置鼠标精确点击判断
	 * @param mouseAccurate
	 */
	public void setMouseAccurate(boolean mouseAccurate) {
		this.mouseAccurate = mouseAccurate;
	}

	/**
	 * 是否居中对其
	 * @return
	 */
	public boolean isPivotMiddle() {
		return pivotMiddle;
	}

	/**
	 * 设置居中对其
	 * @param pivotMiddle
	 */
	public void setPivotMiddle(boolean pivotMiddle) {
		this.pivotMiddle = pivotMiddle;
	}

	/**
	 * 获取矩阵信息
	 * @return
	 */
	public Matrix2D getMatrix2D() {
		return matrix2D;
	}

	/**
	 * 设置矩阵
	 * @param matrix2D
	 */
	public void setMatrix2D(Matrix2D matrix2D) {
		this.matrix2D = matrix2D;
	}
}
