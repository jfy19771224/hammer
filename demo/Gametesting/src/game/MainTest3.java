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
 * @-С��- ��ϵQQ:790763049
 *
 */
public class MainTest3 extends Activity{
	
	 //����2D����
	 private Stage2D stage2d;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //��ʼ��2D����,ָ��GL�İ汾��,1����2
		 stage2d=new Stage2D(this,2);
		 
		 //������ʼ������
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //��������¼�
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_DOWN, new Function(this,"mouseDown"));
	 }
	 
	 /**
	  * ����¼��ص�����
	  * @param e
	  */
	 public void mouseDown(MouseEvent2D e)
	 {
		 //��⵱ǰ��������Ƿ�Ϊnull
		 if(e.getTarget()!=null)
		 {
			 //�ı䵱ǰ�������ĵ�ĳ��ֵ
			 e.getTarget().setRotation(45);
		 }
	 }
	 
	 public void init()
	 {
		 //��������
		 Texture2D texture=new Texture2D("res/1.jpg");
		 
		 //����һ�������,����Ϊ10
		 Sprite2D sprite=new Sprite2D(texture,10);
		 
		 //����һ������,�������ĺô����ڿ��Է�������ͬһ������
		 Quad q1=sprite.getQuad();
		 
		 //������������Ҫ��ӵ��������
		 sprite.addChild(q1);
		 
		 //������һ��
		 Quad q2=sprite.getQuad();
		 //��������
		 
		 q2.setX(300);
		 q2.setY(300);
		 //������������Ҫ��ӵ��������
		 sprite.addChild(q2);
		 
		 //��ӵ�����
		 stage2d.addChild(sprite);
	 }
}
