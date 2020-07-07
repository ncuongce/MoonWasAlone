package com.androidgames.moonwasalone;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.androidgames.framework.Game;
import com.androidgames.framework.Input.TouchEvent;
import com.androidgames.framework.gl.Camera2D;
import com.androidgames.framework.gl.FPSCounter;
import com.androidgames.framework.gl.SpriteBatcher;
import com.androidgames.framework.impl.GLScreen;
import com.androidgames.framework.math.OverlapTester;
import com.androidgames.framework.math.Rectangle;
import com.androidgames.framework.math.Vector2;
import com.androidgames.moonwasalone.World.WorldListener;

public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;    
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;
  
    int state;
    
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;    
    World world;
    WorldListener worldListener;
    WorldRenderer renderer;    

    int currentLevel;
    String levelString;    

    public GameScreen(Game game) {
        super(game);
        state = GAME_READY;
        guiCam = new Camera2D(glGraphics, 640, 480);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        worldListener = new WorldListener() {
            public void jump() {            
            	Assets.playSound(Assets.jumpSound);
            }

            public void hit() {
            	Assets.playSound(Assets.hitSound);
            }    
            
            public void bound() {
            	Assets.playSound(Assets.boundSound);
            }    
            
            public void playNoteA() {
            	Assets.playSound(Assets.noteA);
            } 
            
            public void playNoteB() {
            	Assets.playSound(Assets.noteB);
            } 
            
            public void playNoteC() {
            	Assets.playSound(Assets.noteC);
            } 
            
            public void playNoteD() {
            	Assets.playSound(Assets.noteD);
            } 
            
            public void playNoteE() {
            	Assets.playSound(Assets.noteE);
            } 
            
            public void playNoteF() {
            	Assets.playSound(Assets.noteF);
            } 
            
            public void playNoteG() {
            	Assets.playSound(Assets.noteG);
            } 
        };
        
        currentLevel = 1;
        levelString = "Orbit " + currentLevel;
        
        world = new World(worldListener, currentLevel);
        renderer = new WorldRenderer(glGraphics, batcher, world);
    }

    @Override
    public void update(float deltaTime) {
        if(deltaTime > 0.1f)
            deltaTime = 0.1f;
        
        switch(state) {
        case GAME_READY:
            updateReady();
            break;
        case GAME_RUNNING:
            updateRunning(deltaTime);
            break;
        case GAME_PAUSED:
            updatePaused();
            break;
        case GAME_LEVEL_END:
            updateLevelEnd();
            break;
        case GAME_OVER:
            updateGameOver();
            break;
        }
    }

    private void updateReady() {
        if((game.getInput().getAccelX() != 0) || (game.getInput().getAccelY() != 0)){
            state = GAME_RUNNING;
        }
    }

    private void updateRunning(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;
            
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
                  
        }
        
        world.update(deltaTime, touchEvents, game.getInput().getAccelY());
        if(world.level != currentLevel) {
        	currentLevel = world.level;
        	levelString = "Orbit " + currentLevel;
        }
        if(world.state == World.WORLD_STATE_NEXT_LEVEL) {
            state = GAME_LEVEL_END;        
        }
        if(world.state == World.WORLD_STATE_GAME_OVER) {
            state = GAME_OVER;
        }
    }

    private void updatePaused() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);
            if(event.type != TouchEvent.TOUCH_UP) { 	
                state = GAME_RUNNING;
                return;
            }
        }
    }

    private void updateLevelEnd() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {                   
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;
            world = new World(worldListener, currentLevel);
            renderer = new WorldRenderer(glGraphics, batcher, world);
            state = GAME_READY;
        }
    }

    private void updateGameOver() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {                   
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;
            world = new World(worldListener, currentLevel);
            renderer = new WorldRenderer(glGraphics, batcher, world);
            state = GAME_READY;
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        renderer.render();
        
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        switch(state) {
        case GAME_READY:
            presentReady();
            break;
        case GAME_RUNNING:
            presentRunning();
            break;
        case GAME_PAUSED:
            presentPaused();
            break;
        case GAME_LEVEL_END:
            presentLevelEnd();
            break;
        case GAME_OVER:
            presentGameOver();
            break;
        }
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    private void presentReady() {
    	Assets.font.drawText(batcher, levelString, 640 - 128, 480 - 10);
    }

    private void presentRunning() {
        Assets.font.drawText(batcher, levelString, 640 - 128, 480 - 10);
    }

    private void presentPaused() {        
    	Assets.font.drawText(batcher, levelString, 640 - 128, 480 - 10);

    }

    private void presentLevelEnd() {
    	Assets.font.drawText(batcher, "Moon passed Orbit " + (currentLevel - 1) + "!", 320 - 160, 240);
    }

    private void presentGameOver() {
    	Assets.font.drawText(batcher, "Oops, try again!", 320 - 128, 240);
    }

    @Override
    public void pause() {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() {        
    }

    @Override
    public void dispose() {       
    }
}
