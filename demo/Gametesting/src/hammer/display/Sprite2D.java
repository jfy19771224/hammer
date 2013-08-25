package hammer.display;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import hammer.math.Math2D;
import hammer.net.LoaderAssets;
import hammer.opengl2d.LoadTexture;
import hammer.opengl2d.System2D;
import hammer.textures.Texture2D;
import hammer.textures.TextureData;
import hammer.ui.MouseEvent2D;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import hammer.core.LoadAddress;
import hammer.event.Event2D;
import hammer.filter.FilterBasicShader;
import hammer.filter.FilterShader;
import hammer.geom.Array;
import hammer.geom.BufferFactory;
import hammer.geom.Matrix2D;
import hammer.geom.Point2D;
import hammer.geom.Rectangle;


/**
 * 顶级显示容器核心类,坐标以容器的中心点为准
 * 
 * @author 花菜游戏
 * 
 */
public class Sprite2D extends DisplayerObject2D{

	protected ShortBuffer indexBuffer;
	protected FloatBuffer triggerBuffer;
	protected FloatBuffer uvBuffer;
	protected FloatBuffer colourBuffer;
	
	private float[] triangleFloat;
	private float[] uvFloat;
	private float[] colourFloat;
	
	private Array mChildList;
	/**
	 * 是否启用着色器,关闭此属性可显著提高性能
	 */
	private boolean mShaderDerail;
	
	protected int bitmapInit;
	protected Bitmap bmp;
	protected boolean visible;
	
	private int vertexId;
	public Texture2D texture2D;
	public Array quadList;
	public int SRC;
	public int DST;
	private Quad quad;
	private boolean batch;
	private boolean isUpdata;
    
    private FilterShader filter;

    
	/**
	 * 根据位图路径创建一个新静态的显示对象
	 * @param url
	 */
	public Sprite2D() {
		super();
		mChildList=new Array();
		setMatrix2D(new Matrix2D());
		getMatrix2D().upDataBasrMatrix(mRotation,this.mX,this.mY,this.mScaleX,this.mScaleY, this.mBiasX, this.mBiasY);
		this.mShaderDerail=true;
		SRC=GL10.GL_SRC_ALPHA;
		DST=GL10.GL_ONE_MINUS_SRC_ALPHA;
		initShade();
	}
	
	/**
	 * 根据位图路径创建一个新静态的显示对象
	 * @param url
	 */
	public Sprite2D(Texture2D texture2D,int num) {
		super();
		mChildList=new Array();
		setMatrix2D(new Matrix2D());
		getMatrix2D().upDataBasrMatrix(mRotation,this.mX,this.mY,this.mScaleX,this.mScaleY, this.mBiasX, this.mBiasY);
		this.vertexId=num;
		this.batch=false;
		indexBuffer=BufferFactory.createShortBuffer(6*num);
		triggerBuffer=BufferFactory.createFloatBuffer(8*num);
		uvBuffer=BufferFactory.createFloatBuffer(8*num);
		colourBuffer=BufferFactory.createFloatBuffer(16*num);
		quadList=new Array();
		for(int i=0;i<num;i++)
		{
			Quad quad=new Quad(texture2D.animationSupervisor,getMatrix2D(),(short)i,texture2D.getWidth(),texture2D.getHeight(),indexBuffer,triggerBuffer,uvBuffer,colourBuffer);
			quad.batch=batch;
			quadList.push(quad);
		}
		
		this.texture2D=texture2D;
		bitmapInit = texture2D.getTextureId();
		this.mShaderDerail=true;
		
		triangleFloat=new float[num*8];
		uvFloat=new float[num*8];
		colourFloat=new float[num*16];
		
		SRC=GL10.GL_SRC_ALPHA;
		DST=GL10.GL_ONE_MINUS_SRC_ALPHA;
		initShade();
	}
	/**
	 * 根据位图路径创建一个新静态的显示对象
	 * @param url
	 */
	public Sprite2D(Texture2D texture2D) {
		super();
		mChildList=new Array();
		setMatrix2D(new Matrix2D());
		getMatrix2D().upDataBasrMatrix(mRotation,this.mX,this.mY,this.mScaleX,this.mScaleY, this.mBiasX, this.mBiasY);
		this.vertexId=1;
		this.batch=false;
		indexBuffer=BufferFactory.createShortBuffer(6*vertexId);
		triggerBuffer=BufferFactory.createFloatBuffer(8*vertexId);
		uvBuffer=BufferFactory.createFloatBuffer(8*vertexId);
		colourBuffer=BufferFactory.createFloatBuffer(16*vertexId);
		quadList=new Array();
		for(int i=0;i<vertexId;i++)
		{
			Quad quad=new Quad(texture2D.animationSupervisor,getMatrix2D(),(short)i,texture2D.getWidth(),texture2D.getHeight(),indexBuffer,triggerBuffer,uvBuffer,colourBuffer);
			quad.batch=batch;
			quadList.push(quad);
		}
		
		this.texture2D=texture2D;
		bitmapInit = texture2D.getTextureId();
		this.mShaderDerail=true;
		
		triangleFloat=new float[vertexId*8];
		uvFloat=new float[vertexId*8];
		colourFloat=new float[vertexId*16];
		
		SRC=GL10.GL_SRC_ALPHA;
		DST=GL10.GL_ONE_MINUS_SRC_ALPHA;
		
		quad=this.getQuad();
		quad.maskRectangle();
		this.addChild(quad);
		initShade();
	}
	
