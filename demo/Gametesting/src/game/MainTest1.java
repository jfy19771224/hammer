package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.textures.Texture2D;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * һ�еĿ�ʼ
 * @-С��- ��ϵQQ:790763049
 *
 */
public class MainTest1 extends Activity{
	 //����2D����
	 private Stage2D stage2d;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //��ʼ��2D����,ָ��GL�İ汾��,1.x����2.0
		 stage2d=new Stage2D(this,2);
		 
		 //������ʼ������
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
	 }
	 
	 /**
	  * ��ʼ�����
	  */
	 public void init()
	 {
		 //����һ������
		 Texture2D texture=new Texture2D("res/1.jpg");
		 
		 //������ʾ��������
		 Sprite2D sprite=new Sprite2D(texture);
		 
		 //��������
		 sprite.setX(200);
		 sprite.setRotation(30);
		 
		 //��ӵ�������
		 stage2d.addChild(sprite);
	 }
}
