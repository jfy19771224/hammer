package hammer.textures;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import hammer.core.Stage2D;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * 贴图数据
 * @author Administrator
 *
 */
public class TextureData {
	/**
	 * 贴图缓存数组
	 */
	private IntBuffer mIntbuffer;
	/**
	 * 贴图ID
	 */
	private int mTextureindex;
	/**
	 * 贴图数据
	 */
	private int mTextureId;
	
	private int mWidth;
	
	private int mHeight;
	public TextureData() {
		
	}
	public IntBuffer getIntbuffer() {
		return mIntbuffer;
	}
	public void setIntbuffer(IntBuffer intbuffer) {
		this.mIntbuffer = intbuffer;
	}
	public int getTextureindex() {
		return mTextureindex;
	}
	public void setTextureindex(int textureindex) {
		this.mTextureindex = textureindex;
	}
	public int getTextureId() {
		return mTextureId;
	}
	public void setTextureId(int textureId) {
		this.mTextureId = textureId;
	}
	public int getWidth() {
		return mWidth;
	}
	public void setWidth(int width) {
		this.mWidth = width;
	}
	public int getHeight() {
		return mHeight;
	}
	public void setHeight(int height) {
		this.mHeight = height;
	}
	
	
}