	/**
	 * 根据位图路径创建一个新静态的显示对象
	 * @param url
	 */
	public Sprite2D(Texture2D texture2D,int num,boolean batch) {
		super();
		mChildList=new Array();
		setMatrix2D(new Matrix2D());
		getMatrix2D().upDataBasrMatrix(mRotation,this.mX,this.mY,this.mScaleX,this.mScaleY, this.mBiasX, this.mBiasY);
		this.vertexId=num;
		this.batch=batch;
		indexBuffer=BufferFactory.createShortBuffer(6*num);
		triggerBuffer=BufferFactory.createFloatBuffer(8*num);
		uvBuffer=BufferFactory.createFloatBuffer(8*num);
		colourBuffer=BufferFactory.createFloatBuffer(16*num);
		quadList=new Array();
		for(int i=0;i<num;i++)
		{
			Quad quad=new Quad(texture2D.animationSupervisor,getMatrix2D(),(short)i,texture2D.getWidth(),texture2D.getHeight(),indexBuffer,triggerBuffer,uvBuffer,colourBuffer);
			quad.batch=batch;
			quadList.push(quad);
		}
		
		this.texture2D=texture2D;
		bitmapInit = texture2D.getTextureId();
		this.mShaderDerail=false;
		
		triangleFloat=new float[num*8];
		uvFloat=new float[num*8];
		colourFloat=new float[num*16];
		
		SRC=GL10.GL_SRC_ALPHA;
		DST=GL10.GL_ONE_MINUS_SRC_ALPHA;
		initShade();
	}
	public FilterShader getFilter()
    {
    	return filter;
    }
    public void setFilter(FilterShader filter)
    {
    	this.filter=filter;
    }
	public int getBitmapInit()
    {
    	return bitmapInit;
    }
    public int getVertexId()
    {
    	return vertexId;
    }
    public ShortBuffer getIndexBuffer()
    {
    	return indexBuffer;
    }
    
    public FloatBuffer getTriggerBuffer()
    {
    	return triggerBuffer;
    }
    
    public FloatBuffer getUvBuffer()
    {
    	return uvBuffer;
    }
    
    public FloatBuffer getColourBuffer()
    {
    	return colourBuffer;
    }
	private void initShade()
	{
		filter=new FilterBasicShader();
	}
	
	public void setShaderDerail(boolean value)
	{
		this.mShaderDerail=value;
	}
	
	public boolean getShaderDerail()
	{
		return this.mShaderDerail;
	}
	
	public void setTexture(Texture2D texture)
	{
		bitmapInit = texture.getTextureId();
	}

	/**
	 * 获取一个QUAD对象
	 * @return
	 */
	public Quad getQuad()
	{
		for(int i=0;i<quadList.getLength();i++)
		{
			Quad quad=(Quad) quadList.getArray()[i];
			if(!quad.start)
			{
				quad.maskRectangle();
				quad.launch();
				return quad;
			}
		}
		return null;
	}
	
