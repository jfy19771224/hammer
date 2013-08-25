package game.src;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main2Game extends Activity{
	private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        //创建一个GLSurfaceView实例然后设置为activity的ContentView.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}
