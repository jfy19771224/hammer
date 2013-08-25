package hammer.utils;

import java.lang.reflect.InvocationTargetException;

import hammer.event.EventDisplayer2D;
import hammer.event.IEvent2D;

import android.util.Log;

/**
 * 计时器
 * @-式神-
 *
 */
public class Timer2D implements IEvent2D{
	
	private long goldTimer;
	private long targetTimer;
	
	private boolean start;
	
	private long delay;
	private long currentCount;
	private long repeatCountl;
	
	private EventDisplayer2D eventDisplayer2D;
	
	
	public Timer2D(long delay,long repeatCountl)
	{
		eventDisplayer2D=new EventDisplayer2D();
		this.setDelay(delay);
		this.repeatCountl=repeatCountl;
		TimerStorage.target.addTimer(this);
	}
	
	public Timer2D(long delay)
	{
		eventDisplayer2D=new EventDisplayer2D();
		this.setDelay(delay);
		this.repeatCountl=-1;
		TimerStorage.target.addTimer(this);
	}
	
	public Timer2D()
	{
		eventDisplayer2D=new EventDisplayer2D();
		this.setDelay(10);
		this.repeatCountl=-1;
		TimerStorage.target.addTimer(this);
	}
	
	public void setCurrentCount(int value)
	{
		this.currentCount=value;
	}
	
	public long getCurrentCount()
	{
		return this.currentCount;
	}
	
	public void setRepeatCountl(long value)
	{
		this.repeatCountl=value;
	}
	
	public long getRepeatCountl()
	{
		return this.repeatCountl;
	}
	
	/**
	 * 开启计时器
	 */
	public void start()
	{
		start=true;
	}
	
	/**
	 * 停止计时器
	 */
	public void stop()
	{
		start=false;
	}

	/**
	 * 重置计时器
	 */
	public void reset()
	{
		this.currentCount=0;
	}
	
	/**
	 * 销毁计时器
	 */
	public void dispose()
	{
		TimerStorage.target.removeTimer(this);
	}
	
	public void run()
	{
		if(start)
		{
			goldTimer=System.currentTimeMillis();
			if((goldTimer-targetTimer)>=this.getDelay())
			{
				
				eventDisplayer2D.dispatchEvent(TimerEvent2D.TIMER,this);
				targetTimer=System.currentTimeMillis();
				this.currentCount++;
				if(repeatCountl!=-1)
				if(this.currentCount>=this.repeatCountl)
				{
					start=false;
				}
			}	
		}
	}
	@Override
	public EventDisplayer2D getEvent2D() {
		
		return eventDisplayer2D;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
}
