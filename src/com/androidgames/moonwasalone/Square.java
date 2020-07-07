package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class Square extends GameObject{
    public static float SQUARE_WIDTH = 1.0f;
    public static float SQUARE_HEIGHT = 1.0f;
    
    boolean internalEdge;
    
    public Square(float x, float y) {
        super(x, y, SQUARE_WIDTH, SQUARE_HEIGHT);
        internalEdge = false;
    }
}
