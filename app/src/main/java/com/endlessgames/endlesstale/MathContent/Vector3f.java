package com.endlessgames.endlesstale.MathContent;

/**
 * Created by Julian on 03.03.2017.
 */

public class Vector3f {

    private float[] values;


    public Vector3f(float[] values) {
        if(values.length == 3){
            this.values = values;
        }

    }

    public Vector3f clone(){
        float[] v =  new float[3];
        for(int i = 0; i<3; i++){
            v[i] = values[i];
        }
        return new Vector3f(v);
    }

    public Vector3f(float v1, float v2, float v3){
        this.values = new float[]{v1, v2, v3};
    }

    public Vector3f(){
        this.values = new float[3];
        for(float v : values){
            v = 0;
        }
    }

    public Vector3f normalise() {
        //work in progress
        float sum = 0.0f;
        for (float v: values){
            sum += Math.pow(v, 2);
        }
        multiply((float)Math.pow(sum, -0.5));
        return this;
    }

    public void put(float[] values){
        if(values.length == 3){
            this.values = values;
        }

    }



    public void set(int index, float value){
        if(index+1 <= values.length){
            this.values[index] = value;
        }

    }

    public Vector3f multiply(float scalar){
        for (int i = 0; i < values.length; i++) {
            values[i] *= scalar;
        }
        return this;
    }

    public Vector3f add(Vector3f vector) throws ArrayIndexOutOfBoundsException{
        for (int i = 0; i < values.length; i++) {
            values[i] += vector.get()[i];
        }
        return this;
    }

    public float[] get(){
        return values;
    }

    public float get(int index){
        return values[index];

    }

    public static Vector3f ZEROVECTOR = new Vector3f(0, 0, 0);

}
