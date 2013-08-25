package hammer.math;

import hammer.geom.Point2D;

public class Math2D {
	/**
	 * Ãæ»ý¼ì²â
	 * @param	p1
	 * @param	p2
	 * @param	p3
	 * @return
	 */
	public static int hitTrianglePoint(Point2D p1,Point2D p2,Point2D p3)
	{
		if ((p2.getX()-p1.getX())*(p2.getY()+p1.getY())+(p3.getX()-p2.getX())*(p3.getY()+p2.getY())+(p1.getX()-p3.getX())*(p1.getY()+p3.getY())<0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	/**
	 * ¶¥µãÅö×²¼ì²â
	 * p1,p2,p3 Îª·¶Î§µã
	 * p4ÊÇÅö×²µã¡£
	 * @return
	 */
	public static boolean hitPoint(Point2D p1,Point2D p2,Point2D p3,Point2D p4)
	{
		int a = hitTrianglePoint(p1,p2,p3);
		int b = hitTrianglePoint(p4,p2,p3);
		int c = hitTrianglePoint(p1,p2,p4);
		int d = hitTrianglePoint(p1,p4,p3);
		if ((b==a)&&(c==a)&&(d==a))
		{
			return true;
		}
		else
		{
			return false;

		}
	}
}
