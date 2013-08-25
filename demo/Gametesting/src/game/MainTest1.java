package game;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.textures.Texture2D;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainTest1 extends Activity{
	
	 private Stage2D stage2d;
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState); 
		 stage2d=new Stage2D(this,2);
		 stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE, new Function(this,"init"));
	 }
	 
	 public void init()
	 {
		 Texture2D texture=new Texture2D("321.jpg");
		 Sprite2D sprite=new Sprite2D(texture);
		 sprite.setShaderDerail(false);
		 sprite.setX(200);
		 sprite.setRotation(30);
		 stage2d.addChild(sprite);
	 }
}
