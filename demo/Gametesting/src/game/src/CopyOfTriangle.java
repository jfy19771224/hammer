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
    // 数组中每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;
    public int mProgram;
    static float triangleCoords[] = { // 按逆时针方向顺序:
         0.0f,  0.622008459f, 0.0f,   // top
        -0.5f, -1.311004243f, 0.0f,   // bottom left
         0.5f, -0.311004243f, 0.0f    // bottom right
    };

    // 设置颜色，分别为red, green, blue 和alpha (opacity)
    float color[] = { 1.63671875f, 0.16953125f, 0.22265625f, 1.0f };

    public CopyOfTriangle() {
        // 为存放形状的坐标，初始化顶点字节缓冲
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (坐标数 * 4)float占四字节
                triangleCoords.length * 4);
        // 设用设备的本点字节序
        bb.order(ByteOrder.nativeOrder());

        // 从ByteBuffer创建一个浮点缓冲
        vertexBuffer = bb.asFloatBuffer();
        // 把坐标们加入FloatBuffer中
        vertexBuffer.put(triangleCoords);
        // 设置buffer，从第一个坐标开始读
        vertexBuffer.position(0);
        
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // 创建一个空的OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // 将vertex shader添加到program
        GLES20.glAttachShader(mProgram, fragmentShader); // 将fragment shader添加到program
        GLES20.glLinkProgram(mProgram);                  // 创建可执行的 OpenGL ES program
    }
    
    public static int loadShader(int type, String shaderCode){

        // 创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        // 或fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译之
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
    private int mPositionHandle;
    private int mColorHandle; 
    public void draw(float[] mvpMatrix) {
    	
    	int mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

    	 GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        // 将program加入OpenGL ES环境中
        GLES20.glUseProgram(mProgram);
        
        // 获取指向vertex shader的成员vPosition的 handle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 启用一个指向三角形的顶点数组的handle
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // 准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     0, vertexBuffer);

        // 获取指向fragment shader的成员vColor的handle 
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

       
        // 设置三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // 画三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
     // 应用投影和视口变换
       

        // 禁用指向三角形的顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
