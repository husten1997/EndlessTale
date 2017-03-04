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

    public void multiply(float scalar){
        for (float v :values) {
            v *= scalar;
        }
    }

    public void add(Vector3f vector) throws ArrayIndexOutOfBoundsException{
        for (int i = 0; i < values.length; i++) {
            values[i] += vector.get()[i];
        }


    }

    public float[] get(){
        return values;
    }

    public float get(int index){
        if(index+1 <= values.length){
            return values[index];
        } else return (float)-Math.E;

    }

    public static Vector3f ZEROVECTOR = new Vector3f(0, 0, 0);

}
