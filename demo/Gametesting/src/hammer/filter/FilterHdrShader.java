package hammer.filter;

import javax.microedition.khronos.opengles.GL10;

import hammer.display.Sprite2D;
import hammer.opengl2d.System2D;
import android.opengl.GLES20;
import android.util.Log;

/**
 * HDR�˾�
 * @-ʽ��-
 *
 */
public class FilterHdrShader extends FilterShader
{
	public FilterHdrShader()
	{
		vertexShaderCode =
			    "attribute vec2 triggerBuffer;" +
			    "attribute vec2 uvBuffer;"+	
			    "attribute vec4 colourBuffer;"+
			    "uniform mat4 projectionMatrix;"+
			    "varying vec2 tex;"+
			    "varying vec4 colour;"+
			    "void main() {" +
			    "  gl_Position = projectionMatrix*vec4(triggerBuffer,1.0,1.0);"+
			    "  tex=uvBuffer;"+
			    "  colour=colourBuffer*colourBuffer;"+
		"}";
		
		fragmentShaderCode =
			    "precision mediump float;" +
			    "uniform sampler2D u_samplerTexture;"+
			    "varying vec2 tex;"+
			    "varying vec4 colour;"+
			    "uniform vec3 grayValue;" +
				"vec4 xposure(vec4 _color , float gray , float ex) {"+
			         "float b = ( 4.0 * ex -  1.0 );"+
			         "float a = 1.0 - b;"+
			         "float f = gray * ( a * gray + b );"+
			         "return  _color*f;"+
		         "}"+
			    "void main() {" +
				    "gl_FragColor=texture2D(u_samplerTexture,tex)*colour;"+
				    "vec4 _dsColor = texture2D(u_samplerTexture , tex);"+
				    "float _lum = 0.3 * _dsColor.x + 0.59 * _dsColor.y + 0.11* _dsColor.z;"+
				    "vec4 _fColor = texture2D(u_samplerTexture , tex);"+
				    //��������ɫ��������
				    "vec2 f=vec2(0.5,1);"+
				    "gl_FragColor=xposure(_fColor,_lum,1.5);"+          
				"}";
			 
		initShade();
		
	}
	
	/**
	 * �ϴ�����
	 */
	public void upData(Sprite2D sprite)
	{
		
		GLES20.glUseProgram(program);
        GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(program, "projectionMatrix"),1,false,System2D.projectionMatrix, 0);
       
        GLES20.glUniform3fv(GLES20.glGetUniformLocation(program, "grayValue"),1, new float[]{0.3f,0.59f,0.11f}, 0);
       
        int triggerid=GLES20.glGetAttribLocation(program, "triggerBuffer");
        GLES20.glEnableVertexAttribArray(triggerid);
        GLES20.glVertexAttribPointer(triggerid, 2,GLES20.GL_FLOAT, false,0, sprite.getTriggerBuffer());
      
        int uvid=GLES20.glGetAttribLocation(program, "uvBuffer");
        GLES20.glEnableVertexAttribArray(uvid);
        GLES20.glVertexAttribPointer(uvid,2,GLES20.GL_FLOAT, false,0,sprite.getUvBuffer());
        
        
        int colourid=GLES20.glGetAttribLocation(program, "colourBuffer");
        GLES20.glEnableVertexAttribArray(colourid);
        GLES20.glVertexAttribPointer(colourid,4,GLES20.GL_FLOAT, false,0,sprite.getColourBuffer());
        
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,sprite.getBitmapInit());
        GLES20.glUniform1i(GLES20.glGetUniformLocation(program, "u_samplerTexture"),0);
        GLES20.glDrawElements(GL10.GL_TRIANGLES,6*sprite.getVertexId(),GL10.GL_UNSIGNED_SHORT, sprite.getIndexBuffer());
       
        GLES20.glDisableVertexAttribArray(triggerid);
        GLES20.glDisableVertexAttribArray(uvid);
        GLES20.glDisableVertexAttribArray(colourid);
	}
}
