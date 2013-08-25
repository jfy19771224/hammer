package game.src;

import android.util.Log;
import hammer.display.Quad;

public class UserData {
	public float x;
	public float y;
	public int row;
	public int line;
	public int id;
	public boolean start;
	public float scale=1;
	
	public float vx;
	public float targetX;
	public float friction=.3f;
	public float spring=.9f;
	private Quad quad;
	public UserData(Quad quad)
	{
		this.quad=quad;	
	}
	
	
	public void run()
	{
		
		if(Math.abs(quad.getX()-x)<=1)
		{
			quad.setX(x);
		}else
		{
			quad.setX(quad.getX()+(x-quad.getX())/5);
		}
		if(Math.abs(quad.getY()-y)<=1)
		{
			quad.setY(y);
		}else
		{
			quad.setY(quad.getY()+(y-quad.getY())/5);
		}
		if(scale>1)
		{
			vx += (scale-quad.getScaleX()) * spring;
			quad.setScaleX(quad.getScaleX()+vx*friction);
			quad.setScaleY(quad.getScaleX());
			quad.setG(1.5f);
			quad.setG(1.5f);
			quad.setB(1.5f);
			quad.setRotation(quad.getRotation()+3);
		}else
		{
			
			if(Math.abs(scale-quad.getScaleX())<=.2)
			{
				vx += (scale-quad.getScaleX()) * spring;
				quad.setScaleX(1);
				quad.setScaleY(1);
				quad.setR(1);
				quad.setG(1);
				quad.setB(1);
				quad.setRotation(0);
			}else		
			{
				vx += (scale-quad.getScaleX()) * spring;
				quad.setScaleX(quad.getScaleX()+vx*friction);
				quad.setScaleY(quad.getScaleX());
				quad.setR(1);
				quad.setG(1);
				quad.setB(1);
				quad.setRotation(0);
			}
			
		}
	}
}
