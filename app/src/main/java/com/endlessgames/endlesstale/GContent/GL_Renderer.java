package com.endlessgames.endlesstale.GContent;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.endlessgames.endlesstale.renderEngine.Loader;
import com.endlessgames.endlesstale.renderEngine.RawModel;
import com.endlessgames.endlesstale.shader.StaticShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Julian on 03.03.2017.
 */

public class GL_Renderer implements GLSurfaceView.Renderer {

    private Context context;
    private RawModel model; //TODO
    private StaticShader shader;//TODO clean up shaders as well

    public GL_Renderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };

        model = Loader.getLoader().loadToVAO(vertices, indices);
        shader = new StaticShader(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        GLES20.glScissor(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        double t1 = System.nanoTime();

        prepare();
        shader.start();
        render(model);
        shader.stop();

        double t2 = System.nanoTime();
        //System.out.println(1000000000 / (t2 - t1)); //TODO
    }

    public void prepare(){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    public void render(RawModel model){
        GLES30.glBindVertexArray(model.getVaoID());
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getVertexCount(), GLES30.GL_UNSIGNED_INT, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glBindVertexArray(0);
    }
}
