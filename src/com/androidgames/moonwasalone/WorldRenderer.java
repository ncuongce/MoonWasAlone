package com.androidgames.moonwasalone;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.androidgames.framework.gl.Animation;
import com.androidgames.framework.gl.Camera2D;
import com.androidgames.framework.gl.SpriteBatcher;
import com.androidgames.framework.gl.TextureRegion;
import com.androidgames.framework.impl.GLGraphics;

public class WorldRenderer {
    static final float FRUSTUM_WIDTH = 20;
    static final float FRUSTUM_HEIGHT = 15;    
    GLGraphics glGraphics;
    World world;
    Camera2D cam;
    SpriteBatcher batcher;    
    
    public final Random rand;
    public final float randPos;
    
    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.batcher = batcher;
        
        rand = new Random();
        this.randPos = rand.nextFloat();
    }
    
    public void render() {
    	//int layer;
    	//layer = (int) (world.moon.position.x / FRUSTUM_WIDTH);
        //cam.position.x = layer * FRUSTUM_WIDTH + FRUSTUM_WIDTH / 2;
    	
    	cam.position.x = world.moon.position.x;
    	if (cam.position.x <= FRUSTUM_WIDTH / 2) {
    		cam.position.x = FRUSTUM_WIDTH / 2;
    	}
    	else if (cam.position.x >= world.moon.world_width - FRUSTUM_WIDTH / 2) {
    		cam.position.x = world.moon.world_width - FRUSTUM_WIDTH / 2;
    	}
    	
        cam.setViewportAndMatrices();
        renderBackground();
        renderObjects();        
    }

    public void renderBackground() {
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(cam.position.x, cam.position.y,
                           FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 
                           Assets.backgroundRegion);
        batcher.endBatch();
    }

    public void renderObjects() {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        
        batcher.beginBatch(Assets.items);
        renderMoon();
        renderSquares();
        renderEmptySquares();
        renderBoundSquares();
        renderTriangles();
        renderLongTriangles();
        renderBlackhole();
        renderPhotons();
        renderOrbit();
        
        renderDialogs();
        
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    private void renderMoon() {
        TextureRegion keyFrame;
                               
        if (world.moon.state == Moon.MOON_STATE_HIT)
        {
        	keyFrame = Assets.moon1;
        	batcher.drawSprite(world.moon.position.x, world.moon.position.y, 1, 1, keyFrame);
        	keyFrame = Assets.moon2;
        	batcher.drawSprite(world.moon.position.x - Moon.MOON_WIDTH * randPos, world.moon.position.y + Moon.MOON_HEIGHT * randPos, 1, 1, keyFrame);
        	keyFrame = Assets.moon3;
        	batcher.drawSprite(world.moon.position.x + Moon.MOON_WIDTH * 1.5f * randPos, world.moon.position.y - Moon.MOON_HEIGHT * 1.5f * randPos, 1, 1, keyFrame);
        	keyFrame = Assets.moon4;
        	batcher.drawSprite(world.moon.position.x + Moon.MOON_WIDTH * 2.0f * randPos, world.moon.position.y + Moon.MOON_HEIGHT * 2.0f * randPos, 1, 1, keyFrame);
        	keyFrame = Assets.moon4;
        	batcher.drawSprite(world.moon.position.x + Moon.MOON_WIDTH * 2.5f * randPos, world.moon.position.y - Moon.MOON_HEIGHT * 2.5f * randPos, 1, 1, keyFrame);
        	keyFrame = Assets.moon4;
        	batcher.drawSprite(world.moon.position.x - Moon.MOON_WIDTH * 3.0f * randPos, world.moon.position.y - Moon.MOON_HEIGHT * 3.0f * randPos, 1, 1, keyFrame);
        }
        else
        {
        	keyFrame = Assets.moon;
        	batcher.drawSprite(world.moon.position.x, world.moon.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderDialogs() {
        int len = world.dialogs.size();
        for (int i = 0; i < len; i++) {
        	Dialog dialog = world.dialogs.get(i);
        	Assets.font.drawText(batcher, dialog.getDialog(), dialog.getX(), dialog.getY(), 48);
        }
    }
    
    private void renderBlackhole() {
        TextureRegion keyFrame;
        int len = world.blackholes.size();
        for (int i = 0; i < len; i++) {
            Blackhole blackhole = world.blackholes.get(i);
            keyFrame = Assets.blackhole.getKeyFrame(blackhole.stateTime, Animation.ANIMATION_LOOPING);                       
            
            batcher.drawSprite(blackhole.position.x, blackhole.position.y, 1, 1, keyFrame);   
        }

    }
    
    private void renderOrbit() {
        TextureRegion keyFrame;
        int len = world.orbits.size();
        for (int i = 0; i < len; i++) {
        	Orbit orbit = world.orbits.get(i);
        	keyFrame = Assets.orbit.getKeyFrame(orbit.stateTime, Animation.ANIMATION_LOOPING);                       
     
        	batcher.drawSprite(orbit.position.x, orbit.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderPhotons() {
        TextureRegion keyFrame;
        int len = world.photons.size();
        for (int i = 0; i < len; i++) {
        	Photon photon = world.photons.get(i);
        	keyFrame = Assets.photon.getKeyFrame(photon.stateTime, Animation.ANIMATION_LOOPING);                       
     
        	batcher.drawSprite(photon.position.x, photon.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderSquares() {
        TextureRegion keyFrame;
        keyFrame = Assets.square;                       
        int len = world.squares.size();
        for(int i = 0; i < len; i++) {
            Square square = world.squares.get(i);
            batcher.drawSprite(square.position.x, square.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderEmptySquares() {
        TextureRegion keyFrame;
                               
        int len = world.emptysquares.size();
        for(int i = 0; i < len; i++) {
            EmptySquare emptysquare = world.emptysquares.get(i);
            if (emptysquare.state == EmptySquare.EMPTYSQUARE_BLOCK_STATE)
            {
            	keyFrame = Assets.emptysquare1;
            	batcher.drawSprite(emptysquare.position.x, emptysquare.position.y, 1, 1, keyFrame);
            }
            else if (emptysquare.state == EmptySquare.EMPTYSQUARE_SWITCH_STATE)
            {
            	keyFrame = Assets.emptysquare2;
            	batcher.drawSprite(emptysquare.position.x, emptysquare.position.y, 1, 1, keyFrame);
            }
            else if (emptysquare.state == EmptySquare.EMPTYSQUARE_EMPTY_STATE)
            {
            	keyFrame = Assets.emptysquare3;
            	batcher.drawSprite(emptysquare.position.x, emptysquare.position.y, 1, 1, keyFrame);
            }
        }
    }
    
    private void renderBoundSquares() {
        TextureRegion keyFrame;
        keyFrame = Assets.boundsquare;                       
        int len = world.boundsquares.size();
        for(int i = 0; i < len; i++) {
            BoundSquare boundsquare = world.boundsquares.get(i);
            batcher.drawSprite(boundsquare.position.x, boundsquare.position.y, 1, 1, keyFrame);
        }
    }
    
    private void renderTriangles() {
        TextureRegion keyFrame;
        keyFrame = Assets.triangle;                       
        int len = world.triangles.size();
        for(int i = 0; i < len; i++) {
            Triangle triangle = world.triangles.get(i);
            if (triangle.type == Triangle.TRIANGLE_UP)
            {
            	batcher.drawSprite(triangle.position.x, triangle.position.y, 1, 1, keyFrame);
            }
            else if (triangle.type == Triangle.TRIANGLE_DOWN)
            {
            	batcher.drawSprite(triangle.position.x, triangle.position.y, 1, 1, 180, keyFrame);
            }
            else if (triangle.type == Triangle.TRIANGLE_LEFT)
            {
            	batcher.drawSprite(triangle.position.x, triangle.position.y, 1, 1, 90, keyFrame);
            }
            else if (triangle.type == Triangle.TRIANGLE_RIGHT)
            {
            	batcher.drawSprite(triangle.position.x, triangle.position.y, 1, 1, -90, keyFrame);
            }
        }
    }
    
    private void renderLongTriangles() {
        TextureRegion keyFrame;
        keyFrame = Assets.longtriangle;                       
        int len = world.longtriangles.size();
        for(int i = 0; i < len; i++) {
            LongTriangle longtriangle = world.longtriangles.get(i);
            if (longtriangle.type == LongTriangle.LONGTRIANGLE_UP)
            {
            	batcher.drawSprite(longtriangle.position.x, longtriangle.position.y, 1, 2, keyFrame);
            }
            else if (longtriangle.type == LongTriangle.LONGTRIANGLE_DOWN)
            {
            	batcher.drawSprite(longtriangle.position.x, longtriangle.position.y, 1, 2, 180, keyFrame);
            }
            else if (longtriangle.type == LongTriangle.LONGTRIANGLE_LEFT)
            {
            	batcher.drawSprite(longtriangle.position.x, longtriangle.position.y, 1, 2, 90, keyFrame);
            }
            else if (longtriangle.type == LongTriangle.LONGTRIANGLE_RIGHT)
            {
            	batcher.drawSprite(longtriangle.position.x, longtriangle.position.y, 1, 2, -90, keyFrame);
            }
        }
    }
}

