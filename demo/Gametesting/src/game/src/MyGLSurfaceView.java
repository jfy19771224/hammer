package game.src;

import hammer.opengl2d.System2D;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.WindowManager;

public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Activity context){
        super(context);
    
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		System2D.context=context;	
        setEGLContextClientVersion(2);
        //…Ë÷√RendererµΩGLSurfaceView
        setRenderer(new MyGL20Renderer());
        
    }
}