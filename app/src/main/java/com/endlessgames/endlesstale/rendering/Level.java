package com.endlessgames.endlesstale.rendering;

import com.endlessgames.endlesstale.MathContent.Vector3f;
import com.endlessgames.endlesstale.R;

import java.util.ArrayList;

/**
 * Created by richard on 04.03.17.
 */

public class Level {

    private ArrayList<FlatColoredObject> flatColoredObjects = new ArrayList<>();
    private ArrayList<TexturedObject> texturedObjects = new ArrayList<>();
    private FlatColoredObject triangle;

    public Level(){

        ArrayList<Vector3f> mesh = new ArrayList<>();
        mesh.add(new Vector3f(0, 0.5f, 0));
        mesh.add(new Vector3f(-0.5f, 0, 0));
        mesh.add(new Vector3f(0.5f, 0, 0));
        float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
        triangle = new FlatColoredObject(Vector3f.ZEROVECTOR, 1, mesh, color);
        flatColoredObjects.add(triangle);
    }

    public void translate(Vector3f translation){
        triangle.translate(translation);
    }

    public ArrayList<FlatColoredObject> getFlatColoredObjects() {
        return flatColoredObjects;
    }

    public ArrayList<TexturedObject> getTexturedObjects() {
        return texturedObjects;
    }
}
