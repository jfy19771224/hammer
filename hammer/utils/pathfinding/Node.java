package hammer.utils.pathfinding;

import hammer.geom.Array;

	public class Node {
		public int h;
		//定义行
		public int l;
		//定义列
		public Node Father_node;
		//定义父节点
		public Node Goal_node;
		//定义目标节点
		public Node Start_node;
		//定义开始节点
		public int Father_int;
		//定义父节点的G值
		public Node(int h,int l,Node Father_node,Node Goal_node,Node Start_node) 
		{
			this.h=h;
			//接受行
			this.l=l;
			//接受列
			this.Father_node=Father_node;
			//接受父类节点
			this.Goal_node=Goal_node;
			//接受目标节点
			this.Start_node=this.Father_node;
			//接受开启节点
			if (Father_node==null) {
				this.Father_node=this;
			}
			//如果没有则等于自己
			if (Goal_node==null) {
				this.Goal_node=this;
			}
			//如果没有则等于自己
			if (Start_node==null) {
				this.Start_node=this;
			}
			//如果没有，则等于自己(用于起点和重点)
			if (Start_node!=null) {
				Father_int=Start_node.getG();
			}
			//获得父类节点的代价
		}
		public int getH() {
			return Math.abs(h - Goal_node.h) * 10 + Math.abs(l - Goal_node.l) * 10;
			//返回目标到当前的直线代价
		}
		public int getG() {
			if (Math.abs(h - Start_node.h) == 1&& Math.abs(l - Start_node.l) == 1) {
				return 14+Father_int;
				//返回父节点的带价值和当前节点到开始节点值等于一路走过来的带价值
			} else {
				return Math.abs(h - Start_node.h) * 10 + Math.abs(l - Start_node.l) * 10 + Father_int;
				//同上
			}
		}
		
		//返回从开始节点到当前节点的移动代价
		public int getF() {
			return getH() + getG();
		}
		
		//返回H+G的总值
		public boolean equals(Node me) 
		{
			if (me.h == h && me.l == l) {
				return true;
			} else {
				return false;
			}
		}
		//判断是否和参数里的行列相等
		public Array getPath() {
			if (Father_node== this) {
				Array ccc=new Array();
				ccc.push(new int[]{h,l});
				return ccc;
			} else {
				Array aa = Father_node.getPath();
				Array clone = (Array)aa;
				Array answer=clone;
				answer.push(new int[]{h, l});
				return answer;
			}
			//递归逐个访问父亲节点的父节点的坐标，存放新的数组里
		}
	}
