package com.endlessgames.endlesstale.shader;

import android.content.Context;
import android.opengl.GLES20;

import com.endlessgames.endlesstale.R;
import com.endlessgames.endlesstale.rendering.FlatColoredObject;
import com.endlessgames.endlesstale.rendering.TexturedObject;

/**
 * Created by richard on 04.03.17.
 */

public class TexturedShader extends ShaderProgramm{

    private int positionLocation, scaleLocation, posLocation, textureLocation;

    public TexturedShader(Context context){
        super(R.raw.texturedvertexshader, R.raw.texturedfragmentshader, context);
    }

    public void draw(TexturedObject obj) {
        float[] verticies = obj.getVerticies();

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(shaderProgramm);

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionLocation);
        GLES20.glEnableVertexAttribArray(scaleLocation);
        GLES20.glEnableVertexAttribArray(posLocation);
        GLES20.glEnableVertexAttribArray(textureLocation);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionLocation, 3,
                GLES20.GL_FLOAT, false,
                12, toFloatBuffer(verticies));

        GLES20.glUniform1f(scaleLocation, obj.getScale());
        GLES20.glUniform3fv(posLocation, 1, obj.getPos().get(), 0);
        GLES20.glUniform1i(textureLocation, obj.getTexture());

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, verticies.length/3);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionLocation);
        GLES20.glDisableVertexAttribArray(scaleLocation);
        GLES20.glDisableVertexAttribArray(posLocation);
        GLES20.glDisableVertexAttribArray(textureLocation);
    }

    @Override
    public void getAttributeLocations() {
        positionLocation = GLES20.glGetAttribLocation(shaderProgramm, "vPosition");
        scaleLocation = GLES20.glGetUniformLocation(shaderProgramm, "scale");
        posLocation = GLES20.glGetUniformLocation(shaderProgramm, "pos");
        textureLocation = GLES20.glGetUniformLocation(shaderProgramm, "u_Texture");
    }
}
