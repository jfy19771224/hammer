package hammer.core;
import java.util.HashMap;
/**
 * ��Դ���ص�ַ��֤ϵͳ
 * @-ʽ��-
 *
 */
public class LoadAddress {
	//����
	private HashMap<Object, Object> vessel;
	
	//��ǰ����
	private static LoadAddress _target;
	
	/**
	 * ����һ���µĶ����
	 */
	public LoadAddress()
	{
		vessel=new HashMap<Object, Object>();
	}
	
	/**
	 * ��֤�Ƿ��ظ�
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
	 * ��ӵ�ַ
	 * @param obj
	 */
	public void push(Object key,Object value)
	{
		vessel.put(key, value);
	}
	
	/**
	 * ɾ��
	 * @param obj
	 */
	public void remove(Object obj)
	{
		vessel.remove(obj);
	}
	
	/**
	 * ���һ������
	 * @param url
	 * @return
	 */
	public Object getObject(Object url)
	{
		return vessel.get(""+url);
	}
	/**
	 * 
	 * @return ����б�ǰ����
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
