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
 * 一切的开始
 * @-小神- 联系QQ:790763049
 *
 */
public class MainTest7 extends Activity{
	 //创建2D场景
	 private Stage2D stage2d;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 
		 //初始化2D场景,指定GL的版本号,1.x或者2.0
		 stage2d=new Stage2D(this,2);
		 
		 //侦听初始化函数
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
	 }
	 
	 /**
	  * 初始化完毕
	  */
	 public void init()
	 {
		 //创建一个纹理
		 Texture2D texture=new Texture2D("res/1.jpg");
		 
		 //创建显示对象容器
		 Sprite2D sprite=new Sprite2D(texture);
		 
		 
		 //添加到场景上
		 stage2d.addChild(sprite);
		 
		 //测试阶段
		 
		 //水波滤镜
		 //sprite.setFilter(new FilterGlassShader());
		 
		 //灰度滤镜
		 //sprite.setFilter(new FilterGrayShader());
		 
		 //HDR
		 //sprite.setFilter(new FilterHdrShader());
		 
		 //2D点光源
		 sprite.setFilter(new FilterLightingShader());
		 
		 //马赛克
		 //sprite.setFilter(new FilterMosaicShader());
		 
		 //浮雕
		 //sprite.setFilter(new FilterReliefShader());
	 }
}
