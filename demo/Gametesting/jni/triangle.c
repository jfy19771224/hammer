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

	//����ͶӰ����Ϊ͸��ͶӰ
	glMatrixMode(GL_MODELVIEW);
	//����ͶӰ����(����Ϊ��λ����)
	glLoadIdentity();
	glOrthof(0,ratio,0,1, 0, 1000);
	// �򿪼��ò���
	//gl.glEnable(GL10.GL_SCISSOR_TEST);
	// ��������������������
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	glEnableClientState(GL_VERTEX_ARRAY);
	//��2D��ͼ
	//�򿪻�ɫ����
	glEnable(GL_BLEND);
	//ȥPNG����
	glEnable(GL_ALPHA_TEST);
	//������ʾ��Ե������ֵ
	//glAlphaFunc(GL_GREATER,0);
	// ������Ȳ���,PNG����͸��,�޾��,��Ҫȡ��,�ص�
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

