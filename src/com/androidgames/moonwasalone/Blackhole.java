package com.androidgames.moonwasalone;

import com.androidgames.framework.DynamicGameObject;

public class Blackhole extends DynamicGameObject{
    public static float BLACKHOLE_WIDTH = 1.0f;
    public static float BLACKHOLE_HEIGHT = 1.0f;
    
    float stateTime;
    
    public Blackhole(float x, float y) {
        super(x, y, BLACKHOLE_WIDTH, BLACKHOLE_HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {   
    	stateTime += deltaTime;
    }
}
