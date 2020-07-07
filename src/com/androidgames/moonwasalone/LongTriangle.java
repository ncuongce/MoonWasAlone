package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class LongTriangle extends GameObject{
    public static float LONGTRIANGLE_WIDTH = 1.0f;
    public static float LONGTRIANGLE_HEIGHT = 2.0f;
    
    public static final int LONGTRIANGLE_UP = 1;
    public static final int LONGTRIANGLE_DOWN = 2;
    public static final int LONGTRIANGLE_LEFT = 3;
    public static final int LONGTRIANGLE_RIGHT = 4;
    
    public int type;
    
    public LongTriangle(float x, float y, int type) {
        super(x, y, LONGTRIANGLE_WIDTH, LONGTRIANGLE_HEIGHT);
        this.type = type;
    }
}