	/**
	 * 添加显示元素
	 * @param child
	 */
	public void addChild(Sprite2D child)
	{
		if(child.getParent()!=null)
		{
			mChildList.splice(mChildList.indexOf(child));
			mChildList.push(child);
		}else
		{
			child.setParent(this);
			mChildList.push(child);
		}
		
	}
	
	public void removeChild(Sprite2D child)
	{
		int index=mChildList.indexOf(child);
		if(index!=-1)
		{
			child.setParent(null);
			mChildList.splice(index);
		}
		
	}
	/**
	 * 添加QUAD
	 * @param child
	 */
	public void addChild(Quad child)
	{
		if(child.getParent()!=null)
		{
			int index=quadList.indexOf(child);
			for(int i=index+1;i<quadList.getLength();i++)
			{
				Quad quad=(Quad) quadList.getArray()[i];
				quad.setId((short)(quad.getId()-1));
			}
			child.setId((short) (quadList.getLength()-1));
			
			quadList.splice(index);
			quadList.push(child);
		}
		child.setParent(this);
		
	}
	
	/**
	 * 删除QUAD
	 * @param child
	 */
	public void removeChild(Quad child)
	{
		child.setParent(null);
	}

	
	/**
	 * 获得对象的旋转角度
	 * @return
	 */
	public float getRotation()
	{
		return this.mRotation;
	}
	
	/**
	 * 设置对象的旋转角度
	 * @param value
	 */
	public void setRotation(float value)
	{
		this.mRotation=value;
		this.isUpdata=true;
	}
	
	/**
	 * 获取对象X轴倾斜值
	 */
	public float getBiasX()
	{
		return this.mBiasX;
	}
	/**
	 * 设置对象的X轴倾斜值
	 * @param value
	 */
	public void setBiasX(float value)
	{
		this.mBiasX=value;
		this.isUpdata=true;
	}
	
	/**
	 * 获取对象Y轴倾斜值
	 */
	public float getBiasY()
	{
		return this.mBiasY;
	}
	/**
	 * 设置对象的Y轴倾斜值
	 * @param value
	 */
	public void setBiasY(float value)
	{
		this.mBiasY=value;
		this.isUpdata=true;
	}
	
	/**
	 * 获取对象的X旋转中心
	 * @return
	 */
	public float getPivotX()
	{
		return this.mPivotX;
	}
	
	/**
	 * 设置对象的X旋转中心
	 * @param value
	 */
	public void setPivotX(float value)
	{
		this.mPivotX=value;
		this.isUpdata=true;
	}
	
	
	/**
	 * 获取对象的Y旋转中心
	 * @return
	 */
	public float getPivotY()
	{
		return this.mPivotY;
	}
	
	/**
	 * 设置对象的Y旋转中心
	 * @param value
	 */
	public void setPivotY(float value)
	{
		this.mPivotY=value;
		this.isUpdata=true;
	}
	/**
	 * 获取对象的A通道值
	 * @return
	 */
	public float getA()
	{
		return this.mA;
	}
	
	/**
	 * 设置对象的A通道值
	 * @param value
	 */
	public void setA(float value)
	{
		this.mA=value;
	}
	
	/**
	 * 获取对象的B通道值
	 * @return
	 */
	public float getB()
	{
		return this.mB;
	}
	
	/**
	 * 设置对象的B通道值
	 * @param value
	 */
	public void setB(float value)
	{
		this.mB=value;
	}
	
	/**
	 * 获取对象的G通道值
	 * @return
	 */
	public float getG()
	{
		return this.mG;
	}
	
	/**
	 * 设置对象的G通道值
	 * @param value
	 */
	public void setG(float value)
	{
		this.mG=value;
	}
	
	
	/**
	 * 获取对象的R通道值
	 * @return
	 */
	public float getR()
	{
		return this.mR;
	}
	
	/**
	 * 设置对象的R通道值
	 * @param value
	 */
	public void setR(float value)
	{
		this.mR=value;
	}
	
