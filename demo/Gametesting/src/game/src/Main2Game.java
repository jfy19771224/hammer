package game.src;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Main2Game extends Activity{
	private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        //����һ��GLSurfaceViewʵ��Ȼ������Ϊactivity��ContentView.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}
