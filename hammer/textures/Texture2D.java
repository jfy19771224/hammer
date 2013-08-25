package hammer.textures;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.util.Log;
import android.view.View;

import hammer.batch.QuadData;
import hammer.batch.QuadFrame;
import hammer.batch.QuadResource;
import hammer.core.LoadAddress;
import hammer.geom.Array;
import hammer.net.LoaderAssets;
import hammer.opengl2d.LoadTexture;
import hammer.opengl2d.System2D;

/**
 * 纹理类
 * @author apple
 *
 */
public class Texture2D {
	
	private float mWidth;
	private float mHeight;
	private TextureData texture;
	private int mTextureId;
	private DocumentBuilderFactory docBuilderFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	private String regEx;
	private Pattern pat;
	private Matcher mat;
	public QuadResource animationSupervisor;
	private String url;
	
	/**
	 * 创建一个空纹理
	 * @param width
	 * @param height
	 */
	public Texture2D(String url,String xml) {
		this.url=url;
		if(LoadAddress.target().isRepeat(url))
		{
			texture = LoadTexture.target().getBitmap(LoaderAssets.loadBitmap(url,System2D.context),true);
			LoadAddress.target().push(url, texture);
		}else
		{
			texture=(TextureData) LoadAddress.target().getObject(url);
		}
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
		regEx = "([a-z,0-9,_*]+[0-9]{4})";  
		loadPass(xml);
	}
	
	/**
	 * 创建一个空纹理
	 * @param width
	 * @param height
	 */
	public Texture2D(String url,String xml,boolean quality) {
		this.url=url;
		if(LoadAddress.target().isRepeat(url))
		{
			texture = LoadTexture.target().getBitmap(LoaderAssets.loadBitmap(url,System2D.context),quality);
			LoadAddress.target().push(url, texture);
		}else
		{
			texture=(TextureData) LoadAddress.target().getObject(url);
		}
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
		regEx = "([a-z,0-9,_*]+[0-9]{4})";  
		loadPass(xml);
	}
	
	/**
	 * 通过地址获取纹理
	 * @param url
	 */
	public Texture2D(String url) {
		this.url=url;
		if(LoadAddress.target().isRepeat(url))
		{
			
			texture = LoadTexture.target().getBitmap(LoaderAssets.loadBitmap(url,System2D.context),true);
			LoadAddress.target().push(url, texture);
		}else
		{
			
			texture=(TextureData) LoadAddress.target().getObject(url);
		}
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
	}
	
	/**
	 * 通过地址获取纹理
	 * @param url
	 */
	public Texture2D(String url,boolean quality) {
		this.url=url;
		if(LoadAddress.target().isRepeat(url))
		{
			
			texture = LoadTexture.target().getBitmap(LoaderAssets.loadBitmap(url,System2D.context),quality);
			LoadAddress.target().push(url, texture);
		}else
		{
			
			texture=(TextureData) LoadAddress.target().getObject(url);
		}
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
	}
	
	/**
	 * 创建一个空纹理
	 * @param width
	 * @param height
	 */
	public Texture2D(float width,float height) {
		this.mWidth=width;
		this.mHeight=height;
		mTextureId=0;
	}
	
	public int getTextureId()
	{
		return mTextureId;
	}
	
	public float getWidth()
	{
		return mWidth;
	}
	
	public float getHeight()
	{
		return mHeight;
	}

	
	 /**
	 * 字符串校准
	 * @param	str
	 * @param	value
	 * @return
	 */
	private String correct(String str,int value)
	{
		String s = "";
		for (int i = 0; i < str.length() - value; i++)
		{
			s += str.charAt(i);
		}
		return s;
	}
	
	
	   
	   
	  
   /**
	 * 读取队列关卡信息
	 */
	
