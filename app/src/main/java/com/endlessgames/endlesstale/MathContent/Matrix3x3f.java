package com.endlessgames.endlesstale.MathContent;

/**
 * Created by Julian on 03.03.2017.
 */

public class Matrix3x3f {

    private Vector3f[] values;

    //Basic constructor
    public Matrix3x3f(Vector3f[] values){
        if(values.length == 3){
            this.values = values;
        }
    }

    //Work in progress
    public Matrix3x3f(){

    }

    //Returns column-vector at [index]
    public Vector3f get(int index){
        if(index+1 <= values.length){
            return values[index];
        } else return null;
    }

    //Returns a Float-Array of all Values
    public float[] get(){
        return new float[]{ values[0].get()[0], values[0].get()[1], values[0].get()[2],
                values[1].get()[0], values[1].get()[1], values[1].get()[2],
                values[2].get()[0], values[2].get()[1], values[2].get()[2]};
    }

    //Set values of the matrix via Vector-Array
    public void set(Vector3f[] values){
        if(values.length == 3){
            this.values = values;
        }
    }

    //Adds two Matrices (3x3)
    public void add(Matrix3x3f matrix){
        for(int i = 0; i < 3; i++){
            values[i].add(matrix.get(i));
        }
    }

    //Some standard matrices
    public static Matrix3x3f IDENTITYMATRIX = new Matrix3x3f(new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(0, 1, 0), new Vector3f(0, 0, 1)});
    public static Matrix3x3f ZEROMATRIX = new Matrix3x3f(new Vector3f[]{Vector3f.ZEROVECTOR, Vector3f.ZEROVECTOR, Vector3f.ZEROVECTOR});

}