	/**
	 * 设置对象的透明度
	 * @param value
	 */
	public void setAlpha(float value)
	{
		this.mAlpha=value;
		
	}
	
	/**
	 * 获取对象的透明度
	 * @return
	 */
	public float getAlpha()
	{
		return this.mAlpha;
	}
	
	/**
	 * 获取对象的Y轴比例值
	 * @return
	 */
	public float getScaleY()
	{
		return this.mScaleY;
	}
	
	/**
	 * 设置对象的Y轴比例值
	 * @param value
	 */
	public void setScaleY(float value)
	{
		this.mScaleY=value;
		this.isUpdata=true;
	}
	/**
	 * 获取对象的X比例
	 * @return
	 */
	public float getScaleX()
	{
		return mScaleX;
	}
	
	/**
	 * 设置对象的X比例
	 * @param value
	 */
	public void setScaleX(float value)
	{
		this.mScaleX=value;
		this.isUpdata=true;
	}

	/**
	 * 获取对象的X轴的坐标
	 * @return
	 */
	public float getX() {
		return mX;
	}

	/**
	 * 设置对象X轴的坐标
	 * @param x
	 */
	public void setX(float value) {
		this.mX = value;
		this.isUpdata=true;
	}
	
	/**
	 * 获取对象Y轴的坐标
	 * @return
	 */
	public float getY() {
		return mY;
	}

	/**
	 * 设置对象的Y轴的坐标
	 * @param x
	 */
	public void setY(float value) {
		this.mY = value;
		this.isUpdata=true;
	}
	private float s=0;
	
	
	/**
	 * 设置对象的宽度
	 * @param value
	 */
	public void setWidth(float value)
	{
		this.mWidth=value;
	}
	
	/**
	 * 获取对象的宽度
	 * @return
	 */
	public float getWidth()
	{
		int i;
		if(quadList!=null)
		{
			for(i=0;i<quadList.getLength();i++)
			{
				Quad quad=(Quad) quadList.getArray()[i];
				if(quad.vertexPointToGlobal(Quad.RIGHT_UP).getX()>=maxWidth)
				{
					maxWidth=quad.vertexPointToGlobal(Quad.RIGHT_UP).getX();
				}
			}
			
			for(i=0;i<quadList.getLength();i++)
			{
				if(quad.vertexPointToGlobal(Quad.RIGHT_UP).getX()<=minWidth)
				{
					minWidth=quad.vertexPointToGlobal(Quad.RIGHT_UP).getX();
				}
			}
		}
		return maxWidth-minWidth;
	}
	
	private float maxWidth=Integer.MIN_VALUE;
	private float minWidth=Integer.MAX_VALUE;
	
