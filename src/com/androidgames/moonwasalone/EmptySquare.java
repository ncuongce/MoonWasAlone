package com.androidgames.moonwasalone;

import com.androidgames.framework.GameObject;

public class EmptySquare extends GameObject{
    public static float EMPTYSQUARE_WIDTH = 1.0f;
    public static float EMPTYSQUARE_HEIGHT = 1.0f;
    
    public static int EMPTYSQUARE_BLOCK_STATE = 0;
    public static int EMPTYSQUARE_SWITCH_STATE = 1;
    public static int EMPTYSQUARE_EMPTY_STATE = 2;
    
    boolean internalEdge;
    float duration;
    float stateTime;
    int state;
    
    public EmptySquare(float duration, float x, float y) {
        super(x, y, EMPTYSQUARE_WIDTH, EMPTYSQUARE_HEIGHT);
        this.internalEdge = false;
        this.duration = duration;
        this.stateTime = 0;
        this.state = EMPTYSQUARE_BLOCK_STATE;
        
    }
    
    public void update(float deltaTime) {
    	stateTime += deltaTime;
    	
    	if (stateTime >= duration) {
    		if (state == EMPTYSQUARE_BLOCK_STATE)
    		{
    			state = EMPTYSQUARE_SWITCH_STATE;
    		}
    		else if (state == EMPTYSQUARE_SWITCH_STATE)
    		{
    			state = EMPTYSQUARE_EMPTY_STATE;
    		}
    		else
    		{
    			state = EMPTYSQUARE_BLOCK_STATE;
    		}

    		stateTime = 0;
    	}
    }
    
    public void setDuration(float duration) {
    	this.duration = duration;
    }
}
