package com.endlessgames.endlesstale.shader;

import android.content.Context;
import android.graphics.Shader;
import android.opengl.GLES20;

import com.endlessgames.endlesstale.GContent.GL_Renderer;
import com.endlessgames.endlesstale.R;
import com.endlessgames.endlesstale.rendering.RenderableObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by richard on 04.03.17.
 */

public abstract class ShaderProgramm {

    protected int shaderProgramm, vertexShader, fragmentShader;

    public ShaderProgramm(int vertexShader, int fragmentShader, Context context){
        String vertexShaderCode = "", fragmentShaderCode = "";
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(vertexShader)));
            StringBuilder builder =new StringBuilder();

            String line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            reader.close();
            vertexShaderCode = builder.toString();

            reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(fragmentShader)));
            builder =new StringBuilder();

            line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            reader.close();
            fragmentShaderCode = builder.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        shaderProgramm = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(shaderProgramm, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(shaderProgramm, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(shaderProgramm);

        GLES20.glUseProgram(shaderProgramm);
        getAttributeLocations();
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public abstract void getAttributeLocations();

    protected FloatBuffer toFloatBuffer(float[] coords){
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                coords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        FloatBuffer buffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        buffer.put(coords);
        // set the buffer to read the first coordinate
        buffer.position(0);
        return buffer;
    }
}
