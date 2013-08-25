package hammer.opengl2d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import hammer.core.Controller;
import hammer.core.Stage2D;
import hammer.event.Event2D;
import hammer.event.EventDisplayer2D;
import hammer.utils.TimerStorage;
/**
 * 主要渲染器
 * @-式神-
 *
 */
public class System2D extends SurfaceView implements GLSurfaceView.Renderer
{
	public static int glVersions=1;
	public static int heightPixels=0;
	public static int thickness=40290;
	public static int scale;
	public static double scaleX=1;
	public static double scaleY=1;
	
	
	public static double matchingWidth;
	
	public static double matchingHeight;
	
	/**
	 * 屏幕朝向
	 */
	public static int scrn;

	/**
	 * 程序入口类
	 */
	public static Activity context;
	
	/**
	 * 主场景
	 */
	public static System2D stage;
	
	/**
	 * 游戏帧频
	 */
	public static float FPS;
	
	/**
	 * 游戏实际帧率
	 */
	public static long FRAMEFPS;
	
	
	/**
	 * 刷新时间
	 */
	public static float sleep;

	
	/**
	 * 密度
	 */
	public static float density;
	
	/**
	 * 时间步
	 */
	public static boolean timeStep;
	public static GL10 gl;
	
    private LoadTexture loadView;
	/*
	 * 场景的宽度
	 */
	private static int stageWidth;
	
	/*
	 * 场景的高度
	 */
	private static int stageHeight;
	
	/**
	 * 无自动适应
	 */
	public static int SURVIVAL_CANCEL=0;
	
	/**
	 * 正常宽高比适应
	 */
	public static int SURVIVAL_ORIGINAL=1;
	
	
	/**
	 * 全屏适应
	 */
	public static int SURVIVAL_MATCHING=2;
	/**
	 * 画笔
	 */
	private Paint paint;
	
	

	/**
	 * 游戏场景类的控制器
	 */
	private Controller controller;
	
	

	/**
	 * 抗锯齿
	 */
	private boolean aliasing=true;
	
	/**
	 * 加速度传感器
	 */
	public Sensor accelerometer;
	
	/**
	 * 陀螺仪传感器
	 */
	public Sensor gyroscope;
	
	/**
	 * 光照传感器
	 */
	public Sensor light;
	
	/**
	 * 磁力计
	 */
	public Sensor magneticfield;
	
	/**
	 * 方位传感器
	 */
	public Sensor orientatton;
	
	/**
	 * 压力传感器 
	 */
	public Sensor pressure;
	
	/**
	 * 距离传感器
	 */
	public Sensor proximtty;
	
	/**
	 * 温度传感器
	 */
	public Sensor temperature;
	
	/**
	 * 所有传感器
	 */
	public Sensor typeal;
	

	
	public EventDisplayer2D eventDisplayer2D;

	
	
	/**
	 * 获得场景的宽度
	 * @return
	 */
	public static int getStageWidth()
	{
		return stageWidth;
	}
	
	
	/**
	 * 获得场景的高度
	 * @return
	 */
	public static int getStageHeight()
	{
		return stageHeight;
	}
	
	/**
	 * 获取游戏刷新时间
	 * @return
	 */
	public float getFps()
	{
		return System2D.FPS;
	}
	
	
	
