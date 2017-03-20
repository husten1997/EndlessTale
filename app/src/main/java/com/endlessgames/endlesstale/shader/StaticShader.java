package com.endlessgames.endlesstale.shader;

import android.content.Context;

import com.endlessgames.endlesstale.R;

/**
 * Created by Richard on 20.03.2017.
 */

public class StaticShader extends ShaderProgram{

    public StaticShader(Context context){
        super(R.raw.vertexshader, R.raw.fragmentshader, context);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
