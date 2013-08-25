package hammer.batch;

import hammer.geom.Array;

/**
 * 帧信息
 * @-式神-
 *
 */
public class QuadData {
	/**
	 * 场景名称
	 */
	private String mName;
	
	/**
	 * 帧片段
	 */
	private Array mQuadFrameLst;
	
	/**
	 * 创建一个新的对象
	 */
	public QuadData() 
	{
		setQuadFrameLst(new Array());
	}
	
	/**
	 * 获取名称
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name) {
		this.mName = name;
	}
	
	/**
	 * 获取动画帧
	 * @return
	 */
	public Array getQuadFrameLst() {
		return mQuadFrameLst;
	}
	
	/**
	 * 设置动画帧
	 * @param quadFrameLst
	 */
	public void setQuadFrameLst(Array quadFrameLst) {
		this.mQuadFrameLst = quadFrameLst;
	}
	
	
}
