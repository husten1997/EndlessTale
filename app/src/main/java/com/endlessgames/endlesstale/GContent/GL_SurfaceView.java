package com.endlessgames.endlesstale.GContent;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

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

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                System.out.println("Width: " + this.getWidth() + " Height: " + this.getHeight());
                mRenderer.dothis(dx / this.getWidth(), -dy / this.getHeight());


        }

        mPreviousX = x;
        mPreviousY = y;

        //System.out.println("Done 1");
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
