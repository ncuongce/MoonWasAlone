package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class BoundSquare extends GameObject{
    public static float BOUNDSQUARE_WIDTH = 1.0f;
    public static float BOUNDSQUARE_HEIGHT = 1.0f;
    
    public BoundSquare(float x, float y) {
        super(x, y, BOUNDSQUARE_WIDTH, BOUNDSQUARE_HEIGHT);
    }
}
