package game.src;
import hammer.event.Event2D;
import hammer.textures.Texture2D;
import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
public class DemoGLSurfaceView extends GLSurfaceView {

    DemoRenderer mRenderer;

    public DemoGLSurfaceView(Activity context) {
        super(context);

        mRenderer = new DemoRenderer(context);
        setRenderer(mRenderer);
        mRenderer.addEventListener(123, this,"init",null);
        
    }
    public void init()
    {        
    	Log.d("Tan","初始化完毕222");
    	Texture2D texture2D_3=new Texture2D("321.jpg");
    	mRenderer.setSpeed(texture2D_3.getTextureId());
    	Log.d("Tan","纹理ID"+texture2D_3.getTextureId());
    }
}
