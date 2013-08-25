package game.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import hammer.geom.BufferFactory;
import hammer.textures.Texture2D;

import android.opengl.GLES20;

public class Triangle {
	private FloatBuffer vertexBuffer;
	private final String vertexShaderCode =
	    "attribute vec4 vPosition;" +
	    "attribute vec2 a_texCoord;"+		
	     "uniform vec4 weiyi;" +
	     "varying vec2 v_texCoord;"+
	    "void main() {" +
	    "  gl_Position = vPosition;" +
	    "  gl_Position=gl_Position+weiyi;"+
	    "  v_texCoord=a_texCoord;"+
	    "}";
	
	private final String fragmentShaderCode =
	    "precision mediump float;" +
	    "uniform vec4 vColor;" +
	    "uniform vec4 vtm;" +
	    "uniform sampler2D u_samplerTexture;"+
	    "varying vec2 v_texCoord;"+
	    "void main() {" +
	    "vec4 tk=vec4(1.7, 1.7, 1.7, 1.7);"+
	    "  gl_FragColor = texture2D(u_samplerTexture,v_texCoord);" +
        "  gl_FragColor=gl_FragColor*vtm;"+
	    "}";
    // ������ÿ�������������
    static final int COORDS_PER_VERTEX = 3;
    public int mProgram;
    static float triangleCoords[] = { // ����ʱ�뷽��˳��:
         1f,  -1f,   // ����
        -1f, -1f,   // ����
        1f, 1f,  // ����
        -1f, 1f    // ����
    };
    static float uvList[] = { // ����ʱ�뷽��˳��:
    	0.0f, 0.0f,
        1.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 1.0f
   };
    private FloatBuffer uvBuffer;
    private FloatBuffer vertex;
    private ShortBuffer index;
    private FloatBuffer uv;

    // ������ɫ���ֱ�Ϊred, green, blue ��alpha (opacity)
    float color[] = { 1.63671875f, 0.16953125f, 0.22265625f, 1.0f };

    private short[] indexShort;
    public Triangle() {
    	
    	Texture2D texture=new Texture2D("123.jpg");
    	textureId=texture.getTextureId();
    	
    	indexShort=new short[6];
    	indexBuffer=BufferFactory.createShortBuffer(6);
    	indexBuffer.position(0);
    	indexShort[0]=(short) (0);
		indexShort[1]=(short) (1);
		indexShort[2]=(short) (2);
		indexShort[3]=(short) (1);
		indexShort[4]=(short) (2);
		indexShort[5]=(short) (3);
		indexBuffer.put(indexShort);
		indexBuffer.position(0);
		
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);
        
        
       
        uvBuffer=BufferFactory.createFloatBuffer(8);
        uvBuffer.position(0);
      
		uvBuffer.put(uvList);
		uvBuffer.position(0);
	
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // ����һ���յ�OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // ��vertex shader��ӵ�program
        GLES20.glAttachShader(mProgram, fragmentShader); // ��fragment shader��ӵ�program
        GLES20.glLinkProgram(mProgram);                  // ������ִ�е� OpenGL ES program
    }
    
    /**
     * ������ɫ��
     * @param type
     * @param shaderCode
     * @return
     */
    public static int loadShader(int type, String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
    private int mPositionHandle;
    private int mColorHandle; 
    private int textureId;
    protected ShortBuffer indexBuffer;
    public void draw(float[] mvpMatrix) {
    	
    	//GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    	
        // ��program����OpenGL ES������
        GLES20.glUseProgram(mProgram);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        // ��ȡָ��vertex shader�ĳ�ԱvPosition�� handle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        // ����һ��ָ�������εĶ��������handle
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // ׼�������ε���������
        GLES20.glVertexAttribPointer(mPositionHandle, 2,
                                     GLES20.GL_FLOAT, false,
                                     0, vertexBuffer);
        
       
        // ����һ��ָ�������εĶ��������handle
        GLES20.glEnableVertexAttribArray(GLES20.glGetAttribLocation(mProgram, "a_texCoord"));
        // ׼�������ε���������
        GLES20.glVertexAttribPointer(GLES20.glGetAttribLocation(mProgram, "a_texCoord"),2,
                                     GLES20.GL_FLOAT, false,
                                     0, uvBuffer);
        
       
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(mProgram, "vColor"),1, new float[]{ 1.63671875f, 0.16953125f, 0.22265625f, 1.0f }, 0);
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(mProgram, "vtm"),1, new float[]{ 1.63671875f, 1.16953125f, 1.22265625f, 1.0f }, 0);
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(mProgram, "weiyi"),1, new float[]{1f,1f,0f,0f }, 0);
        GLES20.glUniform1i(GLES20.glGetUniformLocation(mProgram, "u_samplerTexture"),0);
        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        GLES20.glDrawElements(GL10.GL_TRIANGLES,6,GL10.GL_UNSIGNED_SHORT, indexBuffer);
       
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(GLES20.glGetAttribLocation(mProgram, "a_texCoord"));
    }
}
