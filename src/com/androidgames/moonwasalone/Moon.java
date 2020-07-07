package com.androidgames.moonwasalone;

import com.androidgames.framework.DynamicGameObject;

public class Moon extends DynamicGameObject{
	public static final int MOON_STATE_JUMP = 0;
    public static final int MOON_STATE_FALL = 1;
    public static final int MOON_STATE_STAND = 2;
    public static final int MOON_STATE_HIT = 3;
    public static final int MOON_STATE_SIDE_HIT = 4;
    public static final int MOON_STATE_ORBIT = 5;
    public static final int MOON_STATE_DASH = 6;
    public static final float MOON_JUMP_VELOCITY = 32;
    public static final float MOON_BOUND_VELOCITY = 32;
    public static final float MOON_MOVE_VELOCITY = 20;
    public static final float MOON_DASH_VELOCITY = 60;
    public static final float MOON_WIDTH = 1.0f;
    public static final float MOON_HEIGHT = 1.0f;

    float world_width;
    float world_height;
    
    int state;
    float stateTime;    
    
    boolean photonIsEaten;

    public Moon(float x, float y) {
        super(x, y, MOON_WIDTH, MOON_HEIGHT);
        world_width = World.WORLD_WIDTH;
        world_height = World.WORLD_HEIGHT;
        state = MOON_STATE_STAND;
        stateTime = 0;        
        photonIsEaten = false;
    }

    public void update(float deltaTime) {     

    	velocity.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
    	if (velocity.y < -MOON_JUMP_VELOCITY/2)
    		velocity.y = -MOON_JUMP_VELOCITY/2;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
        
        if(velocity.y > 0 && state != MOON_STATE_HIT) {
            if(state != MOON_STATE_JUMP) {
                state = MOON_STATE_JUMP;
                stateTime = 0;
            }
        }
        
        if(velocity.y < 0 && state != MOON_STATE_HIT) {
            if(state != MOON_STATE_FALL) {
                state = MOON_STATE_FALL;
                stateTime = 0;
            }
        }
        
        if(velocity.y == 0 && state != MOON_STATE_HIT) {
            if(state != MOON_STATE_STAND) {
                state = MOON_STATE_STAND;
                stateTime = 0;
            }
        }
        
        if(position.y < 0)
            position.y = world_height;
        if(position.y > world_height)
            position.y = 0;
        
        if(position.x < 0)
        	position.x = MOON_WIDTH / 2;
        if (position.x > world_width)
        	position.x = world_width - MOON_WIDTH / 2;
        
        stateTime += deltaTime;
    }

    public void setWorldLevel(float width, float height) {
    	world_width = width;
    	world_height = height;
    }
    
    public void jump()
    {
        velocity.y = MOON_JUMP_VELOCITY;
        state = MOON_STATE_JUMP;
        stateTime = 0;
    }
    
    public void dash()
    {
    	if (photonIsEaten)
    	{
    		velocity.set(MOON_DASH_VELOCITY, 0);
    		state = MOON_STATE_DASH;
    		stateTime = 0;
    	}
    }
    
    public void boundUp()
    {
        velocity.y = MOON_BOUND_VELOCITY;
        state = MOON_STATE_JUMP;
        stateTime = 0;
    }
    
    public void boundDown()
    {
        velocity.y = -MOON_BOUND_VELOCITY;
        state = MOON_STATE_FALL;
        stateTime = 0;
    }
    
    public void stand()
    {
        velocity.y = 0;
        state = MOON_STATE_STAND;
        stateTime = 0;
    }
    
    public void hit()
    {
    	velocity.set(0, 0);
    	state = MOON_STATE_HIT;
    	stateTime = 0;
    }
    
    public void orbit()
    {
    	velocity.set(0, 0);
    	state = MOON_STATE_ORBIT;
    	stateTime = 0;
    }
    
    public void eat()
    {
    	photonIsEaten = true;
    }
}
