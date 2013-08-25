package hammer.display;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import hammer.batch.QuadData;
import hammer.batch.QuadFrame;
import hammer.batch.QuadResource;
import hammer.core.Function;
import hammer.event.Event2D;
import hammer.event.EventDisplayer2D;
import hammer.event.IEvent2D;
import hammer.geom.Matrix2D;
import hammer.geom.Point2D;
import hammer.geom.Rectangle;
import hammer.opengl2d.System2D;
import hammer.ui.MouseEvent2D;
import hammer.utils.Timer2D;
import hammer.utils.TimerEvent2D;

public class Quad extends DisplayerObject2D implements IEvent2D{
	private Point2D right_down_point;
	private Point2D right_up_point;
	private Point2D left_down_point;
	private Point2D left_up_point;
	private Panel panel;
	private short id;
	private boolean isTrigger;
	private boolean isIndex;
	private boolean isUv;
	private boolean isColour;
	
	private boolean isUpdata;
	private boolean isRotation;
	private boolean isXY;
	private boolean isUpdataPanel;
	private float maskWidth;
	private float maskHeight;
	
	private float uv1;
	private float uv2;
	private float uv3;
	private float uv4;
	private float uv5;
	private float uv6;
	private float uv7;
	private float uv8;
	private Rectangle rectangle;
	
	protected ShortBuffer indexBuffer;
	protected FloatBuffer triggerBuffer;
	protected FloatBuffer uvBuffer;
	protected FloatBuffer colourBuffer;
	
	private short[] indexShort;
	private float[] triangleFloat;
	private float[] colourFloat;
	private float[] uvFloat;
	
	private float stageWidth;
	private float stageHeight;
	private QuadData quadData;
	
	public boolean start;
	
	/**
	 * 当前帧编号
	 */
	private int currentFrame;
	
	/**
	 * 动画帧长度
	 */
	private int totalFrames;
	
	/**
	 * 当前场景
	 */
	private Object mScene;
	
	private QuadResource quadResource;
	
	public boolean batch;
	
	private Matrix2D matrix2D;
	
	private float pWidth;
	private float pHeight;
	
	/**
	 * 当前帧信息
	 */
	private QuadFrame quadFrame;
	
	private EventDisplayer2D eventDisplayer2D;
	
	private Timer2D timer2d;
	private Function function;
	/**
	 * 是否循环播放
	 */
	private boolean mLoop;
	
	/**
	 * 存储用户数据
	 */
	private Object userData;
	
	public Quad(QuadResource quadResource,Matrix2D matrix2D,
			short id,float width,float height,
			ShortBuffer indexBuffer,
			FloatBuffer triggerBuffer,
			FloatBuffer uvBuffer,
			FloatBuffer colourBuffer)
	{
		super();
		this.quadResource=quadResource;
		this.id=id;
		this.matrix2D=matrix2D;
		this.mWidth=width;
		this.mHeight=height;
		this.pWidth=width;
		this.pHeight=height;
		
		this.stageWidth=System2D.getStageWidth();
		this.stageHeight=System2D.getStageHeight();
		
		this.indexBuffer=indexBuffer;
		this.triggerBuffer=triggerBuffer;
		this.uvBuffer=uvBuffer;
		this.colourBuffer=colourBuffer;
		
		right_down_point=new Point2D(0,0);
		right_up_point=new Point2D(0,0);
		left_down_point=new Point2D(0,0);
		left_up_point=new Point2D(0,0);
		
		rectangle=new Rectangle(0,0,width,height);
		maskRectangle(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight());
		maskWidth=width/(rectangle.getWidth());
		maskHeight=height/(rectangle.getHeight());

		
		uv1=rectangle.getX()/width;//x
		uv2=1/maskHeight+rectangle.getY()/height;
		
		uv3=1/maskWidth+rectangle.getX()/width;//x
		uv4=1/maskHeight+1f*rectangle.getY()/height;
		
		uv5=rectangle.getX()/width;//x
		uv6=rectangle.getY()/height;
		
		uv7=1/maskWidth+rectangle.getX()/width;//x
		uv8=rectangle.getY()/height;

		this.currentFrame=0;
		this.isUpdata=true;
		this.isColour=true;
		this.isUpdataPanel=true;
		
		left_down_point.setX(1f*width/stageHeight);
		left_down_point.setY(-1f*height/stageHeight);
		
		right_down_point.setX(-1f*width/stageHeight);
		right_down_point.setY(-1f*height/stageHeight);
		
		left_up_point.setX(1f*width/stageHeight);
		left_up_point.setY(1f*height/stageHeight);
		
		right_up_point.setX(-1f*width/stageHeight);
		right_up_point.setY(1f*height/stageHeight);
		
		
		
		
		
		isTrigger=true;
		isIndex=true;
		isUv=true;
		isColour=true;
		isXY=true;
		
		setScene(1);
		
		panel=new Panel(this.matrix2D,false);
		panel.addItem(right_down_point);
		panel.addItem(right_up_point);
		panel.addItem(left_down_point);
		panel.addItem(left_up_point);

		indexShort=new short[2*3];
		setTriangleFloat(new float[2*4]);
		setUvFloat(new float[2*4]);
		setColourFloat(new float[4*4]);
		initList();
		eventDisplayer2D=new EventDisplayer2D();
		
		function=new Function(this,"animationRun");
		timer2d=new Timer2D();
		timer2d.getEvent2D().addEventListener(TimerEvent2D.TIMER,function);
		timer2d.start();
		gotoAndStop(1);
	
		
	}
	
