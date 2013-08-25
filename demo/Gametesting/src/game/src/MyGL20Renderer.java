package game.src;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import hammer.event.Event2D;
import hammer.opengl2d.System2D;
import hammer.textures.Texture2D;

import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

public class MyGL20Renderer implements GLSurfaceView.Renderer {

	private Triangle mTriangle;
	private Square mSquare;
	private float[] mVMatrix = new float[16];
	private float[] mMVPMatrix = new float[16];
	private float[] mProjMatrix = new float[16];
	 public MyGL20Renderer() {
		
	};
	 
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        //设置背景的颜色
    	
        GLES20.glClearColor(0.5f, 1.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_TEXTURE_2D);
        // Active the texture unit 0
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        mTriangle = new Triangle();
        // 初始化一个正方形
        //mSquare = new Square();
    };
    public static  float ratio;
    private float[] mRotationMatrix = new float[16];  
    private float mAngle;
    public void onDrawFrame(GL10 gl) 
    {
    	mAngle++;
        // 重绘背景色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        //Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // 计算投影和视口变换
        //Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);
        //long time = SystemClock.uptimeMillis() % 4000L;  
        //float angle = 0.090f * ((int) time);  
        //Matrix.setRotateM(mRotationMatrix, 0, 45f, 0, 0, -1.0f);  
       
        // 把旋转矩阵合并到投影和相机矩阵  
        //Matrix.multiplyMM(mMVPMatrix, 0, mRotationMatrix, 0, mMVPMatrix, 0); 
	    // 绘制形状
	    mTriangle.draw(mMVPMatrix);
	  
    };

    public void onSurfaceChanged(GL10 gl, int width, int height) {
    	ratio=(float)width/height;
    	GLES20.glViewport(0,0, width, height);
    	GLES20.glFrontFace(GL10.GL_CCW);
    	//Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    	
    };

}