	public GLSurfaceView stage2d;
	/**
	 * 初始化主场景
	 * @param context 程序入口类
	 */
	public System2D(Activity context,GLSurfaceView stage2d) {
		super(context);	
		sftInit();
		this.stage2d=stage2d;
		context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		System2D.context=context;
		eventDisplayer2D=new EventDisplayer2D();
	}


	
	/**
	 * 画布初始化
	 */
	private void sftInit()
	{
		//初始化画笔
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(aliasing);
		// 保持屏幕长亮
		new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG);
		this.setKeepScreenOn(true);
		paint.setColor(Color.RED);
		// 设置无锯齿
		setFocusable(true);
	}

	public void onReleased(int screenX, int screenY) {
		
	}
	


	/**
	 * @serialData
	 */
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
		
	}


	/**
	 * @return
	 */
	public void surfaceCreated(SurfaceHolder holder) {
		
		
	}
	

	/**
	 * @noindex
	 */
	public void surfaceDestroyed(SurfaceHolder holder) {}
	/**
	 * @noindex
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}
	long timeStart; 
	long time;
	private boolean bool;
	
	private float s=0;
	public static float fps;
	private float maxfps=0;
	private int v=0;
	private float di=100;
	private float gao=0;
	private int kaishi=0;
	public static float[] projectionMatrix=new float[16];
	/**
	 * 帧事件
	 */
	public void onDrawFrame(GL10 gl) {
		
		this.stage2d.requestRender();
		kaishi++;
		if(kaishi>=100)
		{
			if(bool)
			{
				time=System.currentTimeMillis();
			}else
			{
				timeStart =System.currentTimeMillis()-time;
				s++;
				if(s>=10)
				{
					v++;
					s=0;
					fps=(1000/timeStart);
					//if(fps>=60)
					//fps=60;
					if(fps<di)
					{
						di=fps;
					}
					
					if(fps>gao)
					{
						gao=fps;
					}
					maxfps+=fps;
					
					//Log.d("Tan","当前帧率:"+fps+">>>>平均帧率 :"+Math.floor((maxfps/v))+">>>>最低帧率 :"+di+">>>>最高帧率 :"+gao);
				}
				//
			}
			bool=!bool;
		}
		
		if(System2D.glVersions==1)
		{
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
			gl.glLoadIdentity();
			gl.glOrthof(ratio-ratio,-ratio-ratio,-1-1, 1-1, 0f, 1000.0f);
		}else if(System2D.glVersions==2)
		{
			GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
			Matrix.orthoM(projectionMatrix, 0,ratio-ratio,-ratio-ratio,-1-1, 1-1, -10f, 1000.0f);
		}
		
		//首先清理屏幕
		
		//gl.glShadeModel(GL10.GL_FLAT);
		
		//gl.glLightModeli(GL10.GL_LIGHT_MODEL_AMBIENT,GL10.GL_LIGHT_MODEL_TWO_SIDE);
		
		//gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		//直接变黑色
		//gl.glBlendFunc(GL10.GL_SRC_ALPHA_SATURATE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		eventDisplayer2D.dispatchEvent(Event2D.EVENT_FRAME);
		TimerStorage.target.run();
		
		
		if(controller!=null)
		{
			controller.paint(gl);
		}
	}
	
	public static  float ratio;
	/**
	 * 屏幕改变时
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
		ratio=(float)width/height;
		stageWidth=width;
		stageHeight=height;
		if(System2D.glVersions==1)
		{
			gl.glViewport(0,0, width, height);
			gl.glFrontFace(GL10.GL_CCW);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glEnable(GL10.GL_SCISSOR_TEST);
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			gl.glEnable(GL10.GL_TEXTURE_2D);
			gl.glEnable(GL10.GL_BLEND);
		}else if(System2D.glVersions==2)
		{
			GLES20.glViewport(0,0, width, height);
	    	GLES20.glFrontFace(GL10.GL_CCW);
	    	GLES20.glEnable(GL10.GL_SCISSOR_TEST);
			GLES20.glEnable(GL10.GL_BLEND);
			GLES20.glDisable(GL10.GL_DEPTH_TEST);
			GLES20.glDisable(GL10.GL_CULL_FACE);
			GLES20.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		}
		//去PNG背景
	    //gl.glEnable(GL10.GL_ALPHA_TEST);
	    //gl.glEnable(GL10.GL_DEPTH_TEST);
	    //设置显示边缘的像素值
	    //gl.glAlphaFunc(GL10.GL_GREATER,0f);  
		eventDisplayer2D.dispatchEvent(Event2D.INITIALIZE);
	
	}
	
	/**
	 * 窗口创建时
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		DisplayMetrics metric = new DisplayMetrics();
	    System2D.context.getWindowManager().getDefaultDisplay().getMetrics(metric);
	    System2D.heightPixels=metric.heightPixels; 
		System2D.gl=gl;
		System2D.stage=this;
		if(System2D.glVersions==1)
		{
			gl.glClearColor(0f, 0f, 0f, 0f);
	        gl.glEnable(GLES20.GL_TEXTURE_2D);
	        gl.glActiveTexture(GLES20.GL_TEXTURE0);
	        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
		}else if(System2D.glVersions==2)
		{
			GLES20.glClearColor(0f, 0f, 0f, 0f);
	        GLES20.glEnable(GLES20.GL_TEXTURE_2D);
	        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
	        GLES20.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
		}
		
		controller=new Controller();
		TimerStorage.init();
		
		
	}


	
}
