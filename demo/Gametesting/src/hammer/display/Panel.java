package hammer.display;

import android.util.Log;
import hammer.geom.Matrix2D;
import hammer.geom.Point2D;

public class Panel {
	/**
	 * ���Լ���
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
	
	//����
	private Matrix2D matrix2D;
	
	//Ԫ���б�
	private Point2D[] itemList;
	
	//Ԫ����Ϣ�б�
	private Point2D[] itemData;
	
	//Ԫ����Ϣ�б�
	private Point2D[] itemDataAgency;
	
	//�Ƿ��Զ�����
	private boolean upDataBool;
	
	private float[] agency;
	
	private float[] point;
	
	private float[] itemDatalist;
	
	private boolean isUpdata;
	
	private int ids=0;
	
	private Matrix2D parentMatrix2D;

	
	/**
	 * �Ƿ��Զ�����
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
	 * ������Ҫ��Ϣ
	 */
	private void upDataFrame(Point2D p)
	{
		itemData[ids]=new Point2D(p.getX(),p.getY());
		itemDataAgency[ids]=new Point2D(p.getX(),p.getY());
		
	}
	
	/**
	 * ���¾������
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
	 * ���¾���ԭʼ��Ϣ
	 * @param	rotation �Ƕ�
	 * @param	x X����
	 * @param	y Y����
	 * @param	pivotX Xƫ��ֵ
	 * @param	pivotY Yƫ��ֵ
	 * @param	scaleX X����ֵ
	 * @param	scaleY Y����ֵ
	 * @param	biasX X��бֵ
	 * @param	biasY Y��бֵ
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
	 * ���Ԫ��
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
	 * ���Ļ��ȡ���X����
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
	 * ���Ļ��ȡ���Y����
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
	 * ���Ļ��ȡ���Ƕ�
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
	 * ���Ļ��ȡ���X����
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
	 * ���Ļ��ȡ���Y����
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
	 * ���Ļ��ȡ���X��б��
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
	 * ���Ļ��ȡ���Y��б��
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
	 * ���Ļ��ȡ���Xƫ��ֵ
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
	 * ���Ļ��ȡ���Yƫ��ֵ
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

