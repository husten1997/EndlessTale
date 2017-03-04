package com.endlessgames.endlesstale.GContent;

import android.graphics.Matrix;
import android.opengl.GLES20;

import com.endlessgames.endlesstale.MathContent.Matrix3x3f;
import com.endlessgames.endlesstale.MathContent.Vector3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Julian on 03.03.2017.
 */

public class Triangle {

    private FloatBuffer vertexBuffer;
    private final int mProgram;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float triangleCoords[] = new float[9];
    //There is a Matrix class: https://developer.android.com/reference/android/graphics/Matrix.html
    //Someone (RK) should understand this...
    private Matrix m2trianglecoords = new Matrix();


    private Vector3f position = Vector3f.ZEROVECTOR;
    private Matrix3x3f vertexCoords = new Matrix3x3f(new Vector3f[]{new Vector3f(0, 0.5f, 0), new Vector3f(-0.5f, 0, 0), new Vector3f(0.5f, 0, 0)});//Model
    private Matrix3x3f mtriangleCoords = new Matrix3x3f(new Vector3f[]{new Vector3f(0, 0.5f, 0), new Vector3f(-0.5f, 0, 0), new Vector3f(0.5f, 0, 0)});//real space

    private float scale = 1;

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public Triangle() {

        triangleCoords = mtriangleCoords.get();
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        int vertexShader = GL_Renderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = GL_Renderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);

    }

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw() {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    private void put(){
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    }

    public void translate(Vector3f translation){
        position.add(translation);
        mtriangleCoords.set(new Vector3f[]{vertexCoords.get(0), vertexCoords.get(1), vertexCoords.get(2) });
        mtriangleCoords.add(new Matrix3x3f(new Vector3f[]{translation, translation, translation}));
        triangleCoords = mtriangleCoords.get();
        put();
    }
}
