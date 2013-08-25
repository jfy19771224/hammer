package hammer.grain;

import hammer.display.Quad;

/**
 * 粒子
 * @-式神-
 *
 */
public class Grain {
	public Quad skin;
	
	private boolean mStart;
	
	
	private double alphaValue = -.005;
	private double scaleXValue = -.005;
	private double scaleYValue = -.005;
	
	private double aValue = 0;
	private double rValue = 0;
	private double gValue = 0;
	private double bValue = 0;
	
	private double angleValue = 0;
	private double rotationValue = 0;
	
	private double speedValue = 1;
	
	private boolean startRotation;
	private int left = 200;
	private int right = 200;
	
	private int up = 200;
	private int down = 200;
	
	
	public Grain(Quad skin)
	{
		this.skin=skin;
		setaValue(0);
		setrValue(-.02);
		setgValue(-.03);
		setbValue(-.01);
	}
	
	private float mR;
	private float mG;
	private float mB;
	private float mA;
	
	/**
	 * 粒子更新函数
	 */
	public void upData()
	{
		
		//旋转角度
		this.setRotationValue(this.getRotationValue() + getAngleValue());
		//透明度
		this.skin.setAlpha((float) (this.skin.getAlpha()+getAlphaValue()));
		//比例X
		this.skin.setScaleX((float) (this.skin.getScaleX()+getScaleXValue()));
		//比例Y
		this.skin.setScaleY((float) (this.skin.getScaleY()+getScaleYValue()));
		
	
		//颜色
		this.skin.setA((float) (this.skin.getA()+getaValue()));
		this.skin.setR((float) (this.skin.getR()+getrValue()));
		this.skin.setG((float) (this.skin.getG()+getgValue()));
		this.skin.setB((float) (this.skin.getB()+getbValue()));
		this.skin.setX((float) (this.skin.getX()+ Math.cos(getRotationValue()) * getSpeedValue()));
		this.skin.setY((float) (this.skin.getY()+ Math.sin(getRotationValue()) * getSpeedValue()));
		
		//是否开启角度宣闸un
		if(isStartRotation())
		this.skin.setRotation((float) getRotationValue());

		//判断死亡条件,透明度为0或者超出粒子运动范围
		if (this.skin.getAlpha() <= 0||this.skin.getY()>getDown()||this.skin.getY()<-getUp()||this.skin.getX()>getRight()||this.skin.getX()<-getLeft())
		{
			this.mStart = false;
			this.clear();
		}
	}

	private void clear()
	{
		mStart=false;
		this.skin.setVisible(false);
	}
	
	public boolean getStart() {
		return mStart;
	}

	public void setStart(boolean bool) {
		this.mStart = bool;
		this.skin.setAlpha(1);
		this.skin.setVisible(true);
	}

	public boolean isStartRotation() {
		return startRotation;
	}

	public void setStartRotation(boolean startRotation) {
		this.startRotation = startRotation;
	}

	public double getaValue() {
		return aValue;
	}

	public void setaValue(double aValue) {
		this.aValue = aValue;
	}

	public double getgValue() {
		return gValue;
	}

	public void setgValue(double gValue) {
		this.gValue = gValue;
	}

	public double getrValue() {
		return rValue;
	}

	public void setrValue(double rValue) {
		this.rValue = rValue;
	}

	public double getbValue() {
		return bValue;
	}

	public void setbValue(double bValue) {
		this.bValue = bValue;
	}

	public double getAngleValue() {
		return angleValue;
	}

	public void setAngleValue(double angleValue) {
		this.angleValue = angleValue;
	}

	public double getAlphaValue() {
		return alphaValue;
	}

	public void setAlphaValue(double alphaValue) {
		this.alphaValue = alphaValue;
	}

	public double getScaleYValue() {
		return scaleYValue;
	}

	public void setScaleYValue(double scaleYValue) {
		this.scaleYValue = scaleYValue;
	}

	public double getRotationValue() {
		return rotationValue;
	}

	public void setRotationValue(double rotationValue) {
		this.rotationValue = rotationValue;
	}

	public double getScaleXValue() {
		return scaleXValue;
	}

	public void setScaleXValue(double scaleXValue) {
		this.scaleXValue = scaleXValue;
	}

	public double getSpeedValue() {
		return speedValue;
	}

	public void setSpeedValue(double speedValue) {
		this.speedValue = speedValue;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
}
