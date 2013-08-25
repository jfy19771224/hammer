package hammer.batch;

import java.util.HashMap;

import hammer.geom.Array;

public class QuadResource {
	/**
	 * 动画场景信息
	 */
	private Array mQuadDataList;
	
	/**
	 * 动画缓存
	 */
	private HashMap mDictionary;
	
	public QuadResource()
	{
		
	}

	public Array getmQuadDataList() {
		return mQuadDataList;
	}

	public void setmQuadDataList(Array mQuadDataList) {
		this.mQuadDataList = mQuadDataList;
	}

	public HashMap getmDictionary() {
		return mDictionary;
	}

	public void setmDictionary(HashMap mDictionary) {
		this.mDictionary = mDictionary;
	}
}
