package hammer.net;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 资源载入类
 * @author Administrator
 *
 */
public class LoaderAssets {
	
	/**
	 * 载入图像资源
	 * @param url
	 * @return
	 */
	
	private static Bitmap bmp = null;
	
	public static Bitmap loadBitmap(String url,Context context)
	{
		  Bitmap image = null;  
	      AssetManager am = context.getResources().getAssets();  
	      try  
	      {  
	          InputStream is = am.open(url);  
	          image = BitmapFactory.decodeStream(is);  
	          is.close(); 
	      }  
	      catch (IOException e)  
	      {  
	          e.printStackTrace();  
	      }  

		return image;
	}
	
	public static Bitmap loadBitmap(Bitmap url,Context context)
	{
		bmp = url;	
		return bmp;
	}
}
