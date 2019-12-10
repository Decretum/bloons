package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class MusicPlayer {
	
	private Music backgroundMusic;
	
	public MusicPlayer() {
		backgroundMusic = null;
	}
	
	public void playTitleMusic() {
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}
	
	public void pause() {
		backgroundMusic.pause();
	}
	
	public void resume() {
		backgroundMusic.play();
	}
	
	public void stopMusic() {
		backgroundMusic.stop();
	}

}
