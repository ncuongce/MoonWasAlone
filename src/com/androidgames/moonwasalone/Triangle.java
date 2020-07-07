package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class Triangle extends GameObject{
    public static float TRIANGLE_WIDTH = 1.0f;
    public static float TRIANGLE_HEIGHT = 1.0f;
    
    public static final int TRIANGLE_UP = 1;
    public static final int TRIANGLE_DOWN = 2;
    public static final int TRIANGLE_LEFT = 3;
    public static final int TRIANGLE_RIGHT = 4;
    
    public int type;
    
    public Triangle(float x, float y, int type) {
        super(x, y, TRIANGLE_WIDTH, TRIANGLE_HEIGHT);
        this.type = type;
    }
}