	public int getTotalFrames()
	{
		return this.totalFrames;
	}
	
	public void setTotalFrames(int value)
	{
		this.totalFrames=value;
	}
	
	public short getId()
	{
		return this.id;
	}
	
	public void setId(short value)
	{
		this.id=value;
	}
	
	
	/**
	 * 停止播放
	 */
	public void stop()
	{
		timer2d.stop();
	}
	
	/**
	 * 开始播放
	 */
	public void play()
	{
		timer2d.start();
	}
	/**
	 * 从指定的帧开始播放
	 * @param value
	 */
	public void gotoAndPlay(int value)
	{
		this.currentFrame=value-1;
		//upFrame();
		timer2d.start();
	}

	/**
	 * 停止到指定的帧
	 * @param value
	 */
	public void gotoAndStop(int value)
	{
		this.currentFrame=value-1;
		upFrame(false);
		timer2d.stop();
	}
	

	/**
	 * 是否循环播放动画
	 * @param value
	 */
	public void loop(boolean value)
	{
		this.mLoop=value;
	}
	
	/**
	 * 动画
	 * @param e
	 */
	private void animationRun(Timer2D e)
	{
		upFrame(true);
	}
	/**
	 * 设置对象的宽度
	 * @param value
	 */
	public void setHeight(float value)
	{
		this.setScaleY(value/this.pHeight);
	}
	
	/**
	 * 获取对象的宽度
	 * @return
	 */
	public float getHeight()
	{
		return this.pHeight*this.mScaleY;
	}
	
	
	/**
	 * 设置对象的宽度
	 * @param value
	 */
	public void setWidth(float value)
	{
		this.setScaleX(value/this.pWidth);
	}
	
	/**
	 * 获取对象的宽度
	 * @return
	 */
	public float getWidth()
	{
		return this.pWidth*this.mScaleX;
	}
	
	
	
	/**
	 * 设置遮罩矩形
	 * @param value
	 */
	public void maskRectangle()
	{
		
		rectangle.setX(0);
		rectangle.setY(0);
		rectangle.setWidth(mWidth);
		rectangle.setHeight(mHeight);
		rectangle.reset();
		maskWidth=mWidth/rectangle.getWidth();
		maskHeight=mHeight/rectangle.getHeight();
		
		this.pWidth=rectangle.getWidth();
		this.pHeight=rectangle.getHeight();
		this.isUpdataPanel=true;
		this.isTrigger=true;
		this.isUpdata=true;
		this.isUv=true;
		
	}
	
