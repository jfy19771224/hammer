package hammer.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.util.Log;
import hammer.textures.Texture2D;

/**
 * 文本
 * @-式神-
 *
 */
public class TextField2D extends Sprite2D{
	
	private Canvas canvas;
	private Paint paint;
	private Typeface mFont;
	private float tW;
	private float tH;
	private float mSize;
	public TextField2D(float w,float h)
	{
		super(new Texture2D(w,h));
		this.tW=w;
		this.tH=h;
		mSize=30;
		canvas=new Canvas();
		paint=new Paint();
		mFont = Typeface.create("宋体",Typeface.BOLD);
		this.setText("");
	}
	
	
	/**
	 * 设置字体大小
	 * @param value
	 */
	public void setSize(float value)
	{
		mSize=value;
	}
	
	/**
	 * 设置文字样式
	 * @param font
	 */
	public void setFont(Typeface font)
	{
		this.mFont=font;
	}
	
	/**
	 * 设置文本内容
	 * @param value
	 */
	public void setText(String value)
	{
		//需要优化
		String mstrTitle = value;
		Bitmap bitmap = Bitmap.createBitmap((int)this.tW,(int)this.tH, Bitmap.Config.ARGB_8888); 
		//canvas.save();
		canvas.setBitmap(bitmap);
		//是否透明
		//canvas.drawColor(Color.WHITE); 
		paint.setColor(Color.RED); 
		paint.setTypeface(this.mFont); 
		
		paint.setTextSize(mSize);
		canvas.drawText(mstrTitle,0,this.tH/2+mSize/2,paint);
		//canvas.restore();
		
		Bitmap canvasBmp = Bitmap.createBitmap(getScope(bitmap.getWidth()),getScope(bitmap.getHeight()),Config.ARGB_8888);
		canvas.setBitmap(canvasBmp);
		float a=(float)canvasBmp.getWidth()/(float)bitmap.getWidth();
		float b=(float)canvasBmp.getHeight()/(float)bitmap.getHeight();
		canvas.save();
		//canvas.drawColor(Color.WHITE); 
		canvas.scale(a,b,canvasBmp.getWidth()/2,canvasBmp.getHeight()/2);
		canvas.drawBitmap(bitmap,canvas.getWidth()/2-bitmap.getWidth()/2,canvas.getHeight()/2-bitmap.getHeight()/2,paint);
		canvas.restore();
		
		this.texture2D.setBitmap(canvasBmp);
		this.setTexture(this.texture2D);
		
		bitmap.recycle();
		canvasBmp.recycle();
		
		
		
	}
	
	public int getFontHeight(Integer textSize){  
        if(textSize != null){  
            paint.setTextSize(textSize);  
        }  
        FontMetrics fm = paint.getFontMetrics();  
        return (int)(fm.descent - fm.ascent);    
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
