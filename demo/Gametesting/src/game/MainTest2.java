package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.textures.Texture2D;
import hammer.ui.MouseEvent2D;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @-小神- 联系QQ:790763049
 *
 */
public class MainTest2 extends Activity{
	
	 //创建2D场景
	 private Stage2D stage2d;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //初始化2D场景,指定GL的版本号,1或者2
		 stage2d=new Stage2D(this,2);
		 
		 //侦听初始化函数
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //侦听鼠标事件
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_DOWN, new Function(this,"mouseDown"));
	 }
	 
	 /**
	  * 鼠标事件回调函数
	  * @param e
	  */
	 public void mouseDown(MouseEvent2D e)
	 {
		 //检测当前点击对象是否为null
		 if(e.getTarget()!=null)
		 {
			 //改变当前点击对象的的某个值
			 e.getTarget().setRotation(45);
		 }
	 }
	 
	 public void init()
	 {
		 //创建纹理
		 Texture2D texture=new Texture2D("res/1.jpg");
		 
		 //创建一个显示容器
		 Sprite2D sprite=new Sprite2D(texture);
		 
		 //添加到场景
		 stage2d.addChild(sprite);
	 }
}
