package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.opengl2d.System2D;
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
public class MainTest5 extends Activity{
	
	 //创建2D场景
	 private Stage2D stage2d;
	 
	 //为了全局控制
	 private Sprite2D sprite;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //初始化2D场景,指定GL的版本号,1或者2
		 stage2d=new Stage2D(this,2);
		 
		 //侦听初始化函数
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //侦听鼠标事件
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_DOWN, new Function(this,"mouseDown"));
		 
		 //添加帧函数,游戏的核心线程
		 stage2d.getEvent2D().addEventListener(Event2D.EVENT_FRAME, new Function(this,"eventFrame"));
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
			 //设置点击对象的颜色通道
			 e.getTarget().setR((float) Math.random());
			 e.getTarget().setG((float) Math.random());
			 e.getTarget().setB((float) Math.random());
		 }
	 }
	 
	 private float angle=0;
	 /**
	  * 帧函数回调
	  */
	 public void eventFrame()
	 {
		 angle+=.01;
		 
		 //对整个纹理集统一位移,或者旋转倾斜缩放
		 sprite.setX((float) (Math.cos(angle)*50));
		 sprite.setY((float) (Math.sin(angle)*50));
	 }
	 
	 public void init()
	 {
		 //创建纹理
		 Texture2D texture=new Texture2D("res/animation.png","res/animation.xml");
		 
		 //创建一个对象池,数量为6
		 sprite=new Sprite2D(texture,6);
		 stage2d.addChild(sprite);
		 
		 for(int i=0;i<6;i++)
		 {
			 Quad quad=sprite.getQuad();
			 //切换到指定的场景,可以为字符串或者数字ID
			 quad.setScene("animation_"+(i+1));
			 //循环播放动画
			 quad.loop(true);
			 //播放动画
			 quad.play();
			 //设置动画播放速度
			 quad.setAnimationSpeed(45);
			 quad.setX(i*120);
			 quad.setY(200);
			 //添加到显示容器
			 sprite.addChild(quad);
		 }
	 }
}
