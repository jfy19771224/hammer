package game.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class CopyOfTriangle {
	private FloatBuffer vertexBuffer;
	private final String vertexShaderCode =
	    "attribute vec4 vPosition;" +
	    "uniform vec4 m4;" +
	    "void main() {" +
	    "  gl_Position = vPosition*m4;" +
	    "}";
	
	private final String fragmentShaderCode =
	    "precision mediump float;" +
	    "uniform vec4 vColor;" +
	    "void main() {" +
	    "vec4 tk=vec4(1.0, 0.0, 0.0, 1.0);"+
	   "tk.g=1.1;"+
	    "  gl_FragColor = vColor;" +
	   
	    "}";
    // ������ÿ�������������
    static final int COORDS_PER_VERTEX = 3;
    public int mProgram;
    static float triangleCoords[] = { // ����ʱ�뷽��˳��:
         0.0f,  0.622008459f, 0.0f,   // top
        -0.5f, -1.311004243f, 0.0f,   // bottom left
         0.5f, -0.311004243f, 0.0f    // bottom right
    };

    // ������ɫ���ֱ�Ϊred, green, blue ��alpha (opacity)
    float color[] = { 1.63671875f, 0.16953125f, 0.22265625f, 1.0f };

    public CopyOfTriangle() {
        // Ϊ�����״�����꣬��ʼ�������ֽڻ���
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (������ * 4)floatռ���ֽ�
                triangleCoords.length * 4);
        // �����豸�ı����ֽ���
        bb.order(ByteOrder.nativeOrder());

        // ��ByteBuffer����һ�����㻺��
        vertexBuffer = bb.asFloatBuffer();
        // �������Ǽ���FloatBuffer��
        vertexBuffer.put(triangleCoords);
        // ����buffer���ӵ�һ�����꿪ʼ��
        vertexBuffer.position(0);
        
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // ����һ���յ�OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // ��vertex shader��ӵ�program
        GLES20.glAttachShader(mProgram, fragmentShader); // ��fragment shader��ӵ�program
        GLES20.glLinkProgram(mProgram);                  // ������ִ�е� OpenGL ES program
    }
    
    public static int loadShader(int type, String shaderCode){

        // ����һ��vertex shader����(GLES20.GL_VERTEX_SHADER)
        // ��fragment shader����(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // ��Դ����ӵ�shader������֮
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
    private int mPositionHandle;
    private int mColorHandle; 
    public void draw(float[] mvpMatrix) {
    	
    	int mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

    	 GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        // ��program����OpenGL ES������
        GLES20.glUseProgram(mProgram);
        
        // ��ȡָ��vertex shader�ĳ�ԱvPosition�� handle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // ����һ��ָ�������εĶ��������handle
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // ׼�������ε���������
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     0, vertexBuffer);

        // ��ȡָ��fragment shader�ĳ�ԱvColor��handle 
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

       
        // ���������ε���ɫ
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // ��������
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
     // Ӧ��ͶӰ���ӿڱ任
       

        // ����ָ�������εĶ�������
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
