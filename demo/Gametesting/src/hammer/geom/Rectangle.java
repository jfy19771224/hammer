package hammer.geom;

/**
 * 矩形
 * @-式神-
 *
 */
public class Rectangle {
	private float x;
	private float y;
	private float width;
	private float height;
	
	private float cX;
	private float cY;
	private float cWidth;
	private float cHeight;
	public Rectangle(float x,float y,float width,float height)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		this.cX=this.x;
		this.cY=this.y;
		this.cWidth=this.width;
		this.cHeight=this.height;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public void setX(float value)
	{
		 this.x=value;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void setY(float value)
	{
		this.y=value;
	}
	
	public float getWidth()
	{
		return this.width;
	}
	
	public void setWidth(float value)
	{
		this.width=value;
	}
	
	public float getHeight()
	{
		return this.height;
	}
	
	public void setHeight(float value)
	{
		this.height=value;
	}
	/**
	 * 重置
	 */
	public void reset()
	{
		this.cX=this.x;
		this.cY=this.y;
		this.cWidth=this.width;
		this.cHeight=this.height;
	}
	/**
	 * 监测属性状态是否改变
	 * @return
	 */
	public boolean isState()
	{
		if(this.x!=this.cX||this.y!=this.cY||this.cWidth!=this.width||this.cHeight!=this.height)
		{
			this.cX=this.x;
			this.cY=this.y;
			this.cWidth=this.width;
			this.cHeight=this.height;
			return true;
		}
		return false;
	}
}
