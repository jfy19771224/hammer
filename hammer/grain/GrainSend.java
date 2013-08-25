package hammer.grain;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;
import hammer.core.Function;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.geom.Array;
import hammer.textures.Texture2D;
import hammer.utils.Timer2D;
import hammer.utils.TimerEvent2D;

/**
 * 粒子发射器
 * @-式神-
 *
 */
public class GrainSend extends Sprite2D{

	private Timer2D timer2d;
	private Function function;
	private Array array;
	private Launcher[] launcherList;
	
	private int mDelay;
	private int mDelayValue;
	public GrainSend(Texture2D texture,int num,int sendNum)
	{
		super(texture,num);
		launcherList=new Launcher[sendNum]; 
		this.SRC=GL10.GL_SRC_ALPHA;
		this.DST=GL10.GL_ONE;
		
		
		array=new Array();
		
		for(int i=0;i<num;i++)
		{
			Quad quad=this.getQuad();
			quad.setMouseEnabled(false);
			quad.setPivotMiddle(true);
			quad.setVisible(false);
			this.addChild(quad);
			Grain grain=new Grain(quad);
			array.push(grain);
		}
		for(int j=0;j<sendNum;j++)
		{
			Launcher launcher=new Launcher();
			launcher.x =0;
			launcher.y =0;
			launcher.speedValue =.5;
			launcher.rotationRandom =6.28;
			launcher.rotationValue =3.14;
			launcher.coincide =1;
			launcher.angleValue =0;
			launcher.delay = 1;
			launcher.scopeX =5;
			launcher.scopeY =5;
			launcher.up =1050;
			launcher.down =1500;
			launcher.left =1250;
			launcher.right =1250;
			launcher.r =0.46;
			launcher.g =0.15;
			launcher.b =0.86;
			launcher.a =1;
			launcher.rValue =0;
			launcher.gValue =0;
			launcher.bValue =0;
			launcher.aValue =0;
			launcher.alphaValue =-0.02;
			launcher.scaleXValue =0;
			launcher.scaleYValue =0;
			launcher.scaleX=1;
			launcher.scaleY=1;
			launcher.alpha=1;
			launcherList[j]=launcher;
		}
		function=new Function(this,"timerRun");
		timer2d=new Timer2D();
		timer2d.getEvent2D().addEventListener(TimerEvent2D.TIMER,function);
		timer2d.start();
	}
	
	public void getGrain()
	{
		for(int j=0;j<launcherList.length;j++)
		{
			if(launcherList[j].start)
			for(int i=0;i<array.getLength();i++)
			{
				Grain grain=(Grain) array.getArray()[i];
				if(!grain.getStart())
				{
					
					grain.skin.setX((float) (launcherList[j].x + Math.random() * launcherList[j].scopeX -launcherList[j].scopeX / 2));
					grain.skin.setY((float) (launcherList[j].y + Math.random() * launcherList[j].scopeY -launcherList[j].scopeY / 2));
			
					grain.setStartRotation(launcherList[j].startRotation);
					
					grain.setaValue(launcherList[j].aValue);
					grain.setrValue(launcherList[j].rValue);
					grain.setgValue(launcherList[j].gValue);
					grain.setbValue(launcherList[j].bValue);
					
					grain.setAngleValue(launcherList[j].angleValue);
					grain.setAlphaValue(launcherList[j].alphaValue);
					grain.setScaleXValue(launcherList[j].scaleXValue);
					grain.setScaleYValue(launcherList[j].scaleYValue);
			
					grain.skin.setA((float) launcherList[j].a);
					grain.skin.setR((float) launcherList[j].r);
					grain.skin.setG((float) launcherList[j].g);
					grain.skin.setB((float) launcherList[j].b);
					
					
					grain.skin.setScaleX(launcherList[j].scaleX);
					grain.skin.setScaleY(launcherList[j].scaleY);
					grain.skin.setAlpha(launcherList[j].alpha);
					
					grain.setRotationValue(launcherList[j].rotationValue + Math.random() * launcherList[j].rotationRandom);
	
					grain.skin.setRotation((float) launcherList[j].rotationValue);
					
					grain.setSpeedValue(launcherList[j].speedValue);
					
					
					grain.setUp(launcherList[j].up);
					grain.setDown(launcherList[j].down);
					grain.setLeft(launcherList[j].left);
					grain.setRight(launcherList[j].right);
					grain.setStart(true);
					break;
				}
			}
		}
	}
	
	/**
	 * 获得发射器
	 * @param value
	 * @return
	 */
	public Launcher getLauncher(int value)
	{
		return launcherList[value];
	}
	public int getDelay()
	{
		return this.mDelay;
	}
	
	public void setDelay(int value)
	{
		this.mDelay=value;
	}
	
	
	public void timerRun(Timer2D e)
	{
		mDelayValue++;
		if(mDelayValue>=mDelay)
		{
			mDelayValue=mDelay;
			getGrain();
		}
		
		for(int i=0;i<array.getLength();i++)
		{
			Grain grain=(Grain) array.getArray()[i];
			if(grain.getStart())
			{
				grain.upData();
			}
		}
	}
	
	
}
