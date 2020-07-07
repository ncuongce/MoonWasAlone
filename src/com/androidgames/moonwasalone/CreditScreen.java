package com.androidgames.moonwasalone;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.androidgames.framework.Game;
import com.androidgames.framework.Input.TouchEvent;
import com.androidgames.framework.gl.Camera2D;
import com.androidgames.framework.gl.SpriteBatcher;
import com.androidgames.framework.impl.GLScreen;
import com.androidgames.framework.math.Vector2;

public class CreditScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;

    public CreditScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 640, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
        touchPoint = new Vector2();               
    }       

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
            if(event.type == TouchEvent.TOUCH_UP) {   
            	game.setScreen(new MainMenuScreen(game));
                return;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
    	
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        batcher.beginBatch(Assets.creditbg);
        batcher.drawSprite(320, 240, 640, 480, Assets.creditbgRegion);     
        batcher.endBatch();
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDisable(GL10.GL_BLEND);
        
    }

    @Override
    public void pause() {        
        
    }

    @Override
    public void resume() {        
    }       

    @Override
    public void dispose() {        
    }
}
