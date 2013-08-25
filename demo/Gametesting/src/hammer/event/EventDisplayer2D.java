package hammer.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.util.Log;

import hammer.core.Function;
import hammer.geom.Array;


/**
 * 事件基类
 * @-式神-
 *
 */
public class EventDisplayer2D {

	private Array objectArray;
	public EventDisplayer2D()
	{
		objectArray=new Array();
	}
	

	
	/**
	 * 设置回调函数,函数父类,函数名,参数列表
	 * 
	 */
	public void removeEventListener(Function function) 
	{
		int index=objectArray.indexOf(function);
		if(index!=-1)
		{
			objectArray.splice(index);
		}
	}
	
	/**
	 * 清理事件
	 */
	public void dispose()
	{
		objectArray=null;
	}
	
	/**
	 * 设置回调函数,函数父类,函数名,参数列表
	 * 
	 */
	public void addEventListener(String type,Function function) 
	{
		function.setEventName(type);
		objectArray.push(function);
	}
	
	/**
	 * 调用函数
	 */
	public void dispatchEvent(String name,Object... parameter)
	{
		for(int i=0;i<objectArray.getLength();i++)
		{
			Function function2d=(Function) objectArray.getArray()[i];
			if(function2d.getEventName().equals(name))
			{
				this.sendCallBack(function2d.getTargetDefine(),function2d.getFunction(),parameter);
			}
		}
	}
	
	
	
	/**
	 * 事件发送
	 */
	public void sendCallBack(Object callBackObj,Method callBack,Object... parameter)
	{
		
		if(callBackObj!=null&&callBack!=null)
		{
			callBack.setAccessible(true); 
     	   try {
     		  callBack.invoke(callBackObj,parameter);
 			} catch (IllegalArgumentException e) {
 				e.printStackTrace();
 			} catch (IllegalAccessException e) {
 				e.printStackTrace();
 			} catch (InvocationTargetException e) {
 				e.printStackTrace();
 			}     
		}
	}


}