	/**
	 * 设置遮罩矩形
	 * @param value
	 */
	public void maskRectangle(float x,float y,float width,float height)
	{
		this.rectangle.setX(x);
		this.rectangle.setY(y);
		this.rectangle.setWidth(width);
		this.rectangle.setHeight(height);
		maskWidth=mWidth/rectangle.getWidth();
		maskHeight=mHeight/rectangle.getHeight();
	
		this.pWidth=rectangle.getWidth();
		this.pHeight=rectangle.getHeight();
		this.isUpdataPanel=true;
		this.isTrigger=true;
		this.isUpdata=true;
		this.isUv=true;
		
	}
	
	/**
	 * 获取场景名称
	 * @return
	 */
	public Object getScene()
	{
		return mScene;
	}
	
	/**
	 * 设置场景
	 * @param value
	 */
	public void setScene(Object value)
	{
		mScene=value;
		upFrame(false);
	}
	
	/**
	 * 更新帧信息
	 */
	private void upFrame(boolean updata)
	{
		if(quadResource!=null)
		{
			if(mScene instanceof String){ 
				quadData=(QuadData) quadResource.getmDictionary().get((String)mScene);
			}else if(mScene instanceof Integer){ 	
				quadData=(QuadData) quadResource.getmQuadDataList().getArray()[(Integer)mScene];
			}
			totalFrames=quadData.getQuadFrameLst().getLength();	
			quadFrame=(QuadFrame) quadData.getQuadFrameLst().getArray()[currentFrame];
			if(updata)
			currentFrame++;
			if(currentFrame>=totalFrames)
			{
				if(this.mLoop)
				{
					currentFrame=0;
				}else
				{
					currentFrame=totalFrames-1;
				}
			}
			
			rectangle.setX(quadFrame.getX());
			rectangle.setY(quadFrame.getY());
			rectangle.setWidth(quadFrame.getWidth());
			rectangle.setHeight(quadFrame.getHeight());
			this.maskRectangle(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight());
			isXY=true;
		}
	}
	
	/**
	 * 指示动画播放的帧率.
	 * @param	frame 动画播放的帧率.
	 */
	public void setAnimationSpeed(int frame) 
	{
		timer2d.setDelay((long) Math.floor(1000 / frame));
	}
	
	/**
	 * 获取对象的红色通道值
	 */
	public float getR() {
		return mR;
	}

	/**
	 * 设置红色通道值
	 */
	public void setR(float r) {
		if(this.mR!=r)
		{
			this.mR = r;
			this.isColour=true;
			this.isUpdata=true;
		}
	}

	/**
	 * 获取绿色通道值
	 */
	public float getG() {
		return mG;
	}

	/**
	 * 设置绿色通道值
	 */
	public void setG(float g) {
		if(this.mG!=g)
		{
			this.mG = g;
			this.isColour=true;
			this.isUpdata=true;
		}
	}

	/**
	 * 获取蓝色通道值
	 */
	public float getB() {
		return mB;
	}

	/**
	 * 设置蓝色通道值
	 */
	public void setB(float b) {
		if(this.mB!=b)
		{
			this.mB = b;
			this.isColour=true;
			this.isUpdata=true;
		}
	}

	/**
	 * 获取A通道值
	 */
	public float getA() {
		return mA;
	}

	/**
	 * 设置A通道的值
	 */
	public void setA(float a) {
		if(this.mA!=a)
		{
			this.mA = a;
			this.isColour=true;
			this.isUpdata=true;
		}
	}

	/**
	 * 获取透明度
	 */
	public float getAlpha() {
		return mAlpha;
	}

	/**
	 * 设置透明度
	 */
	public void setAlpha(float alpha) {
		this.mAlpha = alpha;
		if(alpha<=0)
		{
			isColour=false;
		}else
		{
			isColour=true;
		}
		this.isUpdata=true;
	} 
	
