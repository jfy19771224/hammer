package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.grain.GrainSend;
import hammer.opengl2d.System2D;
import hammer.textures.Texture2D;
import hammer.ui.Mouse2D;
import hammer.ui.MouseEvent2D;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @-小神- 联系QQ:790763049
 *
 */
public class MainTest6 extends Activity{
	
	 //创建2D场景
	 private Stage2D stage2d;
	
	 //粒子发射器
	 private GrainSend grainSend;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //初始化2D场景,指定GL的版本号,1或者2
		 stage2d=new Stage2D(this,2);
		 
		 //侦听初始化函数
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //侦听鼠标事件
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_MOVE, new Function(this,"mouseMove"));

	 }
	 
	 /**
	  * 鼠标事件回调函数
	  * @param e
	  */
	 public void mouseMove(MouseEvent2D e)
	 {
		 grainSend.getLauncher(0).x=Mouse2D.getMotionEvent().getX();
		 grainSend.getLauncher(0).y=Mouse2D.getMotionEvent().getY();
	 }
	 
	 public void init()
	 {
		 //粒子编辑器目前是AS3版本,之后会加入JAVA版
		 
		 //创建一个粒子发射器,传递纹理,粒子池数量,发射器数量
		 grainSend=new GrainSend(new Texture2D("res/Circle.png"),200,2);
		 grainSend.setDelay(3);
		 stage2d.addChild(grainSend);
		 
		 //获取第一个发射器并且启动
		 grainSend.getLauncher(0).start=true;
	 }
}