	/**
	 * 面积检测
	 * @param	p1
	 * @param	p2
	 * @param	p3
	 * @return
	 */
	public int hitTrianglePoint(Point2D p1,Point2D p2,Point2D p3)
	{
		if ((p2.getX()-p1.getX())*(p2.getY()+p1.getY())+(p3.getX()-p2.getX())*(p3.getY()+p2.getY())+(p1.getX()-p3.getX())*(p1.getY()+p3.getY())<0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * 顶点碰撞检测
	 * p1,p2,p3 为范围点
	 * p4是碰撞点。
	 * @return
	 */
	public boolean hitPoint(Point2D p1,Point2D p2,Point2D p3,Point2D p4)
	{
		int a = hitTrianglePoint(p1,p2,p3);
		int b = hitTrianglePoint(p4,p2,p3);
		int c = hitTrianglePoint(p1,p2,p4);
		int d = hitTrianglePoint(p1,p4,p3);
		if ((b==a)&&(c==a)&&(d==a))
		{
			return true;
		}
		else
		{
			return false;

		}
	}
	public boolean isClick(MouseEvent2D mouseevent,System2D renderer,String value,float x,float y)
	{
		
		for(int j=quadList.getLength()-1;j>=0;j--)
		{
			Quad quad=(Quad) quadList.getArray()[j];
			if(this.isMouseEnabled()&&quad.isMouseEnabled()&&quad.start&&quad.mVisible)
			{
				
				if(quad.isMouseAccurate())
				{
					
					if(
						hitPoint(
						quad.vertexPointToGlobal(Quad.LEFT_UP),
						quad.vertexPointToGlobal(Quad.RIGHT_UP),
						quad.vertexPointToGlobal(Quad.LEFT_DOWN),
						new Point2D(x,y))||
						hitPoint(
						quad.vertexPointToGlobal(Quad.LEFT_DOWN),
						quad.vertexPointToGlobal(Quad.RIGHT_DOWN),
						quad.vertexPointToGlobal(Quad.RIGHT_UP),
						new Point2D(x,y)))
					 {
						
						mouseevent.setTarget(quad);
						mouseevent.setTargetSprite(this);
						if(value==Event2D.MOUSE_MOVE)
						{
							quad.mouseMove(mouseevent);
						}else if(value==Event2D.MOUSE_UP)
						{
							quad.mouseUp(mouseevent);
						}else if(value==Event2D.MOUSE_DOWN)
						{
							quad.mouseDown(mouseevent);
						}
						return true;
					}

					}else
					{
					
					
					if(Math.abs((quad.vertexPointToGlobal(Quad.MIDDLE).getX()-x))<=quad.getWidth()/2&&Math.abs((quad.vertexPointToGlobal(Quad.MIDDLE).getY()-y))<=quad.getHeight()/2)
					{
						
						mouseevent.setTarget(quad);
						mouseevent.setTargetSprite(this);
						if(value==Event2D.MOUSE_MOVE)
						{
							quad.mouseMove(mouseevent);
						}else if(value==Event2D.MOUSE_UP)
						{
							quad.mouseUp(mouseevent);
						}else if(value==Event2D.MOUSE_DOWN)
						{
							quad.mouseDown(mouseevent);
						}
						return true;
					}
				}
			}
		}
		
		for(int i=mChildList.getLength()-1;i>=0;i--)
		{
			Sprite2D child=(Sprite2D)mChildList.getArray()[i];
			if(this.isMouseEnabled()&&child.isMouseEnabled())
			{
				if(child.isClick(mouseevent,renderer,value,x, y))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 返回对象的子列表 
	 * @return
	 */
	public Array getChildList()
	{
		return mChildList;
	}

   
	/*
	 * 绘制动画
	 */
	public void paint(GL10 gl) 
	{
		
		if(System2D.glVersions==1)
		{
			gl.glBlendFunc(this.SRC, this.DST);
		}else if(System2D.glVersions==2)
		{
			GLES20.glBlendFunc(this.SRC, this.DST);
		}
		
		if(isUpdata)
		{
			isUpdata=false;

			getMatrix2D().upDataBasrMatrix(
			this.mRotation,
			-2f*(this.mX)/System2D.getStageHeight(),
			-2f*(this.mY)/System2D.getStageHeight(),
			this.mScaleX,
			this.mScaleY, 
			this.mBiasX, 
			this.mBiasY);
			if(this.getParent()!=null)
			{
				this.getMatrix2D().baseMatrix=getMatrix2D().add33(this.getMatrix2D().baseMatrix,this.getParent().getMatrix2D().baseMatrix);
			}
			
			if(quadList!=null)
			{
				int len=quadList.getLength();
				for(int i=0;i<len;i++)
				{
					Quad quad=(Quad) quadList.getArray()[i];
					quad.upDataMatrix();
					
				}
				
			}
			
			for(int c=0;c<mChildList.getLength();c++)
			{
				Sprite2D child=(Sprite2D)mChildList.getArray()[c];
				child.isUpdata=true;
			}
		}
		
		if(quadList!=null)
		{
			int len=quadList.getLength();
			for(int i=0;i<len;i++)
			{
				Quad quad=(Quad) quadList.getArray()[i];
				quad.upData();
			}
				
			if(batch)
			{
				triggerBuffer.position(0);
				uvBuffer.position(0);
				colourBuffer.position(0);
				for(int j=0;j<vertexId;j++)
				{
					
					Quad quad=(Quad) quadList.getArray()[j];
					triangleFloat[quad.getId()+j*7]=quad.getTriangleFloat()[0];
					triangleFloat[quad.getId()+j*7+1]=quad.getTriangleFloat()[1];
					triangleFloat[quad.getId()+j*7+2]=quad.getTriangleFloat()[2];
					triangleFloat[quad.getId()+j*7+3]=quad.getTriangleFloat()[3];
					triangleFloat[quad.getId()+j*7+4]=quad.getTriangleFloat()[4];
					triangleFloat[quad.getId()+j*7+5]=quad.getTriangleFloat()[5];
					triangleFloat[quad.getId()+j*7+6]=quad.getTriangleFloat()[6];
					triangleFloat[quad.getId()+j*7+7]=quad.getTriangleFloat()[7];
					
					uvFloat[quad.getId()+j*7]=quad.getUvFloat()[0];
					uvFloat[quad.getId()+j*7+1]=quad.getUvFloat()[1];
					uvFloat[quad.getId()+j*7+2]=quad.getUvFloat()[2];
					uvFloat[quad.getId()+j*7+3]=quad.getUvFloat()[3];
					uvFloat[quad.getId()+j*7+4]=quad.getUvFloat()[4];
					uvFloat[quad.getId()+j*7+5]=quad.getUvFloat()[5];
					uvFloat[quad.getId()+j*7+6]=quad.getUvFloat()[6];
					uvFloat[quad.getId()+j*7+7]=quad.getUvFloat()[7];
					
				}
				for(int c=0;c<vertexId;c++)
				{
					Quad quad=(Quad) quadList.getArray()[c];
					colourFloat[quad.getId()+c*15]=quad.getColourFloat()[0];
					colourFloat[quad.getId()+c*15+1]=quad.getColourFloat()[1];
					colourFloat[quad.getId()+c*15+2]=quad.getColourFloat()[2];
					colourFloat[quad.getId()+c*15+3]=quad.getColourFloat()[3];
					colourFloat[quad.getId()+c*15+4]=quad.getColourFloat()[4];
					colourFloat[quad.getId()+c*15+5]=quad.getColourFloat()[5];
					colourFloat[quad.getId()+c*15+6]=quad.getColourFloat()[6];
					colourFloat[quad.getId()+c*15+7]=quad.getColourFloat()[7];
					colourFloat[quad.getId()+c*15+8]=quad.getColourFloat()[8];
					colourFloat[quad.getId()+c*15+9]=quad.getColourFloat()[9];
					colourFloat[quad.getId()+c*15+10]=quad.getColourFloat()[10];
					colourFloat[quad.getId()+c*15+11]=quad.getColourFloat()[11];
					colourFloat[quad.getId()+c*15+12]=quad.getColourFloat()[12];
					colourFloat[quad.getId()+c*15+13]=quad.getColourFloat()[13];
					colourFloat[quad.getId()+c*15+14]=quad.getColourFloat()[14];
					colourFloat[quad.getId()+c*15+15]=quad.getColourFloat()[15];
				}
				triggerBuffer.put(triangleFloat);
				uvBuffer.put(uvFloat);
				colourBuffer.put(colourFloat);
			}
			indexBuffer.position(0);
			triggerBuffer.position(0);
			uvBuffer.position(0);
			colourBuffer.position(0);
			
			if(System2D.glVersions==1)
			{
				if(mShaderDerail)
				{
					gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
				}else
				{
					gl.glColor4f(this.mR*this.mAlpha,this.mG*this.mAlpha,this.mB*this.mAlpha,this.mA*this.mAlpha);
					gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
				}
				gl.glVertexPointer(2, GL10.GL_FLOAT, 0,triggerBuffer);
				gl.glTexCoordPointer(2, GL10.GL_FLOAT,0,uvBuffer);
				gl.glColorPointer(4, GL10.GL_FLOAT,0,colourBuffer);
				gl.glBindTexture(GL10.GL_TEXTURE_2D, bitmapInit);
				gl.glDrawElements(GL10.GL_TRIANGLES,6*vertexId,GL10.GL_UNSIGNED_SHORT, indexBuffer);
			}else if(System2D.glVersions==2)
			{
				filter.upData(this);
			}
		}
		
		for(int c=0;c<mChildList.getLength();c++)
		{
			Sprite2D child=(Sprite2D)mChildList.getArray()[c];
			child.paint(gl);
		}
	}
}
