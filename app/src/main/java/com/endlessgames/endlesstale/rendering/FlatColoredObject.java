package com.endlessgames.endlesstale.rendering;

import com.endlessgames.endlesstale.MathContent.Vector3f;

import java.util.ArrayList;

/**
 * Created by richard on 04.03.17.
 */

public class FlatColoredObject extends RenderableObject{

    private float[] color;

    public FlatColoredObject(Vector3f position, float scale, ArrayList<Vector3f> mesh, float[] color){
        super(position, scale, mesh);
        this.color = color;
    }

    public float[] getColor() {
        return color;
    }
}
