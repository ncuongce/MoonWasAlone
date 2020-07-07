package com.androidgames.moonwasalone;

import com.androidgames.framework.DynamicGameObject;

public class Orbit extends DynamicGameObject{
    public static float ORBIT_WIDTH = 1.0f;
    public static float ORBIT_HEIGHT = 1.0f;
    
    float stateTime;
    
    public Orbit(float x, float y) {
        super(x, y, ORBIT_WIDTH, ORBIT_HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {   
    	stateTime += deltaTime;
    }
}
