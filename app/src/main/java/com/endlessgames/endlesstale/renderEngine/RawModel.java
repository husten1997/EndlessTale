package com.endlessgames.endlesstale.renderEngine;

import com.endlessgames.endlesstale.MainActivity;

/**
 * Created by Richard on 20.03.2017.
 */

public class RawModel {

    private int vaoID, vertexCount;

    public RawModel(int vaoID, int vertexCount){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
