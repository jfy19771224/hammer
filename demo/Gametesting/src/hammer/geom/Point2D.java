package hammer.geom;

/**
 * 2D�����
 * @author apple
 *
 */
public class Point2D {
	private float x;
	private float y;
	public Point2D(float x,float y)
	{
		this.setX(x);
		this.setY(y);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
