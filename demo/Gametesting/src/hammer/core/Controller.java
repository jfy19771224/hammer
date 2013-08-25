package hammer.core;
import javax.microedition.khronos.opengles.GL10;
import android.view.KeyEvent;
import android.view.MotionEvent;
import hammer.display.Sprite2D;
import hammer.opengl2d.OpenGLDisplayList;

/**
 * 控制器,控制整个flash的核心运行
 * @author 花菜游戏
 *
 */
public class Controller {	
	public Controller()
	{
		
	}
	/**
	 * 重绘
	 */
	public void paint(GL10 gl)
	{
		Sprite2D[] vessel=OpenGLDisplayList.target().getVessel();
		Sprite2D target;
		
		for (Sprite2D m : vessel) {
			if(m!=null)
			{
				target=m;
				target.paint(gl);
			}
		}
		
	}
}
