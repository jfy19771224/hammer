package hammer.core;

import java.lang.reflect.Method;

/**
 * ������
 * @-ʽ��-
 *
 */

public class Function {
	
	
	private String eventName;
	/**
	 * ��������
	 */
	private String name;

	/**
	 * �����������
	 */
	private Object targetDefine;
	
	/**
	 * ��������
	 */
	private Method function;
	
	/*
	 * �ص������б�
	 */
	private Method[] functionArry;
	
	public Function(Object targetDefine,String name)
	{
		this.setName(name);
		this.setTargetDefine(targetDefine);
		this.setFunction(function(targetDefine,name));
	}
	

	/*
	 * ��ѯ�����������з�������Ѱ�ҵ�ָ���ķ����洢
	 */
	private Method function(Object child,String name)
    {
		if(functionArry==null)
		{
			functionArry=child.getClass().getDeclaredMethods();
		}
		
    	for(Method m : functionArry)
        {
    		
     	   if(name.equals(m.getName()))
     	   {
     		   return m;   
     	   }
        }
    	
    	return null;
    }


	public Method getFunction() {
		return function;
	}


	public void setFunction(Method function) {
		this.function = function;
	}


	public Object getTargetDefine() {
		return targetDefine;
	}


	public void setTargetDefine(Object targetDefine) {
		this.targetDefine = targetDefine;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}
