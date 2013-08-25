package hammer.grain;

/**
 * 粒子发射器
 * @-式神-
 *
 */
public class Launcher {
		public double x = 0;
		public double y = 0;
		public boolean start;
		public int left = 400;
		public int right = 400;
		public int up = 400;
		public int down = 400;
		
		public int delay;
		public int coincide;
		public float scaleX;
		public float scaleY;
		public float alpha;
		
		/**
		 * 初始点
		 */
		public double scopeX = 0;
		public double scopeY = 0;
		
		public double alphaValue = -.005;
		public double scaleXValue = -.005;
		public double scaleYValue = -.005;
		
		public double aValue = 0;
		public double rValue = 0;
		public double gValue = 0;
		public double bValue = 0;
		
		public double angleValue = 0;
		public double rotationValue = 0;
		public double rotationRandom = 0;
		public double speedValue = 0;
		
		public boolean startRotation;
		
		
		public double r = 1;
		public double g = 1;
		public double b = 1;
		public double a = 1;
		
		public boolean visible=true;
		
		public Launcher()
		{
			
		}

}
