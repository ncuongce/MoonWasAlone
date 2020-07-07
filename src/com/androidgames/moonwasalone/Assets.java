package com.androidgames.moonwasalone;

import com.androidgames.framework.Music;
import com.androidgames.framework.Sound;
import com.androidgames.framework.gl.Animation;
import com.androidgames.framework.gl.Font;
import com.androidgames.framework.gl.Texture;
import com.androidgames.framework.gl.TextureRegion;
import com.androidgames.framework.impl.GLGame;
import com.androidgames.moonwasalone.Settings;

public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    
    public static Texture creditbg;
    public static TextureRegion creditbgRegion;
    
    public static Texture items;
    public static TextureRegion moon;
    public static TextureRegion moon1;
    public static TextureRegion moon2;
    public static TextureRegion moon3;
    public static TextureRegion moon4;
    
    public static TextureRegion square;
    public static TextureRegion emptysquare1;
    public static TextureRegion emptysquare2;
    public static TextureRegion emptysquare3;
    public static TextureRegion boundsquare;
    public static TextureRegion triangle;
    public static TextureRegion longtriangle;
    public static Animation blackhole;
    public static Animation orbit;
    public static Animation photon;
    
    public static TextureRegion logo;
    public static TextureRegion title;
    public static TextureRegion play;
    public static TextureRegion credits;
    
    public static Font font;
    
    public static Music music;
    public static Sound jumpSound;
    public static Sound hitSound;
    public static Sound boundSound;
    
    public static Sound noteA;
    public static Sound noteB;
    public static Sound noteC;
    public static Sound noteD;
    public static Sound noteE;
    public static Sound noteF;
    public static Sound noteG;
    
    public static void load(GLGame game) {
        background = new Texture(game, "background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 640, 480);
        creditbg = new Texture(game, "creditbg.png");
        creditbgRegion = new TextureRegion(creditbg, 0, 0, 640, 480);        
        items = new Texture(game, "items.png");
        
        play = new TextureRegion(items, 0, 224, 96, 32);
        credits = new TextureRegion(items, 96, 224, 96, 32);
        title = new TextureRegion(items, 0, 384, 320, 64);
        logo = new TextureRegion(items, 256, 0, 160, 160);
        
        moon = new TextureRegion(items, 0, 0, 32, 32);
        moon1 = new TextureRegion(items, 32, 0, 32, 32);
        moon2 = new TextureRegion(items, 64, 0, 32, 32);
        moon3 = new TextureRegion(items, 96, 0, 32, 32);
        moon4 = new TextureRegion(items, 128, 0, 32, 32);
        
        square = new TextureRegion(items, 0, 32, 32, 32);
        boundsquare = new TextureRegion(items, 32, 32, 32, 32);
        emptysquare1 = new TextureRegion(items, 64, 32, 32, 32);
        emptysquare2 = new TextureRegion(items, 96, 32, 32, 32);
        emptysquare3 = new TextureRegion(items, 128, 32, 32, 32);
        
        triangle = new TextureRegion(items, 0, 64, 32, 32);
        longtriangle = new TextureRegion(items, 0, 160, 32, 64);
        blackhole = new Animation(0.5f,
                new TextureRegion(items, 0, 96, 32, 32),
                new TextureRegion(items, 32, 96, 32, 32),
                new TextureRegion(items, 64, 96, 32, 32),
                new TextureRegion(items, 96, 96, 32, 32));
        
        photon = new Animation(0.5f,
                new TextureRegion(items, 128, 0, 32, 32),
                new TextureRegion(items, 96, 0, 32, 32),
                new TextureRegion(items, 64, 0, 32, 32));
        
        orbit = new Animation(0.5f,
                new TextureRegion(items, 0, 128, 32, 32),
                new TextureRegion(items, 32, 128, 32, 32),
                new TextureRegion(items, 64, 128, 32, 32),
                new TextureRegion(items, 96, 128, 32, 32),
        		new TextureRegion(items, 128, 128, 32, 32),
        		new TextureRegion(items, 160, 128, 32, 32),
        		new TextureRegion(items, 192, 128, 32, 32),
        		new TextureRegion(items, 224, 128, 32, 32));
        
        font = new Font(items, 0, 256, 16, 16, 20);
        
        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(1.0f);
        if(Settings.soundEnabled)
            music.play();
        
        jumpSound = game.getAudio().newSound("jump.ogg");
        hitSound = game.getAudio().newSound("hit.ogg");  
        boundSound = game.getAudio().newSound("bound.wav");
        
        noteA = game.getAudio().newSound("noteA.mp3");
        noteB = game.getAudio().newSound("noteB.mp3");
        noteC = game.getAudio().newSound("noteC.mp3");
        noteD = game.getAudio().newSound("noteD.mp3");
        noteE = game.getAudio().newSound("noteE.mp3");
        noteF = game.getAudio().newSound("noteF.mp3");
        noteG = game.getAudio().newSound("noteG.mp3");
    }       

    public static void reload() {
    	background.reload();
    	items.reload();
    	
        if(Settings.soundEnabled)
            music.play();
    }

    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
