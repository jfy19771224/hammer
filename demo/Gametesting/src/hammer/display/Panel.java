package hammer.display;

import android.util.Log;
import hammer.geom.Matrix2D;
import hammer.geom.Point2D;

public class Panel {
	/**
	 * 属性集合
	 */
	private float _x = 0;
	private float _y = 0;
	private float _rotation = 0;
	private float _scaleX = 1;
	private float _scaleY = 1;
	private float _biasX = 0;
	private float _biasY = 0;
	private float _pivotX = 0;
	private float _pivotY = 0;
	
	//矩阵
	private Matrix2D matrix2D;
	
	//元素列表
	private Point2D[] itemList;
	
	//元素信息列表
	private Point2D[] itemData;
	
	//元素信息列表
	private Point2D[] itemDataAgency;
	
	//是否自动更新
	private boolean upDataBool;
	
	private float[] agency;
	
	private float[] point;
	
	private float[] itemDatalist;
	
	private boolean isUpdata;
	
	private int ids=0;
	
	private Matrix2D parentMatrix2D;

	
	/**
	 * 是否自动更新
	 * @param	upDataBool
	 */
	public Panel(Matrix2D parentMatrix2D,boolean upDataBool) 
	{
		this.upDataBool = upDataBool;
		matrix2D = new Matrix2D();
		itemData = new Point2D[4];
		itemDataAgency=new Point2D[4];
		itemList=new Point2D[4];
		agency=new float[3];
		itemDatalist=new float[3];
		this.parentMatrix2D=parentMatrix2D;
	}
	

	
	/**
	 * 更新主要信息
	 */
	private void upDataFrame(Point2D p)
	{
		itemData[ids]=new Point2D(p.getX(),p.getY());
		itemDataAgency[ids]=new Point2D(p.getX(),p.getY());
		
	}
	
	/**
	 * 更新矩阵操作
	 */
	public void upDataMatrix()
	{
		matrix2D.upDataBasrMatrix(_rotation,_x + _pivotX, _y + _pivotY, _scaleX, _scaleY, _biasX, _biasY);
		if(parentMatrix2D!=null)
		matrix2D.baseMatrix=matrix2D.add33(matrix2D.baseMatrix,parentMatrix2D.baseMatrix);
		for (int i = 0; i <ids; i++)
		{
			agency[0]=itemDataAgency[i].getX();
			agency[1]=itemDataAgency[i].getY();
			agency[2]=1;
			point = matrix2D.add13(agency,matrix2D.baseMatrix);
			itemList[i].setX(point[0]);
			itemList[i].setY(point[1]);
		}
	}
	
	
	/**
	 * 更新矩阵原始信息
	 * @param	rotation 角度
	 * @param	x X坐标
	 * @param	y Y坐标
	 * @param	pivotX X偏移值
	 * @param	pivotY Y偏移值
	 * @param	scaleX X缩放值
	 * @param	scaleY Y缩放值
	 * @param	biasX X倾斜值
	 * @param	biasY Y倾斜值
	 */
	public void upDataMatrixData(float pivotX,float pivotY,float scaleX,float scaleY,float biasX,float biasY)
	{
		isUpdata=true;
		
		matrix2D.upDataBasrMatrix(0,pivotX,pivotY, scaleX, scaleY, biasX, biasY);
		for (int i = 0; i <ids; i++)
		{
			itemDatalist[0]=itemData[i].getX();
			itemDatalist[1]=itemData[i].getY();
			itemDatalist[2]=1;
			point = matrix2D.add13(itemDatalist,matrix2D.baseMatrix);
			itemDataAgency[i].setX(point[0]);
			itemDataAgency[i].setY(point[1]);
		}
		if(upDataBool)
		upDataMatrix();
	}
	
	
	/**
	 * 添加元素
	 * @param	obj
	 */
	public void addItem(Point2D obj)
	{
		itemList[ids]=obj;
		upDataFrame(obj);
		ids++;
	}
	
	
	
	
	private float cX;
	/**
	 * 更改或读取面板X坐标
	 */
	public float getX() {return _x;}
	public void setX(float value) 
	{
		if(cX!=value)
		{
			cX=value;
			_x = value;
			isUpdata=true;
			if(upDataBool)
			upDataMatrix();
		}
	}
	
	private float cY;
	/**
	 * 更改或读取面板Y坐标
	 */
	public float getY() {return _y;};
	public void setY(float value) 
	{
		if(cY!=value)
		{
			cY=value;
			_y = value;
			isUpdata=true;
			if(upDataBool)
			upDataMatrix();
		}
	}
	
	private float cRotation;
	/**
	 * 更改或读取面板角度
	 */
	public float getRotation(){return _rotation;}
	public void setRotation(float value) 
	{
		if(cRotation!=value)
		{
			cRotation=value;
			_rotation = value;
			isUpdata=true;
			if(upDataBool)
			upDataMatrix();
		}
	}
	
	/**
	 * 更改或读取面板X比例
	 */
	public float getScaleX() {return _scaleX;}
	public void setScaleX(float value) 
	{
		_scaleX = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}
	
	/**
	 * 更改或读取面板Y比例
	 */
	public float getScaleY() {return _scaleY;}
	public void setScaleY(float value) 
	{
		_scaleY = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}
	
	/**
	 * 更改或读取面板X倾斜度
	 */
	public float getBiasX() {return _biasX;}
	public void setBiasX(float value) 
	{
		_biasX = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}

	/**
	 * 更改或读取面板Y倾斜度
	 */
	public float getBiasY(){return _biasY;}	
	public void setBiasY(float value)
	{
		_biasY = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}
	
	/**
	 * 更改或读取面板X偏移值
	 */
	public float getPivotY() {return _pivotY;};
	public void setPivotY(float value) 
	{
		_pivotY = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}
	
	/**
	 * 更改或读取面板Y偏移值
	 */
	public float getPivotX(){return _pivotX;}
	public void setPivotX(float value) 
	{
		_pivotX = value;
		isUpdata=true;
		if(upDataBool)
		upDataMatrix();
	}
	
}

