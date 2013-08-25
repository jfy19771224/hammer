package hammer.utils;

import hammer.geom.Array;

/**
 * 计时器管理类
 * @-式神-
 *
 */
public class TimerStorage {
	public static TimerStorage target;
	
	private Array list;
	public TimerStorage()
	{
		list=new Array();
	}
	
	/**
	 * 添加计时器
	 * @param object
	 */
	public void addTimer(Timer2D obj)
	{
		int index=list.indexOf(obj);
		if(index==-1)
		list.push(obj);
	}
	
	/**
	 * 销毁计时器
	 * @param object
	 */
	public void removeTimer(Timer2D obj)
	{
		int index=list.indexOf(obj);
		if(index!=-1)
		list.splice(index);
	}
	public void run()
	{
		for(int i=0;i<list.getLength();i++)
		{
			Timer2D timer2d=(Timer2D) list.getArray()[i];
			timer2d.run();
		}
		
	}
	
	public static void init()
	{
		if(target==null)
		{
			target=new TimerStorage();
		}
	}
}
