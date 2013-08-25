package game.src;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import hammer.event.Event2D;
import hammer.opengl2d.System2D;
import hammer.textures.Texture2D;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

public class DemoRenderer implements GLSurfaceView.Renderer {

	
	/*
	 * �ص������б�
	 */
	private Method[] functionArry;
	
	/*
	 * �ص�����
	 */
	private Method callBack;
	
	/*
	 * �ص���������
	 */
	private Object callBackObj;
	
	/*
	 * ��������
	 */
	private Object[] parameter;
	
	/*
	 * ��ʼ������
	 */
	private Method initialize;
	
	
    private static native void nativeInit();
    private static native void nativeResize(int w, int h);
    private static native void nativeRender();
    private static native void nativeSpeed(int s);

    
    public DemoRenderer(Activity context) {
		context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		System2D.context=context;
	}
    
    public void setSpeed(int s)
    {
    	nativeSpeed(s);
    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        nativeInit();
    }

    
    public void onSurfaceChanged(GL10 gl, int w, int h) {
    	
        nativeResize(w, h);
        DisplayMetrics metric = new DisplayMetrics();
    	System2D.context.getWindowManager().getDefaultDisplay().getMetrics(metric);
	    System2D.heightPixels=metric.heightPixels; 
		System2D.gl=gl;
		
		this.dispatchEvent(this.callBackObj,this.callBack,parameter);
		
    }
    /**
	 * ���ûص�����,��������,������,�����б�
	 * 
	 */
	public void addEventListener(int type,Object obj,String str,Object... parameter) 
	{
		
		this.callBackObj=obj;
		this.parameter=parameter;
		this.callBack=function(obj,str);
	
	}
	/*
	 * ��ִ̬�к���
	 */
	private Method function(Object child,Object str)
    {
		if(functionArry==null)
		{
			functionArry=child.getClass().getDeclaredMethods();
		}
		
		
    	for(Method m : functionArry)
        {
    		
     	   if(str.equals(m.getName()))
     	   {
     		   return m;   
     	   }
        }
    	return null;
    }
	

	/**
	 * �¼�����
	 */
	public void dispatchEvent(Object callBackObj,Method callBack,Object... parameter)
	{
		if(callBackObj!=null&&callBack!=null)
		{
			callBack.setAccessible(true); 
     	   try {
     		  callBack.invoke(callBackObj,parameter);
 			} catch (IllegalArgumentException e) {
 				e.printStackTrace();
 			} catch (IllegalAccessException e) {
 				e.printStackTrace();
 			} catch (InvocationTargetException e) {
 				e.printStackTrace();
 			}     
		}
	}

    long timeStart; 
	long time;
	private boolean bool;
	
	private float s=0;
	private float fps;
	private float maxfps=0;
	private int v=0;
	private float di=100;
	private float gao=0;
	private int kaishi=0;
	
	private boolean t2d=false;
    public void onDrawFrame(GL10 gl){
    	
    	
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
					
				Log.d("Tan","��ǰ֡��:"+fps+">>>>ƽ��֡�� :"+Math.floor((maxfps/v))+">>>>���֡�� :"+di+">>>>���֡�� :"+gao);
				}
				//
			}
			bool=!bool;
		}
		
        nativeRender();
    }
    
    static {
        System.loadLibrary("triangle");
    }


}