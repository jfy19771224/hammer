package hammer.opengl2d;
import hammer.display.Sprite2D;
public class OpenGLDisplayList {
	//����
	private Sprite2D[] vessel;
	
	//��ǰ����
	private static OpenGLDisplayList _target;
	
	/**
	 * ����һ���µĶ����
	 */
	public OpenGLDisplayList()
	{
		setVessel(new Sprite2D[0]);
	}
	
	/**
	 * 
	 * @return ����б�ǰ����
	 */
	public static  OpenGLDisplayList target()
	{
		if(_target==null)
		{
			_target=new OpenGLDisplayList();
		}
		return _target;
	}
	
	public void add(int index,Sprite2D object)
	{
		Sprite2D obj=(Sprite2D) getVessel()[index];
		int num=this.indexOf(object);
		getVessel()[index]=object;
		getVessel()[num]=obj;
	}
	
	public void add(Sprite2D child)
	{
		if(this.indexOf(child)==-1)
		{
			Sprite2D[] obj=new Sprite2D[getVessel().length+1];
			int i=0;
			for (Sprite2D m : getVessel()) {
				obj[i]=m;
				i++;
			}
			obj[obj.length-1]=child;
			setVessel(obj);
		}else
		{
			
			int index=this.indexOf(child);
			for(int j=index;j<getVessel().length-1;j++)
			{
				getVessel()[j]=getVessel()[j+1];
			}
			getVessel()[getVessel().length-1]=child;
		}
	}
	
	public int indexOf(Sprite2D child)
	{
		int i=0;
		for (Sprite2D m : getVessel()) {
			if(m==child)
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	/**
	 * 
	 * @param child ����ֿ�
	 */
	public void push(Sprite2D child)
	{
		if(indexOf(child)==-1)
		add(child);
	}
	

	/**
	 * 
	 * @param child ɾ������
	 */
	public void remove(Sprite2D child)
	{
		int index=indexOf(child);
		if(index!=-1)
		remove(index);
	}
	
	/**
	 * 
	 * @param child ɾ������
	 */
	public void remove(int value)
	{
		Sprite2D[] obj=new Sprite2D[getVessel().length-1];
		getVessel()[value]=null;
		int i=0;
		for (Sprite2D m : getVessel()) {
			if(m!=null)
			{
				obj[i]=m;
				i++;
			}
		}
		setVessel(obj);
	}
	
	/**
	 * 
	 * @return ���زֿ���������
	 */
	public int size()
	{
		return getVessel().length;
	}
	
	
	/**
	 * ȡ������
	 * @param nlocationum
	 * @return ��������¼�����
	 */
	public Sprite2D find(int location)
	{
		return (Sprite2D) getVessel()[location];
	}

	public Sprite2D get(int value) {
		
		return (Sprite2D) getVessel()[value];
	}

	public Sprite2D[] getVessel() {
		return vessel;
	}

	public void setVessel(Sprite2D[] vessel) {
		this.vessel = vessel;
	}


}
