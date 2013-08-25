package hammer.utils.pathfinding;

import hammer.geom.Array;


/**
 * A*算法
 * 
 * 使用方法:
 * 
 *  ArrayList map=new ArrayList();
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,1,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   map.add(new int[]{0,0,0,0,0,0,0,0,0,0,0});
   
   Astar start=new Astar();
   
   //开始点,技术点,障碍物,地图
   ArrayList<int[]> paint= start.AIdo(new int[]{0,0}, new int[]{5,5},new  int[]{1}, map);
 */

	public class Astar {

		private Array fruit;
		//路径数组
		private Array Start_listing;
		//开启列表
		private Array Stop_listing;
		//关闭列表
		public Node Goal_node;
		//目标节点
		public Node Start_node;
		//开始节点
		public Node Current_node;
		//当前节点
		public Node Fairly_node;
		//比较节点
		public Node About_node;
		//零时节点
		private Array shanchu;
		private int[] _zhangaiwu;
		public boolean kaishi;
		
		
		public Array AIdo(int[] startarr,int[] endarray,int[] zhangaiwu,int[][] map) {
			
			shanchu=new Array();
			
			_zhangaiwu=zhangaiwu;
			//初始化删除列表
			Start_listing=new Array();
			//开启列表
			Stop_listing=new Array();
			
			//关闭列表
			Goal_node=new Node(endarray[0],endarray[1],null,null,null);

			Start_node=new Node(startarr[0],startarr[1],null,Goal_node,null);
			//开始节点
			aido(map);
			//开始寻路
			return fruit;
			//返回寻路后的结果数组
		}
		public void aido(int[][] map) {
			
			if (shanchu.getLength()!=0) {
				for (int t=0; t<shanchu.getLength(); t++) {
					shanchu.push(t);
				}
				
			}
			//如果删除列表不为空,则删除里面所有元素,等待系统回收
			shanchu=new Array();
			//初始化删除列表
			Start_listing=new Array();
			//开启列表
			Stop_listing=new Array();
			//关闭列表
			Start_listing.push(Start_node);
			
			//把当前节点放入开启列表
			shanchu.push(Start_node);
			
			//把当前节点放入删除列表
			do {
				Current_node=(Node)Start_listing.get(0);
				//获取开启列表的第一个元素
				for (int i=0; i<Start_listing.getLength(); i++) 
				{
					Node node=((Node) Start_listing.get(i));
					if(node!=null&&Current_node!=null)
					if ((node.getF()<=Current_node.getF())) 
					{
						Current_node=(Node) Start_listing.get(i);
					}
				}
				if(Current_node==null)
				{
					return;
				}
				//比较数组里的元素，如果数组里的元素的F值比当前的小，则把当前的替换成数组里的
				if (Current_node.equals(Goal_node)) {
					fruit=Current_node.getPath();
					kaishi=true;
					break;
				}
				//是否找到路径
				Stop_listing.push(Current_node);
				//添加当前节点到关闭列表
				Start_listing.splice(Start_listing.indexOf(Current_node));
				//删除开始列表把当前节点从
				for (int j=-1; j<2; j++) {
					for (int k=-1; k<2; k++) {
						//遍历当前节点周围的位置
						if (Current_node.h+j==0&&Current_node.l+k==0) {
							
							continue;
						}
						if (Current_node.h+j>=map.length||Current_node.h+j<0) {
							continue;
						}
						if (Current_node.l+k>=map[0].length||Current_node.l+k<0) {;
							continue;
						}
						
						//Log.d("Tan", "查询"+detection_fraise(Current_node.h+j,Current_node.l+k,map));
						//判断是否遍历到了自己，是否超出了边界,如果是，则返回出去，重新遍历
						if (bianyuan(Current_node.h+j,Current_node.l+k,Current_node.h,Current_node.l,map)&&
							detection_fraise(Current_node.h+j,Current_node.l+k,map)&&
							detection_border(Current_node.h+j,Current_node.l+k,map)&&
							detection_listing(Current_node.h+j,Current_node.l+k,Stop_listing)==null) {
							//如果周边的节点不在关闭列表里,是不是障碍物，斜角的周边是否有障碍物
							About_node=detection_listing(Current_node.h+j,Current_node.l+k,Start_listing);
							
							//判断周边的节点,传递行，列，开启列表
							//如果当前对象在开启列表里，则函数会返回空，如果不在，则会创建新的节点
							if (About_node==null) {
								//如果为空则不用多说了，像病毒一样扩散开来
								About_node=new Node(Current_node.h+j,Current_node.l+k,Current_node,Goal_node,Current_node);
								//初始化节点,传递行，列，父节点(当前的节点),目标节点(上面当鼠标点击时6了一个),开始节点(游戏运行时6了一个)
								Start_listing.push(About_node);
								
								shanchu.push(About_node);
								//如果周边的节点为空,则在周边扩散节点,将扩散的节点放入开启列表，以备下次检测需要,也放入删除列表，待找到路径后删除删除列表里所有的节点,等待系统回收
							} else {
							int oldG=About_node.getG();
								//保存零时变量为开启列表里的G值
								Node Father=About_node.Father_node;
								//保存开启列表里的父节点
								About_node.Father_node=Current_node;
								//替换开启列表里的父节点为当前节点
								if (About_node.getG() >= oldG) {
									//trace("被替换了")
									//比较被替换父节点的开启列表里的节点的值是否大于最开始的G值
									About_node.Father_node=Father;
									//如果大于则,被替换的开启列表里的节点的父节点又被送了回来给与了开启列表里的节点
									}
								//如果周边节点移动的代价比当前节点移动的代价要少的话,就替换当前节点的父节点,递归路径时遍会递归到最短路径的父类
							}
						}
					}
				}
			} while (Start_listing.getLength()!=0);
			//如果开启列表为空，停止寻路
			if(Start_listing.getLength()==0&&Current_node.equals(Goal_node)==false)
			kaishi=false;
		}
		public boolean bianyuan(int h,int l,int hh,int ll,int[][] map) {
			
			for(int i=0;i<_zhangaiwu.length;i++)
			{
				if (hh-1==h&&ll-1==l) {
					if (map[h][l+1]==_zhangaiwu[i]||map[h+1][l]==_zhangaiwu[i]) {
						return false;
					}
				} else if (hh-1==h&&ll+1==l) {
					if (map[h][l-1]==_zhangaiwu[i]||map[h+1][l]==_zhangaiwu[i]) {
						return false;
					}
				} else if (hh+1==h&&ll-1==l) {
					if (map[h-1][l]==_zhangaiwu[i]||map[h][l+1]==_zhangaiwu[i]) {
						return false;
					}
				} else if (hh+1==h&&ll+1==l) {
					if (map[h][l-1]==_zhangaiwu[i]||map[h-1][l]==_zhangaiwu[i]) {
						return false;
					}
				} 
			}
			
			return true;
			//判断当前节点的斜角上周围是否有障碍物
		}
		
		public boolean detection_fraise(int h,int l,int[][] map) {
			for(int i=0;i<_zhangaiwu.length;i++)
			{
				if (map[h][l]==_zhangaiwu[i]) {
					return false;
				}
			}
			return true;
		}
		
		//检测是否有障碍物
		public boolean detection_border(int h,int l,int[][] map) {
			return h < map.length && l < map[0].length && h >= 0 && l >= 0;
		}
		
		//检测是否超出边界
		public Node detection_listing(int h,int l,Array vec) {
			for (int i=0; i < vec.getLength(); i++) {
				Node mc=(Node) vec.get(i);
				if(mc!=null)
				if (h == mc.h && l == mc.l) {
					return mc;
				}
			}
			return null;
		}
		//检测是否在列表里(检测当前行当前列是否与开启列表里的元素一样)
	}
