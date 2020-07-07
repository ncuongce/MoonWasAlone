package com.androidgames.moonwasalone;

import java.util.ArrayList;

public class Dialog {
	float x;
	float y;
	ArrayList<String> phrases;
	
	float duration;
    float stateTime;
	
	public Dialog(float duration, float x, float y, ArrayList<String> phrases) {
		this.duration = duration;
		this.stateTime = 0;
		this.x = x;
		this.y = y;
		this.phrases = phrases;
	}
	
	public String getDialog() {
		
		int phraseIndex = (int) (stateTime / duration);
		
		if (phraseIndex >= phrases.size())
			phraseIndex = phrases.size() - 1;
		
		String s = phrases.get(phraseIndex);

		return s;
	}
	
    public void update(float deltaTime) {   
    	stateTime += deltaTime;
    }
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
