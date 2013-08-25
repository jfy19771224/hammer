package hammer.geom;

public class Matrix2D {
	public float[][] baseMatrix;
	public float[][] spinMatrix;
	private float[][] spinArray;
	private float[][] translationArray;
	private float cos;
	private float sin;
	private float[][] at;
	private float[] a1;
	private float cRotation;
	public Matrix2D() 
	{
		translationArray=new float[3][3];
		spinArray=new float[3][3];
		baseMatrix = new float[3][3];
		at=new float[3][3];
		
		a1=new float[3];
		appendRotation(0);
	}
	
	/**
	 * ÖØÖÃ¾ØÕó
	 */
	public void identity()
	{
		baseMatrix = new float[3][3];
	};
	
	
	/**
	 * 1*3¾ØÕóÈÚºÏ
	 * @param	a
	 * @param	b
	 * @return
	 */
	/*public float[] add13(float[] a,float[][] b)
	{
		
		float[] c= new float[]{
		a[0]*b[0][0]+a[1]*b[1][0]+a[2]*b[2][0],
		a[0]*b[0][1]+a[1]*b[1][1]+a[2]*b[2][1],
		a[0]*b[0][2]+a[1]*b[1][2]+a[2]*b[2][2]
		};
		return c;
	}*/
	
	/**
	 * 3*3¾ØÕóÈÚºÏ
	 * @param	a1
	 * @param	a2
	 * @return
	 */
	public float[][] add33(float[][] a1,float[][] a2)
	{
		at=new float[3][3];
		at[0][0]=a1[0][0]*a2[0][0]+a1[0][1]*a2[1][0]+a1[0][2]*a2[2][0];
		at[0][1]=a1[0][0]*a2[0][1]+a1[0][1]*a2[1][1]+a1[0][2]*a2[2][1];
		at[0][2]=a1[0][0]*a2[0][2]+a1[0][1]*a2[1][2]+a1[0][2]*a2[2][2];
		
		at[1][0]=a1[1][0]*a2[0][0]+a1[1][1]*a2[1][0]+a1[1][2]*a2[2][0];
		at[1][1]=a1[1][0]*a2[0][1]+a1[1][1]*a2[1][1]+a1[1][2]*a2[2][1];
		at[1][2]=a1[1][0]*a2[0][2]+a1[1][1]*a2[1][2]+a1[1][2]*a2[2][2];
		
		at[2][0]=a1[2][0]*a2[0][0]+a1[2][1]*a2[1][0]+a1[2][2]*a2[2][0];
		at[2][1]=a1[2][0]*a2[0][1]+a1[2][1]*a2[1][1]+a1[2][2]*a2[2][1];
		at[2][2]=a1[2][0]*a2[0][2]+a1[2][1]*a2[1][2]+a1[2][2]*a2[2][2];
		return at;
	}
	
	/**
	 * 1*3¾ØÕóÈÚºÏ
	 * @param	a
	 * @param	b
	 * @return
	 */
	public float[] add13(float[]a,float[][] b)
	{
		a1[0]=a[0]*b[0][0]+a[1]*b[1][0]+a[2]*b[2][0];
		a1[1]=a[0]*b[0][1]+a[1]*b[1][1]+a[2]*b[2][1];
		a1[2]=a[0]*b[0][2]+a[1]*b[1][2]+a[2]*b[2][2];
		return a1;
	}
	
	//Ğı×ª
	public void appendRotation(float rotation)
	{
		cos = (float) Math.cos(rotation * Math.PI / 180);
		sin = (float) Math.sin(rotation * Math.PI / 180);
		//Ğı×ª
		spinArray[0][0]=cos;
		spinArray[0][1]=sin;
		spinArray[0][2]=0;
		spinArray[1][0]=-sin;
		spinArray[1][1]=cos;
		spinArray[1][2]=0;
		spinArray[2][0]=0;
		spinArray[2][1]=0;
		spinArray[2][2]=1;
	};
	
	
	
	//Æ½ÒÆ,Ëõ·Å,ÇãĞ±
	public void appendTranslation(float x,float y,float scaleX,float scaleY,float biasX,float biasY)
	{
		translationArray[0][0]=scaleX;
		translationArray[0][1]=biasY;
		translationArray[0][2]=0;
		translationArray[1][0]=biasX;
		translationArray[1][1]=scaleY;
		translationArray[1][2]=0;
		translationArray[2][0]=x;
		translationArray[2][1]=y;
		translationArray[2][2]=1;
	};
	
	
	public void upDataBasrMatrix(float rotation,float x,float y,float scaleX,float scaleY,float biasX,float biasY)
	{
		if(cRotation!=rotation)
		{
			cRotation=rotation;
			appendRotation(rotation);
		}
		appendTranslation(x,y,scaleX,scaleY,biasX,biasY);
		baseMatrix = add33(spinArray, translationArray);
		
	}
	
}
