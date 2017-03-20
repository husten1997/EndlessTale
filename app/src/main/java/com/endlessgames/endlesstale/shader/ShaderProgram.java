package com.endlessgames.endlesstale.shader;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;

import com.endlessgames.endlesstale.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by richard on 04.03.17.
 */

public abstract class ShaderProgram {

    private int shaderProgram, vertexShaderID, fragmentShaderID;

    public ShaderProgram(int vertexShader, int fragmentShader, Context context){
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
            System.exit(-1);
        }

        vertexShaderID = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        fragmentShaderID = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        shaderProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(shaderProgram, vertexShaderID);

        // add the fragment shader to program
        GLES20.glAttachShader(shaderProgram, fragmentShaderID);

        bindAttributes();

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(shaderProgram);

        GLES20.glValidateProgram(shaderProgram);
    }

    private static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        System.out.println(GLES20.glGetShaderInfoLog(shader));//TODO

        return shader;
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName){
        GLES20.glBindAttribLocation(shaderProgram, attribute, variableName);
    }

    public void start(){
        GLES20.glUseProgram(shaderProgram);
    }

    public void stop(){
        GLES20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GLES20.glDetachShader(shaderProgram, vertexShaderID);
        GLES20.glDetachShader(shaderProgram, fragmentShaderID);
        GLES20.glDeleteShader(vertexShaderID);
        GLES20.glDeleteShader(fragmentShaderID);
        GLES20.glDeleteProgram(shaderProgram);
    }
}
