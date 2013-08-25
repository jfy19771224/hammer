package game.src;

import java.io.IOException;
import java.util.ArrayList;

import hammer.core.Function;
import hammer.core.Stage2D;
import hammer.display.Quad;
import hammer.display.Sprite2D;
import hammer.display.TextField2D;
import hammer.event.Event2D;
import hammer.grain.GrainSend;
import hammer.opengl2d.System2D;
import hammer.textures.Texture2D;
import hammer.ui.MouseEvent2D;
import hammer.utils.Timer2D;
import hammer.utils.TimerEvent2D;
import hammer.utils.asqare.AsqareClass;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

public class GameMain extends Activity{
	
	private Stage2D stage2d;
	
	private Sprite2D view;
	
	private Sprite2D quadlist;
	
	private TextField2D textField2D;
	
	private AsqareClass asqareClass;
	
	private ArrayList<Integer> p1;
	private ArrayList<Integer> p2;
	private ArrayList<ArrayList<Integer>> mapList;
	private ArrayList<Quad> clickList;
	private ArrayList<Quad> slowActionList;
	
	private SoundPool soundPool;
	private int explosionId;
	private int explosionId2;
	private MediaPlayer mediaPlayer;
	private GrainSend grainSend;
	private ArrayList<ArrayList<Integer>> list;
	private Quad targetQuad;
	
	public GameMain()
	{
		
	}

    protected void onCreate(Bundle savedInstanceState) 
    {
	       super.onCreate(savedInstanceState);
	       stage2d=new Stage2D(this,2);
	       stage2d.getEvent2D().addEventListener(Event2D.INITIALIZE,new Function(this,"init"));
	       stage2d.getEvent2D().addEventListener(Event2D.MOUSE_DOWN, new Function(this,"mouseDown"));
	       stage2d.getEvent2D().addEventListener(Event2D.EVENT_FRAME, new Function(this,"eventFrame"));
	       
	       setVolumeControlStream(AudioManager.STREAM_MUSIC);  
	       soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);  
     
