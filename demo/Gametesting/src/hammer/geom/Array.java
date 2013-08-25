package hammer.geom;

import android.util.Log;

/**
 * 传说中的数组
 * @-式神-
 *
 */
public class Array {
	
	private Object[] array;
	private Object[] list;
	private int max;
	private int length;
	private int maxLength;
	public Array()
	{
		setLength(0);
		maxLength=100;
		this.max=maxLength;
		init();
	}
	
	public Array(int max)
	{
		setLength(0);
		maxLength=this.max;
		this.max=max;
		init();
	}
	
	/**
	 * 添加元素
	 * @param obj
	 */
	public void push(Object obj)
	{
		getArray()[getLength()]=obj;
		setLength(getLength() + 1);
		if(getLength()>=max)
		{
			max+=maxLength;
			list=new Object[max];
			for(int i=0;i<getLength();i++)
			{
				list[i]=getArray()[i];
			}
			setArray(list);
		}
	}
	
	/**
	 * 删除数组中最后一个元素，并返回该元素的值。
	 * @return
	 */
	public Object pop()
	{
		Object obj=getArray()[getLength()-1];
		getArray()[getLength()-1]=null;
		setLength(getLength() - 1);
		return obj;
	}
	
	
	/**
	 * 删除或者添加元素
	 * @param index
	 * @param num
	 */
	public void splice(int index)
	{
		getArray()[index]=null;
		setLength(getLength() - 1);
		list=new Object[max];
		int ids=0;
		for(int i=0;i<getArray().length;i++)
		{
			if(getArray()[i]!=null)
			{
				list[ids]=getArray()[i];
				ids++;
			}
			
		}
		setArray(list);
	}
	/**
	 * 返回元素所在的位置
	 * @return
	 */
	public int indexOf(Object obj)
	{
		for(int i=0;i<getLength();i++)
		{
			if(getArray()[i]==obj)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 获取元素
	 * @param index
	 * @return
	 */
	public Object get(int index)
	{
		return getArray()[index];
	}
	
	
	/**
	 * 初始化数组
	 */
	private void init()
	{
		setArray(new Object[max]);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Object[] getArray() {
		return array;
	}

	public void setArray(Object[] array) {
		this.array = array;
	}
}
