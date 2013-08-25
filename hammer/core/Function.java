package hammer.core;

import java.lang.reflect.Method;

/**
 * 函数类
 * @-式神-
 *
 */

public class Function {
	
	
	private String eventName;
	/**
	 * 函数名称
	 */
	private String name;

	/**
	 * 函数定义对象
	 */
	private Object targetDefine;
	
	/**
	 * 函数对象
	 */
	private Method function;
	
	/*
	 * 回调函数列表
	 */
	private Method[] functionArry;
	
	public Function(Object targetDefine,String name)
	{
		this.setName(name);
		this.setTargetDefine(targetDefine);
		this.setFunction(function(targetDefine,name));
	}
	

	/*
	 * 查询定义内中所有方法并且寻找到指定的方法存储
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
