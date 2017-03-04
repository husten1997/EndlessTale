package com.endlessgames.endlesstale.rendering;

import com.endlessgames.endlesstale.MathContent.Vector3f;

import java.util.ArrayList;

/**
 * Created by richard on 04.03.17.
 */

public class RenderableObject {

    protected Vector3f position;
    protected float scale;
    protected ArrayList<Vector3f> mesh;

    public RenderableObject(Vector3f position, float scale, ArrayList<Vector3f> mesh){
        this.position = position;
        this.scale = scale;
        this.mesh = mesh;
    }

    public float[] getVerticies(){
        System.out.println(position.get(0));
        System.out.println(position.get(1));
        ArrayList<Vector3f> translatedMesh = new ArrayList<>();
        for(Vector3f vertex: mesh){
            translatedMesh.add(vertex.clone().multiply(scale).add(position));
        }
        float[] verticies = new float[translatedMesh.size()*3];
        for(int j = 0; j<translatedMesh.size(); j++) {
            for (int x = 0; x < 3; x++) {
                verticies[j*3+x] = translatedMesh.get(j).get(x);
            }
        }
        return verticies;
    }

    public void translate(Vector3f translation){
        System.out.println("Translation");
        position.add(translation);
    }
}