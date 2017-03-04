package com.endlessgames.endlesstale.GContent;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.endlessgames.endlesstale.MathContent.Vector3f;
import com.endlessgames.endlesstale.rendering.FlatColoredObject;
import com.endlessgames.endlesstale.rendering.Level;
import com.endlessgames.endlesstale.shader.FlatColoredShader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Julian on 03.03.2017.
 */

public class GL_Renderer implements GLSurfaceView.Renderer {

    private Level level;
    private FlatColoredShader flatColoredShader;
    private Context context;

    public GL_Renderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        level = new Level();
        flatColoredShader = new FlatColoredShader(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        GLES20.glScissor(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        double t1 = System.nanoTime();
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        drawFlatColoredObjects();
        double t2 = System.nanoTime();
        System.out.println(1000000000/(t2 - t1)); //TODO
    }

    private void drawFlatColoredObjects(){
        for(FlatColoredObject obj: level.getFlatColoredObjects()){
            flatColoredShader.draw(obj);
        }
    }

    public void translate(Vector3f translation){
        level.translate(translation);
    }
}
