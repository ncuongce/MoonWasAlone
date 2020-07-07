package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class Photon extends GameObject{
    public static float PHOTON_WIDTH = 0.5f;
    public static float PHOTON_HEIGHT = 0.5f;
    
    float stateTime;
    
    public Photon(float x, float y) {
        super(x, y, PHOTON_WIDTH, PHOTON_HEIGHT);
        stateTime = 0;
    }
    
    public void update(float deltaTime) {
    	stateTime += deltaTime;
    }
}
