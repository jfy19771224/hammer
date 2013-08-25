package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.filter.FilterGlassShader;
import hammer.filter.FilterGrayShader;
import hammer.filter.FilterHdrShader;
import hammer.filter.FilterLightingShader;
import hammer.filter.FilterMosaicShader;
import hammer.filter.FilterReliefShader;
import hammer.textures.Texture2D;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * һ�еĿ�ʼ
 * @-С��- ��ϵQQ:790763049
 *
 */
public class MainTest7 extends Activity{
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
		 
		 
		 //��ӵ�������
		 stage2d.addChild(sprite);
		 
		 //���Խ׶�
		 
		 //ˮ���˾�
		 //sprite.setFilter(new FilterGlassShader());
		 
		 //�Ҷ��˾�
		 //sprite.setFilter(new FilterGrayShader());
		 
		 //HDR
		 //sprite.setFilter(new FilterHdrShader());
		 
		 //2D���Դ
		 sprite.setFilter(new FilterLightingShader());
		 
		 //������
		 //sprite.setFilter(new FilterMosaicShader());
		 
		 //����
		 //sprite.setFilter(new FilterReliefShader());
	 }
}
