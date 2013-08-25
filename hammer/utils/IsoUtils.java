package hammer.utils;

import hammer.geom.Point2D;

/**
 * 2.5D坐标系转换
 * @author icekiller
 */
public class IsoUtils 
{
	
	public IsoUtils() 
	{
		
	}
	
	public static Point2D screenXYToIos(float sx,float sy,float tileWidth) {
		int tileX  = (int) ((sy + sx * 0.5) / (tileWidth * 0.5));
		int tileY  = (int) ((sy - sx * 0.5) / (tileWidth * 0.5));
		return new Point2D(tileX,tileY);
	}
	
	//屏幕坐标转换为2.5D坐标
	public static Point2D screenToIos(Point2D point,float tileWidth)
	{
		int sx = (int) ((point.getY() + point.getX() * 0.5) / (tileWidth * 0.5));
		int sy = (int) ((point.getY() - point.getX() * 0.5) / (tileWidth * 0.5));
		return new Point2D(sx, sy);
	}
	
	public static Point2D isoToscreen(Point2D point,float tileWidth)
	{
		float screenx = (float) ((point.getX() - point.getY()) * tileWidth * 0.5);
		float screeny = (float) ((point.getX() + point.getY()) * tileWidth * 0.5 * 0.5);
		return new Point2D(screenx, screeny);
	}
	
	//
	public static Point2D isoToScreenXY(int tilex, int tiley,float tileWidth)
	{
		float screenx = (float) ((tilex - tiley) * tileWidth * 0.5);
		float screeny = (float) ((tilex + tiley) * tileWidth * 0.5 * 0.5);
		return new Point2D(screenx, screeny);
	}
	
}