	/**
	 * 设置对象的Y偏移量
	 */
	public void setPivotY(float value){
		if(this.mPivotY!=value)
		{
			this.mPivotY=value;
			this.isUpdata=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取Y轴偏移量
	 */
	public float getPivotY(){
		return this.mPivotY;
	}
	
	/**
	 * 设置X轴偏移量
	 */
	public void setPivotX(float value){
		if(this.mPivotX!=value)
		{
			this.mPivotX=value;
			this.isUpdata=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取X轴偏移量
	 */
	public float getPivotX(){
		return this.mPivotX;
	}
	
	/**
	 * 设置X轴倾斜值
	 */
	public void setBiasX(float value){
		if(this.mBiasX!=value)
		{
			this.mBiasX=value;
			this.isUpdata=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取X倾斜值
	 */
	public float getBiasX(){
		return this.mBiasX;
	}
	
	/**
	 * 设置Y倾斜值
	 */
	public void setBiasY(float value){
		if(this.mBiasY!=value)
		{
			this.mBiasY=value;
			this.isUpdata=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取Y倾斜值
	 */
	public float getBiasY(){
		return this.mBiasY;
	}
	
	/**
	 * 设置X比例值
	 */
	public void setScaleX(float value){
		if(this.mScaleX!=value)
		{
			this.mScaleX=value;
			this.isUpdata=true;
			this.isTrigger=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取X比例值
	 */
	public float getScaleX(){
		return this.mScaleX;
	}
	
	/**
	 * 设置Y比例值
	 */
	public void setScaleY(float value){
		if(this.mScaleY!=value)
		{
			this.mScaleY=value;
			this.isUpdata=true;
			this.isTrigger=true;
			this.isUpdataPanel=true;
		}
	}
	
	/**
	 * 获取Y比例值
	 */
	public float getScaleY(){
		return this.mScaleY;
	}
	
	/**
	 * 设置显示对象角度
	 * @param value
	 */
	public void setRotation(float value)
	{
		if(this.mRotation!=value)
		{
			this.mRotation=value;
			this.isUpdata=true;
			this.isTrigger=true;
			this.isRotation=true;
		}
	}
	
	/**
	 * 获取显示对象角度
	 * @return
	 */
	public float getRotation()
	{
		return this.mRotation;
	}
	
	/**
	 * 设置显示对象X轴的值
	 * @param value
	 */
	public void setX(float value)
	{
		if(this.mX!=value)
		{
			this.mX=value;
			this.isUpdata=true;
			this.isTrigger=true;
			this.isXY=true;
		}
	}
	
	/**
	 * 获取显示对象的X轴的值
	 * @return
	 */
	public float getX()
	{
		return this.mX;
	}
	
	/**
	 * 设置显示对象的Y轴值
	 * @param value
	 */
	public void setY(float value)
	{
		if(this.mY!=value)
		{
			this.mY=value;
			this.isUpdata=true;
			this.isTrigger=true;
			this.isXY=true;
		}
	}
	
	/**
	 * 获取显示对象的Y轴值
	 * @return
	 */
	public float getY()
	{
		return this.mY;
	}
	
	/**
	 * 初始化
	 */
	private void initList()
	{
		//创建顶点索引数组
		if(isIndex)
		{
			indexBuffer.position(id*6);
			indexShort[0]=(short) (id*4);
			indexShort[1]=(short) (id*4+1);
			indexShort[2]=(short) (id*4+2);
			indexShort[3]=(short) (id*4+1);
			indexShort[4]=(short) (id*4+2);
			indexShort[5]=(short) (id*4+3);
			indexBuffer.put(indexShort);
		}
		
	
		upDataColour();
	}
	
	/**
	 * 更新颜色信息
	 */
	private void upDataColour()
	{
		if(isColour)
		{
			
			if(!batch)
			colourBuffer.position(id*16);
			
			getColourFloat()[0]=mR*mAlpha;
			getColourFloat()[1]=mG*mAlpha;
			getColourFloat()[2]=mB*mAlpha;
			getColourFloat()[3]=mA*mAlpha;
			
			getColourFloat()[4]=mR*mAlpha;
			getColourFloat()[5]=mG*mAlpha;
			getColourFloat()[6]=mB*mAlpha;
			getColourFloat()[7]=mA*mAlpha;
			
			getColourFloat()[8]=mR*mAlpha;
			getColourFloat()[9]=mG*mAlpha;
			getColourFloat()[10]=mB*mAlpha;
			getColourFloat()[11]=mA*mAlpha;
			
			getColourFloat()[12]=mR*mAlpha;
			getColourFloat()[13]=mG*mAlpha;
			getColourFloat()[14]=mB*mAlpha;
			getColourFloat()[15]=mA*mAlpha;
			
			
			if(!batch)
			colourBuffer.put(getColourFloat());
		}
		
		if(isTrigger)
		{
			if(!batch)
			triggerBuffer.position(id*8);
			getTriangleFloat()[0]=left_down_point.getX();
			getTriangleFloat()[1]=left_down_point.getY();
			getTriangleFloat()[2]=right_down_point.getX();
			getTriangleFloat()[3]=right_down_point.getY();
			getTriangleFloat()[4]=left_up_point.getX();
			getTriangleFloat()[5]=left_up_point.getY();
			getTriangleFloat()[6]=right_up_point.getX();
			getTriangleFloat()[7]=right_up_point.getY();
			if(!batch)
			triggerBuffer.put(getTriangleFloat());
		}
		
		if(isUv)
		{
			
			uv1=rectangle.getX()/mWidth;//x
			uv2=1/maskHeight+rectangle.getY()/mHeight;
			
			uv3=1/maskWidth+rectangle.getX()/mWidth;//x
			uv4=1/maskHeight+rectangle.getY()/mHeight;
			
			uv5=rectangle.getX()/mWidth;//x
			uv6=rectangle.getY()/mHeight;
			
			uv7=1/maskWidth+rectangle.getX()/mWidth;//x
			uv8=rectangle.getY()/mHeight;
			
			if(!batch)
			uvBuffer.position(id*8);
			
			getUvFloat()[0]=uv1;
			getUvFloat()[1]=uv2;
			
			getUvFloat()[2]=uv3;
			getUvFloat()[3]=uv4;
			
			getUvFloat()[4]=uv5;
			getUvFloat()[5]=uv6;
			
			getUvFloat()[6]=uv7;
			getUvFloat()[7]=uv8;
			
			if(!batch)
			uvBuffer.put(getUvFloat());
		}

		
	}
	

	
	
	
	/**
	 * 启动
	 */
	public void launch()
	{
		start=true;
		this.isUpdata=true;
	}
	
	/**
	 * 销毁
	 */
	public void dispose()
	{
		start=false;
		this.mVisible=false;
		this.isUpdata=false;
	}
	/**
	 * 是否隐藏显示对象
	 * @param value
	 */
	public void setVisible(boolean value)
	{
		this.mVisible=value;
		this.isUpdataPanel=true;
		this.isTrigger=true;
		this.isUpdata=true;
		this.isUv=true;
	}
	public static int LEFT_UP=1;
	public static int LEFT_DOWN=2;
	public static int RIGHT_UP=3;
	public static int RIGHT_DOWN=4;
	public static int MIDDLE=5;
	
	public Point2D vertexPointToGlobal(int value)
	{
		float ax=0;
		float ay=0;
		switch(value)
		{
		case 1:
			ax=(this.left_up_point.getX()*stageHeight/-2f);
			ay=(this.left_up_point.getY()*stageHeight/-2f);
			break;
		case 2:
			ax=(this.left_down_point.getX()*stageHeight/-2f);
			ay=(this.left_down_point.getY()*stageHeight/-2f);
			break;
		case 3:
			ax=(this.right_up_point.getX()*stageHeight/-2f);
			ay=(this.right_up_point.getY()*stageHeight/-2f);
			break;
		case 4:
			ax=(this.right_down_point.getX()*stageHeight/-2f);
			ay=(this.right_down_point.getY()*stageHeight/-2f);
			break;
		case 5:
			ax=(this.left_up_point.getX()*stageHeight/-2f)+this.getWidth()/2;
			ay=(this.left_up_point.getY()*stageHeight/-2f)+this.getHeight()/2;
			break;
		}
		
		return new Point2D(ax,ay);
	}
	
	/**
	 * 获取显示对子昂的状态
	 * @return
	 */
	public boolean getVisible()
	{
		return this.mVisible;
	}
	
	/**
	 * 逻辑处理器
	 */
	private void logic()
	{
		if(isXY)
		{
			isXY=false;
			if(quadResource!=null)
			{	
				if(!isPivotMiddle())
				{
					this.setPivotX(quadFrame.getFrameX()-quadFrame.getWidth()/2);
					this.setPivotY(quadFrame.getFrameY()-quadFrame.getHeight()/2);
				}else
				{
					this.setPivotX(0);
					this.setPivotY(0);
				}
				panel.setX(-2f*(this.mX)/stageHeight);
				panel.setY(-2f*(this.mY)/stageHeight);
				//panel.setX(-2f*(this.mX-quadFrame.frameWidth/2)/stageHeight);
				//panel.setY(-2f*(this.mY-quadFrame.frameHeight/2)/stageHeight);
			}else
			{
				if(!isPivotMiddle())
				{
					this.setPivotX(-this.getWidth()/2);
					this.setPivotY(-this.getHeight()/2);
				}else
				{
					this.setPivotX(0);
					this.setPivotY(0);
				}
				panel.setX(-2f*(this.mX)/stageHeight);
				panel.setY(-2f*(this.mY)/stageHeight);
			}
		}
	}
	
	/**
	 * 更新矩阵信息
	 */
	public void upDataMatrix()
	{
		isTrigger=true;
		isUpdata=true;
	}
	public void upData()
	{
		logic();
		if(this.getParent()!=null&&start&&mVisible)
		{
			if(isUpdata)
			{
				if(isUpdataPanel)
				{
					
					panel.upDataMatrixData(
					2f*this.mPivotX/stageHeight*mScaleX,
					2f*this.mPivotY/stageHeight*mScaleY,
					this.mScaleX/maskWidth,
					this.mScaleY/maskHeight,
					this.mBiasX,
					this.mBiasY);
				}
				
				if(isRotation)
				panel.setRotation(this.mRotation);
				panel.upDataMatrix();
				upDataColour();
				isRotation=false;
				isUpdata=false;
				isUpdataPanel=false;
				isIndex=false;
				isTrigger=false;
				isUv=false;
				isColour=false;
			}
		}else
		{
			if(!batch)
			triggerBuffer.position(id*8);
			getTriangleFloat()[0]=0;
			getTriangleFloat()[1]=0;
			getTriangleFloat()[2]=0;
			getTriangleFloat()[3]=0;
			getTriangleFloat()[4]=0;
			getTriangleFloat()[5]=0;
			getTriangleFloat()[6]=0;
			getTriangleFloat()[7]=0;
			if(!batch)
			triggerBuffer.put(getTriangleFloat());
		}
	}

	/**
	 * 移动鼠标回调函数
	 * @param e
	 */
	public void mouseMove(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_MOVE, e);
	}
	
	/**
	 * 松开鼠标回调函数
	 * @param e
	 */
	public void mouseUp(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_UP, e);
	}
	
	/**
	 * 按下鼠标函数传递
	 * @param e
	 */
	public void mouseDown(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_DOWN, e);
	}

	/**
	 * 获取侦听事件对象
	 */
	public EventDisplayer2D getEvent2D() {
		
		return eventDisplayer2D;
	}

	/**
	 * 获取顶点坐标集合
	 * @return
	 */
	public float[] getTriangleFloat() {
		return triangleFloat;
	}

	/**
	 * 设置顶点坐标集合
	 * @param triangleFloat
	 */
	public void setTriangleFloat(float[] triangleFloat) {
		this.triangleFloat = triangleFloat;
	}

	/**
	 * 获取UV信息集合
	 * @return
	 */
	public float[] getUvFloat() {
		return uvFloat;
	}

	/**
	 * 设置UV信息集合
	 * @param uvFloat
	 */
	public void setUvFloat(float[] uvFloat) {
		this.uvFloat = uvFloat;
	}

	/**
	 * 获取颜色信息集合
	 * @return
	 */
	public float[] getColourFloat() {
		return colourFloat;
	}

	/**
	 * 设置颜色信息集合
	 * @param colourFloat
	 */
	public void setColourFloat(float[] colourFloat) {
		this.colourFloat = colourFloat;
	}

	/**
	 * 获取零时存储对象
	 * @return
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * 设置零时存储对象
	 * @param userData
	 */
	public void setUserData(Object userData) {
		this.userData = userData;
	}
}
