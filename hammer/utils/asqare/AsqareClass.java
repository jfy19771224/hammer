package hammer.utils.asqare;
import java.util.ArrayList;

import android.util.Log;

	/**
	 * 连连看算法类。
	 * 
	 * 使用方法:
	 * 
	 * 填入地图数组,起点数组,终点数组
	 * ArrayList ai=lian.AIdo(maplist, myList_1, myList_2);
	 * 
	 * @author 花菜游戏
	 */
	public class AsqareClass {
		//目标点
		private ArrayList<ArrayList<Integer>> pusharr=new ArrayList<ArrayList<Integer>>();
		private ArrayList<ArrayList<Integer>> pusharr_2=new ArrayList<ArrayList<Integer>>();
		//定义存放数组
		private boolean sanji;
		//定义三级开关
		private ArrayList<Integer> p3=new ArrayList<Integer>();
		//节点
		private ArrayList<Integer> p4=new ArrayList<Integer>();
		//节点
		private ArrayList<ArrayList<Integer>> fanhui=new ArrayList<ArrayList<Integer>>();
		//路径数组
		private int pass = 0;
		/**
		* 连连看算法(参数1:地图数据,参数2:开始点,参数3:结束点),如果可以连接则返回路径否则返回null。
		* @example
		*/
		public ArrayList<ArrayList<Integer>> AIdo(ArrayList<?> map,ArrayList<Integer> p1,ArrayList<Integer> p2)
		{
			sanji=false;
			//三级为假
			fanhui=new ArrayList<ArrayList<Integer>>();
			//6个返回数组
			if (Find(map,p1,p2)) {
				fanhui.add(p1);
				fanhui.add(p2);
				
				return fanhui;
				// 如果1级检测成功，则马上返回
			}else {
				
				pusharr=new ArrayList<ArrayList<Integer>>();
				push(map,pusharr,p1);
				push(map, pusharr, p2);
				int leg = pusharr.size() - 1;
				int lej=pusharr.size();
				for(int j=0;j<leg;j++)
				{
					for(int k=j+1;k<lej;k++)
					{
						ArrayList<?> index=((ArrayList<?>)pusharr.get(j));
						int data=(Integer)index.get(0);
						
						ArrayList<?> index2=((ArrayList<?>)pusharr.get(k));
						int data2=(Integer)index2.get(0);
						
						ArrayList<?> index3=((ArrayList<?>)pusharr.get(j));
						int data3=(Integer)index3.get(1);
						
						ArrayList<?> index4=((ArrayList<?>)pusharr.get(k));
						int data4=(Integer)index4.get(1);
						
						if(data==data2&&data3==data4)
						{
							fanhui.add(p1);
							fanhui.add(p2);
							fanhui.add(((ArrayList<Integer>)pusharr.get(k)));
							
							return fanhui;
						}
					}
				}
				
				if (sanji==false) {
					pusharr=new ArrayList<ArrayList<Integer>>();
					pusharr_2=new ArrayList<ArrayList<Integer>>();
					push(map,pusharr,p1);
					push(map, pusharr_2, p2);
					
					int len = pusharr.size();
					int le = pusharr_2.size();
					
					
					for(int n=0;n<len;n++)
					{
						for(int m=0;m<le;m++)
						{
							
							ArrayList<?> index5=((ArrayList<?>)pusharr.get(n));
							int data5=(Integer)index5.get(0);
							
							ArrayList<?> index6=((ArrayList<?>)pusharr_2.get(m));
							int data6=(Integer)index6.get(0);
							
							ArrayList<?> index7=((ArrayList<?>)pusharr.get(n));
							int data7=(Integer)index7.get(1);
							
							ArrayList<?> index8=((ArrayList<?>)pusharr_2.get(m));
							int data8=(Integer)index8.get(1);
						
							if(data5==data6||data7==data8)
							{

								ArrayList<Integer> shuju2=new ArrayList<Integer>();
								shuju2.add(data5);
								shuju2.add(data7);
								p3=shuju2;
								
								ArrayList<Integer> shuju3=new ArrayList<Integer>();
								shuju3.add(data6);
								shuju3.add(data8);
								p4=shuju3;
								
								if(Find(map,p3,p4))
								{
									fanhui.add(p1);
									fanhui.add(p2);
									fanhui.add(p3);
									fanhui.add(p4);
									return fanhui;
									
								}
								
								
							}
						}
					}
				}
			}
			
			return null;
		}
		private void push(ArrayList<?> map,ArrayList<ArrayList<Integer>> arr,ArrayList<Integer> p) 
		{
			
			for(int i=(Integer)p.get(0)+1;i<map.size();i++)
			{
				ArrayList<?> index=((ArrayList<?>)map.get(i));
				int data=(Integer)index.get(((Integer)p.get(1)));
				if(data==pass)
				{
					ArrayList<Integer> shuju=new ArrayList<Integer>();
					shuju.add(i);
					shuju.add(((Integer)p.get(1)));
					arr.add(shuju);
				}else
				{
					break;
				}
				
			}
			
			for(int j=(Integer)p.get(0)-1;j>=0;j--)
			{
				ArrayList<?> index2=((ArrayList<?>)map.get(j));
				int data2=(Integer)index2.get(((Integer)p.get(1)));
				if(data2==pass)
				{
					ArrayList<Integer> shuju2=new ArrayList<Integer>();
					shuju2.add(j);
					shuju2.add(((Integer)p.get(1)));
					arr.add(shuju2);
				}else
				{
					break;
				}
			}
			
			for(int n=(Integer)p.get(1)+1;n<((ArrayList<?>)map.get(0)).size();n++)
			{
				Object index3=((ArrayList<?>)map.get((Integer)p.get(0))).get(n);
				int data3=(Integer)index3;
				if(data3==pass)
				{
					ArrayList<Integer> shuju3=new ArrayList<Integer>();
					shuju3.add((Integer)p.get(0));
					shuju3.add(n);
					arr.add(shuju3);
				}else
				{
					break;
				}
				
			}
			
			for(int m=(Integer)p.get(1)-1;m>=0;m--)
			{
				if((Integer)p.get(1)-1>=0)
				{
					Object index4=((ArrayList<?>)map.get((Integer)p.get(0))).get(m);
					int data4=(Integer)index4;
					if(data4==pass)
					{
						ArrayList<Integer> shuju4=new ArrayList<Integer>();
						shuju4.add((Integer)p.get(0));
						shuju4.add(m);
						arr.add(shuju4);
					}else
					{
						break;
					}
				}
			}
		}
		
		
		private boolean Find(ArrayList<?> map,ArrayList<Integer> p1,ArrayList<Integer> p2)
		{
			if(((Integer)p1.get(0))==((Integer)p2.get(0)))
			{
				if(((Integer)p1.get(1))<((Integer)p2.get(1)))
				{
					for(int i=((Integer)p1.get(1))+1;i<((Integer)p2.get(1));i++)
					{
						Object index1=((ArrayList<?>)map.get(((Integer)p1.get(0)))).get(i);
						int data1=(Integer)index1;
						if(data1!=pass)
						{
							return false;	
						}
					}
					return true;
				}else if(((Integer)p1.get(1))>((Integer)p2.get(1)))
				{
					for(int j=((Integer)p2.get(1))+1;j<((Integer)p1.get(1));j++)
					{
						Object index2=((ArrayList<?>)map.get(((Integer)p2.get(0)))).get(j);
						int data2=(Integer)index2;
						if(data2!=pass)
						{
							return false;	
						}
					}
					return true;
				}
				}else if(((Integer)p1.get(1))==((Integer)p2.get(1)))
				{
					if(((Integer)p1.get(0))<((Integer)p2.get(0)))
					{
						for(int n=((Integer)p1.get(0))+1;n<((Integer)p2.get(0));n++)
						{
							ArrayList<?> index3=((ArrayList<?>)map.get(n));
							int data3=(Integer)index3.get(((Integer)p1.get(1)));
							if(data3!=pass)
							{
								return false;	
							}
						}
						return true;
					}else if(((Integer)p1.get(0))>((Integer)p2.get(0)))
					{
						for(int m=((Integer)p2.get(0))+1;m<((Integer)p1.get(0));m++)
						{
							ArrayList<?> index4=((ArrayList<?>)map.get(m));
							int data4=(Integer)index4.get(((Integer)p2.get(1)));
							if(data4!=pass)
							{
								return false;	
							}
						}
						return true;
				}
				}else
				{
					return false;
				}
				return false;
			}
	}
		
	