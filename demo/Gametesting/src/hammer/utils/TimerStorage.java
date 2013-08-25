package hammer.utils;

import hammer.geom.Array;

/**
 * ��ʱ��������
 * @-ʽ��-
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
	 * ��Ӽ�ʱ��
	 * @param object
	 */
	public void addTimer(Timer2D obj)
	{
		int index=list.indexOf(obj);
		if(index==-1)
		list.push(obj);
	}
	
	/**
	 * ���ټ�ʱ��
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
