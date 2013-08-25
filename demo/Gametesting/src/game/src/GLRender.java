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
    //�����ε���������  
    private int[] triggerBuffer = {  
            0, one, 0, //�϶���  
            -one, -one, 0, //����  
            one, -one, 0, //����  
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
        //�����Ļ����Ȼ���  
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);  
        gl.glLoadIdentity();          
        //�ƶ�����,����1.5������6����λ  
        gl.glTranslatef(-1.5f, 0.0f, -6.0f);  
        //�ƶ����˺����ö���  
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);  
        //����������  
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, getVertexBuffer(9, triggerBuffer));  
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);  
        //���ù۲����  
        gl.glLoadIdentity();  
        //�ƶ�����,�ƶ����ұ�  
        gl.glTranslatef(1.5f, 0f, -6f);  
        //�����ı���  
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, getVertexBuffer(12, quaterBuffer));  
        indexBuffer = ShortBuffer.wrap(new short[] { 
				
				0,1,2,
				1,2,3
				//1,0,2,
		});
        gl.glDrawElements(GL10.GL_TRIANGLES,6,GL10.GL_UNSIGNED_SHORT,indexBuffer);
       
        //gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);  
        //ȡ����������  
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);  
          
    }  
  
    public void onSurfaceChanged(GL10 gl, int width, int height) {  
        float ratio = (float) width / height;  
        gl.glViewport(0, 0, width, height);  
        Log.d("Tan",""+width+"  "+height);
        //����ͶӰ����  
        gl.glMatrixMode(GL10.GL_PROJECTION);  
        //����ͶӰ����  
        gl.glLoadIdentity();  
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);  
        gl.glMatrixMode(GL10.GL_MODELVIEW);  
        gl.glLoadIdentity();  
    }  
  
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {  
        //���� ϵͳ��͸�ӽ�������  
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);  
        //��ɫ����  
        gl.glClearColor(0, 0, 0, 0);  
        //������Ӱƽ��  
        gl.glShadeModel(GL10.GL_SMOOTH);  
        //������Ȼ���  
        //gl.glClearDepthf(1.0f);  
        //������Ȳ���  
        //gl.glEnable(GL10.GL_DEPTH_TEST);  
        //������Ȳ��� ������  
        //gl.glDepthFunc(GL10.GL_LEQUAL);  
        
        gl.glDisable(GL10.GL_CULL_FACE);
  
    }  
  
}  