package com.endlessgames.endlesstale.GContent;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.endlessgames.endlesstale.MathContent.Vector3f;

/**
 * Created by Julian on 03.03.2017.
 */

public class GL_SurfaceView extends GLSurfaceView{

    private final GL_Renderer mRenderer;

    private float mPreviousX;
    private float mPreviousY;

    public boolean onTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();

        if(e.getAction() == MotionEvent.ACTION_MOVE){
            float dx = x - mPreviousX;
            float dy = y - mPreviousY;
            dx *= 2; //Convert from 0-1 KOS to -1-1 KOS
            dy *= 2;

            mRenderer.translate(new Vector3f(dx/getWidth(), -dy/getHeight(), 0));
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }

    public GL_SurfaceView(Context context){
        super(context);

        //Create an OpenGl ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new GL_Renderer();

        //Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}
