package hammer.filter;

import javax.microedition.khronos.opengles.GL10;

import hammer.display.Sprite2D;
import hammer.opengl2d.System2D;
import android.opengl.GLES20;
import android.util.Log;

/**
 * 玻璃滤镜
 * @-式神-
 *
 */
public class FilterLightingShader extends FilterShader
{
	public FilterLightingShader()
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
			    "uniform vec3 lightingValue;" +
			    "void main() {" +
			    "vec3 lighting=vec3(1, 1, 1);"+
			    "lighting.x=abs(tex.x-lightingValue.x);"+
			    "lighting.y=abs(tex.y-lightingValue.y);"+
			    "lighting.z=sqrt(lighting.x*lighting.x+lighting.y*lighting.y);"+
			    "gl_FragColor=texture2D(u_samplerTexture,tex)*colour/(lighting.z*lightingValue.z);"+
		"}";
		initShade();
		
	}
	
	/**
	 * 上传数据
	 */
	public void upData(Sprite2D sprite)
	{
		
		GLES20.glUseProgram(program);
        GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(program, "projectionMatrix"),1,false,System2D.projectionMatrix, 0);
       
        GLES20.glUniform3fv(GLES20.glGetUniformLocation(program, "lightingValue"),1, new float[]{.5f,.5f,7f}, 0);
       
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
