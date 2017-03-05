package com.endlessgames.endlesstale.rendering;

import com.endlessgames.endlesstale.MathContent.Vector3f;

import java.util.ArrayList;

/**
 * Created by richard on 04.03.17.
 */

public class TexturedObject extends RenderableObject{

    private int texture;

    public TexturedObject(Vector3f position, float scale, ArrayList<Vector3f> mesh, int texture){
        super(position, scale, mesh);
        this.texture = texture;
    }

    public float getScale() {
        return scale;
    }

    public Vector3f getPos(){
        return position;
    }

    public int getTexture(){
        return texture;
    }
}