	       slowActionList=new ArrayList<Quad>();
          try{  
               AssetManager assetManager = getAssets();  
               AssetFileDescriptor descriptor = assetManager.openFd("res/click.mp3");  
               explosionId = soundPool.load(descriptor, 1);  
               
               descriptor = assetManager.openFd("res/connect.mp3");  
               explosionId2 = soundPool.load(descriptor, 1);  
               
               AssetFileDescriptor fileDescriptor = assetManager.openFd("res/sound.mp3");
               mediaPlayer = new MediaPlayer();
               
               mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),  
               fileDescriptor.getStartOffset(),                        
               fileDescriptor.getLength());
                  
               mediaPlayer.prepare();
               mediaPlayer.setLooping(true);
               //mediaPlayer.start();
               
           }catch(IOException e){  
        	   Log.d("Tan", "报错");
               e.getMessage();  
           } 
        
   
	 }
	 
	
	 private int ids;
	 private float corl=0;
	 private boolean moveBool;
	 private int dd=1;
	 private int dds;
	 
	 private int[] a1={0,1};
	 private int[] a2={0,2,1};
	 private int[] a3={0,2,3,1};
	 public void eventFrame()
	 {
		 if(guan!=null)
		 guan.setRotation(guan.getRotation()+3);
		 for(int t=0;t<slowActionList.size();t++)
		 {
			 UserData userData=(UserData) slowActionList.get(t).getUserData();
			 userData.run();
		 }
		 
		 if(moveBool)
		 {
			dds++;
			if(dds>=20)
			{
				dds=0;
				dd++;
				if(dd>=list.size())
				{
					grainSend.getLauncher(0).start=false;
					grainSend.getLauncher(1).start=false;
					dd=list.size()-1;
					moveBool=false;
				}
			}
			int speed=10;
			switch(list.size())
			{
			case 2:
				//0,1
				grainSend.getLauncher(0).x+=(list.get(a1[1]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(0).x)/speed;
			 	grainSend.getLauncher(0).y+=(list.get(a1[1]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(0).y)/speed;
			 	
			 	grainSend.getLauncher(1).x+=(list.get(a1[0]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(1).x)/speed;
			 	grainSend.getLauncher(1).y+=(list.get(a1[0]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(1).y)/speed;
				
			 	break;
			case 3:
				
				//0,2,1
				grainSend.getLauncher(0).x+=(list.get(a2[dd]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(0).x)/speed;
			 	grainSend.getLauncher(0).y+=(list.get(a2[dd]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(0).y)/speed;
			 	
			 	
			 	//1,2,0
			 	grainSend.getLauncher(1).x+=(list.get(a2[2-dd]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(1).x)/speed;
			 	grainSend.getLauncher(1).y+=(list.get(a2[2-dd]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(1).y)/speed;
				break;
			case 4:
				
				//0,2,3,1
				grainSend.getLauncher(0).x+=(list.get(a3[dd]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(0).x)/speed;
			 	grainSend.getLauncher(0).y+=(list.get(a3[dd]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(0).y)/speed;
			 	
			 	
			 	//1,3,2,0
			 	grainSend.getLauncher(1).x+=(list.get(a3[3-dd]).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2-grainSend.getLauncher(1).x)/5;
			 	grainSend.getLauncher(1).y+=(list.get(a3[3-dd]).get(0)*70+System2D.getStageHeight()/2-map.length*70/2-grainSend.getLauncher(1).y)/5;
				break;
			}
		 	
		 	
		 }
		
		 ids++;
		 if(ids>=30)
		 {
			 ids=0;
			 textField2D.setText("FPS :"+System2D.fps); 
		 }
		
		 corl+=.1;
		
	 }
	 
	 /**
	  * 鼠标按下
	  * @param e
	  */
	 public void mouseDown(MouseEvent2D e)
	 {
		 if(e.getTarget()!=null)
		 if(e.getTarget() instanceof Quad)
		 {
			
			 Quad quad=(Quad) e.getTarget();
			 if(targetQuad==null)
			 {
				 targetQuad=quad;
				 UserData userData=(UserData) targetQuad.getUserData();
				 userData.scale=1.1f;
			 }else
			 {
				 UserData userData=(UserData) targetQuad.getUserData();
				 userData.scale=1f;
				 targetQuad=quad;
				 userData=(UserData) targetQuad.getUserData();
				 userData.scale=1.1f;
			 }
			 if(clickList.size()==0)
			 {
				 UserData userData=(UserData)quad.getUserData();
				 if(userData!=null)
				 {
					 if(map[userData.row][userData.line]!=0)
					 {
						
						 clickList.add(quad);
					 }
					 
				 }
				 soundPool.play(explosionId, 1, 1, 0, 0, 1);  
			 }else if(clickList.size()==1)
			 {
				 
				 
				 UserData userData0=(UserData) clickList.get(0).getUserData();
				 UserData userData1=(UserData) quad.getUserData();
				 
				 if(userData0.row!=userData1.row||userData0.line!=userData1.line)
				 {
					 if(userData0.id==userData1.id)
					 {
						
					 	clickList.add(quad);
					 }else
					 {
						
						 clickList.clear();
						 clickList.add(quad);
					 }
				 }
			 }
			
			 if(clickList.size()==2)
			 {
				UserData userData0=(UserData) clickList.get(0).getUserData();
				UserData userData1=(UserData) clickList.get(1).getUserData();
				p1=new ArrayList<Integer>();
				p1.add(0,userData0.row);
				p1.add(1,userData0.line);
				
				p2=new ArrayList<Integer>();
				p2.add(0,userData1.row);
				p2.add(1,userData1.line);
			
				//Log.d("Tan",""+asqareClass.AIdo(mapList, p1, p2));
				list=asqareClass.AIdo(mapList, p1, p2);
				if(list!=null&&list.size()>1)
				{
					soundPool.play(explosionId2, 1, 1, 0, 0, 1); 
					dd=1;
					moveBool=true;
					
					grainSend.getLauncher(0).start=true;
					grainSend.getLauncher(0).r=Math.random();
					grainSend.getLauncher(0).g=Math.random();
					grainSend.getLauncher(0).b=Math.random();
					
				 	grainSend.getLauncher(0).x=list.get(0).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2;
				 	grainSend.getLauncher(0).y=list.get(0).get(0)*70+System2D.getStageHeight()/2-map.length*70/2;
				 	
				 	
				 	grainSend.getLauncher(1).start=true;
				 	grainSend.getLauncher(1).r=Math.random();
				 	grainSend.getLauncher(1).g=Math.random();
				 	grainSend.getLauncher(1).b=Math.random();
				 	
				 	grainSend.getLauncher(1).x=list.get(1).get(1)*65+System2D.getStageWidth()/2-map[0].length*65/2+65/2;
				 	grainSend.getLauncher(1).y=list.get(1).get(0)*70+System2D.getStageHeight()/2-map.length*70/2;
				 	
					map[userData0.row][userData0.line]=0;
					map[userData1.row][userData1.line]=0;
					upDataMapList(map);
					
					clickList.get(0).play();
					clickList.get(1).play();
					
					clickList.clear();
				}else
				{
					
					clickList.clear();
				}
					
				 
			 }
			
		 }
		
	 }
	 
	 private int[][] map;
	 private Sprite2D guanghuan;
	 private Quad guan;
	 private int mapId()
	 {
		 return (int)(Math.random()*8)+1;
	 }
	 private void initMap()
	 {
		 map=new int[][]{
				 {0,0,0,0,0,0,0,0,0,0,0,0},
				 {0,mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),1,0},
				 {0,mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),0},
				 {0,mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),0},
				 {0,mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),0},
				 {0,mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),mapId(),0},
				 {0,0,0,0,0,0,0,0,0,0,0,0}
		};
		
		for(int i=0;i<map.length;i++)
		 {
			 for(int j=0;j<map[0].length;j++)
			 {
				 if(map[i][j]!=0)
				 {
					 Quad quad=quadlist.getQuad();
					 quad.setPivotMiddle(true);
					 UserData userData=new UserData(quad);
					 userData.x=j*65+System2D.getStageWidth()/2-(map[0].length*65)/2+65/2;
					 userData.y=i*70+System2D.getStageHeight()/2-(map.length*70)/2;
					 quad.setMouseAccurate(false);
					
					 quad.setX(System2D.getStageWidth()/2);
					 quad.setY(System2D.getStageHeight()/2);
					 quad.loop(false);
					 quad.setAnimationSpeed(24);
					 quad.setScene("item_"+(map[i][j]+1));
					
					 userData.id=map[i][j];
					 userData.row=i;
					 userData.line=j;
					 userData.start=true;
					 quad.setUserData(userData);
					 quad.setB(.5f);
					 quadlist.addChild(quad);
					 slowActionList.add(quad);
				 }
			 }
		 }
		
		upDataMapList(map);
		
		clickList=new ArrayList<Quad>();
		asqareClass=new AsqareClass();
		
		
	 }
	 
	 private void upDataMapList(int[][] map)
	 {
		 mapList=new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<map.length;i++)
			{
				ArrayList<Integer> list=new ArrayList<Integer>();
				for(int j=0;j<map[0].length;j++)
				{
					list.add(map[i][j]);
				}
				mapList.add(list);
			}
	 }
	
	
	 public void init()
	 {
		 
		 Log.d("Tan","初始化");
		 
		 view=new Sprite2D();
		 stage2d.addChild(view);
		 
		 
		 quadlist=new Sprite2D(new Texture2D("res/res.png","res/res.xml"),100);
		 quadlist.setShaderDerail(true);
		
		 view.addChild(quadlist);
		 
		 Quad setting=quadlist.getQuad();
		 setting.setScene("setting");
		 
		 setting.setWidth(System2D.getStageWidth());
		 setting.setHeight(System2D.getStageHeight());
		 setting.gotoAndStop(2);
		 setting.setMouseEnabled(false);
		 quadlist.addChild(setting);
		 
		 initMap();
		 
		 textField2D=new TextField2D(160,50);
		 textField2D.setX(160/2);
		 textField2D.setY(50/2);
		 stage2d.addChild(textField2D);
		
		 grainSend=new GrainSend(new Texture2D("res/Circle.png"),200,2);
		 grainSend.setDelay(3);
		 view.addChild(grainSend);
		 
		 guanghuan=new Sprite2D(new Texture2D("res/gung.png"),1);
		 guanghuan.setScaleY(.5f);
		 view.addChild(guanghuan);
		//做事最快,也是最有效率的办法是知道自己要做什么,有目标
		 guan=guanghuan.getQuad();
		 guan.setPivotMiddle(true);
		 guan.setMouseEnabled(false);
		 guan.setX(200);
		 guan.setY(200);
		 guan.setVisible(true);
		 guanghuan.addChild(guan);
		 
	 }
}
