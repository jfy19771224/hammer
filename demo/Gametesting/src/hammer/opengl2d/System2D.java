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
 * ��Ҫ��Ⱦ��
 * @-ʽ��-
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
	 * ��Ļ����
	 */
	public static int scrn;

	/**
	 * ���������
	 */
	public static Activity context;
	
	/**
	 * ������
	 */
	public static System2D stage;
	
	/**
	 * ��Ϸ֡Ƶ
	 */
	public static float FPS;
	
	/**
	 * ��Ϸʵ��֡��
	 */
	public static long FRAMEFPS;
	
	
	/**
	 * ˢ��ʱ��
	 */
	public static float sleep;

	
	/**
	 * �ܶ�
	 */
	public static float density;
	
	/**
	 * ʱ�䲽
	 */
	public static boolean timeStep;
	public static GL10 gl;
	
    private LoadTexture loadView;
	/*
	 * �����Ŀ��
	 */
	private static int stageWidth;
	
	/*
	 * �����ĸ߶�
	 */
	private static int stageHeight;
	
	/**
	 * ���Զ���Ӧ
	 */
	public static int SURVIVAL_CANCEL=0;
	
	/**
	 * ������߱���Ӧ
	 */
	public static int SURVIVAL_ORIGINAL=1;
	
	
	/**
	 * ȫ����Ӧ
	 */
	public static int SURVIVAL_MATCHING=2;
	/**
	 * ����
	 */
	private Paint paint;
	
	

	/**
	 * ��Ϸ������Ŀ�����
	 */
	private Controller controller;
	
	

	/**
	 * �����
	 */
	private boolean aliasing=true;
	
	/**
	 * ���ٶȴ�����
	 */
	public Sensor accelerometer;
	
	/**
	 * �����Ǵ�����
	 */
	public Sensor gyroscope;
	
	/**
	 * ���մ�����
	 */
	public Sensor light;
	
	/**
	 * ������
	 */
	public Sensor magneticfield;
	
	/**
	 * ��λ������
	 */
	public Sensor orientatton;
	
	/**
	 * ѹ�������� 
	 */
	public Sensor pressure;
	
	/**
	 * ���봫����
	 */
	public Sensor proximtty;
	
	/**
	 * �¶ȴ�����
	 */
	public Sensor temperature;
	
	/**
	 * ���д�����
	 */
	public Sensor typeal;
	

	
	public EventDisplayer2D eventDisplayer2D;

	
	
	/**
	 * ��ó����Ŀ��
	 * @return
	 */
	public static int getStageWidth()
	{
		return stageWidth;
	}
	
	
	/**
	 * ��ó����ĸ߶�
	 * @return
	 */
	public static int getStageHeight()
	{
		return stageHeight;
	}
	
	/**
	 * ��ȡ��Ϸˢ��ʱ��
	 * @return
	 */
	public float getFps()
	{
		return System2D.FPS;
	}
	
	
	
	public GLSurfaceView stage2d;
	/**
	 * ��ʼ��������
	 * @param context ���������
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
	 * ������ʼ��
	 */
	private void sftInit()
	{
		//��ʼ������
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(aliasing);
		// ������Ļ����
		new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG);
		this.setKeepScreenOn(true);
		paint.setColor(Color.RED);
		// �����޾��
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
	 * ֡�¼�
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
					
					//Log.d("Tan","��ǰ֡��:"+fps+">>>>ƽ��֡�� :"+Math.floor((maxfps/v))+">>>>���֡�� :"+di+">>>>���֡�� :"+gao);
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
		
		//����������Ļ
		
		//gl.glShadeModel(GL10.GL_FLAT);
		
		//gl.glLightModeli(GL10.GL_LIGHT_MODEL_AMBIENT,GL10.GL_LIGHT_MODEL_TWO_SIDE);
		
		//gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		//ֱ�ӱ��ɫ
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
	 * ��Ļ�ı�ʱ
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
		//ȥPNG����
	    //gl.glEnable(GL10.GL_ALPHA_TEST);
	    //gl.glEnable(GL10.GL_DEPTH_TEST);
	    //������ʾ��Ե������ֵ
	    //gl.glAlphaFunc(GL10.GL_GREATER,0f);  
		eventDisplayer2D.dispatchEvent(Event2D.INITIALIZE);
	
	}
	
	/**
	 * ���ڴ���ʱ
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
