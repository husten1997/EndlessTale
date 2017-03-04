package com.endlessgames.endlesstale.shader;

import android.opengl.GLES20;
import android.provider.Settings;

import com.endlessgames.endlesstale.rendering.FlatColoredObject;
import com.endlessgames.endlesstale.rendering.RenderableObject;

/**
 * Created by richard on 04.03.17.
 */

public class FlatColoredShader extends ShaderProgramm{

    private int positionLocation, colorLocation;

    public FlatColoredShader(){
        super("attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = vPosition;" +
                "}","precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}");//TODO
    }

    public void draw(FlatColoredObject obj) {
        float[] verticies = obj.getVerticies();

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(shaderProgramm);

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionLocation);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionLocation, 3,
                GLES20.GL_FLOAT, false,
                12, toFloatBuffer(verticies));

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorLocation, 1, obj.getColor(), 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, verticies.length/3);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionLocation);
    }

    @Override
    public void getAttributeLocations() {
        positionLocation = GLES20.glGetAttribLocation(shaderProgramm, "vPosition");
        colorLocation = GLES20.glGetUniformLocation(shaderProgramm, "vColor");
    }
}
