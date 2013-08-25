package hammer.opengl2d;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

import hammer.net.LoaderAssets;
import hammer.textures.TextureData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Bitmap.Config;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
/**
 * 载入纹理
 * @author Administrator
 *
 */
public class LoadTexture {
	/**
	 * 最终贴图
	 */
	private Bitmap canvasBmp;
	/**
	 * 画布操作位图
	 */
	private Canvas canvas;
	/**
	 * 画笔
	 */
	private Paint paint;
	
	
	/**
	 * 贴图ID
	 */
	private int textureindex=1;//0是默认编号,无法倍销毁
	private static LoadTexture _target;
	
	/**
	 * 获得事件发送器
	 * @return
	 */
	public static LoadTexture target()
	{
		if(_target==null)
		{
			_target=new LoadTexture();
		}
		return _target;
	}
	
	/**
	 * 纹理地址
	 * @param url
	 */
	public LoadTexture()
	{
		canvas=new Canvas();
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(true);	
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
	}
	
	

	/**
	 * 检测图片是否为2的次方
	 * @param num
	 * @return
	 */
	public boolean isPower_2(int num)
	{
	    if (num == 1)
	        return true;
	    else
	    {
	        do
	        {
	            if (num % 2 == 0)
	                num = num / 2;
	            else
	                return false;
	        }
	        while (num != 1);
	        return true;
	    }
	}

	/**
	 * 静态图片初始化
	 * @param url
	 * @return
	 */
	public TextureData getBitmap(Bitmap bitmap,boolean quality)
	{
		TextureData textureData=new TextureData();
		if(!isPower_2(bitmap.getWidth())||!isPower_2(bitmap.getHeight()))
		{
			canvasBmp = Bitmap.createBitmap(getScope(bitmap.getWidth()),getScope(bitmap.getHeight()),Config.ARGB_8888);
			canvas.setBitmap(this.canvasBmp);
			float a=(float)canvasBmp.getWidth()/(float)bitmap.getWidth();
			float b=(float)canvasBmp.getHeight()/(float)bitmap.getHeight();
			canvas.save();
			canvas.scale(a,b,canvasBmp.getWidth()/2,canvasBmp.getHeight()/2);
			canvas.drawBitmap(bitmap,canvas.getWidth()/2-bitmap.getWidth()/2,canvas.getHeight()/2-bitmap.getHeight()/2,paint);
			canvas.restore();
			
		}else
		{
			
			canvasBmp = bitmap;
		}
		textureData.setIntbuffer(setTextrue(System2D.gl,textureData,quality));
		textureData.setTextureindex(textureindex);
		textureData.setWidth(bitmap.getWidth());
		textureData.setHeight(bitmap.getHeight());
		bitmap.recycle();
		textureindex++;
		return textureData;
	}
	


	
	/**
	 * 设置装载纹理
	 * @param gl
	 */
	private IntBuffer setTextrue(GL10 gl,TextureData textureData,boolean quality)
	{
		IntBuffer intbuffer=IntBuffer.allocate(10000);
		if(System2D.glVersions==1)
		{
			gl.glGenTextures(textureindex, intbuffer);
	    	textureData.setTextureId(intbuffer.get());
	    	gl.glBindTexture(GL10.GL_TEXTURE_2D, textureData.getTextureId());
			if(quality)
			{
				gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_NICEST);	
				gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);
			    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);	
			    gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST);
			    gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
			    gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
			}else
			{
				gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_FASTEST);	
				gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_FASTEST);
			    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);	
			    gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_FASTEST);
			    gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
			    gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
			}
		}else if(System2D.glVersions==2)
		{
	    	GLES20.glGenTextures(textureindex, intbuffer);
	    	textureData.setTextureId(intbuffer.get());
	    	GLES20.glBindTexture(GL10.GL_TEXTURE_2D, textureData.getTextureId());
			if(quality)
			{
				GLES20.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_NICEST);	
				GLES20.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);
			    GLES20.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);	
			    GLES20.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST);
			    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
			}else
			{
				GLES20.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_FASTEST);	
				GLES20.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_FASTEST);
			    GLES20.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);	
			    GLES20.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_FASTEST);
			    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
		        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
			}
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, canvasBmp,0);
		canvasBmp.recycle(); 
		return intbuffer;
		
	}
	
	/**
	 * 计算图片最接近的乘方图片
	 * @param f
	 * @return
	 */
	public int getScope(float value)
	{
		int textureWidth = 2;
		while(textureWidth < value) {
			textureWidth <<= 1;
		}
		return textureWidth;
	}
}
