package com.hongbao.bloons;

import com.hongbao.bloons.entities.Bloon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BloonQueue {
	
	// Defines a sequence of bloons appearing on the map as well as when they should appear
	
	private List<Bloon> bloons;
	private List<Long> intervals;
	private int currentIndex;
	
	public BloonQueue(List<Bloon> bloons, List<Long> intervals) {
		this.bloons = bloons;
		this.intervals = intervals;
		currentIndex = 0;
	}
	
	public Set<Bloon> getBloons(long timeSinceGameStarted) {
		HashSet<Bloon> generatedBloons = new HashSet<>();
		while (currentIndex < bloons.size()) {
			if (intervals.get(currentIndex) < timeSinceGameStarted) {
				// todo george consider using the game render loop to count this instead of real time, so that if the game lags it doesn't become harder.
				generatedBloons.add(bloons.get(currentIndex));
				currentIndex++;
			} else {
				break;
			}
		}
		return generatedBloons;
	}
	
}
