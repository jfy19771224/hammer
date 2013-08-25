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
 * @-С��- ��ϵQQ:790763049
 *
 */
public class MainTest5 extends Activity{
	
	 //����2D����
	 private Stage2D stage2d;
	 
	 //Ϊ��ȫ�ֿ���
	 private Sprite2D sprite;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //��ʼ��2D����,ָ��GL�İ汾��,1����2
		 stage2d=new Stage2D(this,2);
		 
		 //������ʼ������
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //��������¼�
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_DOWN, new Function(this,"mouseDown"));
		 
		 //���֡����,��Ϸ�ĺ����߳�
		 stage2d.getEvent2D().addEventListener(Event2D.EVENT_FRAME, new Function(this,"eventFrame"));
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
			 //���õ���������ɫͨ��
			 e.getTarget().setR((float) Math.random());
			 e.getTarget().setG((float) Math.random());
			 e.getTarget().setB((float) Math.random());
		 }
	 }
	 
	 private float angle=0;
	 /**
	  * ֡�����ص�
	  */
	 public void eventFrame()
	 {
		 angle+=.01;
		 
		 //����������ͳһλ��,������ת��б����
		 sprite.setX((float) (Math.cos(angle)*50));
		 sprite.setY((float) (Math.sin(angle)*50));
	 }
	 
	 public void init()
	 {
		 //��������
		 Texture2D texture=new Texture2D("res/animation.png","res/animation.xml");
		 
		 //����һ�������,����Ϊ6
		 sprite=new Sprite2D(texture,6);
		 stage2d.addChild(sprite);
		 
		 for(int i=0;i<6;i++)
		 {
			 Quad quad=sprite.getQuad();
			 //�л���ָ���ĳ���,����Ϊ�ַ�����������ID
			 quad.setScene("animation_"+(i+1));
			 //ѭ�����Ŷ���
			 quad.loop(true);
			 //���Ŷ���
			 quad.play();
			 //���ö��������ٶ�
			 quad.setAnimationSpeed(45);
			 quad.setX(i*120);
			 quad.setY(200);
			 //��ӵ���ʾ����
			 sprite.addChild(quad);
		 }
	 }
}
