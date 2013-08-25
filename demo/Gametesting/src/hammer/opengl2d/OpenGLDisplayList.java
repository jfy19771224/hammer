package hammer.opengl2d;
import hammer.display.Sprite2D;
public class OpenGLDisplayList {
	//容器
	private Sprite2D[] vessel;
	
	//当前对象
	private static OpenGLDisplayList _target;
	
	/**
	 * 创建一个新的对象池
	 */
	public OpenGLDisplayList()
	{
		setVessel(new Sprite2D[0]);
	}
	
	/**
	 * 
	 * @return 鼠标列表当前对象
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
	 * @param child 存入仓库
	 */
	public void push(Sprite2D child)
	{
		if(indexOf(child)==-1)
		add(child);
	}
	

	/**
	 * 
	 * @param child 删除数据
	 */
	public void remove(Sprite2D child)
	{
		int index=indexOf(child);
		if(index!=-1)
		remove(index);
	}
	
	/**
	 * 
	 * @param child 删除数据
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
	 * @return 返回仓库数据数量
	 */
	public int size()
	{
		return getVessel().length;
	}
	
	
	/**
	 * 取出数据
	 * @param nlocationum
	 * @return 返回鼠标事件对象
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
