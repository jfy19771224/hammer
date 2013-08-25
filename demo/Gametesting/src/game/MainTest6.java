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
 * @-С��- ��ϵQQ:790763049
 *
 */
public class MainTest6 extends Activity{
	
	 //����2D����
	 private Stage2D stage2d;
	
	 //���ӷ�����
	 private GrainSend grainSend;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //��ʼ��2D����,ָ��GL�İ汾��,1����2
		 stage2d=new Stage2D(this,2);
		 
		 //������ʼ������
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
		 
		 //��������¼�
		 stage2d.getEvent2D().addEventListener(Event2D.MOUSE_MOVE, new Function(this,"mouseMove"));

	 }
	 
	 /**
	  * ����¼��ص�����
	  * @param e
	  */
	 public void mouseMove(MouseEvent2D e)
	 {
		 grainSend.getLauncher(0).x=Mouse2D.getMotionEvent().getX();
		 grainSend.getLauncher(0).y=Mouse2D.getMotionEvent().getY();
	 }
	 
	 public void init()
	 {
		 //���ӱ༭��Ŀǰ��AS3�汾,֮������JAVA��
		 
		 //����һ�����ӷ�����,��������,���ӳ�����,����������
		 grainSend=new GrainSend(new Texture2D("res/Circle.png"),200,2);
		 grainSend.setDelay(3);
		 stage2d.addChild(grainSend);
		 
		 //��ȡ��һ����������������
		 grainSend.getLauncher(0).start=true;
	 }
}
