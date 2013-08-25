#include "triangle.h"

static float triggerBuffer[] = {
		0,  1.0, // Top left
		1.0, 1.0, // Bottom left
		0, 0, // Bottom right
		1,   0  // Top right
		};

static float isuv[] = {
		0.0f,-1.0f,
		-1.0f, -1.0f,
		0.0f, 0.0f,
		-1.0f, 0.0f
};

static float coordsSquare[] = {
		0,1,1,1,
		1,0,1,1,
		1,1,0,1,
		1,1,1,0
		};
static short indexShort[] = {
		0, 1,2, 1, 2,  3
};



static float ratio = 0;
static float sp = 0;
int i=0;
float angle2=0;
void init() {
	glHint(GL_PERSPECTIVE_CORRECTION_HINT,GL_NICEST);
	glClearColor(0, 0, 0, 0);
	glEnableClientState(GL_VERTEX_ARRAY);

}
void setSpeed(GLint speed)
{
	sp=speed;
	glEnable(GL_TEXTURE_2D);
}

void resize(GLint w, GLint h) {
	ratio = w/ h;
	glViewport(0,0, w, h);
	glFrontFace(GL_CCW);

	//设置投影矩阵为透视投影
	glMatrixMode(GL_MODELVIEW);
	//重置投影矩阵(设置为单位矩阵)
	glLoadIdentity();
	glOrthof(0,ratio,0,1, 0, 1000);
	// 打开剪裁测试
	//gl.glEnable(GL10.GL_SCISSOR_TEST);
	// 允许设置纹理坐标数组
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	glEnableClientState(GL_VERTEX_ARRAY);
	//打开2D贴图
	//打开混色功能
	glEnable(GL_BLEND);
	//去PNG背景
	glEnable(GL_ALPHA_TEST);
	//设置显示边缘的像素值
	//glAlphaFunc(GL_GREATER,0);
	// 禁用深度测试,PNG背景透明,无锯齿,需要取消,重点
	glDisable(GL_DEPTH_TEST);
	glDisable(GL_CULL_FACE);
	glBlendFunc(GL_DST_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	//glEnableClientState(GL_COLOR_ARRAY);
}

void render() {

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
	//glLoadIdentity();

	glBindTexture(GL_TEXTURE_2D,sp);
	for(i=0;i<30;i++)
	{
		glVertexPointer(2,GL_FLOAT, 0,triggerBuffer);
		glTexCoordPointer(2, GL_FLOAT, 0,isuv);
		//glColorPointer(4, GL_FLOAT, 0,coordsSquare);
		glDrawElements(GL_TRIANGLES,6,GL_UNSIGNED_SHORT, indexShort);
	}
}

