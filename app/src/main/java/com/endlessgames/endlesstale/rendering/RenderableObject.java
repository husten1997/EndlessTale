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
        ArrayList<Vector3f> translatedMesh = new ArrayList<>();
        for(Vector3f vertex: mesh){
            translatedMesh.add(vertex.clone().multiply(scale).add(position));
        }
        float[] verticies = new float[mesh.size()*3];
        for(int j = 0; j<mesh.size(); j++) {
            for (int x = 0; x < 3; x++) {
                verticies[j*3+x] = mesh.get(j).get()[x];
            }
        }
        return verticies;
    }

    public void translate(Vector3f translation){
        position.add(translation);
    }
}
