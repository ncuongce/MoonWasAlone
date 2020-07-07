package com.androidgames.moonwasalone;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.androidgames.framework.Game;
import com.androidgames.framework.Input.TouchEvent;
import com.androidgames.framework.gl.Animation;
import com.androidgames.framework.gl.Camera2D;
import com.androidgames.framework.gl.SpriteBatcher;
import com.androidgames.framework.gl.TextureRegion;
import com.androidgames.framework.impl.GLScreen;
import com.androidgames.framework.math.OverlapTester;
import com.androidgames.framework.math.Rectangle;
import com.androidgames.framework.math.Vector2;

public class MainMenuScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle playBounds;
    Rectangle creditBounds;
    Vector2 touchPoint;

    public MainMenuScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 640, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
        playBounds = new Rectangle(272, 88, 96, 32);
        creditBounds = new Rectangle(272, 40, 96, 32);
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
                if(OverlapTester.pointInRectangle(playBounds, touchPoint)) {
                    game.setScreen(new GameScreen(game));
                    return;
                }  
                if(OverlapTester.pointInRectangle(creditBounds, touchPoint)) {
                    game.setScreen(new CreditScreen(game));
                    return;
                }  
            }
        }
    }

    @Override
    public void present(float deltaTime) {
    	
        GL10 gl = glGraphics.getGL();        
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(320, 240, 640, 480, Assets.backgroundRegion);     
        batcher.endBatch();
        
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.items);     
    	batcher.drawSprite(320, 240, 160, 160, Assets.logo);
    	batcher.drawSprite(320, 392, 320, 64, Assets.title);
    	batcher.drawSprite(320, 104, 96, 32, Assets.play);
    	batcher.drawSprite(320, 56, 96, 32, Assets.credits);
        batcher.endBatch();
        
        gl.glDisable(GL10.GL_BLEND);
        
    }

    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {        
    }       

    @Override
    public void dispose() {        
    }
}
