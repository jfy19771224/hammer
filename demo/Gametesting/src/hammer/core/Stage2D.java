package hammer.core;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.event.Event2D;
import hammer.event.EventDisplayer2D;
import hammer.event.IEvent2D;
import hammer.geom.Point2D;
import hammer.opengl2d.OpenGLDisplayList;
import hammer.opengl2d.System2D;
import hammer.ui.Mouse2D;
import hammer.ui.MouseEvent2D;
import android.app.Activity;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Stage2D extends GLSurfaceView implements IEvent2D{
	
	/**
	 * 具体实现的渲染器
	 */
	private System2D renderer;
	
	private GLSurfaceView targetGLSurfaceView;
	
	
	public Stage2D(Activity context,int glVersions) {
		super(context);
		System2D.glVersions=glVersions;
		if(glVersions==2)
		{
			setEGLContextClientVersion(2);
		}
		
		renderer = new System2D(context,this);
		targetGLSurfaceView=this;
		setRenderer(renderer);
		context.setContentView(this);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	
	/**
	 * 响应触屏事件
	 */
	public boolean onTouchEvent(MotionEvent e) {	
		Mouse2D.setMotionEvent(e);
		if (e.getAction() == MotionEvent.ACTION_DOWN) { 
			MouseEvent2D mouseevent2d=new MouseEvent2D();
			mouseevent2d.setType(Event2D.MOUSE_DOWN);
			mouseevent2d.setMotionEvent(e);
			for(int i=OpenGLDisplayList.target().size()-1;i>=0;i--)
			{
				
				Sprite2D sp=OpenGLDisplayList.target().get(i);
				if(sp.isMouseEnabled())
				{
					if(sp.getChildList().getLength()>0)
					{
						for(int c=sp.getChildList().getLength()-1;c>=0;c--)
						{
							Sprite2D sq=(Sprite2D) sp.getChildList().getArray()[c];
							if(sq.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
							{
								break;
							}
						}
					}else
					{	
						if(sp.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
						{
							break;
						}
					}
				}
			}
			renderer.eventDisplayer2D.dispatchEvent(Event2D.MOUSE_DOWN,mouseevent2d);
		} else if (e.getAction() == MotionEvent.ACTION_UP) { 
			MouseEvent2D mouseevent2d=new MouseEvent2D();
			mouseevent2d.setType(Event2D.MOUSE_UP);
			mouseevent2d.setMotionEvent(e);
			for(int i=OpenGLDisplayList.target().size()-1;i>=0;i--)
			{
				Sprite2D sp=OpenGLDisplayList.target().get(i);
				if(sp.isMouseEnabled())
				{
					if(sp.getChildList().getLength()>0)
					{
						for(int c=sp.getChildList().getLength()-1;c>=0;c--)
						{
							Sprite2D sq=(Sprite2D) sp.getChildList().getArray()[c];
							if(sq.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
							{
								break;
							}
						}
					}else
					{	
						if(sp.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
						{
							break;
						}
					}
				}
			}
			renderer.eventDisplayer2D.dispatchEvent(Event2D.MOUSE_UP,mouseevent2d);
		} else if (e.getAction() == MotionEvent.ACTION_MOVE) {
			MouseEvent2D mouseevent2d=new MouseEvent2D();
			mouseevent2d.setType(Event2D.MOUSE_MOVE);
			mouseevent2d.setMotionEvent(e);
			for(int i=OpenGLDisplayList.target().size()-1;i>=0;i--)
			{
				Sprite2D sp=OpenGLDisplayList.target().get(i);
				if(sp.isMouseEnabled())
				{
					if(sp.getChildList().getLength()>0)
					{
						for(int c=sp.getChildList().getLength()-1;c>=0;c--)
						{
							Sprite2D sq=(Sprite2D) sp.getChildList().getArray()[c];
							if(sq.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
							{
								break;
							}
						}
					}else
					{	
						if(sp.isClick(mouseevent2d,renderer,mouseevent2d.getType(),e.getX(),e.getY()))
						{
							break;
						}
					}
				}
			}
			renderer.eventDisplayer2D.dispatchEvent(Event2D.MOUSE_MOVE,mouseevent2d);
		}
		return true;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
	    return true;
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		
	    return true;
	}
	
	
	/**
	 * 添加显示元素
	 * @param child
	 */
	public void addChild(Sprite2D child)
	{
		OpenGLDisplayList.target().add(child);
	}
	
	/**
	 * 删除显示元素
	 * @param child
	 */
	public void removeChild(Sprite2D child)
	{
		OpenGLDisplayList.target().remove(child);
	}
	
	@Override
	public EventDisplayer2D getEvent2D() {
		
		return renderer.eventDisplayer2D;
	}
}
