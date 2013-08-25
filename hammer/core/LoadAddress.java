package hammer.core;
import java.util.HashMap;
/**
 * 资源加载地址验证系统
 * @-式神-
 *
 */
public class LoadAddress {
	//容器
	private HashMap<Object, Object> vessel;
	
	//当前对象
	private static LoadAddress _target;
	
	/**
	 * 创建一个新的对象池
	 */
	public LoadAddress()
	{
		vessel=new HashMap<Object, Object>();
	}
	
	/**
	 * 验证是否重复
	 * @return
	 */
	public boolean isRepeat(Object url)
	{
		if(vessel.get(""+url)==null)
		{
			return true;
		}
		return false;
		
	}
	
	/**
	 * 添加地址
	 * @param obj
	 */
	public void push(Object key,Object value)
	{
		vessel.put(key, value);
	}
	
	/**
	 * 删除
	 * @param obj
	 */
	public void remove(Object obj)
	{
		vessel.remove(obj);
	}
	
	/**
	 * 获得一个对象
	 * @param url
	 * @return
	 */
	public Object getObject(Object url)
	{
		return vessel.get(""+url);
	}
	/**
	 * 
	 * @return 鼠标列表当前对象
	 */
	public static  LoadAddress target()
	{
		if(_target==null)
		{
			_target=new LoadAddress();
		}
		return _target;
	}
	
}
