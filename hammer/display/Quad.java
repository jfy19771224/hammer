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
	 * ��ǰ֡���
	 */
	private int currentFrame;
	
	/**
	 * ����֡����
	 */
	private int totalFrames;
	
	/**
	 * ��ǰ����
	 */
	private Object mScene;
	
	private QuadResource quadResource;
	
	public boolean batch;
	
	private Matrix2D matrix2D;
	
	private float pWidth;
	private float pHeight;
	
	/**
	 * ��ǰ֡��Ϣ
	 */
	private QuadFrame quadFrame;
	
	private EventDisplayer2D eventDisplayer2D;
	
	private Timer2D timer2d;
	private Function function;
	/**
	 * �Ƿ�ѭ������
	 */
	private boolean mLoop;
	
	/**
	 * �洢�û�����
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
	 * ֹͣ����
	 */
	public void stop()
	{
		timer2d.stop();
	}
	
	/**
	 * ��ʼ����
	 */
	public void play()
	{
		timer2d.start();
	}
	/**
	 * ��ָ����֡��ʼ����
	 * @param value
	 */
	public void gotoAndPlay(int value)
	{
		this.currentFrame=value-1;
		//upFrame();
		timer2d.start();
	}

	/**
	 * ֹͣ��ָ����֡
	 * @param value
	 */
	public void gotoAndStop(int value)
	{
		this.currentFrame=value-1;
		upFrame(false);
		timer2d.stop();
	}
	

	/**
	 * �Ƿ�ѭ�����Ŷ���
	 * @param value
	 */
	public void loop(boolean value)
	{
		this.mLoop=value;
	}
	
	/**
	 * ����
	 * @param e
	 */
	private void animationRun(Timer2D e)
	{
		upFrame(true);
	}
	/**
	 * ���ö���Ŀ��
	 * @param value
	 */
	public void setHeight(float value)
	{
		this.setScaleY(value/this.pHeight);
	}
	
	/**
	 * ��ȡ����Ŀ��
	 * @return
	 */
	public float getHeight()
	{
		return this.pHeight*this.mScaleY;
	}
	
	
	/**
	 * ���ö���Ŀ��
	 * @param value
	 */
	public void setWidth(float value)
	{
		this.setScaleX(value/this.pWidth);
	}
	
	/**
	 * ��ȡ����Ŀ��
	 * @return
	 */
	public float getWidth()
	{
		return this.pWidth*this.mScaleX;
	}
	
	
	
	/**
	 * �������־���
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
	 * �������־���
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
	 * ��ȡ��������
	 * @return
	 */
	public Object getScene()
	{
		return mScene;
	}
	
	/**
	 * ���ó���
	 * @param value
	 */
	public void setScene(Object value)
	{
		mScene=value;
		upFrame(false);
	}
	
	/**
	 * ����֡��Ϣ
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
	 * ָʾ�������ŵ�֡��.
	 * @param	frame �������ŵ�֡��.
	 */
	public void setAnimationSpeed(int frame) 
	{
		timer2d.setDelay((long) Math.floor(1000 / frame));
	}
	
	/**
	 * ��ȡ����ĺ�ɫͨ��ֵ
	 */
	public float getR() {
		return mR;
	}

	/**
	 * ���ú�ɫͨ��ֵ
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
	 * ��ȡ��ɫͨ��ֵ
	 */
	public float getG() {
		return mG;
	}

	/**
	 * ������ɫͨ��ֵ
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
	 * ��ȡ��ɫͨ��ֵ
	 */
	public float getB() {
		return mB;
	}

	/**
	 * ������ɫͨ��ֵ
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
	 * ��ȡAͨ��ֵ
	 */
	public float getA() {
		return mA;
	}

	/**
	 * ����Aͨ����ֵ
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
	 * ��ȡ͸����
	 */
	public float getAlpha() {
		return mAlpha;
	}

	/**
	 * ����͸����
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
	 * ���ö����Yƫ����
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
	 * ��ȡY��ƫ����
	 */
	public float getPivotY(){
		return this.mPivotY;
	}
	
	/**
	 * ����X��ƫ����
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
	 * ��ȡX��ƫ����
	 */
	public float getPivotX(){
		return this.mPivotX;
	}
	
	/**
	 * ����X����бֵ
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
	 * ��ȡX��бֵ
	 */
	public float getBiasX(){
		return this.mBiasX;
	}
	
	/**
	 * ����Y��бֵ
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
	 * ��ȡY��бֵ
	 */
	public float getBiasY(){
		return this.mBiasY;
	}
	
	/**
	 * ����X����ֵ
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
	 * ��ȡX����ֵ
	 */
	public float getScaleX(){
		return this.mScaleX;
	}
	
	/**
	 * ����Y����ֵ
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
	 * ��ȡY����ֵ
	 */
	public float getScaleY(){
		return this.mScaleY;
	}
	
	/**
	 * ������ʾ����Ƕ�
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
	 * ��ȡ��ʾ����Ƕ�
	 * @return
	 */
	public float getRotation()
	{
		return this.mRotation;
	}
	
	/**
	 * ������ʾ����X���ֵ
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
	 * ��ȡ��ʾ�����X���ֵ
	 * @return
	 */
	public float getX()
	{
		return this.mX;
	}
	
	/**
	 * ������ʾ�����Y��ֵ
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
	 * ��ȡ��ʾ�����Y��ֵ
	 * @return
	 */
	public float getY()
	{
		return this.mY;
	}
	
	/**
	 * ��ʼ��
	 */
	private void initList()
	{
		//����������������
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
	 * ������ɫ��Ϣ
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
	 * ����
	 */
	public void launch()
	{
		start=true;
		this.isUpdata=true;
	}
	
	/**
	 * ����
	 */
	public void dispose()
	{
		start=false;
		this.mVisible=false;
		this.isUpdata=false;
	}
	/**
	 * �Ƿ�������ʾ����
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
	 * ��ȡ��ʾ���Ӱ���״̬
	 * @return
	 */
	public boolean getVisible()
	{
		return this.mVisible;
	}
	
	/**
	 * �߼�������
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
	 * ���¾�����Ϣ
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
	 * �ƶ����ص�����
	 * @param e
	 */
	public void mouseMove(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_MOVE, e);
	}
	
	/**
	 * �ɿ����ص�����
	 * @param e
	 */
	public void mouseUp(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_UP, e);
	}
	
	/**
	 * ������꺯������
	 * @param e
	 */
	public void mouseDown(MouseEvent2D e)
	{
		e.setCurrentTarget(this);
		eventDisplayer2D.dispatchEvent(Event2D.MOUSE_DOWN, e);
	}

	/**
	 * ��ȡ�����¼�����
	 */
	public EventDisplayer2D getEvent2D() {
		
		return eventDisplayer2D;
	}

	/**
	 * ��ȡ�������꼯��
	 * @return
	 */
	public float[] getTriangleFloat() {
		return triangleFloat;
	}

	/**
	 * ���ö������꼯��
	 * @param triangleFloat
	 */
	public void setTriangleFloat(float[] triangleFloat) {
		this.triangleFloat = triangleFloat;
	}

	/**
	 * ��ȡUV��Ϣ����
	 * @return
	 */
	public float[] getUvFloat() {
		return uvFloat;
	}

	/**
	 * ����UV��Ϣ����
	 * @param uvFloat
	 */
	public void setUvFloat(float[] uvFloat) {
		this.uvFloat = uvFloat;
	}

	/**
	 * ��ȡ��ɫ��Ϣ����
	 * @return
	 */
	public float[] getColourFloat() {
		return colourFloat;
	}

	/**
	 * ������ɫ��Ϣ����
	 * @param colourFloat
	 */
	public void setColourFloat(float[] colourFloat) {
		this.colourFloat = colourFloat;
	}

	/**
	 * ��ȡ��ʱ�洢����
	 * @return
	 */
	public Object getUserData() {
		return userData;
	}

	/**
	 * ������ʱ�洢����
	 * @param userData
	 */
	public void setUserData(Object userData) {
		this.userData = userData;
	}
}
