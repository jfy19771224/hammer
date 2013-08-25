package game.src;

import hammer.geom.BufferFactory;

import java.nio.IntBuffer;  
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;  
import javax.microedition.khronos.opengles.GL10;  
  
import android.opengl.GLSurfaceView.Renderer;  
import android.util.Log;
  
public class GLRender implements Renderer {  
      
    int one = 0x10000;  
    //三角形的三个顶点  
    private int[] triggerBuffer = {  
            0, one, 0, //上顶点  
            -one, -one, 0, //下左  
            one, -one, 0, //下右  
    };  
      
    private int[] quaterBuffer = {  
            -one, one, 0,  
            one, one, 0,   
            -one, -one, 0,   
            one, -one, 0  
    };  
      
    private IntBuffer getVertexBuffer(int capacity, int sum[]) {  
        IntBuffer vertexBuffer = BufferFactory.createIntBuffer(capacity).put(sum);  
        vertexBuffer.position(0);  
        return vertexBuffer;  
    }  
      
  
    protected ShortBuffer indexBuffer;
    public void onDrawFrame(GL10 gl) {  
        //清除屏幕和深度缓存  
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        gl.glLoadIdentity();          
        //移动顶点,左移1.5，向里6个单位  
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);  
        //移动好了后，设置顶点  
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);  
        //绘制三角形  
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, getVertexBuffer(9, triggerBuffer));  
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);  
        //重置观察矩阵  
        gl.glLoadIdentity();  
        //移动顶点,移动到右边  
        gl.glTranslatef(1.5f, 0f, -6f);  
        //绘制四边形  
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, getVertexBuffer(12, quaterBuffer));  
        indexBuffer = ShortBuffer.wrap(new short[] { 
				
				0,1,2,
				1,2,3
				//1,0,2,
		});
        gl.glDrawElements(GL10.GL_TRIANGLES,6,GL10.GL_UNSIGNED_SHORT,indexBuffer);
       
        //gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);  
        //取消顶点设置  
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);  
          
    }  
  
    public void onSurfaceChanged(GL10 gl, int width, int height) {  
        float ratio = (float) width / height;  
        gl.glViewport(0, 0, width, height);  
        Log.d("Tan",""+width+"  "+height);
        //设置投影矩阵  
        gl.glMatrixMode(GL10.GL_PROJECTION);  
        //重置投影矩阵  
        gl.glLoadIdentity();  
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);  
        gl.glMatrixMode(GL10.GL_MODELVIEW);  
        gl.glLoadIdentity();  
    }  
  
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {  
        //告诉 系统对透视进行修正  
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);  
        //白色背景  
        gl.glClearColor(0, 0, 0, 0);  
        //启用阴影平滑  
        gl.glShadeModel(GL10.GL_SMOOTH);  
        //设置深度缓存  
        //gl.glClearDepthf(1.0f);  
        //启用深度测试  
        //gl.glEnable(GL10.GL_DEPTH_TEST);  
        //所做深度测试 的类型  
        //gl.glDepthFunc(GL10.GL_LEQUAL);  
        
        gl.glDisable(GL10.GL_CULL_FACE);
  
    }  
  
}  