package com.endlessgames.endlesstale.renderEngine;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 20.03.2017.
 */

public class Loader {

    private List<Integer> vaos = new ArrayList<>(), vbos = new ArrayList<>();
    private static Loader loader;

    public static Loader getLoader(){
        if(loader == null){
            loader = new Loader();
        }
        return loader;
    }

    public RawModel loadToVAO(float[] positions, int indices[]){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, positions);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    private int createVAO(){
        int[] array = new int[1];
        GLES30.glGenVertexArrays(1, array, 0);
        int vaoID = array[0];
        vaos.add(vaoID);
        GLES30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data){
        int[] array = new int[1];
        GLES30.glGenBuffers(1, array, 0);
        int vboID = array[0];
        vbos.add(vboID);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, data.length, buffer, GLES30.GL_STATIC_DRAW);
        GLES30.glVertexAttribPointer(attributeNumber, 3, GLES30.GL_FLOAT, false, 0, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO(){
        GLES30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices){
        int[] array = new int[1];
        GLES30.glGenBuffers(1, array, 0);
        int vboID = array[0];
        vbos.add(vboID);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, indices.length, buffer, GLES30.GL_STATIC_DRAW);
    }

    private IntBuffer storeDataInIntBuffer(int[] data){
        ByteBuffer temp = ByteBuffer.allocateDirect(data.length*4);
        temp.order(ByteOrder.nativeOrder());
        IntBuffer buffer = temp.asIntBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        ByteBuffer temp = ByteBuffer.allocateDirect(data.length*4);
        temp.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = temp.asFloatBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    public void cleanUp(){
        for(int vao: vaos){
            GLES30.glDeleteVertexArrays(1, new int[]{vao}, 0);
        }
        for(int vbo: vbos){
            GLES30.glDeleteBuffers(1, new int[]{vbo},0);
        }
    }
}