	public void loadPass(String url)
	{
		Array quadDataList=new Array();
		Array quadFrameList=null;
		readXML(url);
		Element root = doc.getDocumentElement();
		NodeList nodeList = root.getElementsByTagName("SubTexture");
		String name="";
		for(int i =0;i< nodeList.getLength();i++)
		{ 
			QuadData sceneData = null;
			
			Element personNode = (Element) nodeList.item(i); 
			
			pat = Pattern.compile(regEx);  
			Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
			mat = pat.matcher(personNode.getAttribute("name"));  
				 
				if (mat.find())
				{
					
					if (!name.equals(correct(personNode.getAttribute("name"),4)))
					{
						name = correct(personNode.getAttribute("name"),4);
						quadFrameList=new Array();
						sceneData=new QuadData();
						sceneData.setName(name);
						sceneData.setQuadFrameLst(quadFrameList);
						quadDataList.push(sceneData);
					}
				}else
				{
					if (!name.equals(personNode.getAttribute("name")))
					{
						name = personNode.getAttribute("name");
						quadFrameList=new Array();
						sceneData=new QuadData();
						sceneData.setName(name);
						sceneData.setQuadFrameLst(quadFrameList);
						quadDataList.push(sceneData);
					}
					
				}
				
				QuadFrame frameData = new QuadFrame();
				frameData.setName(personNode.getAttribute("name"));
				frameData.setX(Float.valueOf(personNode.getAttribute("x")));
				frameData.setY(Float.valueOf(personNode.getAttribute("y")));
				frameData.setWidth(Float.valueOf(personNode.getAttribute("width")));
				frameData.setHeight(Float.valueOf(personNode.getAttribute("height")));
				
				if(personNode.getAttribute("frameHeight").length()!=0)
				frameData.setFrameHeight(Float.valueOf(personNode.getAttribute("frameHeight")));
				
				if(personNode.getAttribute("frameWidth").length()!=0)
				frameData.setFrameWidth(Float.valueOf(personNode.getAttribute("frameWidth")));
				
				if(personNode.getAttribute("frameX").length()!=0)
				frameData.setFrameX(Float.valueOf(personNode.getAttribute("frameX")));
				
				if(personNode.getAttribute("frameY").length()!=0)
				frameData.setFrameY(Float.valueOf(personNode.getAttribute("frameY")));
				
				quadFrameList.push(frameData);
		}
		
		HashMap<String, QuadData> dictionary=new HashMap<String, QuadData>();
		
		for (int d = 0; d < quadDataList.getLength(); d++)
		{
			QuadData quadData=(QuadData)quadDataList.get(d);
			dictionary.put(quadData.getName(),quadData);
		}
		
		animationSupervisor=new QuadResource();
		animationSupervisor.setmDictionary(dictionary);
		animationSupervisor.setmQuadDataList(quadDataList);
		
		
	} 
	
   private void readXML(String url)
   {
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(System2D.context.getResources().getAssets().open(url));
		} catch (IOException e) {} catch (SAXException e) {} catch (ParserConfigurationException e) {} finally {}
   }
		  
	
   
	
	/**
	 * 设置纹理内容
	 * @param bitmap
	 */
	public void setBitmap(Bitmap bitmap,boolean quality)
	{
		dispose(true);
		texture = LoadTexture.target().getBitmap(bitmap,quality);
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
	}
	
	/**
	 * 设置纹理内容
	 * @param bitmap
	 */
	public void setBitmap(Bitmap bitmap)
	{
		dispose(true);
		texture = LoadTexture.target().getBitmap(bitmap,false);
		mWidth=texture.getWidth();
		mHeight=texture.getHeight();
		mTextureId=texture.getTextureId();
	}
	
	/**
	 * 销毁纹理
	 */
	public void dispose()
	{
		if(texture!=null)
		{
			LoadAddress.target().remove(this.url);
			if(System2D.glVersions==1)
			{
				System2D.gl.glDeleteTextures(texture.getTextureindex(),texture.getIntbuffer());
			}else if(System2D.glVersions==2)
			{
				GLES20.glDeleteTextures(texture.getTextureindex(),texture.getIntbuffer());
			}
			texture=null;
		}
	}
	
	/**
	 * 销毁纹理
	 */
	public void dispose(boolean bool)
	{
		if(texture!=null)
		{
			if(System2D.glVersions==1)
			{
				System2D.gl.glDeleteTextures(texture.getTextureindex(),texture.getIntbuffer());
			}else if(System2D.glVersions==2)
			{
				GLES20.glDeleteTextures(texture.getTextureindex(),texture.getIntbuffer());
			}
			texture=null;
		}
	}
	
	
}
