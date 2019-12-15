package com.hongbao.bloons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public class MusicPlayer {
	
	private Music backgroundMusic;
	
	public MusicPlayer() {
		backgroundMusic = null;
	}
	
	public void playTitleMusic() {
		stopMusic();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/title.mp3"));
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	public void playStageMusic() {
		stopMusic();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/demystify_feast.mp3"));
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	public void playFinalBossMusic() {
		stopMusic();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/night_falls.mp3"));
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}
	
	public void pause() {
		if (backgroundMusic != null) {
			backgroundMusic.pause();
		}
	}
	
	public void resume() {
		if (backgroundMusic != null) {
			backgroundMusic.play();
		}
	}
	
	public void stopMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.stop();
		}
	}

}
