package com.endlessgames.endlesstale.MathContent;

/**
 * Created by Julian on 03.03.2017.
 */

public class Matrix3x3f {

    private Vector3f[] values;

    public Matrix3x3f(Vector3f[] values){
        if(values.length == 3){
            this.values = values;
        }
    }

    public Matrix3x3f(){

    }

    public Vector3f get(int index){
        if(index+1 <= values.length){
            return values[index];
        } else return null;
    }

    public float[] get(){
        return new float[]{ values[0].get()[0], values[0].get()[1], values[0].get()[2],
                values[1].get()[0], values[1].get()[1], values[1].get()[2],
                values[2].get()[0], values[2].get()[1], values[2].get()[2]};
    }

    public void set(Vector3f[] values){
        if(values.length == 3){
            this.values = values;
        }
    }

    public void add(Matrix3x3f matrix){
        for(int i = 0; i < 3; i++){
            values[i].add(matrix.get(i));
        }
    }

    public static Matrix3x3f IDENTITYMATRIX = new Matrix3x3f(new Vector3f[]{new Vector3f(1, 0, 0), new Vector3f(0, 1, 0), new Vector3f(0, 0, 1)});
    public static Matrix3x3f ZEROMATRIX = new Matrix3x3f(new Vector3f[]{Vector3f.ZEROVECTOR, Vector3f.ZEROVECTOR, Vector3f.ZEROVECTOR});

}
