package hammer.batch;

import hammer.geom.Array;

/**
 * ֡��Ϣ
 * @-ʽ��-
 *
 */
public class QuadData {
	/**
	 * ��������
	 */
	private String mName;
	
	/**
	 * ֡Ƭ��
	 */
	private Array mQuadFrameLst;
	
	/**
	 * ����һ���µĶ���
	 */
	public QuadData() 
	{
		setQuadFrameLst(new Array());
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * ��������
	 * @param name
	 */
	public void setName(String name) {
		this.mName = name;
	}
	
	/**
	 * ��ȡ����֡
	 * @return
	 */
	public Array getQuadFrameLst() {
		return mQuadFrameLst;
	}
	
	/**
	 * ���ö���֡
	 * @param quadFrameLst
	 */
	public void setQuadFrameLst(Array quadFrameLst) {
		this.mQuadFrameLst = quadFrameLst;
	}
	
	
}
