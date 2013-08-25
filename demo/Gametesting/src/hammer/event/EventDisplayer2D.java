package hammer.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.util.Log;

import hammer.core.Function;
import hammer.geom.Array;


/**
 * �¼�����
 * @-ʽ��-
 *
 */
public class EventDisplayer2D {

	private Array objectArray;
	public EventDisplayer2D()
	{
		objectArray=new Array();
	}
	

	
	/**
	 * ���ûص�����,��������,������,�����б�
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
	 * �����¼�
	 */
	public void dispose()
	{
		objectArray=null;
	}
	
	/**
	 * ���ûص�����,��������,������,�����б�
	 * 
	 */
	public void addEventListener(String type,Function function) 
	{
		function.setEventName(type);
		objectArray.push(function);
	}
	
	/**
	 * ���ú���
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
	 * �¼�����
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
