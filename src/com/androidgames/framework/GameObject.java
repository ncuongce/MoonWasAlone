package com.androidgames.framework;

import com.androidgames.framework.math.Rectangle;
import com.androidgames.framework.math.Circle;
import com.androidgames.framework.math.Vector2;

public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;
    public final Circle circlebounds;
    
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x,y);
        this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
        this.circlebounds = new Circle(x, y, (width + height)/4);
    }
}
